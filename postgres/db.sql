-- create new user for the mastercard database --
--CREATE ROLE data_generator WITH LOGIN PASSWORD 'data_generator';

CREATE SCHEMA data_schema;
SET search_path TO data_schema;

-- create a new database for the mastercard service
--CREATE DATABASE data_generator_db WITH OWNER data_generator;

-- connect to the mastercard_database
-- connect data_generator_db;

-- create transaction table
CREATE TABLE IF NOT EXISTS data_schema.transaction(
  transaction_uid UUID PRIMARY KEY NOT NULL,
  account_uid UUID NOT NULL,
  created_timestamp TIMESTAMP NOT NULL,
  amount NUMERIC NOT NULL,
  direction VARCHAR(20) NOT NULL,
  currency_code VARCHAR(3) NOT NULL
);

-- grant CRUD rights to data_generator user
GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA data_schema
TO data_generator;

-- grant this too
grant all privileges on all sequences in schema data_schema to data_generator;
