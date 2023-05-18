CREATE SCHEMA field_schema AUTHORIZATION postgres;

CREATE TABLE field_schema.field (
	field_id int4 NOT NULL,
	capacity int4 NOT NULL,
	field_address varchar(255) NOT NULL,
	field_name varchar(255) NOT NULL,
	is_active bool NOT NULL DEFAULT true,
	CONSTRAINT field_pkey PRIMARY KEY (field_id),
	CONSTRAINT uk_bn7np5noyay4x6258si8y41h9 UNIQUE (field_address),
	CONSTRAINT uk_f8mr6kl8yhhwvv1j7ywr8hout UNIQUE (field_name)
);