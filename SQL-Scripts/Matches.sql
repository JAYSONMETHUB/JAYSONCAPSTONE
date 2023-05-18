CREATE SCHEMA match_schema AUTHORIZATION postgres;

CREATE TABLE match_schema."match" (
	match_id int4 NOT NULL,
	date_time timestamp NOT NULL,
	field_id int4 NOT NULL,
	is_active bool NOT NULL DEFAULT true,
	teams_id varchar(255) NOT NULL,
	tournament_id int4 NOT NULL,
	CONSTRAINT match_pkey PRIMARY KEY (match_id)
);