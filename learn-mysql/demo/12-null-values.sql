-- What is a NULL Value?
-- A field with a NULL value is a field with no value.
-- If a field in a table is optional, it is possible to insert a new record or update a record without adding a value to this field.
-- Then, the field will be saved with a NULL value.
-- Note: A NULL value is different from a zero value or a field that contains spaces. A field with a NULL value is one that has been left blank during record creation!
/*
IS NULL Syntax:
SELECT column_names
FROM table_name
WHERE column_name IS NULL;
*/
SELECT
    customer_name,
    contact_name,
    address
FROM customers
WHERE
    address IS NULL;

/*
IS NOT NULL Syntax:
SELECT column_names
FROM table_name
WHERE column_name IS NOT NULL;
*/
SELECT
    customer_name,
    contact_name,
    address
FROM customers
WHERE
    address IS NOT NULL;