-- The MIN() function returns the smallest value of the selected column.
-- The MAX() function returns the largest value of the selected column.
/*
MIN() Syntax:
SELECT MIN(column_name)
FROM table_name
WHERE condition;
*/
SELECT MIN(price) AS smallest_price
FROM products;

/*
MAX() Syntax:
SELECT MAX(column_name)
FROM table_name
WHERE condition;
*/
SELECT MAX(price) AS largest_price
FROM products;