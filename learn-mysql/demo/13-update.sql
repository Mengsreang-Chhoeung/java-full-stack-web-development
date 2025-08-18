-- The UPDATE statement is used to modify the existing records in a table.
/*
Syntax:
UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;
*/
-- Note: Be careful when updating records in a table! Notice the WHERE clause in the UPDATE statement.
-- The WHERE clause specifies which record(s) that should be updated. If you omit the WHERE clause, all records in the table will be updated!
UPDATE customers
SET contact_name = 'Kok Dara', city = 'Siem Reap'
WHERE customer_id = 1;

-- UPDATE multiple records
UPDATE customers
SET postal_code = '00000'
WHERE country = 'Cambodia';

-- Be careful when updating records. If you omit the WHERE clause, ALL records will be updated!
UPDATE customers
SET postal_code = '00001';