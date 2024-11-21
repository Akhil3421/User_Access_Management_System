CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) CHECK (role IN ('Employee', 'Manager', 'Admin')) NOT NULL
);

CREATE TABLE software (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    access_levels VARCHAR(50) -- Comma-separated values: Read, Write, Admin
);

CREATE TABLE requests (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    software_id INT REFERENCES software(id),
    access_type VARCHAR(20) CHECK (access_type IN ('Read', 'Write', 'Admin')) NOT NULL,
    reason TEXT NOT NULL,
    status VARCHAR(20) CHECK (status IN ('Pending', 'Approved', 'Rejected')) DEFAULT 'Pending'
);
