CREATE TABLE chat_room(
id INTEGER PRIMARY KEY AUTOINCREMENT,
room_name TEXT,
create_date DATE
);

CREATE TABLE chatUser(
room_id INTEGER,
user_id INTEGER,
PRIMARY KEY(room_id, user_id)
);
/* 토큰은 바뀔 수 있는 값이므로 Pkey는 부적절 */
CREATE TABLE user(
id INTEGER PRIMARY KEY AUTOINCREMENT,
email TEXT,
nickname TEXT,
profile_image TEXT,
modified_date DATE,
status_message TEXT,
auth_token TEXT
);

CREATE TABLE friend(
user_id INTEGER,
friend_id INTEGER,
PRIMARY KEY (user_id, friend_id)
);