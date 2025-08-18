-- Runs an interactive (-it) bash shell inside the running Docker container named learn-mysql-db.
-- This lets you “enter” the container and work inside it.
---> "docker exec learn-mysql-db -it bash"

-- Starts the MySQL client, logging in as the user root.
-- The -p flag tells it to prompt you for the root user’s password.
---> "mysql -u root -p"