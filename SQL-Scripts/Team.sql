CREATE SCHEMA team_schema AUTHORIZATION postgres;


CREATE TABLE team_schema.team (
	team_id int4 NOT NULL,
	team_name varchar(255) NOT NULL,
	is_active bool NOT NULL DEFAULT true,
	CONSTRAINT team_pkey PRIMARY KEY (team_id),
	CONSTRAINT uk_sob22siqdnn2rfsxk6f00pgwb UNIQUE (team_name)
);