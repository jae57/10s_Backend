INSERT INTO chatroom(room_name) values('My First ChattingRoom :) ~ !');
INSERT INTO chatroom(room_name) values('채팅방');
INSERT INTO chatroom(room_name) values('삼번방');
INSERT INTO chatroom(room_name) values('Room Num.4');
INSERT INTO chatroom(room_name) values('Last room 끝방');

INSERT INTO chat_user(room_id,user_id) VALUES (1,1);
INSERT INTO chat_user(room_id,user_id) VALUES (1,3);
INSERT INTO chat_user(room_id,user_id) VALUES (2,1);
INSERT INTO chat_user(room_id,user_id) VALUES (2,2);
INSERT INTO chat_user(room_id,user_id) VALUES (3,3);
INSERT INTO chat_user(room_id,user_id) VALUES (3,4);
INSERT INTO chat_user(room_id,user_id) VALUES (4,5);
INSERT INTO chat_user(room_id,user_id) VALUES (4,4);

INSERT INTO user(email,
nickname,
profile_image,
status_message,
auth_token)
VALUES (
'userOne@hello.world',
'user1',
'mypic1.png',
'happy',
'tooookkkkkeeeeennnnn'
);

INSERT INTO user(
email,
nickname,
profile_image,
status_message,
auth_token)
VALUES (
'userTwo@hello.world',
'user2',
'mypic2.png',
'sad',
'ttttttokkkkkeeeeennnnn'
);

INSERT INTO user(
email,
nickname,
profile_image,
status_message,
auth_token)
VALUES (
'userThree@hello.world',
'user3',
'mypic3.png',
'soso',
'tttttooookeeeeennnnn'
);

INSERT INTO user(
email,
nickname,
profile_image,
status_message,
auth_token)
VALUES (
'userFour@hello.world',
'user4',
'mypic4.png',
'good',
'ttttttooookkkkkennnnn'
);

INSERT INTO user(
email,
nickname,
profile_image,
status_message,
auth_token
) VALUES (
'userFive@hello.world',
'user5',
'mypic5.png',
'angry',
'ttttttooookkkkkeeeeen'
);

INSERT INTO friend(user_id,friend_id) VALUES (1,2);
INSERT INTO friend(user_id,friend_id) VALUES (2,1);
INSERT INTO friend(user_id,friend_id) VALUES (1,4);
INSERT INTO friend(user_id,friend_id) VALUES (4,1);
INSERT INTO friend(user_id,friend_id) VALUES (1,3);
INSERT INTO friend(user_id,friend_id) VALUES (3,1);
INSERT INTO friend(user_id,friend_id) VALUES (3,2);
INSERT INTO friend(user_id,friend_id) VALUES (2,3);