CREATE SCHEMA ticket_schema AUTHORIZATION postgres;


CREATE TABLE ticket_schema.ticket (
	ticket_id int4 NOT NULL,
	customer_name varchar(255) NOT NULL,
	is_active bool NOT NULL DEFAULT true,
	match_id int4 NOT NULL,
	ticket_price float4 NOT NULL,
	CONSTRAINT ticket_pkey PRIMARY KEY (ticket_id)
);