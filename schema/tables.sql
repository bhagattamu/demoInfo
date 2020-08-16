-- - admin, norm_user -
CREATE TABLE roles (
	role_id INT AUTO_INCREMENT,
	role_name VARCHAR(20),
	role_desc VARCHAR(200),
	PRIMARY KEY (role_id)
);
--- Java, .Net, JS, ... -
CREATE TABLE departments (
	dept_id INT AUTO_INCREMENT,
	dept_name VARCHAR(50),
	PRIMARY KEY (dept_id)
);
--- Senior, Mid-Level, Junior -
CREATE TABLE designations (
	design_id INT AUTO_INCREMENT,
	design_short VARCHAR(10),
	design_full VARCHAR(20),
	PRIMARY KEY (design_id)
);

CREATE TABLE users (
	user_id INT NOT NULL AUTO_INCREMENT,
	role_id INT NOT NULL,
	user_name VARCHAR(20) NOT NULL UNIQUE,
	email VARCHAR(100) NOT NULL UNIQUE,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50),
	password VARCHAR(100) NOT NULL,
--	| seperated id of department for multiple department 
	department VARCHAR(50) NOT NULL, 
--	| seperated id of designation for multiple designations
	designation VARCHAR(50) NOT NULL, 
	PRIMARY KEY (user_id),
	CONSTRAINT FK_role FOREIGN KEY (role_id)
	REFERENCES roles(role_id)
);
-- time for answer is in sec
CREATE TABLE questions (
	question_id int AUTO_INCREMENT,
	user_id int NOT NULL,
	dept_id VARCHAR(50) NOT NULL,
	design_id VARCHAR(50) NOT NULL,
	question VARCHAR(1000) NOT NULL,
	weight float NOT NULL,
	timeforans float default 4,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (question_id),
	CONSTRAINT FK_user FOREIGN KEY (user_id)
	REFERENCES users(user_id)
);

CREATE TABLE answers (
	answer_id int AUTO_INCREMENT,
	question_id int NOT NULL,
	user_id int NOT NULL,
	answer VARCHAR(1000) NOT NULL,
	correctness BOOLEAN NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (answer_id),
	CONSTRAINT FK_question FOREIGN KEY (question_id)
	REFERENCES questions(question_id),
	CONSTRAINT FK_useranswer FOREIGN KEY (user_id)
	REFERENCES users(user_id)
); 
-- testtaker_ids is a | seperated value of user id who are going to give a test
-- test_questions is a | seperated value of question id
-- test_time is a time in minute 
-- test_complete true if ready to supply for exam
CREATE TABLE tests (
	test_id int AUTO_INCREMENT,
	testmaker_id int NOT NULL,
	testtaker_ids VARCHAR(500) NOT NULL,
	dept_ids VARCHAR(50) NOT NULL,
	design_ids VARCHAR(50) NOT NULL,
	test_desc VARCHAR(500) DEFAULT 'No Description',
	test_complete BOOLEAN DEFAULT 0, 
	test_questions VARCHAR(500) DEFAULT 'NO',
	test_full_weight float NOT NULL,
 	test_pass_weight float NOT NULL,
	test_time float DEFAULT '0',
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (test_id)
);

-- fullresult is [questionid],[answerid]|[questionid],[answerid]|[questionid],[answerid]...
-- i.e question and answer seperated by comma for single question and seperated by | 
CREATE TABLE results (
	result_id int AUTO_INCREMENT,
	user_id int NOT NULL,
	test_id int NOT NULL,
	fullresult VARCHAR(500) NOT NULL,
	marks float default 0 NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (result_id),
	FOREIGN KEY (user_id)
	REFERENCES users(user_id),
	FOREIGN KEY (test_id)
	REFERENCES tests(test_id)
);

CREATE TABLE notification(
	notification_id int AUTO_INCREMENT,
	user_id int NOT NULL,
	notification_message VARCHAR(200) NOT NULL,
	notification_seen BOOLEAN NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (notification_id),
	FOREIGN KEY (user_id)
	REFERENCES users(user_id)
);

CREATE  TABLE timer(
	timer_id int AUTO_INCREMENT,
	result_id int NOT NULL,
	timefinish Boolean NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (timer_id),
	FOREIGN KEY (result_id)
	REFERENCES results(result_id)
);