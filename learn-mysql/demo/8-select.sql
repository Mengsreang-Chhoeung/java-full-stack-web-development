-- The SELECT statement is used to select data from a database.
-- The data returned is stored in a result table, called the result-set.
/*
Syntax:
SELECT column1, column2, ...
FROM table_name;
*/
-- Select all the columns
SELECT * FROM customers;

-- Select the specific columns
SELECT customer_name, city, country FROM customers;

-- The SELECT DISTINCT statement is used to return only distinct (different) values.
-- Inside a table, a column often contains many duplicate values; and sometimes you only want to list the different (distinct) values.
/*
Syntax:
SELECT DISTINCT column1, column2, ...
FROM table_name;
*/
SELECT DISTINCT country FROM customers;

-- The following SQL statement counts and returns the number of different (distinct) countries in the "customers" table:
SELECT COUNT(DISTINCT country) FROM customers;