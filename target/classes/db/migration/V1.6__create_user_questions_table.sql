CREATE TABLE IF NOT EXISTS user_questions (

    id varchar(255) NOT NULL  PRIMARY KEY,
    user_id varchar(255) NULL,
    question_id varchar(255) NULL,
    status varchar(255) NULL DEFAULT 'Save'
);