COPY control_families (family_id, name)
FROM './control_families.csv' 
DELIMITER ',' 
CSV HEADER;
