from flask import Flask


app = Flask(__name__)


@app.route("/auth", methods=["GET", "POST"])
def auth():
    return "auth"


@app.route("/chatRoom", methods=["GET", "POST", "PUT", "DELETE"])
def chatRoom():
    return "chat room"


@app.route("/friend", methods=["GET", "POST"])
def friend():
    return "friend"


@app.route("/profile", methods=["GET", "PUT"])
def profile():
    return "profile"
    

app.run(port=80, host="0.0.0.0", debug=True)
