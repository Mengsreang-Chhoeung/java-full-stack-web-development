-- The LIKE operator is used in a WHERE clause to search for a specified pattern in a column.
/*
There are two wildcards often used in conjunction with the LIKE operator:
 - The percent sign (%) represents zero, one, or multiple characters
 - The underscore sign (_) represents one, single character
The percent sign and the underscore can also be used in combinations!
*/
/*
Syntax:
SELECT column1, column2, ...
FROM table_name
WHERE columnN LIKE pattern;
*/

-- The following SQL statement selects all customers with a customer_name starting with "a":
SELECT * FROM customers
WHERE customer_name LIKE 'a%';

-- The following SQL statement selects all customers with a customer_name ending with "a":
SELECT * FROM customers
WHERE customer_name LIKE '%a';

-- The following SQL statement selects all customers with a customer_name that have "or" in any position:
SELECT * FROM customers
WHERE customer_name LIKE '%or%';

-- The following SQL statement selects all customers with a customer_name that have "r" in the second position:
SELECT * FROM customers
WHERE customer_name LIKE '_r%';

-- The following SQL statement selects all customers with a customer_name that starts with "a" and are at least 3 characters in length:
SELECT * FROM customers
WHERE customer_name LIKE 'a__%';

-- The following SQL statement selects all customers with a customer_name that starts with "a" and ends with "o":
SELECT * FROM customers
WHERE contact_name LIKE 'a%o';

-- The following SQL statement selects all customers with a customer_name that does NOT start with "a":
SELECT * FROM customers
WHERE customer_name NOT LIKE 'a%';

-- The following SQL statement selects all customers with a City starting with "L", followed by any character, followed by "n", followed by any character, followed by "on":
SELECT * FROM customers
WHERE city LIKE 'L_n_on';
