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

