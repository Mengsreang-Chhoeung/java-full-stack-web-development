-- The INSERT INTO statement is used to insert new records in a table.
/*
Syntax:
1. Specify both the column names and the values to be inserted:
INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);

2. If you are adding values for all the columns of the table, you do not need to specify the column names in the SQL query. However, make sure the order of the values is in the same order as the columns in the table. Here, the INSERT INTO syntax would be as follows:
INSERT INTO table_name
VALUES (value1, value2, value3, ...);
*/
INSERT INTO
    customers (
        customer_id,
        customer_name,
        contact_name,
        address,
        city,
        postal_code,
        country
    )
VALUES (
        1,
        'Kok Koko',
        'Kok Koko',
        'Wat Phnom',
        'Phnom Penh',
        '120101',
        'Cambodia'
    );

-- Insert data only in specified columns
INSERT INTO
    customers (
        customer_id,
        customer_name,
        city,
        country
    )
VALUES (
        2,
        'Kok Tola',
        'Phnom Penh',
        'Cambodia'
    );