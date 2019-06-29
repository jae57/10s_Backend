from flask import Flask, request
import sqlite3


app = Flask(__name__)

@app.route("/auth", methods=["GET", "POST"])
def auth():

    try:
        conn = sqlite3.connect("/root/10s.db")
        c = conn.cursor()
    
        #join: addUser
        if request.method == 'POST':
            email = request.form['email']
            nickname = request.form['nickname']
            profilepic = request.form['profilepic']
            c.execute("INSERT INTO User(email, nickname, profilepic) VALUES (?,?,?)", [email, nickname, profilepic])
            conn.commit()
            return "join success"


        #login: getUser
        elif request.method == 'GET':
            email = request.form['email']
            c.execute("SELECT * FROM User WHERE email = '{}'".format(email))
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
    return "chat room"


@app.route("/friend", methods=["GET", "POST"])
def friend():

    try:
        conn = sqlite3.connect("/root/10s.db")
        c = conn.cursor()

        #search friend
        if request.method == 'GET':
            user_id = request.form[user_id]
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
