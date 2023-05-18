CREATE SCHEMA player_schema AUTHORIZATION postgres;

CREATE TABLE player_schema.player (
	player_id int4 NOT NULL,
	country varchar(255) NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	team_id int4 NULL,
	is_active bool NOT NULL DEFAULT true,
	CONSTRAINT player_pkey PRIMARY KEY (player_id)
);