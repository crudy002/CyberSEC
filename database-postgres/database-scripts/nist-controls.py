import csv
import psycopg2

# Connect to the PostgreSQL database
connection = psycopg2.connect("dbname=compliance_tracker user=postgres password=password host=localhost port=5432")
cursor = connection.cursor()

# Count initial records in the nist_controls table
cursor.execute("SELECT COUNT(*) FROM nist_controls")
initial_count = cursor.fetchone()[0]
print(f"Initial number of records in nist_controls: {initial_count}")

# Initialize counters for added or updated records
records_added = 0
records_updated = 0

with open('/workspace/CyberSEC/database-postgres/input/nist_controls.csv', 'r') as f:
    reader = csv.reader(f)
    next(reader)  # Skip header row
    for row in reader:
        control_id, control_name, control_description, discussion, _ = row  # Ignore related_controls

        # Check if the record already exists
        cursor.execute("SELECT control_name FROM nist_controls WHERE control_id = %s", (control_id,))
        existing_record = cursor.fetchone()

        if existing_record:
            # If the record exists, check if the control_name is the same
            if existing_record[0] == control_name:
                print(f"Record for control_id {control_id} already exists with the same control_name. Skipping.")
                continue  # Skip if the record is the same

            # If the record exists but the control_name is different, update it
            cursor.execute(
                "UPDATE nist_controls SET control_name = %s, control_description = %s, discussion = %s WHERE control_id = %s",
                (control_name, control_description, discussion, control_id)
            )
            records_updated += 1
            print(f"Updated record for control_id {control_id}.")
        else:
            # Insert the new record if it doesn't exist
            cursor.execute(
                "INSERT INTO nist_controls (control_id, control_name, control_description, discussion) VALUES (%s, %s, %s, %s)",
                (control_id, control_name, control_description, discussion)
            )
            records_added += 1
            print(f"Added new record for control_id {control_id}.")

# Commit changes and close connection
connection.commit()
cursor.close()
connection.close()