CREATE DATABASE compliance_tracker;

CREATE TABLE control_families (
    family_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE nist_controls (
    control_id VARCHAR(10) PRIMARY KEY,
    control_name VARCHAR(100) NOT NULL,
    control_description TEXT,
    discussion TEXT
);

ALTER TABLE nist_controls
ALTER COLUMN control_name TYPE TEXT;

SELECT * FROM control_families;
DELETE FROM control_families WHERE family_id = 'CA';
DELETE FROM control_families WHERE family_id = 'CM';
DELETE FROM control_families WHERE family_id = 'CP';
TRUNCATE control_families;

/*
ALTER TABLE control_families
DROP description;

DROP TABLE control_families;

INSERT INTO control_families (family_id, name)
VALUES (
    family_id:integer,
    'name:character varying'
  );

COPY control_families (family_id, name)
FROM 'database-postgres/control_families.csv' 
DELIMITER ',' 
CSV HEADER;
*/

# Command line
# \l - list databases
# \c compliance_tracker; - connect to database
# \dt - describe tables
# \q - quit
