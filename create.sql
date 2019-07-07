CREATE TABLE chat_room(
id INTEGER PRIMARY KEY AUTOINCREMENT,
room_name TEXT,
create_date DATE
);

CREATE TABLE chat_user(
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

INSERT INTO chat_room(
room_name,
create_date)
VALUES (
'mychatting',
datetime('now')
);

INSERT INTO chat_room(
room_name,
create_date)
VALUES (
'yourchatting',
datetime('now')
);

INSERT INTO chat_room(
room_name,
create_date)
VALUES (
'herchatting',
datetime('now')
);

INSERT INTO chat_room(
room_name,
create_date)
VALUES (
'hischatting',
datetime('now')
);

INSERT INTO chat_user(
room_id,
user_id)
VALUES (
1,
1
);

INSERT INTO chat_user(
room_id,
user_id)
VALUES (
2,
1
);

INSERT INTO chat_user(
room_id,
user_id)
VALUES (
2,
2
);

INSERT INTO chat_user(
room_id,
user_id)
VALUES (
3,
3
);

INSERT INTO chat_user(
room_id,
user_id)
VALUES (
3,
4
);

INSERT INTO chat_user(
room_id,
user_id)
VALUES (
3,
5
);

INSERT INTO chat_user(
room_id,
user_id)
VALUES (
4,
4
);

INSERT INTO user(
email,
nickname,
profile_image,
modified_date,
status_message,
auth_token)
VALUES (
'userOne@hello.world',
'user1',
'mypic1.png',
datetime('now'),
'happy',
'tooookkkkkeeeeennnnn'
);

INSERT INTO user(
email,
nickname,
profile_image,
modified_date,
status_message,
auth_token)
VALUES (
'userTwo@hello.world',
'user2',
'mypic2.png',
datetime('now'),
'sad',
'ttttttokkkkkeeeeennnnn'
);

INSERT INTO user(
email,
nickname,
profile_image,
modified_date,
status_message,
auth_token)
VALUES (
'userThree@hello.world',
'user3',
'mypic3.png',
datetime('now'),
'soso',
'tttttooookeeeeennnnn'
);

INSERT INTO user(
email,
nickname,
profile_image,
modified_date,
status_message,
auth_token)
VALUES (
'userFour@hello.world',
'user4',
'mypic4.png',
datetime('now'),
'good',
'ttttttooookkkkkennnnn'
);

INSERT INTO user(
email,
nickname,
profile_image,
modified_date,
status_message,
auth_token
) VALUES (
'userFive@hello.world',
'user5',
'mypic5.png',
datetime('now'),
'angry',
'ttttttooookkkkkeeeeen'
);

INSERT INTO friend(
user_id,
friend_id
) VALUES (
1,
2
);

INSERT INTO friend(
user_id,
friend_id
) VALUES (
2,
1
);

INSERT INTO friend(
user_id,
friend_id
) VALUES (
1,
3
);

INSERT INTO friend(
user_id,
friend_id
) VALUES (
3,
1
);

INSERT INTO friend(
user_id,
friend_id
) VALUES (
1,
4
);

INSERT INTO friend(
user_id,
friend_id
) VALUES (
4,
1
);

INSERT INTO friend(
user_id,
friend_id
) VALUES (
3,
2
);

INSERT INTO friend(
user_id,
friend_id
) VALUES (
2,
3
);
