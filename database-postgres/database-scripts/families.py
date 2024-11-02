import csv
import psycopg2

# Connect to the PostgreSQL database
connection = psycopg2.connect("dbname=compliance_tracker user=postgres password=password host=localhost port=5432")
cursor = connection.cursor()

# Count initial records in the control_families table
cursor.execute("SELECT COUNT(*) FROM control_families")
initial_count = cursor.fetchone()[0]
print(f"Initial number of records in control_families: {initial_count}")

# Initialize counters for added or updated records
records_added = 0
records_updated = 0

with open('/workspace/CyberSEC/database-postgres/control_families.csv', 'r') as f:
    reader = csv.reader(f)
    next(reader)  # Skip header row
    for row in reader:
        family_id, name = row  # Assuming the CSV has family_id and name in the first two columns

        # Check if the record already exists
        cursor.execute("SELECT name FROM control_families WHERE family_id = %s", (family_id,))
        existing_record = cursor.fetchone()

        if existing_record:
            # If the record exists, check if the name is the same
            if existing_record[0] == name:
                print(f"Record for family_id {family_id} already exists with the same name. Skipping.")
                continue  # Skip if the record is the same

            # If the record exists but the name is different, update it
            cursor.execute(
                "UPDATE control_families SET name = %s WHERE family_id = %s",
                (name, family_id)
            )
            records_updated += 1
            print(f"Updated record for family_id {family_id}.")
        else:
            # Insert the new record if it doesn't exist
            cursor.execute(
                "INSERT INTO control_families (family_id, name) VALUES (%s, %s)",
                (family_id, name)
            )
            records_added += 1
            print(f"Added new record for family_id {family_id}.")

# Commit changes and close connection
connection.commit()

# Print the number of records added or updated
print(f"Records added: {records_added}")
print(f"Records updated: {records_updated}")

# Final count of records in the table
cursor.execute("SELECT COUNT(*) FROM control_families")
final_count = cursor.fetchone()[0]
print(f"Final number of records in control_families: {final_count}")

# Clean up
cursor.close()
connection.close()
