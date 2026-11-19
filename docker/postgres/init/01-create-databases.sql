-- Runs once, on first start of an empty postgres_data volume.
-- identity_db is created via POSTGRES_DB; the other services need their own databases.
CREATE DATABASE workforce_db;
CREATE DATABASE roster_db;
