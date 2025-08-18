/*
Aliases are used to give a table, or a column in a table, a temporary name.
Aliases are often used to make column names more readable.
An alias only exists for the duration of that query.
An alias is created with the AS keyword.
*/

/*
Alias Column Syntax:
SELECT column_name AS alias_name
FROM table_name;
*/
-- The following SQL statement creates two aliases, one for the customer_id column and one for the customer_name column:
SELECT customer_id AS ID, customer_name AS Customer
FROM customers;

-- The following SQL statement creates two aliases, one for the customer_name column and one for the contact_name column. 
-- Note: Single or double quotation marks are required if the alias name contains spaces:
SELECT customer_name AS Customer, contact_name AS "Contact Person"
FROM customers;

-- The following SQL statement creates an alias named "Address" that combine four columns (address, postal_code, city and country):
SELECT customer_name, CONCAT_WS(', ', address, postal_code, city, country) AS Address
FROM customers;

/*
Alias Table Syntax:
SELECT column_name(s)
FROM table_name AS alias_name;
*/
-- The following SQL statement selects all the orders from the customer with customer_id=4 (Around the Horn). 
-- We use the "customers" and "orders" tables, and give them the table aliases of "c" and "o" respectively (Here we use aliases to make the SQL shorter):
SELECT o.order_id, o.order_date, c.customer_name
FROM customers AS c, orders AS o
WHERE c.customer_name='Around the Horn' AND c.customer_id=o.customer_id;

-- The following SQL statement is the same as above, but without aliases:
SELECT orders.order_id, orders.order_date, customers.customer_name
FROM customers, orders
WHERE customers.customer_name='Around the Horn' AND customers.customer_id=orders.customer_id;