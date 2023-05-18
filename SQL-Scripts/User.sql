CREATE SCHEMA user_schema AUTHORIZATION postgres;


CREATE TABLE user_schema."user" (
	user_id int4 NOT NULL,
	email varchar(255) NOT NULL,
	is_active bool NOT NULL DEFAULT true,
	"password" varchar(255) NOT NULL,
	"role" varchar(255) NOT NULL,
	user_name varchar(255) NOT NULL,
	CONSTRAINT uk_lqjrcobrh9jc8wpcar64q1bfh UNIQUE (user_name),
	CONSTRAINT uk_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email),
	CONSTRAINT user_pkey PRIMARY KEY (user_id)
);