-- The ALTER TABLE statement is used to add, delete, or modify columns in an existing table.
-- The ALTER TABLE statement is also used to add and drop various constraints on an existing table.

/*
ALTER TABLE - ADD Column Syntax:
ALTER TABLE table_name
ADD column_name datatype;
*/
ALTER TABLE customers ADD email VARCHAR(255);

/*
ALTER TABLE - DROP COLUMN Syntax:
ALTER TABLE table_name
DROP COLUMN column_name;
*/
ALTER TABLE customers DROP COLUMN email;

/*
ALTER TABLE - MODIFY COLUMN Syntax:
ALTER TABLE table_name
MODIFY COLUMN column_name datatype;
*/
-- Now we want to add a column named "date_of_birth" in the "customers" table.
ALTER TABLE customers ADD date_of_birth DATE;
-- Now we want to change the data type of the column named "Datedate_of_birthOfBirth" in the "customers" table.
ALTER TABLE customers MODIFY COLUMN date_of_birth YEAR;

/*
ALTER TABLE - DROP COLUMN Syntax:
ALTER TABLE table_name
DROP COLUMN column_name;
*/
ALTER TABLE customers DROP COLUMN date_of_birth;