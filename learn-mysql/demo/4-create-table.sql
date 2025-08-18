-- The CREATE TABLE statement is used to create a new table in a database.
/*
Syntax:
CREATE TABLE table_name (
column1 datatype,
column2 datatype,
column3 datatype,
....
);

The column parameters specify the names of the columns of the table.
The datatype parameter specifies the type of data the column can hold (e.g. varchar, integer, date, etc.).
*/
CREATE TABLE customers (
    customer_id INT,
    customer_name VARCHAR(255),
    contact_name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    postal_code VARCHAR(255),
    country VARCHAR(255)
);