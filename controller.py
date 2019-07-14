#-*- coding:utf-8 -*-
from flask import Flask, request, jsonify, json, g
import sqlite3
import datetime
import message_manager
import s3_manager
import logging
import db_manager

app = Flask(__name__)
message_manager = message_manager.MessageManager()
db_manager = db_manager.DatabseManager("10s.db")

def json_message(message):
    return jsonify({"message": message})


@app.route("/api/auth", methods=["GET", "POST"])
def auth():
    # join: addUser
    if request.method == 'POST':
        body = request.json
        email = body["email"]
        nickname = body['nickname']
        profile_image = body['profile_image']

        token = hash(email)
        db_manager.add_user(email, nickname, profile_image, token)

        auth_token = {'auth_token': token}
        return jsonify(auth_token), 200

    # login: getUser
    else: #request.method == 'GET':
        user_email = request.headers['email']
        auth_token = check_user(user_email)
        if auth_token == None:
            return jsonify(message="Not Exist User"), 400

        result_json = {'auth_token': auth_token[0]}
        return jsonify(result_json), 200
    

@app.route("/api/chatRoom", methods=["GET", "POST", "PUT", "DELETE"])
def chat_room():
    #try:
    # create a chat_room
    if request.method == 'POST':
        auth_token = request.headers["Authorization"].split()[1]
        user_id = db_manager.get_user_id(auth_token)

        body = request.json
        room_name = body['room_name']
        time_created = datetime.datetime.now()

        db_manager.create_chatroom(room_name, time_created, user_id)

        return json_message("chat_room created"), 200

    ## get chat_room that user is in
    elif request.method == 'GET':
        auth_token = request.headers["Authorization"].split()[1]
        user_id = db_manager.get_user_id(auth_token)
        
        room_infos = db_manager.get_room_infos(user_id)

        room_infos_json = list()
        
        for room_info in room_infos:
            room_infos_json.append({"room_id":room_info[0], "room_name":room_info[1]})
        
        return jsonify(room_infos_json), 200

    # delete chat_room
    elif request.method == 'DELETE':
        body = request.json
        room_id = body['room_id']
        auth_token = request.headers["Authorization"].split()[1]
        user_id = db_manager.get_user_id(auth_token)
        
        db_manager.out_chatroom(room_id, user_id)

        check_delete_room(room_id)

        return json_message("exit successfully"), 200


    # invite friend to chat_room
    elif request.method == 'PUT':
        body = request.json
        room_id = body['room_id']
        invited_id = body['invited_id']
        db_manager.update_chatuser(room_id, invited_id)

        return json_message("friend invited"), 200


@app.route("/api/friend", methods=["GET", "POST"])
def friend():
    auth_token = request.headers["Authorization"].split()[1]
    user_id = db_manager.get_user_id(auth_token)

    # search friend
    if request.method == 'GET':
        friend_infos = db_manager.get_friend_info(user_id)
        friends = []
        for friend_info in friend_infos:
            friend = {'id': friend_info[0], 'nickname': friend_info[1], 'profile_image': friend_info[2], 'status_message': friend_info[3]}
            friends.append(friend)

        dic = { 'friends' : friends }
        return json.dumps(dic, ensure_ascii=False), 200


@app.route("/api/profile/<user_id>", methods=["GET"])
def get_profile(user_id):
    user_profile = get_user_profile(user_id)
    return json.dumps(user_profile, ensure_ascii=False)


@app.route("/api/profile", methods=["GET", "PUT"])
def profile():
    auth_token = request.headers["Authorization"].split()[1]
    user_id = db_manager.get_user_id(auth_token)
    
    # get profile
    if request.method == 'GET':
        # 내 프로필 정보
        return get_profile(user_id)

    # update profile
    elif request.method == 'PUT':
        new_nickname = request.form['nickname']
        new_status = request.form['status_message']
        image_file = request.files['profile_image']
        new_image_path = s3_manager.upload_file(image_file, user_id, "10s-profile", image_file.filename)
        db_manager.update_user_profile(new_nickname, new_status, new_image_path, user_id)
        
        return json_message("user updated"), 200


#when message is received from client
@app.route("/api/chatRoom/<int:id>/message", methods = ['POST', 'GET'])
def receive(id):
    if request.method == 'POST':
        auth_token = request.headers["Authorization"].split()[1]
        user_id = get_user_id(auth_token)

        #upload file onto S3
        f = request.files['file']
        path = s3_manager.upload_file(f, id, '10s-voice', f.filename)

        order = message_manager.countMessage(id) + 1
        message = {"index" : order, 
                                "sender" : user_id,
                                "receiver" : id, 
                                "content" : path,
                                "date" : datetime.datetime.now()}
        message_manager.pushMessage(id, message)
        return json_message("success"), 200

    else:
        result = message_manager.getMessage(id)
        return jsonify({"messages": result}), 200


#bring messages with index
@app.route("/api/chatRoom/<id>/message/<int:start>", methods = ['GET'])
def bring(id, start):
        result = message_manager.getMessage(id, start)
        return jsonify({"messages": result}), 200
        