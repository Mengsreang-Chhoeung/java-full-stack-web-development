 /*
The COUNT() function returns the number of rows that matches a specified criterion.
Syntax:
SELECT COUNT(column_name)
FROM table_name
WHERE condition;
*/
-- Note: NULL values are not counted.
SELECT COUNT(product_id)
FROM products;

/*
The AVG() function returns the average value of a numeric column. 
Syntax:
SELECT AVG(column_name)
FROM table_name
WHERE condition;
*/
-- Note: NULL values are ignored.
SELECT AVG(price)
FROM products;

/*
The SUM() function returns the total sum of a numeric column. 
Syntax:
SELECT SUM(column_name)
FROM table_name
WHERE condition;
*/
-- Note: NULL values are ignored.
SELECT SUM(quantity)
FROM order_details;