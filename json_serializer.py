#-*- coding:utf-8 -*-
from flask import jsonify

def message(message):
    return jsonify({"message": message})