from flask import Flask, request, jsonify, json, g
import sqlite3
import datetime
import sys


app = Flask(__name__)
def json_message(message):
    return jsonify({"message": message})


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
            email = body["email"]
            token = hash(email)
            auth_token = {'auth_token' : token}
            nickname = body['nickname']
            profile_image = body['profile_image']
            c.execute("INSERT INTO user(email, nickname, profile_image, modified_date, status_message, auth_token) VALUES (?,?,?,?,?,?)",
                                [email, nickname, profile_image, datetime.datetime.now(), "", token])
            conn.commit()
            return jsonify(auth_token), 200


        #login: getUser
        elif request.method == 'GET':
            user_email = request.headers['email']
            c.execute("SELECT auth_token FROM user WHERE email = '{}'".format(user_email))
            auth_token = c.fetchone()
            if auth_token == None:
                return jsonify(message="Not Exist User"), 400

            result_json = {'auth_token' : auth_token[0]}
            return jsonify(result_json), 200
    
    except TypeError:
        conn.rollback()
        raise
    except:
        conn.rollback()
        print(sys.exc_info()[0])
        raise

    finally:
        conn.close()

    return jsonify(message="ERROR"), 500


@app.route("/api/chatRoom", methods=["GET", "POST", "PUT", "DELETE"])
def chat_room():
    try:
        conn = sqlite3.connect("10s.db")
        c = conn.cursor()
        
        #create a chat_room
        if request.method == 'POST':
            user_id = request.headers["Authorization"].split[1]
            body = request.json
            room_name = body['room_name']
            time_created = datetime.datetime.now()
            c.execute("INSERT INTO chat_room(room_name, create_date) VALUES (?,?)", [room_name, time_created])
            conn.commit()

            c.execute("SELECT id FROM chat_room WHERE room_name = ?", [room_name])
            room_id = c.fetchone()
            
            c.execute("INSERT INTO chat_user(room_id, user_id) VALUES (?,?)", [room_id,user_id])
            conn.commit()

            return json_message("chat_room created"), 200

        #get chat_room that user is in
        elif request.method == 'GET':
            user_id = request.headers["Authorization"].split[1]
            c.execute("SELECT room_id FROM chat_user WHERE user_id = ?", [user_id])
            room_id = c.fetchall()
            
            return json.dumps(room_id), 200


        #delete chat_room
        elif request.method == 'DELETE':
            body = request.json
            room_id = body['room_id']
            c.execute("DELETE FROM chat_room WHERE id = ?", (room_id,))
            conn.commit()

            c.execute("DELETE FROM chat_user WHERE room_id = ?", (room_id,))
            conn.commit()

            return json_message("Chat room deleted"), 200


        #invite friend to chat_room
        elif request.method == 'PUT':
            body = request.json
            room_id = body['room_id']
            invited_id = body['invited_id']
            c.execute("INSERT INTO chat_user(room_id, user_id) VALUES(?,?)", [room_id, invited_id])
            conn.commit()

            return json_message("friend invited"), 200


    except:
        print(sys.exc_info()[0])
        conn.rollback()

    finally:
        conn.close()

    return jsonify(message="ERROR"), 500


@app.route("/api/friend", methods=["GET", "POST"])
def friend():
    try:
        conn = sqlite3.connect("10s.db")
        c = conn.cursor()

        #search friend
        if request.method == 'GET':
            user_id = request.headers["Authorization"].split()[1]
            c.execute("SELECT id, email, nickname, profile_image, modified_date, status_message FROM user INNER JOIN friend ON friend.friend_id = user.email WHERE user_id = ?", [user_id])
            friend_info = c.fetchall()
            print(friend_info)
            return json.dumps(friend_info), 200


        #add friend
        elif request.method == 'POST':
            user_id = request.headers["Authorization"].split()[1]
            body = request.json
            friend_email = body['friend_email']
            c.execute("SELECT id FROM user WHERE email='{}'".format(friend_email))
            friend_id = c.fetchone()[0]
            if friend_id is None:
                return jsonify({"message":"cannot find friend"}), 400

            c.execute("INSERT INTO friend(user_id, friend_id) VALUES(?,?)", [user_id, friend_id])
            conn.commit()
            return json_message("add friend success"), 200
    except sqlite3.OperationalError:
        conn.rollback()
        raise
    except sqlite3.InterfaceError:
        conn.rollback()
        raise
    except:
        print(sys.exc_info()[0])
        conn.rollback()
    finally:
        conn.close()

    return jsonify(message="ERROR"), 500


@app.route("/api/profile/<user_id>", methods=["GET", "PUT"])
def profile(user_id):
    try:
        conn = sqlite3.connect("10s.db")
        c = conn.cursor()

        # get profile
        if request.method == 'GET':
            c.execute("SELECT * FROM user where id="+user_id)
            row = c.fetchone()
            user = {'nickname': row[2], 'status_message': row[5], 'profile_image': row[3]}
            return json.dumps(user)

        # update profile
        elif request.method == 'PUT':
            is_updated = False
            print(request.form)
            print(request.files)

            sql = "UPDATE user SET "
            columns = []

            if 'nickname' in body :
                columns.append("nickname = '"+body['nickname']+"'")
                is_updated = True

            if 'status_message' in body :
                columns.append("status_message = '" + body['status_message'] + "'")
                is_updated = True

            if 'profile_image' in body:
                #s3_manager.upload_file(
                columns.append("profile_image = '"+body['profile_image']+"'")
                is_updated = True

            if is_updated == True:
                columns.append("modified_date = datetime('now')")
                sql+=" ,".join(columns)
                sql+=" where id="+str(user_id)
                print("sql :"+sql)
                c.execute(sql)
                conn.commit()

            return "user updated", 200

    except KeyError:
        conn.rollback()
        raise

    except:
        print(sys.exc_info()[0])
        conn.rollback()

    finally:
        conn.close()

    return jsonify(message="ERROR"), 500


