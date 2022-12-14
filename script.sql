DROP DATABASE IF EXISTS oftalmologia WITH(FORCE);
CREATE DATABASE oftalmologia;

\c oftalmologia

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

insert into CLIENTE (NIF, NOMBRE, APELLIDOS, EDAD) values ('99134078Y', 'Illya', 'Roih', 58);
insert into CLIENTE (NIF, NOMBRE, APELLIDOS, EDAD) values ('03988779D', 'José', 'Sáric', 15);
insert into CLIENTE (NIF, NOMBRE, APELLIDOS, EDAD) values ('68600590G', 'Javier', 'Joluq', 30);
insert into CLIENTE (NIF, NOMBRE, APELLIDOS, EDAD) values ('10087780A', 'Lucía', 'Gumol', 79);


insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('99134078Y', '2022-11-29', 1.64, 0.59, -3.94, 52, -1.88, 1.8, -4.79, 76);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('99134078Y', '2022-11-05', -10.05, 0.22, -3.99, 67, 8.09, 0.3, 2.23, 82);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('99134078Y', '2022-10-13', 12.5, -1.05, 1.46, 64, -18.03, -1.28, 4.6, 91);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('99134078Y', '2022-10-07', 19.2, -0.13, -1.59, 99, -12.61, -0.57, -0.56, 69);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('03988779D', '2022-11-12', 15.54, 1.29, 3.0, 67, 16.05, -1.58, -1.94, 52);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('03988779D', '2022-10-12', 10.55, -0.22, -2.07, 83, 14.73, -0.5, -1.32, 69);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('03988779D', '2022-10-13', -13.42, -1.14, 0.52, 70, -0.45, -0.03, 4.6, 65);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('68600590G', '2022-11-23', 17.1, 0.87, -3.97, 74, 15.68, -0.75, -0.83, 86);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('68600590G', '2022-09-18', -18.97, 0.88, -1.86, 74, 3.63, 0.23, 4.14, 87);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('68600590G', '2022-09-26', -9.26, 0.77, -4.25, 89, 18.2, -0.45, 2.02, 89);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('68600590G', '2022-11-01', 8.78, -0.99, -4.7, 92, 11.93, 0.28, 0.96, 98);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('10087780A', '2022-09-16', -7.27, -0.79, 1.6, 60, 3.62, -1.7, 0.77, 91);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('10087780A', '2022-10-05', 3.03, 1.5, 3.2, 72, -13.41, -1.29, -3.63, 82);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('10087780A', '2022-11-29', 16.3, 0.29, 0.14, 90, -10.96, -1.7, -2.95, 50);
insert into RECETA (nif, consulta, od_esfera, od_cilindro, od_adicion, od_agudeza, oi_esfera, oi_cilindro, oi_adicion, oi_agudeza) values ('10087780A', '2022-12-04', -10.95, -1.14, -0.64, 92, -4.7, -0.86, -1.49, 70);

