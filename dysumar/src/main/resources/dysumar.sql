use dysumar;
INSERT INTO users (nombre,apellidos,username, password, enabled,telefono) VALUES('Carlos','Hernandez','andres', '$2a$10$pXSjZhKajePgUvQZplTkOuA6n4ee/wHaOF/UJWornHmRxhN3D0Cd6', 1,71208113);
INSERT INTO users (nombre,apellidos,username, password, enabled,telefono) VALUES('Christopher Elias','Maldonado Martinez','admin', '$2a$10$pLiT5n4R/wOZ8SyKgNvvOeRuMd07/m9QzguNQFJPfphWUB0ktQ2zS', 1,83208113);
INSERT INTO users (nombre,apellidos,username, password, enabled,telefono) VALUES('David Tadeo','Ramos','DATARA12', '$2a$10$fpZ3pU63UnhsJcAPopsz1.hBzYFe5ptoCXcP4UXkqwv8MBwdHV6x6', 1,7220853);
INSERT INTO users (nombre,apellidos,username, password, enabled,telefono) VALUES('Melody','','MLD123', '$2a$10$IBncau/R54WrXROHE/QEnObji7HOoH5TjrLQSzuJnoVsVlSF73stO', 1,7283113);

INSERT INTO roles (user_id, authority) VALUES(1, 'ROLE_USER');
INSERT INTO roles (user_id, authority) VALUES(2, 'ROLE_ADMIN');
INSERT INTO roles (user_id, authority) VALUES(3, 'ROLE_ADMIN');
INSERT INTO roles (user_id, authority) VALUES(4, 'ROLE_ADMIN');
INSERT INTO roles (user_id, authority) VALUES(4, 'ROLE_USER');

insert into tipo_cliente values (null,"privada"), (null,"publica");

    
insert into categoria_giro values (null,"AGRICULTURA, GANADERÍA, SILVICULTURA Y PESCA"), (null,"EXPLOTACIÓN DE MINAS Y CANTERAS"), (null,"INDUSTRIAS MANUFACTURERAS"),(null,"ELABORACIÓN DE BEBIDAS"),(null,"ELABORACIÓN DE PRODUCTOS DE TABACO"), (null,"FABRICACIÓN DE PRODUCTOS TEXTILES"),(null,"FABRICACIÓN DE PRENDAS DE VESTIR"),(null,"FABRICACIÓN DE CUEROS Y PRODUCTOS CONEXOS"),(null,"FABRICACIÓN DE PAPEL Y DE PRODUCTOS DE PAPEL"),(null,"IMPRESIÓN Y REPRODUCCIÓN DE GRABACIONES"),(null,"CONSTRUCCIÓN"),(null,"SUMINISTROS DE ELECTRICIDAD, GAS, VAPOR Y AIRE ACONDICIONADO");

insert into giro values (null,"Encargadas de la transformación de la materia prima en bienes elaborados o semielaborados, que o bien se dirigen al consumidor final, o a otras industrias, por lo que operan como instancias intermedias de la cadena",1);

insert into giro values (null,"Se dedican a la explotación de recursos naturales, ya sean o no renovables, para iniciar la cadena de su transformación en productos elaborados de distinto tipo",4);

INSERT INTO clientes (nombre, apellido, email, create_at,dui,codigo,giro_id,tipo_cliente_id,usuario_id) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01',"01-2324123","44123",1,1,1);
INSERT INTO clientes (nombre, apellido, email, create_at,dui,codigo,giro_id,tipo_cliente_id,usuario_id) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02',"01-2333098", "65123",1,1,4);
INSERT INTO clientes (nombre, apellido, email, create_at,dui,codigo,giro_id,tipo_cliente_id,usuario_id) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03',"99-012398","66412",1,1,4);
INSERT INTO clientes (nombre, apellido, email, create_at,dui,codigo,giro_id,tipo_cliente_id,usuario_id) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04',"12-012356","86012",1,1,4);

/* Showing results for onlyproveedores.xlsx */

/* INSERT QUERY NO: 1 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'N/D', 1, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 2 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'LIBRERÍA Y PAPELERÍA LA IBERICA SA DE CV', 2, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 3 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'NOE ALBERTO GUILLEN (NUEVA SAN SALVADOR)', 3, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 4 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'SANREY', 4, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 5 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'PAPELERA SAN REY SA DE CV', 5, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 6 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'ANRO DISTRIBUIDORA', 6, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 7 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'RAYOVAC EL SALVADOR SA DE VC', 7, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 8 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'HERCULES', 8, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 9 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'PAPELCO', 9, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 10 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'TERMOENCOGIBLES', 10, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 11 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'DISEÑO', 11, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 12 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'MYTEC', 12, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 13 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'FORMULARIOS ', 13, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 14 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'OLG', 14, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 15 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'AGELSA', 15, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 16 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'IBERPLASTIC', 16, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 17 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'COMERSAL SA DE CV', 17, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 18 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'PRICESMART', 18, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 19 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'DPG', 19, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 20 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'ROXY', 20, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 21 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'PRODINA', 21, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 22 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'CERVANTES', 22, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 23 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'HASGAL', 23, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 24 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'CHINITA', 24, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 25 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'CENTRAL', 25, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 26 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'COPLASA', 26, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 27 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'ARROCERA', 27, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 28 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'DCASA', 28, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 29 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'PURIFASA', 29, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 30 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'MERINSA', 30, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 31 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'VIDRI', 31, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 32 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'OFFICE DEPOT', 32, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);

/* INSERT QUERY NO: 33 */
INSERT INTO proveedor(nombre, ID, codigop, direccion, email, nit, razonsocial, telefono, giro_id)
VALUES
(
'CARVAJAL', 33, 'N/D', 'Sin asignar', 'sin asignar', 'Sin asignar', '', 0, 1
);



insert into margenes (id,detalles,porcentaje) values (null,"Indefinido",0.0);
insert into margenes (id,detalles,porcentaje) values (null,"Debido a la demanda de este producto en 2020 el margen subio a 50",25.5);


/* Showing results for marcas.xlsx */



/* INSERT QUERY NO: 1 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'GENERICO', 1
);

/* INSERT QUERY NO: 2 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PLEDGE', 2
);

/* INSERT QUERY NO: 3 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CRAYOLA', 3
);

/* INSERT QUERY NO: 4 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'TUCAN', 4
);

/* INSERT QUERY NO: 5 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CAVALIER', 5
);

/* INSERT QUERY NO: 6 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ARTLINE', 6
);

/* INSERT QUERY NO: 7 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MR MUSCULO ', 7
);

/* INSERT QUERY NO: 8 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'RENO', 8
);

/* INSERT QUERY NO: 9 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'FENIX', 9
);

/* INSERT QUERY NO: 10 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'AMPO', 10
);

/* INSERT QUERY NO: 11 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'GLADE', 11
);

/* INSERT QUERY NO: 12 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'STUDMARK', 12
);

/* INSERT QUERY NO: 13 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ALLIANCE', 13
);

/* INSERT QUERY NO: 14 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CLUB SELECT', 14
);

/* INSERT QUERY NO: 15 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'FOAM', 15
);

/* INSERT QUERY NO: 16 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PANASONIC', 16
);

/* INSERT QUERY NO: 17 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MAXELL', 17
);

/* INSERT QUERY NO: 18 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'RAYOVAC', 18
);

/* INSERT QUERY NO: 19 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'QUIJOTE', 19
);

/* INSERT QUERY NO: 20 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CONCEPT', 20
);

/* INSERT QUERY NO: 21 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PANDA', 21
);

/* INSERT QUERY NO: 22 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ZEUS', 22
);

/* INSERT QUERY NO: 23 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'TERMO', 23
);

/* INSERT QUERY NO: 24 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'GLAD', 24
);

/* INSERT QUERY NO: 25 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'STAEDTLER', 25
);

/* INSERT QUERY NO: 26 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PAPERMATE', 26
);

/* INSERT QUERY NO: 27 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'WEST BEND', 27
);

/* INSERT QUERY NO: 28 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MAPED', 28
);

/* INSERT QUERY NO: 29 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'STANDARD', 29
);

/* INSERT QUERY NO: 30 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CLEAR STACK ', 30
);

/* INSERT QUERY NO: 31 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CASIO', 31
);

/* INSERT QUERY NO: 32 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'EPSON', 32
);

/* INSERT QUERY NO: 33 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'KIKA', 33
);

/* INSERT QUERY NO: 34 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'FLAMINGO', 34
);

/* INSERT QUERY NO: 35 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'IBERPLASTIC', 35
);

/* INSERT QUERY NO: 36 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SCOTCH', 36
);

/* INSERT QUERY NO: 37 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'KORES', 37
);

/* INSERT QUERY NO: 38 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'QINGLING', 38
);

/* INSERT QUERY NO: 39 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SRY', 39
);

/* INSERT QUERY NO: 40 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SANI', 40
);

/* INSERT QUERY NO: 41 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SUPER CUP', 41
);

/* INSERT QUERY NO: 42 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'DIPSA', 42
);

/* INSERT QUERY NO: 43 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CLICK CORRECT', 43
);

/* INSERT QUERY NO: 44 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'NOKY', 44
);

/* INSERT QUERY NO: 45 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'A-INK', 45
);

/* INSERT QUERY NO: 46 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'DON PEPE', 46
);

/* INSERT QUERY NO: 47 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'STANLEY', 47
);

/* INSERT QUERY NO: 48 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MAE', 48
);

/* INSERT QUERY NO: 49 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'AZOR', 49
);

/* INSERT QUERY NO: 50 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MEMBER\'S SELECT', 50
);

/* INSERT QUERY NO: 51 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'LARIANSA', 51
);

/* INSERT QUERY NO: 52 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'RENDIDOR', 52
);

/* INSERT QUERY NO: 53 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'RINSO', 53
);

/* INSERT QUERY NO: 54 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SABLO', 54
);

/* INSERT QUERY NO: 55 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'AIR WICK', 55
);

/* INSERT QUERY NO: 56 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'BOSTITCH', 56
);

/* INSERT QUERY NO: 57 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ROXY', 57
);

/* INSERT QUERY NO: 58 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PENTEL', 58
);

/* INSERT QUERY NO: 59 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'BACO', 59
);

/* INSERT QUERY NO: 60 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'TRODAT', 60
);

/* INSERT QUERY NO: 61 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'POINTER', 61
);

/* INSERT QUERY NO: 62 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'AOS', 62
);

/* INSERT QUERY NO: 63 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'IRASA', 63
);

/* INSERT QUERY NO: 64 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'STARFILE', 64
);

/* INSERT QUERY NO: 65 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SCOTCH BRITE', 65
);

/* INSERT QUERY NO: 66 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'DERMACARE', 66
);

/* INSERT QUERY NO: 67 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SWEEP', 67
);

/* INSERT QUERY NO: 68 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'RAID MAX', 68
);

/* INSERT QUERY NO: 69 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ZIXX', 69
);

/* INSERT QUERY NO: 70 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'XTRA', 70
);

/* INSERT QUERY NO: 71 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'REINA', 71
);

/* INSERT QUERY NO: 72 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'OLE', 72
);

/* INSERT QUERY NO: 73 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'UNOX ', 73
);

/* INSERT QUERY NO: 74 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'OLIMPO', 74
);

/* INSERT QUERY NO: 75 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'AXION', 75
);

/* INSERT QUERY NO: 76 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ESPUMIL', 76
);

/* INSERT QUERY NO: 77 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MILDER', 77
);

/* INSERT QUERY NO: 78 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'BOLIK', 78
);

/* INSERT QUERY NO: 79 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PARKER', 79
);

/* INSERT QUERY NO: 80 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'BIC', 80
);

/* INSERT QUERY NO: 81 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PILOT', 81
);

/* INSERT QUERY NO: 82 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ZEBRA', 82
);

/* INSERT QUERY NO: 83 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MONGOL', 83
);

/* INSERT QUERY NO: 84 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'Y-PLUS', 84
);

/* INSERT QUERY NO: 85 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'KLIMPIAX', 85
);

/* INSERT QUERY NO: 86 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'TIBURON', 86
);

/* INSERT QUERY NO: 87 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'DEL MONTE', 87
);

/* INSERT QUERY NO: 88 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'NAKAJIMA', 88
);

/* INSERT QUERY NO: 89 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'TOP PLUS', 89
);

/* INSERT QUERY NO: 90 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'3M', 90
);

/* INSERT QUERY NO: 91 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'LITO', 91
);

/* INSERT QUERY NO: 92 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ECONOMICA', 92
);

/* INSERT QUERY NO: 93 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SPARTA', 93
);

/* INSERT QUERY NO: 94 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'STARBRANDT ', 94
);

/* INSERT QUERY NO: 95 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'HAMMERMILL', 95
);

/* INSERT QUERY NO: 96 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MAGNUM', 96
);

/* INSERT QUERY NO: 97 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PAPERLINE', 97
);

/* INSERT QUERY NO: 98 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'EPAPER ', 98
);

/* INSERT QUERY NO: 99 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ECOSOFT', 99
);

/* INSERT QUERY NO: 100 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SCOTT', 100
);

/* INSERT QUERY NO: 101 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CARMESSI ', 101
);

/* INSERT QUERY NO: 102 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ALAS DORADAS', 102
);

/* INSERT QUERY NO: 103 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'KLEENEX', 103
);

/* INSERT QUERY NO: 104 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ELITE', 104
);

/* INSERT QUERY NO: 105 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'BIOSOFT', 105
);

/* INSERT QUERY NO: 106 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MASTER', 106
);

/* INSERT QUERY NO: 107 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'UHU', 107
);

/* INSERT QUERY NO: 108 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ARTIS', 108
);

/* INSERT QUERY NO: 109 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'FOAM ', 109
);

/* INSERT QUERY NO: 110 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CONGA', 110
);

/* INSERT QUERY NO: 111 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'FABER-CASTELL', 111
);

/* INSERT QUERY NO: 112 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'WEX', 112
);

/* INSERT QUERY NO: 113 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PRISMACOLOR', 113
);

/* INSERT QUERY NO: 114 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'NUBE BLANCA', 114
);

/* INSERT QUERY NO: 115 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MYCLEARBAG', 115
);

/* INSERT QUERY NO: 116 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'OD', 116
);

/* INSERT QUERY NO: 117 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'CLOROX', 117
);

/* INSERT QUERY NO: 118 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ABRO', 118
);

/* INSERT QUERY NO: 119 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'MCCORMICK', 119
);

/* INSERT QUERY NO: 120 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'ARGONTECH', 120
);

/* INSERT QUERY NO: 121 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'SUPER PACK', 121
);

/* INSERT QUERY NO: 122 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'HYSTICK', 122
);

/* INSERT QUERY NO: 123 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'HP', 123
);

/* INSERT QUERY NO: 124 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'PICNIC', 124
);

/* INSERT QUERY NO: 125 */
INSERT INTO marca(nombrem, idm)
VALUES
(
'TUC ', 125
);



INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'UN ', 1, 'sin detalle'
);

/* INSERT QUERY NO: 2 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'750ML', 2, 'sin detalle'
);

/* INSERT QUERY NO: 3 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'400ML', 3, 'sin detalle'
);

/* INSERT QUERY NO: 4 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'TARRO', 4, 'sin detalle'
);

/* INSERT QUERY NO: 5 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'LB ', 5, 'sin detalle'
);

/* INSERT QUERY NO: 6 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'25PK', 6, 'sin detalle'
);

/* INSERT QUERY NO: 7 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'PQT', 7, 'sin detalle'
);

/* INSERT QUERY NO: 8 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'10PK', 8, 'sin detalle'
);

/* INSERT QUERY NO: 9 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'36PK', 9, 'sin detalle'
);

/* INSERT QUERY NO: 10 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'CJ12', 10, 'sin detalle'
);

/* INSERT QUERY NO: 11 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'CAJA', 11, 'sin detalle'
);

/* INSERT QUERY NO: 12 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'BATON (200 UN)', 12, 'sin detalle'
);

/* INSERT QUERY NO: 13 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'2PK', 13, 'sin detalle'
);

/* INSERT QUERY NO: 14 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'22LB', 14, 'sin detalle'
);

/* INSERT QUERY NO: 15 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'KG ', 15, 'sin detalle'
);

/* INSERT QUERY NO: 16 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'1KG', 16, 'sin detalle'
);

/* INSERT QUERY NO: 17 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'500G', 17, 'sin detalle'
);

/* INSERT QUERY NO: 18 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'1.5KG', 18, 'sin detalle'
);

/* INSERT QUERY NO: 19 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'140G', 19, 'sin detalle'
);

/* INSERT QUERY NO: 20 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'ROLLO 50 YRD', 20, 'sin detalle'
);

/* INSERT QUERY NO: 21 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'PAR', 21, 'sin detalle'
);

/* INSERT QUERY NO: 22 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'3PK', 22, 'sin detalle'
);

/* INSERT QUERY NO: 23 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'GAL ', 23, 'sin detalle'
);

/* INSERT QUERY NO: 24 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'425G', 24, 'sin detalle'
);

/* INSERT QUERY NO: 25 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'15.25OZ', 25, 'sin detalle'
);

/* INSERT QUERY NO: 26 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'PAQUETE', 26, 'sin detalle'
);

/* INSERT QUERY NO: 27 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'ROLLO', 27, 'sin detalle'
);

/* INSERT QUERY NO: 28 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'RESMA ', 28, 'sin detalle'
);

/* INSERT QUERY NO: 29 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'ROLLO', 29, 'sin detalle'
);

/* INSERT QUERY NO: 30 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'PK', 30, 'sin detalle'
);

/* INSERT QUERY NO: 31 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'5 BLOCK', 31, 'sin detalle'
);

/* INSERT QUERY NO: 32 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'6PK', 32, 'sin detalle'
);

/* INSERT QUERY NO: 33 */
INSERT INTO presentacion(unidad, id, detalle)
VALUES
(
'DOYPACK 500ML', 33, 'sin detalle'
);

