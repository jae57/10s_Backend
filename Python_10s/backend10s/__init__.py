from flask import Flask, request, jsonify, json, g

import sqlite3
import logging
import datetime

def create_app():
    app = Flask(__name__)
    from backend10s.controller import controller
    from backend10s.manager import message
    from backend10s.manager import s3
    from backend10s.manager import database
    
    from backend10s.backend10s_blueprint import backend10s
    app.register_blueprint(backend10s)
    
    return app
