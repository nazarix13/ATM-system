CREATE USER 'bank_user'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON *.* TO 'bank_user'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;

create database atmsystem;

use atmsystem;

CREATE TABLE signup (
    form_number VARCHAR(20) PRIMARY KEY,
    full_name VARCHAR(50),
    date_of_birth VARCHAR(20),
    gender ENUM('Male', 'Female', 'Other'),
    email VARCHAR(50) UNIQUE,
    phone_number VARCHAR(30) UNIQUE,
    marital_status ENUM('Single', 'Married', 'Divorced', 'Widowed'),
    address VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    zip VARCHAR(10),
    ssn VARCHAR(20) UNIQUE,
    drivers_license_or_stateid VARCHAR(20) UNIQUE,
    employer_name VARCHAR(50),
    job_title VARCHAR(50),
    years_at_job INT CHECK (years_at_job >= 0),
    income VARCHAR(20),
    us_citizen VARCHAR(10),
    existing_account VARCHAR(10),
    beneficiary_name VARCHAR(50),
    beneficiary_contact_number VARCHAR(20),
    account_type VARCHAR(50),
    card_number VARCHAR(25) UNIQUE,
    pin_number CHAR(4) UNIQUE CHECK (pin_number REGEXP '^[0-9]{4}$'),
    facility VARCHAR(255)
);

create table transactions(
	card_number varchar(25), 
    date varchar(50), 
    type varchar(20), 
    amount varchar(20)
);





