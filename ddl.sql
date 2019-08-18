DROP TABLE IF EXISTS chatroom;
DROP TABLE IF EXISTS chat_user;
DROP TABLE IF EXISTS friend;
DROP TABLE IF EXISTS user;

CREATE TABLE `chatroom`(
`id` INT(11) NOT NULL AUTO_INCREMENT, 
`room_name` TEXT, 
`create_date` DATETIME DEFAULT CURRENT_TIMESTAMP, 
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `chat_user`(
`room_id` INT(11) NOT NULL, 
`user_id` INT(11) NOT NULL, 
PRIMARY KEY(`room_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `friend`(
`user_id` INT(11) NOT NULL, 
`friend_id` INT(11) NOT NULL, 
`create_date` DATETIME DEFAULT CURRENT_TIMESTAMP, 
PRIMARY KEY(`user_id`, `friend_id`)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user`(
`id` INT(11) NOT NULL AUTO_INCREMENT, 
`email` TEXT,
`password` TEXT, 
`nickname` TEXT, 
`profile_image` TEXT, 
`status_message` TEXT, 
`register_date` DATETIME DEFAULT CURRENT_TIMESTAMP, 
`modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP, 
PRIMARY KEY(`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;