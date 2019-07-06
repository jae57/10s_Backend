CREATE TABLE chat_room(
id INTEGER PRIMARY KEY AUTOINCREMENT,
room_name TEXT,
create_date DATETIME DEFAULT CURRENT_TIMESTAMP
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
status_message TEXT,
auth_token TEXT,
modified_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE friend(
user_id INTEGER,
friend_id INTEGER,
create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (user_id, friend_id)
);

INSERT INTO user VALUES(0,
                        "pikachu@pokemon.com", 
                        "pikapika", 
                        "https://pokemonletsgo.pokemon.com/assets/img/common/char-pikachu.png", 
                        "None", 
                        "1",
                        CURRENT_TIMESTAMP);


INSERT INTO user VALUES(0,
                        "eevee@pokemon.com", 
                        "vee", 
                        "https://pokemonletsgo.pokemon.com/assets/img/common/char-pikachu.png", 
                        "None", 
                        "1",
                        CURRENT_TIMESTAMP);


INSERT INTO user VALUES(0,
                        "pichu@pokemon.com", 
                        "pi", 
                        "https://pokemonletsgo.pokemon.com/assets/img/common/char-pikachu.png", 
                        "None", 
                        "1",
                        CURRENT_TIMESTAMP);