insert into categorias values (1,"No definido");
insert into categorias values (null,"Offina"),(null,"Herramientas");
INSERT INTO PRODUCTOS VALUES
    (2,'LIMPIEZA','10002','2020-08-27 00:00:00',0.5,'0','ACEITE RENOVADOR DE MUEBLES 500ML',0,1,2,1,2,1,1),
    (3,'VITRINA','10003','2020-08-27 00:00:00',0.5,'0','ACUARELA PASTILLA',0,1,1,1,3,1,1),
    (4,'VITRINA','12065','2020-08-27 00:00:00',0.5,'0','ACUARELA PASTILLAS',1.28,1,11,1,4,1,2),
    (5,'VITRINA','10004','2020-08-27 00:00:00',0.5,'0','AGENDA 2020',9.9,1,1,1,5,1,1),
    (6,'OFICINA','460','2020-08-27 00:00:00',0.5,'0','ALMOHADILLA #2 METALICA AZUL (14.3X8.7 CM)',3.67,1,24,1,6,1,3),
    (7,'OFICINA','10005','2020-08-27 00:00:00',0.5,'0','ALMOHADILLA #2 METALICA NEGRA (14.3X8.7 CM)',3.67,1,2,1,6,1,1),
    (8,'OFICINA','10006','2020-08-27 00:00:00',0.5,'0','ALMOHADILLA ARTLINE #1 AZUL',1.63,1,46,1,6,1,4),
    (9,'OFICINA','10007','2020-08-27 00:00:00',0.5,'0','ALMOHADILLA ARTLINE #1 NEGRO',1.63,1,52,1,6,1,4),
    (10,'LIMPIEZA','10008','2020-08-27 00:00:00',0.5,'0','ANTIHONGOS',0,1,1,1,7,1,1),
    (11,'DON JUAN ','10010','2020-08-27 00:00:00',0.5,'0','ARCHIVADOR DE PALANCA CARTON T/CARTA',0,1,196,1,8,1,1),
    (12,'DON JUAN ','10009','2020-08-27 00:00:00',0.5,'0','ARCHIVADOR DE PALANCA CARTON T/CARTA',1.36,1,2,1,9,1,1),
    (13,'DON JUAN ','10012','2020-08-27 00:00:00',0.5,'0','ARCHIVADOR DE PALANCA CARTON T/OFICIO ',1.09,1,75,1,8,1,1),
    (14,'DON JUAN ','10011','2020-08-27 00:00:00',0.5,'0','ARCHIVADOR DE PALANCA CARTON T/OFICIO ',1.47,1,1,1,10,1,1),
    (15,'DON JUAN ','10013','2020-08-27 00:00:00',0.5,'0','ARCHIVADOR DE PALANCA PVC T/OFICIO',1.4,1,26,1,8,3,1),
    (16,'LIMPIEZA','10014','2020-08-27 00:00:00',0.5,'0','AROMATIZANTE AMBIENTAL 5 EN 1',0,1,37,1,11,4,1),
    (17,'VITRINA','10015','2020-08-27 00:00:00',0.5,'0','AROMATIZANTE AMBIENTAL EN GEL TARRO PARA CARRO M/GLADE',0.93,1,4,1,11,2,1),
    (18,'LIMPIEZA','10016','2020-08-27 00:00:00',0.5,'0','ATOMIZADOR MULTISUPERFICIES',0,1,3,1,2,1,1),
    (19,'LIMPIEZA','10018','2020-08-27 00:00:00',0.5,'0','ATOMIZADOR MULTIUSOS 16OZ',0,1,138,1,1,1,1),
    (20,'LIMPIEZA','10017','2020-08-27 00:00:00',0.5,'0','ATOMIZADOR MULTIUSOS 32OZ',0,1,137,1,1,5,1),
    (21,'DON JUAN ','SBHULE','2020-08-27 00:00:00',0.5,'0','BANDA DE HULE',2.29,1,50,1,12,5,1),
    (22,'DON JUAN ','64-320','2020-08-27 00:00:00',0.5,'0','BANDAS DE HULE #32 ADVANTAGE',4.45,1,27,1,13,6,5),
    (23,'LIMPIEZA','10019','2020-08-27 00:00:00',0.5,'0','BANDEJA DESECHABLE #5 CON DIVISIÓN ECO',0,1,240,1,1,6,1),
    (24,'LIMPIEZA','10020','2020-08-27 00:00:00',0.5,'0','BANDEJA DESECHABLE 8X8 ',0,1,130,1,14,6,1),
    (25,'LIMPIEZA','10021','2020-08-27 00:00:00',0.5,'0','BANDEJA DESECHABLE 9X9 CON DIVISION',0,1,50,1,15,1,1),
    (26,'OFICINA','10022','2020-08-27 00:00:00',0.5,'0','BANDERITAS PARA CARTAPACIO STICKY FLAGS  5436',0.52,1,23,1,12,1,1),
    (27,'OFICINA','10023','2020-08-27 00:00:00',0.5,'0','BANDERITAS STICKY FLAGS 5425',0.47,1,6,1,12,1,1),
    (28,'LIMPIEZA','10308','2020-08-27 00:00:00',0.5,'0','BASURERO #1',0,1,100,1,1,1,1),
    (29,'LIMPIEZA','10306','2020-08-27 00:00:00',0.5,'0','BASURERO #2 ECO',0,1,29,1,1,1,1),
    (30,'LIMPIEZA','10307','2020-08-27 00:00:00',0.5,'0','BASURERO #2 PRIMIUM',0,1,41,1,1,1,1),
    (31,'LIMPIEZA','10309','2020-08-27 00:00:00',0.5,'0','BASURERO DE PEDAL',0,1,4,1,1,1,1),
    (32,'OFICINA','AAPANA','2020-08-27 00:00:00',0.5,'0','BATERÍA AA 2PACK BLISTER',0.8,1,0,1,16,1,1),
    (33,'OFICINA','10024','2020-08-27 00:00:00',0.5,'0','BATERÍA AA 2PACK BLISTER',0.4,1,148,1,17,1,6),
    (34,'OFICINA/DON JUAN','815-10MBLA','2020-08-27 00:00:00',0.5,'0','BATERIA AA ALKALINE',0.26,1,127,1,18,1,7),
    (35,'OFICINA/DON JUAN','AAAMAX','2020-08-27 00:00:00',0.5,'0','BATERÍA AAA 2PACK BLISTER ',0.79645,1,36,1,17,1,1),
    (36,'OFICINA','824-10MBLA','2020-08-27 00:00:00',0.5,'0','BATERIA AAA ALKALINE',0.26,1,294,1,18,1,7),
    (37,'BAÑO','BEHCRA','2020-08-27 00:00:00',0.5,'0','BLOCK ESPIRAL HORIZONTAL CARTA RAYADO',0,1,65,1,19,1,2),
    (38,'DON JUAN ','10026','2020-08-27 00:00:00',0.5,'0','BOLSA CADAVER',0,1,20,1,1,1,8),
    (39,'BAÑO','PT107445','2020-08-27 00:00:00',0.5,'20','BOLSA MANILA 1/2 CARTA 6X9 (CIENTO)',1.92,1,84,1,20,1,9),
    (40,'BAÑO','PT10738','2020-08-27 00:00:00',0.5,'20','BOLSA MANILA 1/2 OFICIO 7X10 (CIENTO)',2.51,1,47,1,20,1,9),
    (41,'BAÑO','PT11907','2020-08-27 00:00:00',0.5,'20','BOLSA MANILA 10X15 EXTRA GRANDE OFICIO (CIENTO)',4.85,1,7,1,20,1,9),
    (42,'BAÑO','PT10742','2020-08-27 00:00:00',0.5,'20','BOLSA MANILA 11.5X15 EXTRA OFICIO JUMBO',5.53,1,55,1,1,1,9),
    (43,'BAÑO','PT14502','2020-08-27 00:00:00',0.5,'20','BOLSA MANILA 12X15 EXTRA JUMBO (CIENTO)',5.5,1,10,1,20,1,9),
    (44,'BAÑO','PT11903','2020-08-27 00:00:00',0.5,'20','BOLSA MANILA CARTA 9X12 (CIENTO)',3.66,1,45,1,20,1,9),
    (45,'BAÑO','PT11905','2020-08-27 00:00:00',0.5,'20','BOLSA MANILA OFICIO 10X13 (CIENTO)',4.31,1,70,1,20,7,9),
    (46,'LIMPIEZA','10025','2020-08-27 00:00:00',0.5,'0','BOLSA PLAST 24X32 ',0,1,100,1,1,8,1),
    (47,'LIMPIEZA','10027','2020-08-27 00:00:00',0.5,'0','BOLSA PLAST 24X32 NEGRA',0,1,6,1,21,8,1),
    (48,'LIMPIEZA','10028','2020-08-27 00:00:00',0.5,'0','BOLSA PLAST 24X32 ROJA',0,1,350,1,1,8,1),
    (49,'LIMPIEZA','10029','2020-08-27 00:00:00',0.5,'0','BOLSA PLAST 34X52 NEGRA',0,1,6,1,21,8,1),
    (50,'LIMPIEZA','10030','2020-08-27 00:00:00',0.5,'0','BOLSA PLAST 34X52 NEGRA',0,1,3,1,22,1,1),
    (51,'DON JUAN ','10031','2020-08-27 00:00:00',0.5,'0','BOLSA TRANSPARENTE 9X14 (5 LIBRAS)',0.0065752,1,10000,1,23,1,10),
    (52,'ALTAVISTA','TS34X52','2020-08-27 00:00:00',0.5,'500','BOLSA TSTB TERMO 34X52 BASURA NEGRA 1.3 ZEUS X$56.78',56.78,1,0,1,22,9,10),
    (53,'LIMPIEZA','10032','2020-08-27 00:00:00',0.5,'0','BOLSAS CON CIERRE 3.78LB',0,1,6,1,24,1,1),
    (54,'VITRINA','BESCOB','2020-08-27 00:00:00',0.5,'0','BORRADOR DE ESCOBILLA MARS RASOR LAPIZ',0.719,1,28,1,25,1,1),
    (55,'VITRINA','10033','2020-08-27 00:00:00',0.5,'0','BORRADOR ER007',0.056,1,77,1,12,1,1),
    (56,'VITRINA','10034','2020-08-27 00:00:00',0.5,'0','BORRADOR ER008',0.08,1,1,1,12,1,1),
    (57,'OFICINA','10035','2020-08-27 00:00:00',0.5,'0','BORRADOR MAGNETICO GRANDE',0.9,1,8,1,6,1,4),
    (58,'VITRINA','DBLRST','2020-08-27 00:00:00',0.5,'0','BORRADOR RASOPLAST 526 B20',0.345,1,219,1,25,1,11),
    (59,'VITRINA','10036','2020-08-27 00:00:00',0.5,'0','BORRADOR TIPO PLUMA MARS PLASTIC BARRA',1.1216,1,59,1,25,1,1),
    (60,'VITRINA','10037','2020-08-27 00:00:00',0.5,'0','BORRADOR TUFF STUFF INKJOY',0.25,1,42,1,26,1,1),
    (61,'OFICINA','10038','2020-08-27 00:00:00',0.5,'0','CAFETERA ELECTRICA DE PICHEL 9 TAZAS ',0,1,1,1,27,1,12),
    (62,'OFICINA','10039','2020-08-27 00:00:00',0.5,'0','CAJA DE COLORES  36 PCS ',3,1,36,1,26,1,1),
    (63,'OFICINA','10040','2020-08-27 00:00:00',0.5,'0','CAJA DE COLORES 12 PCS ',1,1,12,1,26,1,1),
    (64,'OFICINA','10042','2020-08-27 00:00:00',0.5,'0','CAJA DE COLORES 24 PCS ',0,1,2,1,28,1,1),
    (65,'OFICINA','10041','2020-08-27 00:00:00',0.5,'0','CAJA DE COLORES 24 PCS ',2,1,22,1,26,1,1),
    (66,'BAÑO','TEMPCPC','2020-08-27 00:00:00',0.5,'3','CAJA DE PAPEL CONTINUO 9 1/2"X11"',5.25,1,1,1,29,1,13),
    (67,'LIMPIEZA','10043','2020-08-27 00:00:00',0.5,'0','CAJA PLASTICA 42LB',0,1,3,1,30,1,1),
    (68,'OFICINA','10044','2020-08-27 00:00:00',0.5,'0','CALCULADORA DC-379 (12 DIGITOS)',3.9,1,13,1,12,1,14),
    (69,'OFICINA','CMX128','2020-08-27 00:00:00',0.5,'0','CALCULADORA DE MESA 12DIG. MX-12B-BK',4.05,1,31,1,31,1,15),
    (70,'OFICINA','CFX82N','2020-08-27 00:00:00',0.5,'0','CALCULADORA FX-82NS',7.05,1,32,1,31,1,15),
    (71,'OFICINA','CMX120','2020-08-27 00:00:00',0.5,'0','CALCULADORA MX-120 B-W',4,1,0,1,31,1,1),
    (72,'BAÑO','CCCART','2020-08-27 00:00:00',0.5,'0','CARPETA COLGANTE CARTA',0,1,75,1,10,1,2),
    (73,'BAÑO','CCOFIC','2020-08-27 00:00:00',0.5,'0','CARPETA COLGANTE OFICIO',0,1,125,1,10,1,2),
    (74,'DON JUAN ','10045','2020-08-27 00:00:00',0.5,'0','CARTAPACIO CARTA DE 1.0"',1.81,1,4,1,12,1,1),
    (75,'DON JUAN ','10046','2020-08-27 00:00:00',0.5,'0','CARTAPACIO CARTA DE 1.5"',2.25,1,6,1,12,1,1),
    (76,'DON JUAN ','10047','2020-08-27 00:00:00',0.5,'0','CARTAPACIO CARTA DE 2.0"',2.39,1,6,1,12,1,1),
    (77,'DON JUAN ','10048','2020-08-27 00:00:00',0.5,'0','CARTAPACIO CARTA DE 3.0"',3.83,1,1,1,12,1,14),
    (78,'OFICINA','10049','2020-08-27 00:00:00',0.5,'0','CARTUCHO DE CINTA SO15329',0,1,4,1,32,1,1),
    (79,'LIMPIEZA','10050','2020-08-27 00:00:00',0.5,'0','CEPILLO INODORO IZOPO SIN BASE',0.6638,1,22,1,33,1,1),
    (80,'LIMPIEZA','10051','2020-08-27 00:00:00',0.5,'0','CEPILLO INODORO ROTORCIDO ',0.5753,1,28,1,34,1,1),
    (81,'LIMPIEZA','10052','2020-08-27 00:00:00',0.5,'0','CEPILLO OVALADO #2 ',0,1,42,1,35,1,1),
    (82,'ALTAVISTA','ISOPOK','2020-08-27 00:00:00',0.5,'125','CEPILLO PARA INODORO ISOPO',0.6638,1,24,1,33,1,16),
    (83,'ALTAVISTA','RETORC','2020-08-27 00:00:00',0.5,'125','CEPILLO PARA INODORO RETORCIDO',0.5753,1,72,1,33,1,16),
    (84,'LIMPIEZA','10053','2020-08-27 00:00:00',0.5,'0','CEPILLO PARA PISO LARGO',0,1,29,1,33,1,16),
    (85,'VITRINA','CMAGS','2020-08-27 00:00:00',0.5,'0','CINTA MAGICA 3/4"X36YRD 18MMX25M',1.15,1,250,1,36,1,1),
    (86,'VITRINA','10054','2020-08-27 00:00:00',0.5,'0','CINTA MAQUINA DE ESCRIBIR',0,1,28,1,37,1,1),
    (87,'VITRINA/DON JUAN','CTS500','2020-08-27 00:00:00',0.5,'0','CINTA TRANSPARENTE 3/4  500',0.274,1,304,1,36,1,1),
    (88,'DON JUAN ','CTS600','2020-08-27 00:00:00',0.5,'144','CINTA TRANSPARENTE 3/4  600',1.07,1,740,1,36,1,1),
    (89,'DON JUAN ','CST48','2020-08-27 00:00:00',0.5,'144','CINTA TRANSPARENTE 48MMX100M',1.75,1,521,1,36,10,1),
    (90,'VITRINA','10055','2020-08-27 00:00:00',0.5,'0','CLIP BINDER 19MM 3/4" CAJA (12 UND)',0.6,1,300,1,38,10,1),
    (91,'VITRINA','10056','2020-08-27 00:00:00',0.5,'0','CLIP BINDER 25MM 1"  CAJA (12UND)',1.08,1,144,1,38,10,1),
    (92,'VITRINA','10057','2020-08-27 00:00:00',0.5,'0','CLIP BINDER 25MM 1" CAJA (12 UND)',0.96,1,672,1,39,10,4),
    (93,'VITRINA','10058','2020-08-27 00:00:00',0.5,'0','CLIP BINDER 32MM 1 1/4" CAJA (12 UND)',1.2,1,288,1,38,10,1),
    (94,'VITRINA','10059','2020-08-27 00:00:00',0.5,'0','CLIP BINDER 41MM 1 5/8" CAJA (12 UND )',1.92,1,276,1,38,10,1),
    (95,'VITRINA','10060','2020-08-27 00:00:00',0.5,'0','CLIP BINDER 51MM CAJA (12UND)',2.16,1,204,1,39,11,4),
    (96,'OFICINA','10061','2020-08-27 00:00:00',0.5,'0','CLIP DE MARIPOSA #1 CAJA',0.8,1,80,1,39,11,4),
    (97,'OFICINA','10062','2020-08-27 00:00:00',0.5,'0','CLIP DE MARIPOSA #2 CAJA',0.7125,1,70,1,39,1,4),
    (98,'OFICINA','CN1SRY','2020-08-27 00:00:00',0.5,'0','CLIP NIQUELADO #1 CAJA',0.16,1,440,1,39,11,4),
    (99,'OFICINA','10063','2020-08-27 00:00:00',0.5,'0','CLIP NIQUELADO #2 JUMBO CAJA',82,1,140,1,39,12,4),
    (100,'LIMPIEZA','10064','2020-08-27 00:00:00',0.5,'0','CONO DE PAPEL ACERADO ',0,1,6,1,40,12,1),
    (101,'LIMPIEZA','10065','2020-08-27 00:00:00',0.5,'0','CONO DE PAPEL ACERADO 25X200X4.5OZ',0,1,16,1,41,12,1),
    (102,'ALTAVISTA','2081044','2020-08-27 00:00:00',0.5,'625','CONO DE PAPEL ACERADO 25X200X4OZ',1.4232,1,775,1,40,6,17),
    (103,'LIMPIEZA','10066','2020-08-27 00:00:00',0.5,'0','CONO ESPUMA 4.5OZ',0,1,2,1,42,1,1),
    (104,'VITRINA','10067','2020-08-27 00:00:00',0.5,'0','CORRECTOR',0,1,18,1,43,1,1),
    (105,'OFICINA','10068','2020-08-27 00:00:00',0.5,'0','CORRECTOR 2 EN 1',0,1,38,1,26,1,1),
    (106,'VITRINA','10069','2020-08-27 00:00:00',0.5,'0','CORRECTOR TIPO LAPICERO 7ML ',0.25,1,3,1,44,1,1),
    (107,'OFICINA','CTLPAP','2020-08-27 00:00:00',0.5,'0','CORRECTOR TIPO LAPIZ',0.8,1,2316,1,26,1,1),
    (108,'VITRINA','10070','2020-08-27 00:00:00',0.5,'0','CORRECTOR ZEND 15ML',0.34,1,104,1,45,1,1),
    (109,'VITRINA','10071','2020-08-27 00:00:00',0.5,'0','CRAYONES TRIANGULARES 12',0.38,1,22,1,44,1,1),
    (110,'DON JUAN ','10072','2020-08-27 00:00:00',0.5,'0','CUADERNO COSIDO CUADRICULADO #3',0.86,1,144,1,19,1,1),
    (111,'DON JUAN ','10073','2020-08-27 00:00:00',0.5,'0','CUADERNO COSIDO LISO #3',0.86,1,11,1,19,1,1),
    (112,'DON JUAN ','QCCR#3','2020-08-27 00:00:00',0.5,'0','CUADERNO COSIDO RAYADO #3',0.86,1,234,1,19,1,1),
    (113,'DON JUAN ','10074','2020-08-27 00:00:00',0.5,'0','CUADERNO ESPIRAL RAYADO #3',0.6637,1,49,1,19,6,1),
    (114,'LIMPIEZA','10075','2020-08-27 00:00:00',0.5,'0','CUCHARA ',0,1,17,1,46,1,1),
    (115,'OFICINA','10076','2020-08-27 00:00:00',0.5,'0','CUCHILLA MULTIUSOS DE 18MM MOD 10323',1,1,9,1,47,1,1),
    (116,'OFICINA','10077','2020-08-27 00:00:00',0.5,'0','CUCHILLA MULTIUSOS DE 9MM',0.87,1,1,1,48,1,1),
    (117,'VITRINA','10078','2020-08-27 00:00:00',0.5,'0','CUENTA FACIL 14G',0.37,1,83,1,49,1,1),
    (118,'VITRINA','110 662','2020-08-27 00:00:00',0.5,'0','CUENTA FACIL 42G',0.56,1,3,1,49,13,1),
    (119,'LIMPIEZA','10079','2020-08-27 00:00:00',0.5,'0','CUIDADO DE MUEBLE 460ML',0,1,1,1,2,14,1),
    (120,'LIMPIEZA','10081','2020-08-27 00:00:00',0.5,'0','DETERGENTE EN POLVO',0,1,24,1,50,15,18),
    (121,'LIMPIEZA','10080','2020-08-27 00:00:00',0.5,'0','DETERGENTE EN POLVO',0,1,170,1,51,16,1),
    (122,'LIMPIEZA','10083','2020-08-27 00:00:00',0.5,'0','DETERGENTE EN POLVO ',0,1,6,1,52,17,1),
    (123,'LIMPIEZA','10082','2020-08-27 00:00:00',0.5,'0','DETERGENTE EN POLVO 18/F',0,1,72,1,50,18,18),
    (124,'LIMPIEZA','10084','2020-08-27 00:00:00',0.5,'0','DETERGENTE EN POLVO 5 EN 1',0,1,23,1,53,19,1),
    (125,'LIMPIEZA','10085','2020-08-27 00:00:00',0.5,'0','DETERGENTE EN POLVO FLORES DE VERANO',0,1,20,1,53,1,1),
    (126,'OFICINA','10086','2020-08-27 00:00:00',0.5,'0','DISPENSADOR DE CINTA ECO',1,1,20,1,54,1,1),
    (127,'OFICINA','10087','2020-08-27 00:00:00',0.5,'0','DISPENSADOR DE CINTA MOD 115',1,1,27,1,54,1,1),
    (128,'OFICINA','DCSTUD','2020-08-27 00:00:00',0.5,'0','DISPENSADOR DE CINTA MOD 2213',3.77,1,7,1,12,1,1),
    (129,'LIMPIEZA','10088','2020-08-27 00:00:00',0.5,'0','DISPENSADOR PARA AROMATIZANTE AMBIENTAL',0,1,7,1,55,1,1),
    (130,'OFICINA','EBO400','2020-08-27 00:00:00',0.5,'0','ENGRAPADORA MOD 400-1/2 TIRA',4.33,1,60,1,56,1,19),
    (131,'OFICINA','10089','2020-08-27 00:00:00',0.5,'0','ENGRAPADORA MOD 440-TIRA COMPLETA',4.72,1,7,1,56,1,19),
    (132,'ALTAVISTA','1G-90-38NT HDPE','2020-08-27 00:00:00',0.5,'0','ENV. DE 3.68 L CILINDRICO 90G',0.3572,1,144,1,57,1,20),
    (133,'ALTAVISTA/LIMPIEZA','ETUCAN','2020-08-27 00:00:00',0.5,'120','ESCOBA BOLILLO BARNIZADO',0.885,1,180,1,4,1,16),
    (134,'ALTAVISTA','RIALTO','2020-08-27 00:00:00',0.5,'120','ESCOBA CERDA GRANDE MOD SUPER RIALTO',0.9825,1,96,1,34,1,16),
    (135,'LIMPIEZA','VENECI','2020-08-27 00:00:00',0.5,'0','ESCOBA MOD SUPER VENECIA',0,1,11,1,34,1,16),
    (136,'DON JUAN ','10090','2020-08-27 00:00:00',0.5,'0','ESTUCHE CELESTE (PORTAMINA, BOLIGRAFO, BORRADOR, PLUMA GEL)',1,1,15,1,58,1,1),
    (137,'DON JUAN ','10091','2020-08-27 00:00:00',0.5,'0','ESTUCHE MORADO (PORTAMINA, BOLIGRAFO, BORRADOR, PLUMA GEL) ',1,1,15,1,58,1,1),
    (138,'DON JUAN ','10092','2020-08-27 00:00:00',0.5,'0','ESTUCHE NEGRO (PORTAMINA, BOLIGRAFO, BORRADOR, PLUMA GEL) ',1,1,9,1,58,1,1),
    (139,'DON JUAN ','10093','2020-08-27 00:00:00',0.5,'0','ESTUCHE PINK (PORTAMINA, BOLIGRAFO, BORRADOR, PLUMA GEL) ',1,1,15,1,58,1,1),
    (140,'OFICINA/DON JUAN','FASSRY','2020-08-27 00:00:00',0.5,'0','FASTENER CAJA',0.72,1,520,1,39,1,1),
    (141,'VITRINA','FGUSRY','2020-08-27 00:00:00',0.5,'0','FASTENER DE GUSANO',0.25,1,59,1,39,1,4),
    (142,'OFICINA','10094','2020-08-27 00:00:00',0.5,'0','FASTENER METALICO',1.5,1,66,1,59,1,1),
    (143,'OFICINA','10095','2020-08-27 00:00:00',0.5,'0','FASTENER PLASTICO 50 PZS MOD 2105',0.8,1,62,1,12,1,3),
    (144,'VITRINA','10096','2020-08-27 00:00:00',0.5,'0','FECHADOR 1010',0,1,5,1,60,1,21),
    (145,'VITRINA','FEPO27','2020-08-27 00:00:00',0.5,'0','FECHADOR 2027',0.58,1,10,1,61,1,1),
    (146,'VITRINA','10097','2020-08-27 00:00:00',0.5,'0','FECHADOR 6301',0,1,4,1,12,1,1),
    (147,'VITRINA','10098','2020-08-27 00:00:00',0.5,'0','FECHADOR MULTIPLE',0,1,6,1,62,1,3),
    (148,'DON JUAN ','10099','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR AMARILLO T/CARTA FLASHCOLOR',0.07,1,150,1,63,1,22),
    (149,'DON JUAN ','10100','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR AQUA T/CARTA FLASHCOLOR',0.07,1,50,1,63,1,22),
    (150,'DON JUAN ','10101','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR AZUL T/CARTA FLASHCOLOR',0.07,1,150,1,63,1,22),
    (151,'DON JUAN ','10102','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR CAFE T/CARTA FLASHCOLOR',0.07,1,50,1,63,1,22),
    (152,'DON JUAN ','10103','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR CAFE T/OFICIO FLASHCOLOR',0.0884,1,100,1,63,1,22),
    (153,'DON JUAN ','10104','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR CELESTE T/CARTA FLASHCOLOR',0.07,1,50,1,63,1,22),
    (154,'DON JUAN ','10105','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR NARANJA T/CARTA FLASHCOLOR',0.07,1,100,1,63,1,22),
    (155,'DON JUAN ','10106','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR NEGRO T/CARTA FLASHCOLOR',0.07,1,50,1,63,1,22),
    (156,'DON JUAN ','10107','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR ROJO T/CARTA FLASHCOLOR',0.07,1,100,1,63,1,22),
    (157,'DON JUAN ','10108','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR ROSADO FUCSIA T/CARTA FLASHCOLOR',0.07,1,100,1,63,1,22),
    (158,'DON JUAN ','10109','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR ROSADO PASTEL T/CARTA FLASHCOLOR',0.07,1,50,1,63,1,22),
    (159,'DON JUAN ','10110','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR VERDE OLIVO T/CARTA FLASHCOLOR',0.07,1,50,1,63,1,22),
    (160,'DON JUAN ','10111','2020-08-27 00:00:00',0.5,'0','FOLDER COLOR VERDE T/CARTA FLASHCOLOR',0.07,1,100,1,63,1,22),
    (161,'VITRINA','10112','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON CARTA AMARILLO M/STARFILE',0.0755,1,19,1,64,1,1),
    (162,'VITRINA','10113','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON CARTA AZUL M/STARFILE',0.0755,1,10,1,64,1,1),
    (163,'VITRINA','10114','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON CARTA CELESTE M/STARFILE',0.0755,1,34,1,64,1,1),
    (164,'VITRINA','10115','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON CARTA MORADO M/STARFILE',0.0755,1,2,1,64,1,1),
    (165,'VITRINA','10116','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON CARTA NARANJA M/STARFILE',0.0755,1,19,1,64,1,1),
    (166,'VITRINA','10117','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON CARTA VERDE M/STARFILE',0.0755,1,34,1,64,1,1),
    (167,'VITRINA','10118','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON OFICIO AMARILLO M/STARFILE',0.0884,1,16,1,64,1,1),
    (168,'VITRINA','10119','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON OFICIO AZUL M/STARFILE',0.0884,1,16,1,64,1,1),
    (169,'VITRINA','10120','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON OFICIO CELESTE M/STARFILE',0.0884,1,20,1,64,1,1),
    (170,'VITRINA','10121','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON OFICIO MORADO M/STARFILE',0.0884,1,20,1,64,1,1),
    (171,'VITRINA','10122','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON OFICIO NARANJA M/STARFILE',0.0884,1,18,1,64,1,1),
    (172,'VITRINA','10123','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON OFICIO NEGRO M/STARFILE',0.0884,1,22,1,64,1,1),
    (173,'VITRINA','10124','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON OFICIO ROSADO M/STARFILE',0.0884,1,19,1,64,1,1),
    (174,'VITRINA','10125','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON OFICIO ROSADO PALIDO M/STARFILE',0.0884,1,20,1,64,1,1),
    (175,'VITRINA','10126','2020-08-27 00:00:00',0.5,'0','FOLDER DE CARTON OFICIO VERDE M/STARFILE',0.0884,1,14,1,64,1,1),
    (176,'BAÑO','TEMPPFC','2020-08-27 00:00:00',0.5,'20','FOLDER DE COLORES T/OFICIO M/STARFILE PQT DE 25',0.0884,1,375,1,64,1,1),
    (177,'BAÑO','PT18118','2020-08-27 00:00:00',0.5,'20','FOLDER MANILA 8.5X11 (CIENTO)',2.87,1,59,1,1,1,9),
    (178,'BAÑO','PT18131','2020-08-27 00:00:00',0.5,'20','FOLDER MANILA 8.5X13 (CIENTO)',3.47,1,85,1,1,1,9),
    (179,'VITRINA','10127','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO CARTA AMARILLO M/STUDMARK',0.25,1,6,1,12,1,1),
    (180,'VITRINA','10128','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO CARTA AZUL M/STUDMARK',0.25,1,5,1,12,1,1),
    (181,'VITRINA','10129','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO CARTA NEGRO M/STUDMARK',0.25,1,6,1,12,1,1),
    (182,'VITRINA','10130','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO CARTA ROJO M/STUDMARK',0.25,1,6,1,12,1,1),
    (183,'VITRINA','10131','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO CARTA VERDE M/STUDMARK',0.25,1,4,1,12,1,1),
    (184,'VITRINA','10132','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO OFICIO AMARILLO M/STUDMARK',0.28,1,2,1,12,1,1),
    (185,'VITRINA','10133','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO OFICIO AZUL M/STUDMARK',0.28,1,3,1,12,1,1),
    (186,'VITRINA','10134','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO OFICIO AZUL OSCURO M/STUDMARK',0.28,1,2,1,12,1,1),
    (187,'VITRINA','10135','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO OFICIO NEGRO M/STUDMARK',0.28,1,2,1,12,1,1),
    (188,'VITRINA','10136','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO OFICIO ROJO M/STUDMARK',0.28,1,12,1,12,1,1),
    (189,'VITRINA','10137','2020-08-27 00:00:00',0.5,'0','FOLDER PLASTICO OFICIO VERDE M/STUDMARK',0.28,1,3,1,12,1,1),
    (190,'BAÑO','TEPMFPC','2020-08-27 00:00:00',0.5,'10','FOLDER PLASTIFICADO T/CARTA STUDMARK COLORES (NEGRO, ROJO, AMARILLO, AZUL)',0.23,1,204,1,12,1,1),
    (191,'BAÑO','TEPMFPO','2020-08-27 00:00:00',0.5,'10','FOLDER PLASTIFICADO T/OFICIO STUDMARK COLORES (NEGRO, ROJO, AMARILLO, AZUL)',0.28,1,125,1,12,1,1),
    (192,'BAÑO','TEMPFPOR','2020-08-27 00:00:00',0.5,'10','FOLDER PLASTIFICADO T/OFICIO STUDMARK ROJO',0.28,1,84,1,12,20,1),
    (193,'LIMPIEZA','10310','2020-08-27 00:00:00',0.5,'0','FRANELA VARIEDAD DE COLORES ',0,1,4,1,1,1,23),
    (194,'DON JUAN/OFICINA','GRABOS','2020-08-27 00:00:00',0.5,'0','GRAPA M/BOSTITCH STANDARD CAJA',0.84,1,290,1,56,1,1),
    (195,'DON JUAN/OFICINA','GRASRY','2020-08-27 00:00:00',0.5,'0','GRAPA M/SRY CAJA',0.56,1,265,1,39,21,1),
    (196,'LIMPIEZA','10312','2020-08-27 00:00:00',0.5,'0','GUANTE AMARILLO T/L',0,1,4,1,65,21,1),
    (197,'LIMPIEZA','10314','2020-08-27 00:00:00',0.5,'0','GUANTE AMARILLO T/M',0,1,4,1,66,21,1),
    (198,'LIMPIEZA','10313','2020-08-27 00:00:00',0.5,'0','GUANTE AMARILLO T/M',0,1,14,1,65,21,1),
    (199,'LIMPIEZA','10315','2020-08-27 00:00:00',0.5,'0','GUANTE AMARILLO T/S',0,1,13,1,66,1,1),
    (200,'LIMPIEZA','10311','2020-08-27 00:00:00',0.5,'0','HALA AGUA DE 45CM ',0,1,6,1,67,3,1),
    (201,'LIMPIEZA','10138','2020-08-27 00:00:00',0.5,'0','INSECTICIDA MATA CUCARACHAS',0,1,65,1,68,22,1),
    (202,'LIMPIEZA','10139','2020-08-27 00:00:00',0.5,'0','JABON',0,1,8,1,69,22,1),
    (203,'LIMPIEZA','10140','2020-08-27 00:00:00',0.5,'0','JABON  ',0,1,22,1,70,22,1),
    (204,'LIMPIEZA','10141','2020-08-27 00:00:00',0.5,'0','JABON DE BOLA',0,1,40,1,71,1,1),
    (205,'DON JUAN/VITRINA','10142','2020-08-27 00:00:00',0.5,'0','JABON DE TOCADOR 19G',0,1,589,1,72,1,1),
    (206,'LIMPIEZA','10143','2020-08-27 00:00:00',0.5,'0','JABON EN BARRA MULTIUSOS ',0,1,7,1,73,23,1),
    (207,'LIMPIEZA','10146','2020-08-27 00:00:00',0.5,'0','JABON GEL',0,1,49,1,74,1,1),
    (208,'LIMPIEZA','319109','2020-08-27 00:00:00',0.5,'0','JABON LAVAPLATOS',5.83,1,0,1,75,1,1),
    (209,'LIMPIEZA','10144','2020-08-27 00:00:00',0.5,'0','JABON LAVAPLATOS 810G',0,1,6,1,76,23,1),
    (210,'LIMPIEZA','10145','2020-08-27 00:00:00',0.5,'0','JABON LIQUIDO',0,1,4,1,77,1,1),
    (211,'LIMPIEZA','10147','2020-08-27 00:00:00',0.5,'0','JABON LIQUIDO LAVAPLATOS 2.65LB',0,1,1,1,50,1,1),
    (212,'VITRINA','10165','2020-08-27 00:00:00',0.5,'0','LAPICERO  KILOMETRICO CON INKJOY 100 1.0 M  ROJO',0.25,1,154,1,26,1,1),
    (213,'VITRINA','10150','2020-08-27 00:00:00',0.5,'0','LAPICERO 7MM AZUL',0.11,1,72,1,78,1,1),
    (214,'VITRINA','10158','2020-08-27 00:00:00',0.5,'0','LAPICERO 7MM NEGRO ',0.11,1,72,1,78,1,1),
    (215,'VITRINA','10153','2020-08-27 00:00:00',0.5,'0','LAPICERO AZUL',14.8,1,4,1,79,1,1),
    (216,'VITRINA','10152','2020-08-27 00:00:00',0.5,'0','LAPICERO BALLPOINT AZUL',0.083,1,72,1,26,1,1),
    (217,'VITRINA','10161','2020-08-27 00:00:00',0.5,'0','LAPICERO BALLPOINT NEGRO',0.083,1,77,1,26,1,1),
    (218,'VITRINA','10151','2020-08-27 00:00:00',0.5,'0','LAPICERO BX-417 FEEL-IT AZUL ',0.19,1,5,1,58,1,1),
    (219,'VITRINA','10159','2020-08-27 00:00:00',0.5,'0','LAPICERO BX-417 FEEL-IT NEGRO',0.19,1,5,1,58,1,1),
    (220,'VITRINA','10163','2020-08-27 00:00:00',0.5,'0','LAPICERO BX-417 FEEL-IT ROJO',0.19,1,25,1,58,1,1),
    (221,'VITRINA','10156','2020-08-27 00:00:00',0.5,'0','LAPICERO COLORES PAPER MATE KILOMETRICO CON INKJOY 100 1.0 M',0.25,1,126,1,26,1,1),
    (222,'DON JUAN/OFICINA','LBAZUL','2020-08-27 00:00:00',0.5,'1200','LAPICERO CRISTAL AZUL',0.1005309,1,5336,1,80,1,1),
    (223,'DON JUAN/OFICINA','LBNEGR','2020-08-27 00:00:00',0.5,'1200','LAPICERO CRISTAL NERGO',0.1005309,1,5208,1,80,1,1),
    (224,'DON JUAN/OFICINA','LBROJO','2020-08-27 00:00:00',0.5,'1200','LAPICERO CRISTAL ROJO',0.1005309,1,1411,1,80,1,1),
    (225,'DON JUAN ','LBDNEG','2020-08-27 00:00:00',0.5,'0','LAPICERO DIAMANTE NEGRO',0.1483,1,144,1,80,1,1),
    (226,'VITRINA','10154','2020-08-27 00:00:00',0.5,'0','LAPICERO INKJOY 300  1.0 M AZUL',0.25,1,309,1,26,1,1),
    (227,'VITRINA','10157','2020-08-27 00:00:00',0.5,'0','LAPICERO KILOMETRICO 0.7MM ROJO',0.25,1,56,1,26,1,1),
    (228,'VITRINA','10148','2020-08-27 00:00:00',0.5,'0','LAPICERO POPLOL 0.7MM  AZUL',0.65,1,24,1,81,1,22),
    (229,'VITRINA','10149','2020-08-27 00:00:00',0.5,'0','LAPICERO POPLOL 0.7MM  NEGRO',0.65,1,24,1,81,1,22),
    (230,'VITRINA','10168','2020-08-27 00:00:00',0.5,'0','LAPICERO SUPER GEL 0.7 AZUL',0.55,1,31,1,81,1,22),
    (231,'VITRINA','10166','2020-08-27 00:00:00',0.5,'0','LAPICERO SUPER GEL 0.7 NEGRO',0.55,1,31,1,81,1,22),
    (232,'VITRINA','10167','2020-08-27 00:00:00',0.5,'0','LAPICERO SUPER GEL 0.7 ROJO',0.55,1,12,1,81,1,22),
    (233,'VITRINA','10155','2020-08-27 00:00:00',0.5,'0','LAPICERO ULTRA SMOOTH AZUL',0.25,1,0,1,26,1,1),
    (234,'VITRINA','10162','2020-08-27 00:00:00',0.5,'0','LAPICERO ULTRA SMOOTH NEGRO',0.25,1,0,1,26,1,22),
    (235,'VITRINA','10160','2020-08-27 00:00:00',0.5,'0','LAPICERO ULTRA SUAVE NEGRO',0.11,1,60,1,78,1,1),
    (236,'VITRINA','10164','2020-08-27 00:00:00',0.5,'0','LAPICERO ULTRA SUAVE ROJO',0.11,1,0,1,78,1,1),
    (237,'VITRINA','10169','2020-08-27 00:00:00',0.5,'0','LAPICERO Z-1 0.7MM AZUL',0,1,2,1,82,1,1),
    (238,'VITRINA','10170','2020-08-27 00:00:00',0.5,'0','LAPICERO Z-1 0.7MM NEGRO',0,1,3,1,82,1,1),
    (239,'VITRINA','10171','2020-08-27 00:00:00',0.5,'0','LAPICERO Z-1 0.7MM ROJO',0,1,6,1,82,1,1),
    (240,'N/A','14892','2020-08-27 00:00:00',0.5,'0','LAPIZ DE CERA BACO AZUL',0.353982,1,15,1,59,1,2),
    (241,'VITRINA','10172','2020-08-27 00:00:00',0.5,'0','LAPIZ HB 2',0,1,576,1,83,1,1),
    (242,'VITRINA','10173','2020-08-27 00:00:00',0.5,'0','LAPIZ HB 2',0,1,38,1,84,1,1),
    (243,'VITRINA','7199','2020-08-27 00:00:00',0.5,'0','LAPIZ STAEDTLER AMARILLO ',0.07868055,1,2328,1,25,1,2),
    (244,'VITRINA','7198','2020-08-27 00:00:00',0.5,'0','LAPIZ STAEDTLER NORICA AZUL',0.07868055,1,691,1,25,24,1),
    (245,'LIMPIEZA','10174','2020-08-27 00:00:00',0.5,'0','LAVAPLATOS EN TARRO',0,1,255,1,75,1,1),
    (246,'LIMPIEZA','10175','2020-08-27 00:00:00',0.5,'0','LAVAPLATOS EN TARRO',0,1,8,1,85,1,1),
    (247,'BAÑO','PT11865','2020-08-27 00:00:00',0.5,'144','LIBRETA 60H TAQUIGRAFIA CONCEPT PLUS 1X24',0.29,1,96,1,20,1,9),
    (248,'OFICINA','10176','2020-08-27 00:00:00',0.5,'0','LIBRETA EJECUTIVA BLOCK BLANCO T/CARTA',0.902655,1,0,1,39,1,1),
    (249,'OFICINA','10177','2020-08-27 00:00:00',0.5,'0','LIBRETA EJECUTIVA BLOCK RAYADO BLANCO T/CARTA',0.44,1,58,1,39,1,1),
    (250,'OFICINA','10178','2020-08-27 00:00:00',0.5,'0','LIBRETA EJECUTIVA BLOCK RAYADO BLANCO T/OFICIO',0,1,54,1,39,1,1),
    (251,'OFICINA','10179','2020-08-27 00:00:00',0.5,'0','LIBRETA EJECUTIVA RAYADO AMARILLO T/OFICIO',0.7,1,36,1,39,1,1);
