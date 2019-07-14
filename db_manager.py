#-*- coding:utf-8 -*-
import sqlite3
import sys
import datetime

class DatabseManager:
    def __init__(self, db):
        self.db = db    


    def check_user(self, user_email):
        with sqlite3.connect(self.db) as conn:
            c = conn.cursor()
            c.execute("SELECT auth_token FROM user WHERE email = '{}'".format(user_email))
            auth_token = c.fetchone()
            return auth_token


    def add_user(self, email, nickname, profile_image, token):
        with sqlite3.connect(self.db) as conn:
            try:
                c = conn.cursor()
                c.execute(
                    "INSERT INTO user(email, nickname, profile_image, modified_date, status_message, auth_token) VALUES (?,?,?,?,?,?)",
                    [email, nickname, profile_image, datetime.datetime.now(), "", token])
                conn.commit()
            except:
                conn.rollback()
                print(sys.exc_info()[0])
                raise


    def check_delete_room(self, room_id):
        with sqlite3.connect(self.db) as conn:
            c = conn.cursor()
            c.execute("SELECT count(*) FROM chat_user WHERE room_id = "+str(room_id))
            remains = c.fetchone()[0]
            if remains == 0:
                c.execute("DELETE FROM chat_room WHERE id = ?", (room_id,))
                conn.commit()


    def out_chatroom(self, room_id, user_id):
        with sqlite3.connect(self.db) as conn:
            c = conn.cursor()
            c.execute("DELETE FROM chat_user WHERE room_id = ? AND user_id=?", [room_id, user_id])
            conn.commit()


    def get_room_infos(self, user_id):
        room_infos = []
        with sqlite3.connect(self.db) as conn:
            c = conn.cursor()
            c.execute("SELECT cr.id, cr.room_name FROM chat_user AS cu INNER JOIN chat_room AS cr on cu.room_id = cr.id WHERE cu.user_id="+str(user_id))
            room_infos = c.fetchall()
        return room_infos


    def create_chatroom(self, room_name, time_created, user_id):
        with sqlite3.connect(self.db) as conn:
            c = conn.cursor()
            c.execute("INSERT INTO chat_room(room_name, create_date) VALUES (?,?)", [room_name, time_created])

            c.execute("SELECT id FROM chat_room WHERE room_name = ?", [room_name])
            room_id = c.fetchone()[0]

            c.execute("INSERT INTO chat_user(room_id, user_id) VALUES (?,?)", [room_id, user_id])
            conn.commit()


    def get_user_id(self, auth_token):
        with sqlite3.connect(self.db) as conn:
            c = conn.cursor()
            c.execute("SELECT id FROM user WHERE auth_token = ?",[auth_token])
            user_id = c.fetchone()[0]
        return user_id


    def update_chatuser(self, room_id, invited_id):
        with sqlite3.connect(self.db) as conn:
            try:
                c = conn.cursor()
                c.execute("INSERT INTO chat_user(room_id, user_id) VALUES(?,?)", [room_id, invited_id])
                conn.commit()
            except:
                print(sys.exc_info()[0])
                conn.rollback()


    def get_user_id_by_email(self, email):
        with sqlite3.connect(self.db) as conn:
            c = conn.cursor()
            c.execute("SELECT id FROM user WHERE email='{}'".format(friend_email))
            friend_id = c.fetchone()
            return friend_id


    def join_friend(self, user_id, friend_id):
        try:
            with sqlite3.connect(self.db) as conn:
                c = conn.cursor()
                c.execute("INSERT INTO friend(user_id, friend_id) VALUES(?,?)", [user_id, friend_id])
                c.execute("INSERT INTO friend(user_id, friend_id) VALUES(?,?)", [friend_id, user_id])
                conn.commit()
            return conn
        except:
            print(sys.exc_info()[0])
            conn.rollback()


    def get_friend_info(self, user_id):
        with sqlite3.connect(self.db) as conn:
            c = conn.cursor()
            c.execute(
                "SELECT id, nickname, profile_image, status_message FROM user INNER JOIN friend ON friend.friend_id = user.id WHERE user_id = ?",
                [user_id])
            friend_info = c.fetchall()
        return friend_info


    def get_user_profile(self, user_id):
        with sqlite3.connect(self.db) as conn:
            c = conn.cursor()
            c.execute("SELECT * FROM user where id=" + str(user_id))
            row = c.fetchone()
            user_profile = {'nickname': row[2], 'status_message': row[4], 'profile_image': row[3]}
            return user_profile


    def update_user_profile(self, new_nickname, new_status, new_image_path, user_id):
        with sqlite3.connect(self.db) as conn:
            try:
                c = conn.cursor()
                c.execute("UPDATE user SET nickname = '" + new_nickname +
                        "', status_message = '" + new_status +
                        "', profile_image= '" + new_image_path +
                        "', modified_date = datetime('now') where id=" + str(user_id)
                        );    
                conn.commit()
            except:
                print(sys.exc_info()[0])
                conn.rollback()

