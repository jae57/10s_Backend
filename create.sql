CREATE TABLE chatRoom(
id INTEGER PRIMARY KEY AUTOINCREMENT,
roomName TEXT,
createDate date
);

CREATE TABLE chatUser(
roomID INTEGER,
userID INTEGER,
PRIMARY KEY(roomID, userID)
);
/* 토큰으로 아이디를 하면 문제의 */
CREATE TABLE user(
ID TEXT PRIMARY KEY,
email TEXT,
nickname TEXT,
profilepic TEXT,
modifiedDate date,
statusMessage TEXT
);

CREATE TABLE friend(
userID INTEGER,
friendID INTEGER,
PRIMARY KEY (userID, friendID)
);