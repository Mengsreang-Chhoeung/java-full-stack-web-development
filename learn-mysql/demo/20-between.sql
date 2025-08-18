-- The BETWEEN operator selects values within a given range. The values can be numbers, text, or dates.
-- The BETWEEN operator is inclusive: begin and end values are included.
/*
Syntax:
SELECT column_name(s)
FROM table_name
WHERE column_name BETWEEN value1 AND value2;
*/

-- The following SQL statement selects all products with a price between 10 and 20:
SELECT * FROM products
WHERE price BETWEEN 10 AND 20;

-- To display the products outside the range of the previous example, use NOT BETWEEN:
SELECT * FROM products
WHERE price NOT BETWEEN 10 AND 20;

-- The following SQL statement selects all products with a price between 10 and 20. In addition; do not show products with a category_id of 1,2, or 3:
SELECT * FROM products
WHERE price BETWEEN 10 AND 20
AND category_id NOT IN (1,2,3);

-- The following SQL statement selects all products with a product_name between "Carnarvon Tigers" and "Mozzarella di Giovanni":
SELECT * FROM products
WHERE product_name BETWEEN 'Carnarvon Tigers' AND 'Mozzarella di Giovanni'
ORDER BY product_name;

-- The following SQL statement selects all products with a product_name between "Carnarvon Tigers" and "Chef Anton's Cajun Seasoning":
SELECT * FROM products
WHERE product_name BETWEEN "Carnarvon Tigers" AND "Chef Anton's Cajun Seasoning"
ORDER BY product_name;

-- The following SQL statement selects all products with a product_name not between "Carnarvon Tigers" and "Mozzarella di Giovanni":
SELECT * FROM products
WHERE product_name NOT BETWEEN 'Carnarvon Tigers' AND 'Mozzarella di Giovanni'
ORDER BY product_name;

-- The following SQL statement selects all orders with an order_date between '01-July-1996' and '31-July-1996':
SELECT * FROM orders
WHERE order_date BETWEEN '1996-07-01' AND '1996-07-31';