-- The WHERE clause can be combined with AND, OR, and NOT operators.
/*
The AND operator displays a record if all the conditions separated by AND are TRUE.
Syntax:
SELECT column1, column2, ...
FROM table_name
WHERE condition1 AND condition2 AND condition3 ...;
*/
SELECT *
FROM customers
WHERE
    country = 'Cambodia'
    AND City = 'Phnom Penh';

/*
The OR operator displays a record if any of the conditions separated by OR is TRUE.
Syntax:
SELECT column1, column2, ...
FROM table_name
WHERE condition1 OR condition2 OR condition3 ...;
*/
SELECT *
FROM customers
WHERE
    city = 'Siem Reap'
    OR city = 'Phnom Penh';

/*
The NOT operator displays a record if the condition(s) is NOT TRUE.
Syntax:
SELECT column1, column2, ...
FROM table_name
WHERE NOT condition;
*/
SELECT * FROM customers WHERE NOT country = 'China';

-- Combining AND, OR and NOT
SELECT *
FROM customers
WHERE
    country = 'Cambodia'
    AND (
        city = 'Siem Reap'
        OR city = 'Phnom Penh'
    );

SELECT *
FROM customers
WHERE
    NOT country = 'Germany'
    AND NOT country = 'USA';