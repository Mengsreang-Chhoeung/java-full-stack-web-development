-- The LIMIT clause is used to specify the number of records to return.
-- The LIMIT clause is useful on large tables with thousands of records. Returning a large number of records can impact performance.
/*
Syntax:
SELECT column_name(s)
FROM table_name
WHERE condition
LIMIT number;
*/
SELECT * FROM customers
LIMIT 3;

-- With OFFSET
SELECT * FROM customers
LIMIT 3 OFFSET 3;

-- ADD a WHERE CLAUSE
SELECT * FROM customers
WHERE country='Cambodia'
LIMIT 3;

-- ADD an ORDER BY CLAUSE
SELECT * FROM customers
ORDER BY country
LIMIT 3;