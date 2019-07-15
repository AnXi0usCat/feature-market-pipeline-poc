-- create new user for the mastercard database --
CREATE ROLE data_generator WITH LOGIN PASSWORD 'data_generator';

-- create a new database for the mastercard service
CREATE DATABASE data_generator_db WITH OWNER data_generator;

-- connect to the mastercard_database
\connect data_generator_db;

-- create transaction table
CREATE TABLE IF NOT EXISTS public.transaction(
  transaction_uid UUID PRIMARY KEY NOT NULL,
  account_uid UUID NOT NULL,
  created_timestamp TIMESTAMP NOT NULL,
  amount NUMERIC NOT NULL,
  direction VARCHAR(20) NOT NULL,
  currency_code VARCHAR(3) NOT NULL
);

-- grant CRUD rights to data_generator user
GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO data_generator;

-- grant this too
grant all privileges on all sequences in schema public to data_generator;
