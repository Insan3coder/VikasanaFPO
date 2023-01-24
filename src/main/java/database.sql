
use vikasana;

drop table Users

CREATE TABLE `Users` (
	`username` varchar(255),
	`user_id` numeric(10) NOT NULL,
	`PASSWORD` varchar(255) ,
	`USER_DESIGNATION` varchar(255),
	`USER_DOJ` DATE,
	`FILE_ID` varchar(255),
    `USER_EMAIL` varchar(255),
	PRIMARY KEY (`user_id`)
);


CREATE TABLE `User_Role_Restriction` (
	`user_id` numeric(10) NOT NULL ,
	`role_id` varchar(255) NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

CREATE TABLE `Roles` (
	`role_id` numeric(10) NOT NULL ,
	`role_name` varchar(255) NOT NULL ,
	PRIMARY KEY (`role_id`)
);

CREATE TABLE `Files` (
	`file_id` numeric(10) NOT NULL,
	`file_type` varchar(255) ,
	`file_content` blob ,
	`file description` varchar(4000),
	PRIMARY KEY (`file_id`)
);

CREATE TABLE `Commoditty` (
	`sl_no` numeric(10) NOT NULL ,
	`commodity_type` varchar(255) NOT NULL,
	`commodity_name` varchar(255) NOT NULL,
	`quantity` DECIMAL (65) NOT NULL,
	`Price` DECIMAL (65) NOT NULL,
	PRIMARY KEY (`sl_no`)
);

CREATE TABLE `Events` (
	`event_id` numeric(10) NOT NULL ,
	`event_name` varchar(255) NOT NULL,
	`event_file_map` varchar(255) NOT NULL,
	`event_details` varchar(4000) NOT NULL,
	PRIMARY KEY (`event_id`)
);

CREATE TABLE `Event_File_Maps` (
	`event_id` numeric(10) NOT NULL,
	`file_id` numeric(10) NOT NULL
);

ALTER TABLE `User` ADD CONSTRAINT `User_fk0` FOREIGN KEY (`file_id`) REFERENCES `File`(`file_id`);

ALTER TABLE `User_Role_Restriction` ADD CONSTRAINT `User_Role_Restriction_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `User_Role_Restriction` ADD CONSTRAINT `User_Role_Restriction_fk1` FOREIGN KEY (`role_id`) REFERENCES `Role`(`role_id`);

ALTER TABLE `Event_File_Map` ADD CONSTRAINT `Event_File_Map_fk0` FOREIGN KEY (`event_id`) REFERENCES `Event`(`event_id`);

ALTER TABLE `Event_File_Map` ADD CONSTRAINT `Event_File_Map_fk1` FOREIGN KEY (`file_id`) REFERENCES `File`(`file_id`);








