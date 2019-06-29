from flask import Flask, request, jsonify, json
import sqlite3
import datetime

app = Flask(__name__)

@app.route("/auth", methods=["GET", "POST"])
def auth():

    try:
        conn = sqlite3.connect("/root/10s.db")
        c = conn.cursor()
    
        #join: addUser
        if request.method == 'POST':
            body = request.json
            email = body['email']
            auth_token = {'auth_token' : hash(email)}
            nickname = body['nickname']
            profilepic = body['profilepic']
            c.execute("INSERT INTO User(ID, email, nickname, profilepic) VALUES (?,?,?,?)", [auth_token, email, nickname, profilepic])
            conn.commit()
            return jsonify(auth_token), 200


        #login: getUser
        elif request.method == 'GET':
            user_email = request.headers['email']
            auth_token = {'auth_token' : hash(email)}
            c.execute("SELECT * FROM User WHERE email = '{}'".format(user_email))
            user_info = c.fetchall()
            return jsonify(auth_token), 200
    
    except:
        print("log")
        conn.rollback()

    finally:
        conn.close()

    return "auth"


@app.route("/chatRoom", methods=["GET", "POST", "PUT", "DELETE"])
def chatRoom():
    try:
        conn = sqlite3.connect("/root/10s.db")
        c = conn.cursor()

        #create a chatroom
        if request.method == 'POST':
            body = request.json
            user_id = body['user_id']
            room_name = body['room_name']
            time_created = datetime.datetime.now()
            c.execute("INSERT INTO ChatRoom(RoomName, CreateDate) VALUES (?,?)", [room_name, time_created])
            conn.commit()

            c.execute("SELECT ID FROM ChatRoom WHERE RoomName = room_name")
            room_id = c.fetchone()
            
            c.execute("INSERT INTO ChatUser(RoomID, UserID) VALUES (?,?)", [room_id,user_id])
            conn.commit()

            return "chatroom created", 200

        #get chatroom that user is in
        elif request.method == 'GET':
            body = request.json
            user_id = body['user_id']
            c.execute("SELECT RoomID FROM ChatUser WHERE UserID = user_id")
            room_id = c.fetchall()

            return "chatroom fetched", 200


        #delete chatroom
        elif request.method == 'DELETE':
            body = request.json
            room_id = body['room_id']
            c.execute("DELETE FROM ChatRoom WHERE ID = ?", (room_id,))
            conn.commit()

            c.execute("DELETE FROM ChatUser WHERE RoomID = ?", (room_id,))
            conn.commit()

            return "chatroom deleted", 200


        #invite friend to chatroom
        elif request.method == 'PUT':
            body = request.json
            room_id = body['room_id']
            user_id = body['user_id']
            c.execute("INSERT INTO ChatUser(RoomID, UserID) VALUES(?,?)", [room_id, user_id])
            conn.commit()

            return "friend invited", 200




    except:
        print("log")
        conn.rollback()

    finally:
        conn.close()

    return "chat room"


@app.route("/friend", methods=["GET", "POST"])
def friend():
    try:
        conn = sqlite3.connect("/root/10s.db")
        c = conn.cursor()

        #search friend
        if request.method == 'GET':
            body = request.json
            user_id = body['user_id']
            c.execute("SELECT FriendID FROM User WHERE UserID = user_id")
            friend_info = c.fetchone()
            conn.commit()
            return "search friend success", 200


        #add friend
        elif request.method == 'POST':
            body = request.json
            user_id = body['user_id']
            friend_email = body['friend_email']
            c.execute("SELECT ID FROM User WHERE email='{}'".format(friend_email))
            friend_id = c.fetchone()
            c.execute("INSERT INTO Friends(UserID, FriendID) VALUES(?,?,?)", [user_id, friend_id])
            conn.commit()
            return "add friend success", 200

    except:
        print("log")
        conn.rollback()

    finally:
        conn.close()

    return "friend_"


@app.route("/profile", methods=["GET", "PUT"])
def profile():
    return "profile"
    

app.run(port=80, host="0.0.0.0", debug=True)
