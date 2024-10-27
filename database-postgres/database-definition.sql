CREATE DATABASE compliance_tracker;


-- Systems Table
CREATE TABLE systems (
    system_id SERIAL PRIMARY KEY,
    system_name VARCHAR(100) NOT NULL,
    description TEXT,
    owner VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Controls Table
CREATE TABLE controls (
    control_id SERIAL PRIMARY KEY,
    control_name VARCHAR(50) NOT NULL,
    control_description TEXT,
    control_type VARCHAR(50),
    family VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Mappings Table
CREATE TABLE system_control_mappings (
    mapping_id SERIAL PRIMARY KEY,
    system_id INT REFERENCES systems(system_id) ON DELETE CASCADE,
    control_id INT REFERENCES controls(control_id) ON DELETE CASCADE,
    implementation_status VARCHAR(50),
    comments TEXT,
    last_reviewed TIMESTAMP
);

-- Documentation Table
CREATE TABLE documentation (
    doc_id SERIAL PRIMARY KEY,
    control_id INT REFERENCES controls(control_id) ON DELETE CASCADE,
    system_id INT REFERENCES systems(system_id) ON DELETE CASCADE,
    document_link VARCHAR(255) NOT NULL,
    version INT DEFAULT 1,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

# Command line
# \l - list databases
# \c compliance_tracker; - connect to database
# \dt - describe tables
# \q - quit
