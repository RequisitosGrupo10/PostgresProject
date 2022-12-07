Ebean + Java + PostgresSQL
=====================

En esta práctica se resolverá el problema de una clínica de oftalmología con tecnologías de bases de datos como Ebean ORM.

Lo primero necesario será descargar la imagen de docker de Postgres y correrla:

```
docker pull postgres
```
```
docker run --name postgres -e POSTGRES_PASSWORD=1111 -p 5432:5432 -d postgres
```
A continuación, podremos iniciar desde el terminal el servicio de postgres con
```
psql -U postgres -W
```
Finalmente, será necesaria la inicialización de la base de datos
```
\l # Listar bases de datos
create database oftalmologia;
\c oftalmologia  

```
Script:
```
CREATE TABLE cliente (
    nif       VARCHAR(50) NOT NULL,
    nombre    VARCHAR(250) NOT NULL,
    apellidos VARCHAR(250) NOT NULL,
    edad      INTEGER NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( nif );

CREATE TABLE receta (
    id          INTEGER Primary Key Generated Always as Identity,
    nif         VARCHAR(50) NOT NULL,
    consulta    DATE NOT NULL,
    od_esfera   REAL NOT NULL,
    od_cilindro REAL NOT NULL,
    od_adicion  REAL NOT NULL,
    od_agudeza  REAL NOT NULL,
    oi_esfera   REAL NOT NULL,
    oi_cilindro REAL NOT NULL,
    oi_adicion  REAL NOT NULL,
    oi_agudeza  REAL NOT NULL
);

ALTER TABLE receta
    ADD CONSTRAINT receta_cliente_fk FOREIGN KEY ( nif )
        REFERENCES cliente ( nif )
            ON DELETE CASCADE;
```