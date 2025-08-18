-- The IN operator allows you to specify multiple values in a WHERE clause.
-- The IN operator is a shorthand for multiple OR conditions.
/*
Syntax:
SELECT column_name(s)
FROM table_name
WHERE column_name IN (value1, value2, ...);

or:

SELECT column_name(s)
FROM table_name
WHERE column_name IN (SELECT STATEMENT);
*/

-- The following SQL statement selects all customers that are located in "Cambodia", "France" or "UK":
SELECT * FROM customers
WHERE country IN ('Cambodia', 'France', 'UK');

-- The following SQL statement selects all customers that are NOT located in "Cambodia", "France" or "UK":
SELECT * FROM customers
WHERE country NOT IN ('Germany', 'France', 'UK');

-- The following SQL statement selects all customers that are from the same countries as the suppliers:
SELECT * FROM customers
WHERE country IN (SELECT country FROM suppliers);
