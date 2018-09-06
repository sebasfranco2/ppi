insert into tipos_vehiculos(tipo_vehiculo) values('Carro')
insert into tipos_vehiculos(tipo_vehiculo) values('Moto')
insert into tipos_vehiculos(tipo_vehiculo) values('Camioneta')
insert into tipos_vehiculos(tipo_vehiculo) values('Bus')
insert into tipos_vehiculos(tipo_vehiculo) values('Colectivo')
insert into tipos_vehiculos(tipo_vehiculo) values('Camion')
insert into tipos_vehiculos(tipo_vehiculo) values('Tractomula')

insert into vehiculos(cedula,nombre,apellido,placa,tipo_vehiculo_id) values(102045123, "carlos", "jimenez","lwx30c",6);
insert into vehiculos(cedula,nombre,apellido,placa,tipo_vehiculo_id) values(102044123, "juanes", "jimenez","lrx30c",5);
insert into vehiculos(cedula,nombre,apellido,placa,tipo_vehiculo_id) values(102043523, "estela", "jimenez","l2x30c",4);
insert into vehiculos(cedula,nombre,apellido,placa,tipo_vehiculo_id) values(102022123, "flor", "jimenez","lax30c",7);
insert into vehiculos(cedula,nombre,apellido,placa,tipo_vehiculo_id) values(102045431, "sebastian", "jimenez","lxt30c",2);
insert into vehiculos(cedula,nombre,apellido,placa,tipo_vehiculo_id) values(101145123, "ruben", "jimenez","lzo30c",1);
insert into vehiculos(cedula,nombre,apellido,placa,tipo_vehiculo_id) values(102745123, "camilo", "jimenez","ltx30c",3);

insert into usuarios(nombre,apellido,email,contrasena) values("sebastian","franco restrepo" ,"sebas@gmail.com","$2a$10$czJ/sN42.qMGWXED.JPH0uczoEFE6JuhL1h43Zo0LTYu5mBa7o7S.");

insert into usuarios(nombre,apellido,email,contrasena) values('milena','cardona','milena@gmail.com','$2a$10$CaRPwXBPFWgqvS6EKoo1Ge5jN9lS76iOLJOdrr0sv4MzMEdyB6SZ2');

insert into perfiles(perfil,usuario_id) values('ROLE_ADMIN',1);
insert into perfiles(perfil,usuario_id) values('ROLE_USER',2);

insert into servicios (id,nombre, precio) values (1, "lavado",10000)
insert into servicios (id,nombre, precio) values (2, "polichado",15000)
insert into servicios (id,nombre, precio) values (3, "aspiracion",8000)
insert into servicios (id,nombre, precio) values (4, "balanceo",7000)
insert into servicios (id,nombre, precio) values (5, "cambio aceite",10000)
insert into servicios (id,nombre, precio) values (6, "alineacion",17000)
insert into servicios (id,nombre, precio) values (7, "limpieza motor",5000)
insert into servicios (id,nombre, precio) values (8, "polichado",6000)
insert into servicios (id,nombre, precio) values (9, "lavado",8000)
insert into servicios (id,nombre, precio) values (10, "balanceo",8000)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (1, 1)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (2, 1)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (3, 1)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (4, 1)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (5, 1)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (6, 1)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (7, 1)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (8, 2)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (9, 2)
insert into servicios_tipos_vehiculos (servicio_id, tipos_vehiculos_id) values (10, 2)

