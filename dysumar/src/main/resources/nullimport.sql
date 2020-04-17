use dysumar;
INSERT INTO users (apellidos,nombre,username, password, enabled,telefono) VALUES('Carlos','Hernandez','andres', '$2a$10$pXSjZhKajePgUvQZplTkOuA6n4ee/wHaOF/UJWornHmRxhN3D0Cd6', 1,71208113);
INSERT INTO users (apellidos,nombre,username, password, enabled,telefono) VALUES('Vanessa','Ramirez','admin', '$2a$10$pLiT5n4R/wOZ8SyKgNvvOeRuMd07/m9QzguNQFJPfphWUB0ktQ2zS', 1,83208113);
INSERT INTO users (apellidos,nombre,username, password, enabled,telefono) VALUES('Juan','Carlos','JUAK', '$2a$10$fpZ3pU63UnhsJcAPopsz1.hBzYFe5ptoCXcP4UXkqwv8MBwdHV6x6', 1,7220853);
INSERT INTO users (apellidos,nombre,username, password, enabled,telefono) VALUES('Marlon','Perez','MP123', '$2a$10$IBncau/R54WrXROHE/QEnObji7HOoH5TjrLQSzuJnoVsVlSF73stO', 1,7283113);

INSERT INTO roles (user_id, authority) VALUES(1, 'ROLE_USER');
INSERT INTO roles (user_id, authority) VALUES(2, 'ROLE_ADMIN');
INSERT INTO roles (user_id, authority) VALUES(3, 'ROLE_ADMIN');
INSERT INTO roles (user_id, authority) VALUES(4, 'ROLE_ADMIN');
INSERT INTO roles (user_id, authority) VALUES(4, 'ROLE_USER');

insert into tipo_cliente values (null,"privada"), (null,"publica");

insert into giro (id,detalles,nombre) values (null,"Se dedican a la explotación de recursos naturales, ya sean o no renovables, para iniciar la cadena de su transformación en productos elaborados de distinto tipo","Extractivas");
	
insert into giro (id,detalles,nombre) values (null,"Manufactureras", "Encargadas de la transformación de la materia prima en bienes elaborados o semielaborados, que o bien se dirigen al consumidor final, o a otras industrias, por lo que operan como instancias intermedias de la cadena");

INSERT INTO clientes (nombre, apellido, email, create_at, foto,dui,codigo,giro_id,tipo_cliente_id) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01', '',"01-2324123","44123",1,1);
INSERT INTO clientes (nombre, apellido, email, create_at, foto,dui,codigo,giro_id,tipo_cliente_id) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '',"01-2333098", "65123",1,1);
INSERT INTO clientes (nombre, apellido, email, create_at, foto,dui,codigo,giro_id,tipo_cliente_id) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '',"99-012398","66412",1,1);
INSERT INTO clientes (nombre, apellido, email, create_at, foto,dui,codigo,giro_id,tipo_cliente_id) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '',"12-012356","86012",1,1);

insert into proveedor values (null,"12-34193401","Carretera Puerto La Libertad Km 11","info@facela.com","0123333","Facela","Grupo Fernández","71238312",1), (null,"42-12396681","Pasaje Bustamante, No. 114, Col La Rábida,San Salvador, El Salvador, C.","info@mongol.com","91238331","Mongol","Mongol Asociados","7842 3138",1);

insert into margenes (id,detalles,porcentaje) values (null,"Debido a la demanda de este producto en 2020 el margen subio a 50",25.5);
insert into marca values (null,"Generico"), (null,"Facela");
insert into presentacion values (null,"Mililitros","ml"),(null,"Unidad","un"), (null,"Litros","ltr");
insert into categorias values (null,"Offina"),(null,"Herramientas");

insert into productos values (null,"LAP123","2019-02-02", "Lapiz Graduado",2.50,120,1,1,1,1,1), (null, "LAP124", "2019-02-02", "Lapiz Graduado",2.70,0,1,1,1,1,2);
insert into productos values (null,"LAP126","2019-02-02", "Lapicero Azul",1.50,0,1,1,1,1,1), (null, "LAP127","2019-02-02", "Lapicero negro",1.70,0,1,1,1,1,1);
insert into descuentos values (null,1000,45,1), (null,800,30,1);
insert into direcciones values (null,"Boulevar Constitucion, calle zacamil, casa #2"),(null,"Redoldel Masferrer, calle los santos, pasaje 12, casa #5");

insert into cliente_direcciones values (null,1,1),(null,1,2);
insert into inventario values (null,"02213","2019-03-03",20,1);
insert into inventario values(null,"02255","2019-02-03",100,1);
insert into cdepago values (1,"Condicion 1"), (2,"condicion 2"), (3, "condicion 3");	
insert into formasdepago values (1,"forma 1"), (2,"forma 2"), (3, "forma 3");	
insert into tipo_factura values (1,"Credito fiscal"),(2,"Factura");
insert into cotizacion values (1,"2020-04-17");
insert into carrito_items values(1,300,"536xxx",1,1,1), (2,500,"972xxx",1,4,1);	
insert into facturacion values (1,"Carlos Alberto","un detalle",1,1,1,1,1);