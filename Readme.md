# skill management Project
This project is to manage Employees and skills.

## Building

There is one Applications

1. skillmanagement-api

To build all projects/Applications run the following from the root of the project:

    mvn clean install

Output will be as follows:

1. skillmanagement-api

         root/skillmanagement-api/target/skillmanagement-api.jar

---

## Running the Project locally

### Creating Local Environment

---

#### Install MySql

1. Make sure you have docker installed

        brew install --cask docker

2. Pull latest MySql Docker image

        docker pull mysql

3. Start the container

        docker run --name skillmanagement-api -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql


---

#### Configure the MySql Database.
Reference: https://spring.io/guides/gs/accessing-data-mysql/

1. Connect to MySql

        docker exec -it skillmanagement-api mysql -u root -p

2. Create the database

        mysql> create database skillmanagement
        Query OK, 1 row affected (0.01 sec)

3. Create the user for Spring to use

        mysql> create user 'springuser'@'%' identified by 'password';
        Query OK, 0 rows affected (0.02 sec)

4. Grant needed privileges

        mysql> grant all on skillmanagement.* to 'springuser'@'%';
        Query OK, 0 rows affected (0.01 sec)

5. Exit

        mysql> exit

---

### Running skillmanagement-api locally using the following Maven goals:

One can simply use the sprig run/debug configuration or follow the bellow items:

Basic
1. skillmanagement-api
    1. Make sure both the mysql database running locally
    2. mvn -pl skillmanagement-api -am spring-boot:run -D spring-boot.run.profiles=local-api

---
## API Docs
http://localhost:8080/v2/api-docs
Swagger UI: http://localhost:8080/swagger-ui.html

---