INSERT INTO PRODUCTOS VALUES
    (252,'VITRINA','10180','2020-08-27 00:00:00',0.5,'0','LIBRETA P/DIBUJO CARTA EL QUIJOTE ',1.67,1,5,1,19,1,1),
    (253,'BAÑO','PT12364','2020-08-27 00:00:00',0.5,'0','LIBRO DE ACTAS ENGRAPADO 120 PAG RAYADO',0.61,1,2,1,20,1,9),
    (254,'VITRINA','10181','2020-08-27 00:00:00',0.5,'0','LIMPIA PIPAS',0.025,1,60,1,1,1,24),
    (255,'VITRINA','10182','2020-08-27 00:00:00',0.5,'0','LISTON PAPEL ROSADO ROLLO',1.2389,1,5,1,1,1,1),
    (256,'VITRINA','10183','2020-08-27 00:00:00',0.5,'0','LISTON TIBURON TELA DORADO YRD',0.0442,1,1,1,86,25,1),
    (257,'LIMPIEZA','10184','2020-08-27 00:00:00',0.5,'0','MAIZ ENLATADO',0,1,24,1,87,1,1),
    (258,'VITRINA','10185','2020-08-27 00:00:00',0.5,'0','MARAGARITA MAQ DE ESCRIBIR',0,1,1,1,88,1,1),
    (259,'VITRINA','10186','2020-08-27 00:00:00',0.5,'0','MARCADOR FLUORESCENTE TOP PLUS',0.35,1,6,1,89,1,4),
    (260,'LIMPIEZA','10187','2020-08-27 00:00:00',0.5,'0','MASCON  COCINA  FIBRA ESPONJA SCOTCH BRITE',0,1,44,1,90,1,1),
    (261,'LIMPIEZA','10189','2020-08-27 00:00:00',0.5,'0','MASCON DE FIBRA SCOTCH BRITE',0,1,178,1,90,1,1),
    (262,'LIMPIEZA','10188','2020-08-27 00:00:00',0.5,'0','MASCON DE FIBRA VERDE',0,1,265,1,91,1,1),
    (263,'LIMPIEZA','10190','2020-08-27 00:00:00',0.5,'0','MASCON DE FIBRA VERDE',0,1,75,1,92,1,25),
    (264,'DON JUAN ','OFCL3M','2020-08-27 00:00:00',0.5,'0','OFFICE CLEANER SPRAY 3M',3.4,1,40,1,90,1,3),
    (265,'BAÑO','PT12363','2020-08-27 00:00:00',0.5,'144','ORDER BOOK ENGRAPADO 144 PAG RAYADO CONCEPT',0.61,1,110,1,20,1,9),
    (266,'OFICINA','10192','2020-08-27 00:00:00',0.5,'0','ORGANIZADOR ACRILICO',0,1,1,1,12,1,1),
    (267,'LIMPIEZA','10193','2020-08-27 00:00:00',0.5,'0','PALA PLASTICA',0,1,90,1,93,26,1),
    (268,'VITRINA','3954','2020-08-27 00:00:00',0.5,'0','PALETA COLOR JUMBO 60 UN',1.3717,1,7,1,1,11,24),
    (269,'LIMPIEZA','10195','2020-08-27 00:00:00',0.5,'0','PALILLO MONDADIENTES GIGANTE',0,1,22,1,1,27,1),
    (270,'LIMPIEZA','10196','2020-08-27 00:00:00',0.5,'0','PAPEL ALUMINIO 25 PIES',0,1,8,1,94,28,1),
    (271,'DON JUAN ','111','2020-08-27 00:00:00',0.5,'50','PAPEL BOND CARTA',2.47,1,103,1,95,28,9),
    (272,'ALTAVISTA/DON JUAN','PT10102','2020-08-27 00:00:00',0.5,'0','PAPEL BOND CARTA',2.96,1,450,1,96,28,9),
    (273,'DON JUAN ','10265','2020-08-27 00:00:00',0.5,'0','PAPEL BOND DE COLORES PASTEL BASE 20',4.75,1,5,1,97,28,1),
    (274,'DON JUAN ','10266','2020-08-27 00:00:00',0.5,'0','PAPEL BOND OFICIO',0,1,4,1,98,28,1),
    (275,'DON JUAN ','112','2020-08-27 00:00:00',0.5,'50','PAPEL BOND OFICIO',2.98,1,127,1,95,28,9),
    (276,'ALTAVISTA','PT10004','2020-08-27 00:00:00',0.5,'250','PAPEL BOND OFICIO',3.56,1,700,1,96,1,9),
    (277,'DON JUAN ','10197','2020-08-27 00:00:00',0.5,'0','PAPEL BOND P/CONTOMETRO ROLLO',0.2035398,1,37,1,1,1,2),
    (278,'OFICINA','10198','2020-08-27 00:00:00',0.5,'0','PAPEL CARBON PAQUETE 100H CARTA',0,1,10,1,39,1,5),
    (279,'OFICINA','10199','2020-08-27 00:00:00',0.5,'0','PAPEL CARBON PAQUETE 100H OFICIO',0,1,5,1,39,1,5),
    (280,'DON JUAN ','10200','2020-08-27 00:00:00',0.5,'0','PAPEL CHINA AMARILLO',0.0531,1,28,1,1,1,26),
    (281,'DON JUAN ','10201','2020-08-27 00:00:00',0.5,'0','PAPEL CHINA AZUL',0.0531,1,34,1,1,1,26),
    (282,'DON JUAN ','10202','2020-08-27 00:00:00',0.5,'0','PAPEL CHINA BLANCO',0.0531,1,34,1,1,28,26),
    (283,'BAÑO','RESPCO','2020-08-27 00:00:00',0.5,'0','PAPEL COVER COLO',0,1,4,1,1,1,1),
    (284,'DON JUAN ','10203','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON AMARILLO TIBURON',0.095,1,50,1,86,1,1),
    (285,'DON JUAN ','10204','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON AZUL PANDA',0.095,1,1,1,21,1,1),
    (286,'DON JUAN ','10205','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON AZUL TIBURON',0.095,1,50,1,86,1,1),
    (287,'DON JUAN ','10206','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON BLANCO HUESO PANDA',0.095,1,30,1,21,1,1),
    (288,'DON JUAN ','10207','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON BLANCO PANDA',0.095,1,46,1,21,1,1),
    (289,'DON JUAN ','10208','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON CAFE CLARO PANDA',0.095,1,50,1,21,1,1),
    (290,'DON JUAN ','10209','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON CAFE OSCURO PANDA',0.095,1,30,1,21,1,1),
    (291,'DON JUAN ','10210','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON CELESTE PANDA',0.095,1,11,1,21,1,1),
    (292,'DON JUAN ','10211','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON CELESTE TIBURON',0.095,1,50,1,86,1,1),
    (293,'DON JUAN ','10212','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON GRIS PANDA',0.095,1,48,1,21,1,1),
    (294,'DON JUAN ','10213','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON LILA PANDA',0.095,1,40,1,21,1,1),
    (295,'DON JUAN ','10214','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON MORADO FUCSIA PANDA',0.095,1,40,1,21,1,1),
    (296,'DON JUAN ','10215','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON MORADO PANDA',0.095,1,37,1,21,1,1),
    (297,'DON JUAN ','10216','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON MORADO UVA PANDA',0.095,1,30,1,21,1,1),
    (298,'DON JUAN ','10217','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON NARANJA PANDA',0.095,1,21,1,21,1,1),
    (299,'DON JUAN ','10218','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON NARANJA TIBURON',0.095,1,50,1,86,1,1),
    (300,'DON JUAN ','10219','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON NEGRO',0.095,1,33,1,21,1,1),
    (301,'DON JUAN ','10220','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON ROJO',0.095,1,10,1,21,1,1),
    (302,'DON JUAN ','10221','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON ROJO TIBURON',0.095,1,50,1,86,1,1),
    (303,'DON JUAN ','10222','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON ROSADO ENCENDIDO',0.095,1,40,1,21,1,1),
    (304,'DON JUAN ','10223','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON ROSADO FUCSIA',0.095,1,40,1,21,1,1),
    (305,'DON JUAN ','10224','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON ROSADO PALIDO',0.095,1,46,1,21,1,1),
    (306,'DON JUAN ','10225','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON SALMON',0.095,1,34,1,21,1,1),
    (307,'DON JUAN ','10226','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON VERDE LIMON',0.095,1,40,1,21,1,1),
    (308,'DON JUAN ','10227','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON VERDE OSCURO',0.095,1,27,1,21,1,1),
    (309,'DON JUAN ','10228','2020-08-27 00:00:00',0.5,'0','PAPEL CRESPON VERDE TIBURON',0.095,1,50,1,86,27,1),
    (310,'BAÑO','ROLFAX','2020-08-27 00:00:00',0.5,'0','PAPEL FAX',0,1,10,1,1,1,1),
    (311,'LIMPIEZA','10229','2020-08-27 00:00:00',0.5,'0','PAPEL HIGIENICO ',0,1,5,1,99,27,1),
    (312,'ALTAVISTA/LIMPIEZA','30195366','2020-08-27 00:00:00',0.5,'0','PAPEL HIGIENICO 2PX220H DOBLE HIGIENE SUAVITELA',0.46021,1,840,1,100,27,27),
    (313,'ALTAVISTA','30195365','2020-08-27 00:00:00',0.5,'0','PAPEL HIGIENICO 2PX450H RINDEMAX',0.4425,1,764,1,100,27,27),
    (314,'ALTAVISTA','PHMILH','2020-08-27 00:00:00',0.5,'480','PAPEL HIGIENICO 3PX1000H',0.497786,1,1400,1,100,27,27),
    (315,'ALTAVISTA','PH3PCU','2020-08-27 00:00:00',0.5,'0','PAPEL HIGIENICO 3PXTRIPLE CUIDADO',0.52464,1,816,1,100,1,27),
    (316,'BAÑO','TEMPCPK','2020-08-27 00:00:00',0.5,'100 pliegos','PAPEL KRAFT (CIENTO)',0,1,1,1,1,1,1),
    (317,'DON JUAN ','10230','2020-08-27 00:00:00',0.5,'0','PAPEL TERMICO P/CONTOMETRO ROLLO',1.25,1,80,1,1,1,12),
    (318,'LIMPIEZA','10231','2020-08-27 00:00:00',0.5,'0','PAPEL TOALLA 280G',0,1,6,1,50,27,1),
    (319,'ALTAVISTA','92-3130','2020-08-27 00:00:00',0.5,'300','PAPEL TOALLA CLASICO 10/3PK/60 H',0.449,1,1500,1,101,1,28),
    (320,'LIMPIEZA','10232','2020-08-27 00:00:00',0.5,'0','PAPEL TOALLA INSTITUCIONAL 250M',0,1,8,1,102,1,1),
    (321,'LIMPIEZA','PFCPTI','2020-08-27 00:00:00',0.5,'0','PAPEL TOALLA INTERFOLIADO',30,1,0,1,103,1,29),
    (322,'OFICINA','30120343','2020-08-27 00:00:00',0.5,'0','PAPEL TOALLA INTERFOLIADO 18X125 HOJAS',28.88,1,2,1,103,1,30),
    (323,'ALTAVISTA/LIMPIEZA','1033522','2020-08-27 00:00:00',0.5,'0','PAPEL TOALLA MEGA ROLL 180H 20X1UN ELITE',0.7225,1,223,1,104,27,17),
    (324,'LIMPIEZA','PT18454','2020-08-27 00:00:00',0.5,'0','PAPEL TOALLA P/MANOS P/DISPENSADOR DE 1000 PIES BLANCO',3.79,1,0,1,105,1,9),
    (325,'ALTAVISTA','30219278','2020-08-27 00:00:00',0.5,'240','PAPEL TOALLA SCOTT PAÑO IND MULTIUSO RLL 2P',0.9,1,525,1,100,1,30),
    (326,'OFICINA','10233','2020-08-27 00:00:00',0.5,'0','PAPELERA METALICA',0,1,2,1,1,1,1),
    (327,'BAÑO ','TEPMPPP','2020-08-27 00:00:00',0.5,'500 pli','PAQUETE DE PAPEL PERIODICO',0,1,1,1,1,1,1),
    (328,'OFICINA','10234','2020-08-27 00:00:00',0.5,'0','PEGAMENTO BLANCO 1 OZ (30G) M/NOKY',0.17,1,77,1,44,1,1),
    (329,'OFICINA','4-32009','2020-08-27 00:00:00',0.5,'0','PEGAMENTO BLANCO 1/4 GALON M/MASTER ',1.9469,1,9,1,106,1,1),
    (330,'OFICINA','1-32010','2020-08-27 00:00:00',0.5,'0','PEGAMENTO BLANCO 1/8 GALON M/MASTER ',1.2832,1,21,1,106,1,1),
    (331,'OFICINA','10237','2020-08-27 00:00:00',0.5,'0','PEGAMENTO BLANCO 2 OZ (60G) M/NOKY ',0.22,1,50,1,44,1,1),
    (332,'DON JUAN ','10238','2020-08-27 00:00:00',0.5,'0','PEGAMENTO BLANCO 8 OZ (240G)',0,1,72,1,44,1,1),
    (333,'DON JUAN ','PBAR3M','2020-08-27 00:00:00',0.5,'144','PEGAMENTO EN BARRA 3M 40G',1.25,1,531,1,90,1,1),
    (334,'VITRINA','10239','2020-08-27 00:00:00',0.5,'0','PEGAMENTO UNIVERSAL UHU',0.5884,1,0,1,107,1,1),
    (335,'OFICINA','10240','2020-08-27 00:00:00',0.5,'0','PERFORADOR DE 2 HOYOS 20 HOJAS M/STUDMARK 4524',1.64,1,44,1,12,1,1),
    (336,'OFICINA','10241','2020-08-27 00:00:00',0.5,'0','PERFORADOR DE 2 HOYOS 25 HOJAS M/STUDMARK 4509',2.15,1,8,1,12,1,1),
    (337,'OFICINA','10242','2020-08-27 00:00:00',0.5,'0','PERFORADOR DE 3 HOYOS 10 HOJAS M/STUDMARK ST 04512',3.93,1,2,1,12,1,1),
    (338,'VITRINA','10243','2020-08-27 00:00:00',0.5,'0','PINTURA DE DEDO 30ML ARTIS',2.4,1,22,1,108,1,1),
    (339,'OFICINA','11882','2020-08-27 00:00:00',0.5,'0','PISTOLA DE SILICON DELGADO M/STANLEY',5.75,1,5,1,47,27,31),
    (340,'DON JUAN ','10244','2020-08-27 00:00:00',0.5,'0','PLASTICO FILM P/ALIMENTOS 18" X 2000''',0,1,5,1,50,1,1),
    (341,'VITRINA','10245','2020-08-27 00:00:00',0.5,'0','PLASTICOLA TIBURON COLORES VARIADOS',0.25,1,14,1,86,1,1),
    (342,'VITRINA','2698','2020-08-27 00:00:00',0.5,'0','PLASTILINA 10 BARRITAS',0.3982,1,6,1,59,6,1),
    (343,'LIMPIEZA','10247','2020-08-27 00:00:00',0.5,'0','PLATO DESECHABLE #6',0,1,3,1,109,6,1),
    (344,'LIMPIEZA','10248','2020-08-27 00:00:00',0.5,'0','PLATO DESECHABLE #7',0,1,1,1,14,30,1),
    (345,'LIMPIEZA','10249','2020-08-27 00:00:00',0.5,'0','PLATO DESECHABLE #9',0,1,75,1,14,1,1),
    (346,'LIMPIEZA','10250','2020-08-27 00:00:00',0.5,'0','PLATO DESECHABLE CON DIVISON #9 ECO',0,1,180,1,1,6,1),
    (347,'LIMPIEZA','10251','2020-08-27 00:00:00',0.5,'0','PLATO PLASTICO DESECHABLE #6',0,1,80,1,110,1,1),
    (348,'VITRINA','10252','2020-08-27 00:00:00',0.5,'0','PLUMILLA 0.2MM AZUL',0.43,1,32,1,6,1,1),
    (349,'VITRINA','10253','2020-08-27 00:00:00',0.5,'0','PLUMILLA 0.2MM NEGRA',0.43,1,20,1,6,1,1),
    (350,'VITRINA','10254','2020-08-27 00:00:00',0.5,'0','PLUMILLA 0.2MM ROJA',0.43,1,57,1,6,1,1),
    (351,'VITRINA','APRJ04','2020-08-27 00:00:00',0.5,'0','PLUMILLA 0.4MM AZUL',0.46,1,240,1,6,1,1),
    (352,'VITRINA','10255','2020-08-27 00:00:00',0.5,'0','PLUMILLA 0.4MM ROJA',0.46,1,20,1,6,1,1),
    (353,'DON JUAN/OFICINA','EK509A','2020-08-27 00:00:00',0.5,'576','PLUMON EK-509 AZUL RECARGABLE ARTLINE',0.59,1,3277,1,6,1,4),
    (354,'DON JUAN/OFICINA','EK509N','2020-08-27 00:00:00',0.5,'576','PLUMON EK-509 NEGRO RECARGABLE ARTLINE',0.59,1,3411,1,6,1,4),
    (355,'DON JUAN/OFICINA','EK509R','2020-08-27 00:00:00',0.5,'576','PLUMON EK-509 ROJO RECARGABLE ARTLINE',0.59,1,2040,1,6,1,4),
    (356,'DON JUAN/OFICINA','EK509V','2020-08-27 00:00:00',0.5,'0','PLUMON EK-509 VERDE RECARGABLE ARTLINE',0.59,1,404,1,6,1,4),
    (357,'OFICINA','EK90AM','2020-08-27 00:00:00',0.5,'0','PLUMON EK-90 AMARILLO RECARGABLE ARTLINE',0.45,1,5,1,6,1,4),
    (358,'DON JUAN/OFICINA','EK90AZ','2020-08-27 00:00:00',0.5,'576','PLUMON EK-90 AZUL RECARGABLE ARTLINE',0.45,1,2698,1,6,1,4),
    (359,'OFICINA','EK90CL','2020-08-27 00:00:00',0.5,'0','PLUMON EK-90 CELESTE RECARGABLE ARTLINE',0.45,1,3,1,6,1,4),
    (360,'OFICINA','EK90MR','2020-08-27 00:00:00',0.5,'0','PLUMON EK-90 MORADO RECARGABLE ARTLINE',0.45,1,2,1,6,1,4),
    (361,'OFICINA','EK90NR','2020-08-27 00:00:00',0.5,'0','PLUMON EK-90 NARANJA RECARGABLE ARTLINE',0.45,1,3,1,6,1,4),
    (362,'DON JUAN/OFICINA','EK90NG','2020-08-27 00:00:00',0.5,'576','PLUMON EK-90 NEGRO RECARGABLE ARTLINE',0.45,1,2514,1,6,1,4),
    (363,'DON JUAN/OFICINA','EK90RJ','2020-08-27 00:00:00',0.5,'576','PLUMON EK-90 ROJO RECARGABLE ARTLINE',0.45,1,1362,1,6,1,4),
    (364,'OFICINA','EK90RS','2020-08-27 00:00:00',0.5,'0','PLUMON EK-90 ROSA RECARGABLE ARTLINE',0.45,1,4,1,6,1,4),
    (365,'DON JUAN/OFICINA','EK90VD','2020-08-27 00:00:00',0.5,'576','PLUMON EK-90 VERDE RECARGABLE ARTLINE',0.45,1,360,1,6,1,4),
    (366,'DON JUAN ','MW85AZ','2020-08-27 00:00:00',0.5,'0','PLUMÓN PARA PIZARRA AZUL M/PENTEL',5.67,1,696,1,58,1,15),
    (367,'OFICINA','10256','2020-08-27 00:00:00',0.5,'0','PLUMÓN PARA PIZARRA AZUL M/PENTEL',5.67,1,14,1,58,1,15),
    (368,'DON JUAN ','MW85NG','2020-08-27 00:00:00',0.5,'0','PLUMÓN PARA PIZARRA NEGRO M/PENTEL',5.67,1,696,1,58,1,15),
    (369,'OFICINA','10257','2020-08-27 00:00:00',0.5,'0','PLUMÓN PARA PIZARRA NEGRO M/PENTEL',5.67,1,12,1,58,1,15),
    (370,'DON JUAN ','PPPZRJ','2020-08-27 00:00:00',0.5,'0','PLUMÓN PARA PIZARRA ROJO M/PENTEL',5.67,1,300,1,58,1,15),
    (371,'OFICINA','10258','2020-08-27 00:00:00',0.5,'0','PLUMÓN PARA PIZARRA ROJO M/PENTEL',5.67,1,11,1,58,1,15),
    (372,'VITRINA','10259','2020-08-27 00:00:00',0.5,'0','PORTAMINAS FABER-CASTELL TKFINE',2.2191,1,10,1,111,1,1),
    (373,'VITRINA','PPPMMM','2020-08-27 00:00:00',0.5,'0','PORTAMINAS MONGOL M/PAPER MATE',0.25,1,696,1,26,1,1),
    (374,'VITRINA','10260','2020-08-27 00:00:00',0.5,'0','PORTAMINAS STAEDTLER GRAPHITE 777',0.752,1,2,1,25,1,1),
    (375,'VITRINA','10261','2020-08-27 00:00:00',0.5,'0','PORTAMINAS STAEDTLER TRIPLUS MICRO 0.5MM',1.1283,1,0,1,25,1,11),
    (376,'OFICINA','FLE684','2020-08-27 00:00:00',0.5,'0','POST IT FLECHAS FLAG C/DISPENSADOR 4 COLORES 684 ARR4',2.05,1,127,1,90,1,4),
    (377,'OFICINA','FLE683','2020-08-27 00:00:00',0.5,'0','POST IT FLECHAS FLAG C/DISPENSADOR 5 COLORES 683-5CF',0,1,253,1,90,31,4),
    (378,'OFICINA','PIN3X3','2020-08-27 00:00:00',0.5,'0','POST IT NEON 3X3',2.61,1,103,1,90,1,4),
    (379,'OFICINA','10191','2020-08-27 00:00:00',0.5,'0','POST IT NOTE FIX (POST IT AMARILLO) 2 7/8" X 4 7/8" 100 HOJAS',0.532,1,60,1,90,1,1),
    (380,'VITRINA','10262','2020-08-27 00:00:00',0.5,'0','REGLA ACRILICA NOKY',0.07,1,341,1,44,1,1),
    (381,'VITRINA','RMSTUD','2020-08-27 00:00:00',0.5,'0','REGLA METALICA (DE ALUMINIO)',0.33,1,50,1,12,1,14),
    (382,'VITRINA','10263','2020-08-27 00:00:00',0.5,'0','REGLA METALICA 30CM M/OKY',0.64,1,17,1,44,1,1),
    (383,'VITRINA','10264','2020-08-27 00:00:00',0.5,'0','REPUESTO GLADE VIDRIO 7ML AUTOSPORT',2.76,1,4,1,11,1,1),
    (384,'OFICINA','R660AM','2020-08-27 00:00:00',0.5,'0','RESALTADOR AMARILLO 660 ARTLINE',0.49,1,236,1,6,1,1),
    (385,'OFICINA','R660AZ','2020-08-27 00:00:00',0.5,'0','RESALTADOR AZUL 660 ARTLINE',0.49,1,285,1,6,1,1),
    (386,'OFICINA','RVIVIX','2020-08-27 00:00:00',0.5,'0','RESALTADOR MORADO VIVIX ARTLINE',0.4,1,79,1,6,1,1),
    (387,'OFICINA','R660NJ','2020-08-27 00:00:00',0.5,'0','RESALTADOR NARANJA 660 ARTLINE',0.49,1,377,1,6,1,1),
    (388,'OFICINA','R660RS','2020-08-27 00:00:00',0.5,'0','RESALTADOR ROSADO 660 ARTLINE',0.49,1,276,1,6,1,1),
    (389,'OFICINA','R660VD','2020-08-27 00:00:00',0.5,'0','RESALTADOR VERDE 660 ARTLINE',0.49,1,432,1,6,1,1),
    (390,'OFICINA','10267','2020-08-27 00:00:00',0.5,'0','SACAGRAPA M/WEX',0.2,1,79,1,112,1,1),
    (391,'OFICINA','10268','2020-08-27 00:00:00',0.5,'0','SACAGRAPAS M/STUDMARK MOD 4401',0.24,1,2,1,12,1,1),
    (392,'VITRINA','10269','2020-08-27 00:00:00',0.5,'0','SACAPUNTA METALICO DOBLE AGUJERO M/MAE',0.19,1,23,1,48,1,1),
    (393,'VITRINA','10270','2020-08-27 00:00:00',0.5,'0','SACAPUNTAS PRISMACOLOR PREMIER',4.89,1,14,1,113,1,1),
    (394,'LIMPIEZA','10271','2020-08-27 00:00:00',0.5,'0','SERVILLETA DE 100 UN',0,1,6,1,114,1,1),
    (395,'ALTAVISTA/LIMPIEZA','SSCOTT','2020-08-27 00:00:00',0.5,'120','SERVILLETA DE SCOTT 100 UN',0.6741666,1,339,1,100,1,30),
    (396,'VITRINA','10272','2020-08-27 00:00:00',0.5,'0','SILICON BARRA DELGADO',0.0796,1,81,1,1,1,26),
    (397,'VITRINA','10273','2020-08-27 00:00:00',0.5,'0','SILICON BARRA GRUESO',0.1504,1,92,1,1,1,1),
    (398,'DON JUAN ','10274','2020-08-27 00:00:00',0.5,'0','SILICON LIQUIDO 100ML TIBURON ',0.6637,1,36,1,86,1,26),
    (399,'VITRINA','10275','2020-08-27 00:00:00',0.5,'0','SILICON LIQUIDO 100ML TIBURON ',0.6637,1,11,1,86,1,1),
    (400,'DON JUAN ','10276','2020-08-27 00:00:00',0.5,'0','SILICON LIQUIDO 200ML TIBURON ',1.0619,1,51,1,86,1,26),
    (401,'VITRINA','10277','2020-08-27 00:00:00',0.5,'0','SILICON LIQUIDO 200ML TIBURON ',1.0619,1,10,1,86,1,1),
    (402,'VITRINA','10278','2020-08-27 00:00:00',0.5,'0','SILICON LIQUIDO STUDMARK 250ML',0,1,3,1,12,1,1),
    (403,'VITRINA','10279','2020-08-27 00:00:00',0.5,'0','SOBRES PLASTICOS CON BROCHE MULTICOLOR',0,1,7,1,115,1,24),
    (404,'LIMPIEZA','10280','2020-08-27 00:00:00',0.5,'0','SODA CAUSTICA 400G',0,1,1,1,1,1,1),
    (405,'LIMPIEZA','10281','2020-08-27 00:00:00',0.5,'0','SOPERO DESECHABLE 32OZ',0,1,25,1,42,1,1),
    (406,'OFICINA','359364','2020-08-27 00:00:00',0.5,'0','TABLA ACRILICA T/CARTA M/AMPO',2.15,1,12,1,10,1,19),
    (407,'OFICINA','10282','2020-08-27 00:00:00',0.5,'0','TABLA ACRILICA T/CARTA M/OFFICE DEPOT',1.68,1,4,1,116,1,32),
    (408,'OFICINA','10283','2020-08-27 00:00:00',0.5,'0','TABLA DE MADERA T/OFICIO M/AMPO',1.06,1,12,1,10,32,19),
    (409,'LIMPIEZA','10284','2020-08-27 00:00:00',0.5,'0','TABLETAS 3.5OZ',0,1,6,1,117,27,1),
    (410,'DON JUAN ','4331','2020-08-27 00:00:00',0.5,'0','TAPE 2" (48MMX25M) 3M 3609',0.43,1,684,1,90,1,1),
    (411,'DON JUAN ','10285','2020-08-27 00:00:00',0.5,'0','TAPE 3/4 ABRO',0.2,1,144,1,118,1,1),
    (412,'DON JUAN ','10286','2020-08-27 00:00:00',0.5,'0','TE DE MANZANILLA (CAJA DE 20 UNIDADES)',1.1191,1,47,1,119,1,25),
    (413,'DON JUAN ','TCCTMM','2020-08-27 00:00:00',0.5,'0','TE DE MANZANILLA (CAJA DE 20 UNIDADES)',53.7168,1,0,1,119,1,25),
    (414,'OFICINA','10287','2020-08-27 00:00:00',0.5,'0','TECLADO USB',0,1,2,1,120,1,1),
    (415,'VITRINA','10288','2020-08-27 00:00:00',0.5,'0','TEMPERA TUCAN JUNIOR 22G',0,1,3,1,4,6,1),
    (416,'LIMPIEZA','10289','2020-08-27 00:00:00',0.5,'0','TENEDOR GRANDE',0,1,2,1,121,1,1),
    (417,'LIMPIEZA','NLTGRA','2020-08-27 00:00:00',0.5,'0','TENEDOR GRANDE',0.2212,1,1,1,46,1,1),
    (418,'OFICINA','10290','2020-08-27 00:00:00',0.5,'0','TIJERA  8" SRY',0.55,1,40,1,39,1,4),
    (419,'OFICINA','10291','2020-08-27 00:00:00',0.5,'0','TIJERA 6" INFANTIL STUDMARK',0.56,1,1,1,12,1,1),
    (420,'OFICINA','ST-4212','2020-08-27 00:00:00',0.5,'0','TIJERA 7" 4212',0.78,1,12,1,12,1,1),
    (421,'OFICINA','ST-04204','2020-08-27 00:00:00',0.5,'0','TIJERA 7.5" 4204',0.95,1,96,1,12,1,1),
    (422,'OFICINA','ST-04210','2020-08-27 00:00:00',0.5,'0','TIJERA 8.5" 4210',1.1,1,116,1,12,1,1),
    (423,'OFICINA','10292','2020-08-27 00:00:00',0.5,'0','TIJERA MULTIFORMAS CURVA TCT-02',0.98,1,8,1,48,1,1),
    (424,'OFICINA','10293','2020-08-27 00:00:00',0.5,'0','TINTA AZUL P/ALMOHADILLA 2 OZ',1.05,1,42,1,6,1,4),
    (425,'OFICINA','TNA2OZ','2020-08-27 00:00:00',0.5,'0','TINTA NEGRA P/ALMOHADILLA 2 OZ',1.05,1,127,1,6,1,4),
    (426,'OFICINA','10294','2020-08-27 00:00:00',0.5,'0','TINTA NEGRA P/ALMOHADILLA 20ML',0.82,1,0,1,6,1,4),
    (427,'DON JUAN ','3M3400','2020-08-27 00:00:00',0.5,'0','TIRRO 2" (48MMX25M) 3M 3400',1.25,1,113,1,90,27,1),
    (428,'DON JUAN ','10295','2020-08-27 00:00:00',0.5,'0','TIRRO 3/4"',0,1,30,1,122,1,1),
    (429,'DON JUAN ','TI3400','2020-08-27 00:00:00',0.5,'144','TIRRO 3/4" 3M 3400',0.425,1,1652,1,90,1,1),
    (430,'LIMPIEZA','10296','2020-08-27 00:00:00',0.5,'0','TOALLAS HUMEDAS SANITIZANTES ',0,1,2,1,117,1,1),
    (431,'OFICINA','HPLCE505A','2020-08-27 00:00:00',0.5,'0','TONER HP NEGRO CE-505A P20/35/2055 2.3K',80.88,1,0,1,123,1,1),
    (432,'ALTAVISTA','DOBLET','2020-08-27 00:00:00',0.5,'50','TRAPEADOR DOBLE TOALLA ',2.25,1,116,1,1,1,16),
    (433,'ALTAVISTA/LIMPIEZA','TOAPER','2020-08-27 00:00:00',0.5,'100','TRAPEADOR TOALLA PERSA',1.25,1,670,1,1,1,16),
    (434,'VITRINA','10298','2020-08-27 00:00:00',0.5,'0','TUBO DE MINA 0.5 STAEDTLER  HB',0.5641,1,2,1,25,6,11),
    (435,'LIMPIEZA','10299','2020-08-27 00:00:00',0.5,'0','VASO DESECHABLE #10',0,1,3,1,14,6,1),
    (436,'LIMPIEZA','10300','2020-08-27 00:00:00',0.5,'0','VASO PLASTICO #5',0,1,11,1,124,6,33),
    (437,'LIMPIEZA','10301','2020-08-27 00:00:00',0.5,'0','VASO PLASTICO #5',0,1,12,1,125,1,1),
    (438,'BAÑO','VAACCO','2020-08-27 00:00:00',0.5,'0','VASOS ACRILICOS DE COLORES ',0,1,1,1,1,33,1),
    (439,'LIMPIEZA','10302','2020-08-27 00:00:00',0.5,'0','VIDRIO Y SUPERFICIES',0,1,147,1,7,1,1),
    (440,'VITRINA','10303','2020-08-27 00:00:00',0.5,'0','VIÑETA CUADRADA P/FOLDER',0.35398,1,30,1,1,1,1),
    (441,'VITRINA','10304','2020-08-27 00:00:00',0.5,'0','VIÑETA DELGADA P/FOLDER PAQUETE',0.4867,1,60,1,1,1,1),
    (442,'VITRINA','10305','2020-08-27 00:00:00',0.5,'0','VIÑETA DELGADA P/FOLDER ROLLO',0.4867,1,43,1,1,1,1);


insert into direcciones values (null,"Boulevar Constitucion, calle zacamil, casa #2"),(null,"Redoldel Masferrer, calle los santos, pasaje 12, casa #5");

insert into cliente_direcciones values (null,1,1),(null,1,2);
/*insert into inventario values (null,"02213","2019-03-03",20,1);
insert into inventario values(null,"02255","2019-02-03",100,1);*/
-- insert into inventario (stock,producto_idp) select stock,idp from productos where idp>1;

-- update inventario set create_at="2020-02-02", codigo_proveedor=2 where inventario_id>1;
insert into cdepago values (1,"Credito"), (2,"Contado");
insert into formadepago values (1,"Efectivo"), (2,"Cheque"), (3, "Transaccion");    
insert into tipo_factura values (1,"Credito fiscal"),(2,"Consumidor Final"),(3,"Exportacion");
-- insert into cotizacion values (1,"2020-02-02",true);
-- update cotizacion set create_at="2020-02-02" where id=1;
-- insert into carrito_items values(1,300,"536xxx",50.0,1,1), (2,500,"972xxx",50.0,1,4);    
-- insert into facturacion values (1,'2221',"un detalle",1,1,1,1,1,1);
alter table proveedor MODIFY razonsocial varchar (80);