CREATE SCHEMA tournament_schema AUTHORIZATION postgres;


CREATE TABLE tournament_schema.tournament (
	tournament_id int4 NOT NULL,
	is_active bool NOT NULL DEFAULT true,
	sports_category varchar(255) NOT NULL,
	tournament_name varchar(255) NOT NULL,
	tournament_style varchar(255) NOT NULL,
	CONSTRAINT tournament_pkey PRIMARY KEY (tournament_id),
	CONSTRAINT uk_qophifbrf7ui49wk4rjdrdt3i UNIQUE (tournament_name)
);