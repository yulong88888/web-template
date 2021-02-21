```shell
docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d --name testdb mysql 
```

datasource

utf8mb4_general_ci

create table student
(
id       int auto_increment
primary key,
username varchar(255)                        null,
password varchar(255)                        null,
birthday timestamp                           null,
updatee  timestamp default CURRENT_TIMESTAMP null,
avatar   mediumtext                          null
);

