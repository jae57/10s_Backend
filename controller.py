from flask import Flask, request, jsonify, json
import sqlite3
import datetime
import sys

app = Flask(__name__)

@app.route("/api/auth", methods=["GET", "POST"])
def auth():
    print("auth")
    try:
        conn = sqlite3.connect("10s.db")
        print("conn")
        c = conn.cursor()
        print("cursor")
        #join: addUser
        if request.method == 'POST':
            body = request.json
            print(body)
            email = body["email"]
            print(email)
            auth_token = {'auth_token' : hash(email)}
            print(auth_token)
            nickname = body['nickname']
            print(nickname)
            profilepic = body['profile_image']
            print(profilepic)
            c.execute("INSERT INTO user(id, email, nickname, profilepic, modifiedDate, statusMessage) VALUES (?,?,?,?,?,?)",
                                [hash(email), email, nickname, profilepic, datetime.datetime.now(), ""])
            conn.commit()
            return jsonify(auth_token), 200


        #login: getUser
        elif request.method == 'GET':
            user_email = request.headers['email']
            c.execute("SELECT ID FROM User WHERE email = '{}'".format(user_email))
            auth_token = c.fetchone()
            return jsonify({'auth_token' : auth_token}), 200
    
    except TypeError:
        raise
        conn.rollback()
    except:
        print(sys.exc_info()[0])
        raise

    finally:
        conn.close()

    return jsonify(message="ERROR"), 500


@app.route("/chatRoom", methods=["GET", "POST", "PUT", "DELETE"])
def chatRoom():
    try:
        conn = sqlite3.connect("10s.db")
        c = conn.cursor()

        #create a chatroom
        if request.method == 'POST':
            user_id = request.headers["Authorization"].split[1]
            body = request.json
            room_name = body['room_name']
            time_created = datetime.datetime.now()
            c.execute("INSERT INTO ChatRoom(RoomName, CreateDate) VALUES (?,?)", [room_name, time_created])
            conn.commit()

            c.execute("SELECT ID FROM ChatRoom WHERE RoomName = ?", [room_name])
            room_id = c.fetchone()
            
            c.execute("INSERT INTO ChatUser(RoomID, UserID) VALUES (?,?)", [room_id,user_id])
            conn.commit()

            return "chatroom created", 200

        #get chatroom that user is in
        elif request.method == 'GET':
            user_id = request.headers["Authorization"].split[1]
            c.execute("SELECT RoomID FROM ChatUser WHERE UserID = ?", [user_id])
            room_id = c.fetchall()
            
            return json.dumps(room_id), 200


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
            invited_id = body['invited_id']
            c.execute("INSERT INTO ChatUser(RoomID, UserID) VALUES(?,?)", [room_id, invited_id])
            conn.commit()

            return "friend invited", 200




    except:
        print(sys.exc_info()[0])
        conn.rollback()

    finally:
        conn.close()

    return "chat room"


@app.route("/friend", methods=["GET", "POST"])
def friend():
    try:
        conn = sqlite3.connect("10s.db")
        c = conn.cursor()

        #search friend
        if request.method == 'GET':
            user_id = request.headers["Authorization"].split[1]
            c.execute("SELECT FriendID FROM User WHERE UserID = ?", [user_id])
            friend_info = c.fetchone()

            return json.dumps(friend_info), 200


        #add friend
        elif request.method == 'POST':
            user_id = request.headers["Authorization"].split[1]
            print(user_id)
            body = request.json
            friend_email = body['friend_email']
            c.execute("SELECT ID FROM User WHERE email='{}'".format(friend_email))
            friend_id = c.fetchone()
            c.execute("INSERT INTO Friends(UserID, FriendID) VALUES(?,?,?)", [user_id, friend_id])
            conn.commit()
            return "add friend success", 200

    except:
        print(sys.exc_info()[0])
        conn.rollback()

    finally:
        conn.close()

    return "friend_"


@app.route("/profile", methods=["GET", "PUT"])
def profile():
    try:
        conn = sqlite3.connect("10s.db")
        c = conn.cursor()

        # get profile
        if request.method == 'GET':
            user_id = request.headers["Authorization"].split[1]
            c.execute("SELECT * FROM user where id="+user_id)
            row = c.fetchone()
            user = {'nickname': row[2], 'status_message': row[3], 'profile_image': row[4] }
            return json.dumps(user)

        # update profile
        elif request.method == 'PUT':
            user_id = request.headers["Authorization"].split[1]
            body = request.json
            nickname = body['nickname']
            status = body['status_message']
            profile_image = body['profile_image']
            modified_date = datetime.datetime.now()
            c.execute("UPDATE user SET nickname = '?', status_message='?', profilepic='?', modifieddate = '?' where id=?",[nickname, status, profile_image, modified_date, user_id])
            return "user updated", 200

    except KeyError:
        conn.rollback()
        raise

    except:
        print(sys.exc_info()[0])
        conn.rollback()

    finally:
        conn.close()

    return "profile_"
    

app.run(port=80, host="0.0.0.0", debug=True)
