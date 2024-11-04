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


# Parse out the related controls
with open('/workspace/CyberSEC/database-postgres/input/nist_controls.csv', 'r') as f:
    reader = csv.reader(f)
    next(reader)  # Skip header row
    # Parse out the related controls
    related_controls = []
    for row in reader:
        control_id, control_name, control_description, discussion, related_controls_str = row

        # Check if the related controls string is "None."
        if related_controls_str == "None.":
            related_controls_str = ""

        # Parse the related controls string into a list of control IDs
        related_controls_list = [control_id.strip().rstrip('.') for control_id in related_controls_str.split(', ') if control_id.strip()]

        # Remove any leading or trailing whitespace from each control ID
        related_controls_list = [control_id.strip() for control_id in related_controls_list]


        # Create a list of tuples containing the control ID and related control ID
        for related_control_id in related_controls_list:    
            related_controls.append((control_id, related_control_id))

# Write the related controls to the database
cursor.executemany("INSERT INTO related_controls (control_id, related_control_id) VALUES (%s, %s)", related_controls)

# Commit changes and close connection
connection.commit()
cursor.close()
connection.close()