from flask import Flask, request
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
            email = request.form['user_email']
            nickname = request.form['user_nickname']
            profilepic = request.form['user_profilepic']
            c.execute("INSERT INTO User(email, nickname, profilepic) VALUES (?,?,?)", [email, nickname, profilepic])
            conn.commit()
            return "join success"


        #login: getUser
        elif request.method == 'GET':
            user_email = request.form['user_email']
            c.execute("SELECT * FROM User WHERE email = '{}'".format(user_email))
            user_info = c.fetchall()        
            return "login success"
    
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
            user_id = request.form['user_id']
            roomname = request.form['room_name']
            time_created = datetime.datetime.now()
            c.execute("INSERT INTO ChatRoom(RoomName, CreateDate) VALUES (?,?)", [roomname, time_created])
            conn.commit()

            c.execute("SELECT ID FROM ChatRoom WHERE RoomName = roomname")
            room_id = c.fetchone()
            
            c.execute("INSERT INTO ChatUser(RoomID, UserID) VALUES (?,?)", [room_id,user_id])
            conn.commit()

            return "chatroom created"

        #get chatroom that user is in
        elif request.method == 'GET':
            user_id = request.form[user_id]
            c.execute("SELECT RoomID FROM ChatUser WHERE UserID = user_id")
            room_id = c.fetchall()

            return "chatroom fetched"


        #delete chatroom
        elif request.method == 'DELETE':
            room_id = request.form[room_id]
            c.execute("DELETE FROM ChatRoom WHERE ID = ?", (room_id,))
            conn.commit()

            c.execute("DELETE FROM ChatUser WHERE RoomID = ?", (room_id,))
            conn.commit()

            return "chatroom deleted"


        #invite friend to chatroom
        elif request.method == 'PUT':
            room_id = request.form[room_id]
            user_id = request.form[user_id]
            c.execute("INSERT INTO ChatUser(RoomID, UserID) VALUES(?,?)", [room_id, user_id])
            conn.commit()

            return "friend invited"




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
            user_id = request.form['user_id']
            c.execute("SELECT FriendID FROM User WHERE UserID = user_id")
            friend_info = c.fetchone()[0]
            conn.commit()
            return "search friend success"


        #add friend
        elif request.method == 'POST':
            user_id = request.form['user_id']
            friend_email = request.form['friend_email']
            c.execute("SELECT ID FROM User WHERE email='{}'".format(friend_email))
            friend_id = c.fetchone()[0]
            c.execute("INSERT INTO Friends(UserID, FriendID) VALUES(?,?,?)", [user_id, friend_id])
            conn.commit()
            return "add friend success"

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
