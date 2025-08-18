-- The ORDER BY keyword is used to sort the result-set in ascending or descending order.
-- The ORDER BY keyword sorts the records in ascending order by default. To sort the records in descending order, use the DESC keyword.
/*
Syntax:
SELECT column1, column2, ...
FROM table_name
ORDER BY column1, column2, ... ASC|DESC;
*/
SELECT * FROM customers ORDER BY country;

SELECT * FROM customers ORDER BY country DESC;

-- ORDER BY several columns
SELECT * FROM customers ORDER BY country, customer_name;

SELECT * FROM customers ORDER BY country ASC, customer_name DESC;