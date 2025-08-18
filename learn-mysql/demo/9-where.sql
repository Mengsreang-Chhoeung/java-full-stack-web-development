-- The WHERE clause is used to filter records.
-- It is used to extract only those records that fulfill a specified condition.
/*
Syntax:
SELECT column1, column2, ...
FROM table_name
WHERE condition;
*/
-- Note: The WHERE clause is not only used in SELECT statements, it is also used in UPDATE, DELETE, etc.!
SELECT * FROM customers WHERE country = 'Cambodia';

SELECT * FROM customers WHERE customer_id = 1;