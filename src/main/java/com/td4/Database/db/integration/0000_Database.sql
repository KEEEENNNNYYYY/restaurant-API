/*
    connection to the database
    (this isn't mean't to be there but i just can't remember things i named)

psql -U gastroowner -d grastropizza;

spring.datasource.url=jdbc:postgresql://localhost:5432/grastropizza
spring.datasource.username=postgres
spring.datasource.password=0000
spring.datasource.driver-class-name=org.postgresql.Driver
*/
CREATE DATABASE grastropizza;

do
$$
    begin
        if not exists (select from pg_type where typename = unit)then
            CREATE TYPE unit AS ENUM ('G', 'L', 'U');
        end if;
    end
$$