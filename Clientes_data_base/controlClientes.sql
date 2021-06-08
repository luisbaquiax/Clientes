CREATE SCHEMA IF NOT EXISTS CLIENTES;
USE CLIENTES;


CREATE TABLE IF NOT EXISTS CLIENTE(
  ID INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  telefono VARCHAR(45) NOT NULL,
  saldo DOUBLE NOT NULL,
  PRIMARY KEY(ID)
);
