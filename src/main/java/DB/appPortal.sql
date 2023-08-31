use [master];
drop database [appPortal];

create database [appPortal];
use [appPortal];
GO

create function ENCRIPTA_PASS
 (
 @contrasena varchar(50)
 )
 returns VarBinary(500)
 as
 begin
 declare @pass as VarBinary(500)
 set @pass=ENCRYPTBYPASSPHRASE('MiclaveSecreta',@contrasena)
 return @pass
 end;
GO

create function desencriptar_pass
  (
  @contrasena varbinary(500)
  )
  returns varchar(50)
  as
  begin
  declare @pass as varchar(50)
  set @pass=DECRYPTBYPASSPHRASE('MiclaveSecreta',@contrasena)
  return @pass
  end;
GO


-------------------------------------------------------------------------------------------
-----------------------------------------USUARIO-------------------------------------------
-------------------------------------------------------------------------------------------
create table usuario
(
identificacion int NOT NULL
,nombre varchar(255)
,apellido varchar(255)
,apellido2 varchar(255)
,estado int default 1
,PRiMARY KEY (identificacion),
);


create table autentication
(
usuario int NOT NULL
,correo_electronico varchar(255)
,contrasena varbinary(500)
,PRiMARY KEY (usuario)
,FOREIGN KEY (usuario) REFERENCES usuario(identificacion),
);

---------------------------
--------ROL USUARIO--------
---------------------------	

create table rol
(
usuario int NOT NULL 
,rolAdministrador int default 0
,rolPlanillero integer default 0
,rolRecursosHumanos integer default 0
,PRiMARY KEY (usuario)
,FOREIGN KEY (usuario) REFERENCES usuario(identificacion),
);
GO

--drop table rol;
--drop table autentication;
--drop table usuario;

--------------------------------
--------TRIGGER USUARIOS--------
--------------------------------

CREATE TRIGGER usuario_trigger  
ON usuario 
AFTER INSERT 
AS 
BEGIN 
DECLARE @NEWidentificacion int
SET NOCOUNT ON;

 SELECT @NEWidentificacion= i.identificacion from inserted i;
INSERT INTO autentication(usuario) VALUES (@NEWidentificacion);
INSERT INTO rol(usuario) VALUES (@NEWidentificacion);
END;
GO

------Prueba------
insert into usuario (
identificacion
,nombre 
,apellido
,apellido2
) 
values (
891426789
, 'Usu'
,'Usuariezin'
,'Usuario');
GO
--select * from rol;
--select * from autentication;


--------------------------------------------------------------
----------Store Procedures de Insertar Administrador----------
--------------------------------------------------------------



create procedure sp_Insertar_Administrador
(
@identificacion int
,@nombre varchar(255)
,@apellido varchar(255)
,@apellido2 varchar(255)
,@correo_electronico varchar(255)
,@contrasena varchar(50)
)
As
Begin

insert into usuario (
identificacion
,nombre 
,apellido
,apellido2
) 
values (
@identificacion
,@nombre
,@apellido
,@apellido2);

UPDATE autentication
SET correo_electronico = @correo_electronico
WHERE usuario = @identificacion;

UPDATE autentication
SET contrasena = dbo.ENCRIPTA_PASS(@contrasena)
WHERE usuario = @identificacion;

UPDATE rol
SET rolAdministrador = 1
WHERE usuario = @identificacion;

End;
GO

--------------------------------------------------------------
------------Store Procedures de Insertar Planillero-----------
--------------------------------------------------------------

create procedure sp_Insertar_Planillero
(
@identificacion int
,@nombre varchar(255)
,@apellido varchar(255)
,@apellido2 varchar(255)
,@correo_electronico varchar(255)
,@contrasena varchar(50)
)
As
Begin

insert into usuario (
identificacion
,nombre 
,apellido
,apellido2
) 
values (
@identificacion
,@nombre
,@apellido
,@apellido2);

UPDATE autentication
SET correo_electronico = @correo_electronico
WHERE usuario = @identificacion;

UPDATE autentication
SET contrasena = dbo.ENCRIPTA_PASS(@contrasena)
WHERE usuario = @identificacion;

UPDATE rol
SET rolPlanillero = 1
WHERE usuario = @identificacion;

End;
GO


---------------------------------------------------------------------
------------Store Procedures de Insertar Recursos Humanos------------
---------------------------------------------------------------------

create procedure sp_Insertar_Recursos_Humanos
(
@identificacion int
,@nombre varchar(255)
,@apellido varchar(255)
,@apellido2 varchar(255)
,@correo_electronico varchar(255)
,@contrasena varchar(50)
)
As
Begin

insert into usuario (
identificacion
,nombre 
,apellido
,apellido2
) 
values (
@identificacion
,@nombre
,@apellido
,@apellido2);

UPDATE autentication
SET correo_electronico = @correo_electronico
WHERE usuario = @identificacion;

UPDATE autentication
SET contrasena = dbo.ENCRIPTA_PASS(@contrasena)
WHERE usuario = @identificacion;

UPDATE rol
SET rolRecursosHumanos = 1
WHERE usuario = @identificacion;

End;
GO

------------------------------------------------------
----------Store Procedures de Validar Usuario---------
------------------------------------------------------
create procedure sp_Validar
(
@correo_electronico varchar(255)
,@contrasena varchar(50)
)
As
Begin
DECLARE @contrasenaDesencriptada varchar(50)
SET NOCOUNT ON;
SET @contrasenaDesencriptada = ( select dbo.desencriptar_pass(a.contrasena) from autentication a  where a.correo_electronico= @correo_electronico);

IF(@contrasena = @contrasenaDesencriptada)
BEGIN
select a.usuario from autentication a where a.correo_electronico = @correo_electronico and a.contrasena= (select a.contrasena from autentication a  where a.correo_electronico= @correo_electronico);
END;
End;
GO

-----------------------------------------------------------------
----------Store Procedures de Obtener Usuario por Cedula---------
-----------------------------------------------------------------
create procedure sp_Usuario_Por_Identificacion
(
@identificacion int
)
As
Begin
select  u.identificacion, 
       u.nombre,
	   u.apellido,
	   u.apellido2,
	   a.correo_electronico,
	   a.contrasena,
	   r.rolAdministrador,
	   r.rolRecursosHumanos,
	   r.rolPlanillero,
	   u.estado
from  usuario u, 
      autentication a,
	  rol r
where 
u.identificacion = @identificacion
and
a.usuario = @identificacion
and
r.usuario = @identificacion;
End;
GO
--drop procedure sp_Usuario_Por_Identificacion;

----------------------------------------------------------------
----------Store Procedures de Obtener Todos Los Usuario---------
----------------------------------------------------------------
create procedure sp_Obtener_Todos_Los_Usuarios
As
Begin
select u.identificacion, 
       u.nombre,
	   u.apellido,
	   u.apellido2,
	   a.correo_electronico,
	   a.contrasena,
	   r.rolAdministrador,
	   r.rolRecursosHumanos,
	   r.rolPlanillero,
	   u.estado
from  usuario u
	  inner join
      autentication a
	  
	  on u.identificacion = a.usuario 
	  inner join 
	  rol r 
	  on u.identificacion = r.usuario
	  Where  u.estado = 1;
End;
GO

-----------------------------------------------------------------------------
----------Store Procedures de Obtener Todos Los Usuario Desactivados---------
-----------------------------------------------------------------------------
create procedure sp_Obtener_Todos_Los_Usuarios_Desactivados
As
Begin
select u.identificacion, 
       u.nombre,
	   u.apellido,
	   u.apellido2,
	   a.correo_electronico,
	   a.contrasena,
	   r.rolAdministrador,
	   r.rolRecursosHumanos,
	   r.rolPlanillero,
	   u.estado
from  usuario u
	  inner join
      autentication a
	  
	  on u.identificacion = a.usuario 
	  inner join 
	  rol r 
	  on u.identificacion = r.usuario
	  Where  u.estado = 0;
End;
GO

---------------------------------------------------------------------------
-------------Store Procedures de desactivar Usuario por cedula-------------
---------------------------------------------------------------------------
create procedure sp_Desactivar_Usuario_Por_Identificacion
(
@identificacion int 
)
As
Begin
Update usuario
SET estado=0
WHERE identificacion = @identificacion;
End;
GO
---------------------------------------------------------------------------
-------------Store Procedures de Activar Usuario por cedula----------------
---------------------------------------------------------------------------
create procedure sp_Activar_Usuario_Por_Identificacion
(
@identificacion int 
)
As
Begin
Update usuario
SET estado=1
WHERE identificacion = @identificacion;
End;
GO


-------------------------------------------------------------
------------Store Procedure de Actualizar Usuario------------
-------------------------------------------------------------

create procedure sp_Actualizar_Usuario
(
@identificacion int
,@nombre varchar(255)
,@apellido varchar(255)
,@apellido2 varchar(255)
,@correo_electronico varchar(255)
,@contrasena varchar(50)
,@rolAdministrador int 
,@rolPlanillero int
,@rolRecursosHumanos int 
,@estado int
)
As
Begin

UPDATE usuario
SET 
nombre =@nombre
,apellido = @apellido
,apellido2 = @apellido2
,estado = @estado
WHERE identificacion = @identificacion;

UPDATE autentication
SET 
correo_electronico = @correo_electronico
,contrasena = dbo.ENCRIPTA_PASS(@contrasena)
WHERE usuario = @identificacion;

UPDATE rol
SET rolRecursosHumanos = @rolRecursosHumanos
	,rolAdministrador = @rolAdministrador
	,rolPlanillero = @rolPlanillero
WHERE usuario = @identificacion;

End;
GO



UPDATE autentication
SET correo_electronico = 'admin@correo.com'
WHERE usuario = 891426789;

UPDATE autentication
SET contrasena = dbo.ENCRIPTA_PASS('123456')
WHERE usuario = 891426789;

UPDATE rol
SET rolAdministrador = 1
WHERE usuario = 891426789;


EXEC sp_Usuario_Por_Identificacion 891426789;

Exec sp_Obtener_Todos_Los_Usuarios;

EXEC sp_Validar 'admin@correo.com', '123456';
/*
EXEC  sp_Actualizar_Usuario 891426789,'usuario.getIdentificacion()','usuario.getApellido()','usuario.getApellido2()','usuario.getCorreo_electronico()','usuario.getContrasena()',1,0,0,0;

EXEC sp_Usuario_Por_Identificacion 891426789;
*/
-------------------------------------------------------------------------------------------
-----------------------------------------EMPLEADO------------------------------------------
-------------------------------------------------------------------------------------------
create table empleado
(
identificacion int NOT NULL
,nombre varchar(255)
,apellido varchar(255)
,apellido2 varchar(255)
,tipo_jornada varchar(255)
,estado int default 1
,PRiMARY KEY (identificacion),
);

create table tipo_empleado
(
empleado int NOT NULL
,descripcion_puesto varchar(255)
,semanal int default 0
,quincenal int default 0
,salarioXhora money default 0
,salario_bruto_actual money default 0
,PRiMARY KEY (empleado)
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
);
GO

--------------------------------
--------EMPLEADO TRIGGERS-------
--------------------------------

CREATE TRIGGER empleado_trigger
ON  empleado
AFTER INSERT 
AS 
BEGIN 
DECLARE @NEWidentificacion int
SET NOCOUNT ON;
SELECT @NEWidentificacion= i.identificacion from inserted i;
INSERT INTO tipo_empleado(empleado) VALUES (@NEWidentificacion);
END;
GO


CREATE TRIGGER empleado_updated_trigger
ON  tipo_empleado
AFTER UPDATE
AS 
BEGIN 
DECLARE @NEWidentificacion int
, @salarioXhora money
SET NOCOUNT ON;
SELECT @NEWidentificacion= i.empleado from inserted i;
SELECT @salarioXhora = i.salarioXhora from inserted i;
if((select semanal from tipo_empleado where empleado=@NEWidentificacion)=1)
BEGIN
UPDATE tipo_empleado
SET
salario_bruto_actual = @salarioXhora *48
WHERE empleado = @NEWidentificacion;
END;

if((select quincenal from tipo_empleado where empleado=@NEWidentificacion)=1)
BEGIN
UPDATE tipo_empleado
SET
salario_bruto_actual = @salarioXhora *96
WHERE empleado = @NEWidentificacion;
END;

END;
GO




----------------------------------------------------------------------
-------------Store Procedures de Insertar Empleado Semanal------------
----------------------------------------------------------------------
create procedure sp_Insertar_Empleado_Semanal
(
@identificacion int 
,@nombre varchar(255)
,@apellido varchar(255)
,@apellido2 varchar(255)
,@tipo_jornada varchar(255)
,@descripcion_puesto varchar(255)
,@salarioXhora money
)
As
Begin
INSERT INTO empleado (identificacion,nombre,apellido,apellido2,tipo_jornada) VALUES (@identificacion,@nombre,@apellido,@apellido2,@tipo_jornada);

UPDATE tipo_empleado
SET
semanal = 1
,descripcion_puesto = @descripcion_puesto
,salarioXhora =@salarioXhora
,salario_bruto_actual = 48*@salarioXhora
WHERE empleado=@identificacion;

End;
GO

------------------------------------------------------------------------
-------------Store Procedures de Insertar Empleado Quincenal------------
------------------------------------------------------------------------
create procedure sp_Insertar_Empleado_Quincenal
(
@identificacion int 
,@nombre varchar(255)
,@apellido varchar(255)
,@apellido2 varchar(255)
,@tipo_jornada varchar(255)
,@descripcion_puesto varchar(255)
,@salarioXhora money
)
As
Begin
INSERT INTO empleado (identificacion,nombre,apellido,apellido2,tipo_jornada) VALUES (@identificacion,@nombre,@apellido,@apellido2,@tipo_jornada);

UPDATE tipo_empleado
SET
quincenal = 1
,descripcion_puesto = @descripcion_puesto
,salarioXhora =@salarioXhora
,salario_bruto_actual = 96*@salarioXhora
WHERE empleado=@identificacion;
End;
GO

------------------------------------------------------------------------
-------------Store Procedures de Insertar Empleado Quincenal------------
------------------------------------------------------------------------
create procedure sp_Actualizar_Empleado
(
@identificacion int 
,@nombre varchar(255)
,@apellido varchar(255)
,@apellido2 varchar(255)
,@tipo_jornada varchar(255)
,@descripcion_puesto varchar(255)
,@quincenal int
,@semanal int
,@salarioXhora money
,@estado int
)
As
Begin

UPDATE empleado 
SET 
nombre = @nombre
,apellido = @apellido
,apellido2 = @apellido2
,tipo_jornada = @tipo_jornada
,estado = @estado
WHERE identificacion=@identificacion;

UPDATE tipo_empleado
SET
semanal=@semanal
,quincenal = @quincenal
,descripcion_puesto = @descripcion_puesto
,salarioXhora =@salarioXhora
WHERE empleado=@identificacion;
End;
GO


---------------------------------------------------------------------------------------------
-------------Store Procedures de Obtener la identificacion de todos los empleados------------
---------------------------------------------------------------------------------------------
create procedure sp_Obtener_Identificacion_De_Empleados
As
Begin
SELECT e.identificacion FROM empleado e;
End;
GO



--------------------------------------------------------------------------------
-------------Store Procedures de Obtener Empleado Por Identificacion------------
--------------------------------------------------------------------------------
create procedure sp_Obtener_Empleado_Por_Identificacion
(
@identificacion int 
)
As
Begin
SELECT e.identificacion, e.nombre, e.apellido, e.apellido2,e.tipo_jornada,t.descripcion_puesto,t.semanal,t.quincenal, t.salarioXhora,t.salario_bruto_actual, e.estado FROM empleado e, tipo_empleado t WHERE e.identificacion = @identificacion AND t.empleado = @identificacion;
End;
GO
--------------------------------------------------------------
-------------Store Procedures de Obtener Empleados------------
--------------------------------------------------------------
create procedure sp_Obtener_Todos_Los_Empleados
As
Begin
select e.identificacion, e.nombre, e.apellido, e.apellido2,e.tipo_jornada,t.descripcion_puesto,t.semanal,t.quincenal, t.salarioXhora,t.salario_bruto_actual, e.estado from empleado e inner join tipo_empleado t on e.identificacion = t.empleado where e.estado =1;
End;
GO

---------------------------------------------------------------------------
-------------Store Procedures de Obtener Empleados Desactivados------------
---------------------------------------------------------------------------
create procedure sp_Obtener_Todos_Los_Empleados_Desactivados
As
Begin
select e.identificacion, e.nombre, e.apellido, e.apellido2,e.tipo_jornada,t.descripcion_puesto,t.semanal,t.quincenal, t.salarioXhora,t.salario_bruto_actual, e.estado from empleado e inner join tipo_empleado t on e.identificacion = t.empleado where e.estado =0;
End;
GO



---------------------------------------------------------------------------
-------------Store Procedures de desactivar Empleado por cedula------------
---------------------------------------------------------------------------
create procedure sp_Desactivar_Empleado_Por_Identificacion
(
@identificacion int 
)
As
Begin
Update empleado
SET estado=0
WHERE identificacion = @identificacion;
End;
GO
---------------------------------------------------------------------------
-------------Store Procedures de Activar Empleado por cedula---------------
---------------------------------------------------------------------------
create procedure sp_Activar_Empleado_Por_Identificacion
(
@identificacion int 
)
As
Begin
Update empleado
SET estado=1
WHERE identificacion = @identificacion;
End;
GO


Exec sp_Insertar_Empleado_Semanal 10220344,'Conta','Contador','Contadorzin','Diurno','Contador',3300;
Exec sp_Insertar_Empleado_Quincenal 30620937,'Ope','Operador','Operadorzin','Nocturno','Operario',2200;

Exec sp_Obtener_Empleado_Por_Identificacion 10220344;
Exec sp_Obtener_Empleado_Por_Identificacion 30620937;

EXEC sp_Desactivar_Empleado_Por_Identificacion 10220344;

EXEC sp_Actualizar_Empleado 10220344,'Contadorsersin','Contadorin','Contadorzini','Diurno','Contador',1,0,25000,1;

Exec sp_Obtener_Todos_Los_Empleados;



-------------------------------------------------------------------------------------------
-----------------------------------------PLANILLLA-----------------------------------------
-------------------------------------------------------------------------------------------
create table planilla
(
numero int identity(1,1) not null
,usuario int NOT NULL 
,fecha_creacion datetime default CURRENT_TIMESTAMP
,PRiMARY KEY (numero)
,FOREIGN KEY (usuario) REFERENCES usuario(identificacion),
);

create table pago_planilla
(
planilla int not null
,estado_pagado int default 0
,fecha_pago datetime 
,monto_bruto_a_pagar money default 0
,PRiMARY KEY (planilla)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);
GO
--------------------------------
--------TRIGGER PLANILLA--------
--------------------------------

CREATE TRIGGER planilla_trigger
ON  planilla
AFTER INSERT 
AS 
BEGIN 
DECLARE @NEWnumero int
SET NOCOUNT ON;
SELECT @NEWnumero= i.numero from inserted i;
INSERT INTO pago_planilla(planilla) VALUES (@NEWnumero);
END;
GO




CREATE TRIGGER pago_planilla_updated_trigger
ON pago_planilla
AFTER UPDATE
AS 
BEGIN 
DECLARE @NEWplanilla int
SET NOCOUNT ON;
SELECT @NEWplanilla = i.planilla from inserted i;

if((select estado_pagado from pago_planilla where planilla = @NEWplanilla )=1)
BEGIN
UPDATE pago_planilla
SET
fecha_pago= CURRENT_TIMESTAMP
WHERE planilla = @NEWplanilla;
END;
END;
GO

/*
update pago_planilla
set estado_pagado=1
where planilla = 4;


select * from pago_planilla where planilla = 4;
*/

--------------------------------------------------------------------------
---------------Store Procedure de Crear planilla por Usuario--------------
--------------------------------------------------------------------------

create procedure sp_Insertar_Plantilla
(
@usuario int 
)
As
Begin
Declare @numero_planilla int;

INSERT INTO planilla(usuario) VALUES (@usuario);
SET @numero_planilla =SCOPE_IDENTITY();
select @numero_planilla as 'numero_planilla';
End;
GO
----------------------------------------------------------------------------
---------------Store Procedure de obtener planilla por Usuario--------------
----------------------------------------------------------------------------
create procedure sp_Obtener_Plantilla
(
@usuario int 
)
As
Begin
SELECT p.numero, p.fecha_creacion, p.usuario, pp.estado_pagado, pp.fecha_pago, pp.monto_bruto_a_pagar FROM planilla p, pago_planilla pp WHERE p.usuario = @usuario and pp.planilla=p.numero;
End;
GO
----------------------------------------------------------------------------
---------------Store Procedure de obtener todas las planilla----------------
----------------------------------------------------------------------------
create procedure sp_Obtener_Todas_Las_Planillas
As
Begin
select
p.numero,
p.fecha_creacion,
p.usuario,
pp.estado_pagado,
pp.fecha_pago,
pp.monto_bruto_a_pagar
from planilla p, pago_planilla pp
where p.numero=pp.planilla;
End;
GO



--PRUEBA CREAR PLANILLA
Exec sp_Insertar_Plantilla 891426789;

-------------------------------------------------------------------------------------------
-------------------------------------EMPLEADO_PLANILLA-------------------------------------
-------------------------------------------------------------------------------------------
create table empleado_planilla
(
empleado int NOT NULL
,planilla int not null
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);


------------------------------------------------
------BENEFICIOS DE EMPLEADO POR PLANILLA-------
------------------------------------------------
create table beneficios
(
empleado int NOT NULL
,planilla int not null
 ,acronimo varchar(255)
,descripcion_beneficios varchar(255)
,porcentaje_beneficios float
,pagaEmpleado int default 1
,proceso int default 0
,monto_a_reducir money default 0
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);
GO

--------------------------------------------------------------
------IMPUESTOS SOBRE LA RENTA DE EMPLEADO POR PLANILLA-------
--------------------------------------------------------------
create table renta
(
empleado int NOT NULL
,planilla int not null
,descripcion_renta varchar(255)
,porcentaje_deduccion float
,aplica int default 0
,monto_a_reducir money default 0
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);
GO


------------------------------------------------------
------CATEGORIA DE PAGO POR PLANILLA Y EMPLEADO-------
------------------------------------------------------
create table categoria_pago
(
empleado int NOT NULL
,planilla int not null
,descripcion_categoria varchar(255)
,porcentaje_categoria float
,cantidadhoras int default 0
,monto_a_pagar money default 0
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);
GO

create table salarioxHora_Empleado_Planilla
(
empleado int NOT NULL
,planilla int not null
,SalarioxHora money default 0
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);

create table salario_bruto
(
empleado int NOT NULL
,planilla int not null
,salario_bruto money default 0
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);

create table salario_neto
(
empleado int NOT NULL
,planilla int not null
,salario_neto money default 0
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);

create table monto_renta
(
empleado int NOT NULL
,planilla int not null
,monto_renta money default 0
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);

create table monto_beneficios
(
empleado int NOT NULL
,planilla int not null
,monto_beneficios money default 0
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);

create table monto_categoria_pago
(
empleado int NOT NULL
,planilla int not null
,monto_categoria_pago money default 0
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion)
,FOREIGN KEY (planilla) REFERENCES planilla(numero),
);



create table tipo_planilla_empleado
(
planilla int not null
,empleado int NOT NULL
,quincenal int default 0
,semanal int default 0
,periodo_inicio date
,periodo_final date
,FOREIGN KEY (planilla) REFERENCES planilla(numero)
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion),
);

create table turno_planilla_empleado
(
planilla int not null
,empleado int NOT NULL
,diurno int default 0
,nocturno int default 0
,mixto int default 0
,FOREIGN KEY (planilla) REFERENCES planilla(numero)
,FOREIGN KEY (empleado) REFERENCES empleado(identificacion),
);
GO



-----------------------------------------
--------EMPLEADO PlANILLA TRIGGERS-------
-----------------------------------------

-----------------------------------------
---------------AFTER INSERT--------------
-----------------------------------------
CREATE TRIGGER empleado_planilla_trigger
ON  empleado_planilla
AFTER INSERT 
AS 
BEGIN 
DECLARE @NEWempleado int,
		@NEWplanilla int,
		@salario_bruto money,
		@renta money
SET NOCOUNT ON;
SELECT @NEWempleado= i.empleado from inserted i;
SELECT @NEWplanilla= i.planilla from inserted i;

INSERT INTO beneficios(empleado, planilla, acronimo,descripcion_beneficios,porcentaje_beneficios,proceso) VALUES (@NEWempleado, @NEWplanilla, 'ccss','Caja Costarricense del Seguro Social',9.25,1);
INSERT INTO beneficios(empleado, planilla, acronimo,descripcion_beneficios,porcentaje_beneficios) VALUES (@NEWempleado, @NEWplanilla, 'Asociación','Asociación',8);
INSERT INTO beneficios(empleado, planilla, acronimo,descripcion_beneficios,porcentaje_beneficios) VALUES (@NEWempleado, @NEWplanilla, 'Pensión','Juzgado',0);
INSERT INTO beneficios(empleado, planilla, acronimo,descripcion_beneficios,porcentaje_beneficios) VALUES (@NEWempleado, @NEWplanilla, 'Colegiaturas','Colegios Profesionales',4);
INSERT INTO beneficios(empleado, planilla, acronimo,descripcion_beneficios,porcentaje_beneficios) VALUES (@NEWempleado, @NEWplanilla, 'Embargos','Embargos',0);
INSERT INTO beneficios(empleado, planilla, acronimo,descripcion_beneficios,porcentaje_beneficios) VALUES (@NEWempleado, @NEWplanilla, 'Impuestos','Impuestos al salario',0);
INSERT INTO beneficios(empleado, planilla, acronimo,descripcion_beneficios,porcentaje_beneficios) VALUES (@NEWempleado, @NEWplanilla, 'Soda','Soda',0);

INSERT INTO categoria_pago(empleado, planilla, descripcion_categoria,porcentaje_categoria) VALUES(@NEWempleado, @NEWplanilla, 'Horas Ordinarias',1.00);
INSERT INTO categoria_pago(empleado, planilla, descripcion_categoria,porcentaje_categoria) VALUES(@NEWempleado, @NEWplanilla, 'Horas Extras',1.50);
INSERT INTO categoria_pago(empleado, planilla, descripcion_categoria,porcentaje_categoria) VALUES(@NEWempleado, @NEWplanilla, 'Horas Dobles',2.00);
INSERT INTO categoria_pago(empleado, planilla, descripcion_categoria,porcentaje_categoria) VALUES(@NEWempleado, @NEWplanilla, 'Incapacidades de C.C.S.S',0.50);
INSERT INTO categoria_pago(empleado, planilla, descripcion_categoria,porcentaje_categoria) VALUES(@NEWempleado, @NEWplanilla, 'Incapacidades del INS',0.50);
INSERT INTO categoria_pago(empleado, planilla, descripcion_categoria,porcentaje_categoria) VALUES(@NEWempleado, @NEWplanilla, 'Vacaciones',1);
INSERT INTO categoria_pago(empleado, planilla, descripcion_categoria,porcentaje_categoria) VALUES(@NEWempleado, @NEWplanilla, 'Licencias Por Maternidad',0.50);

INSERT INTO renta(empleado, planilla,descripcion_renta,porcentaje_deduccion,aplica) VALUES(@NEWempleado, @NEWplanilla,'Menos de 842.000 colones mensuales',0,0);
INSERT INTO renta(empleado, planilla,descripcion_renta,porcentaje_deduccion,aplica) VALUES(@NEWempleado, @NEWplanilla,'De 842.000 hasta 1,236.000 colones mensuales',10,0);
INSERT INTO renta(empleado, planilla,descripcion_renta,porcentaje_deduccion,aplica) VALUES(@NEWempleado, @NEWplanilla,'De 1,236.000 hasta 2,169.000 colones mensuales',15,0);
INSERT INTO renta(empleado, planilla,descripcion_renta,porcentaje_deduccion,aplica) VALUES(@NEWempleado, @NEWplanilla,'De 2,169.000 hasta 4,337.000 colones mensuales',20,0);
INSERT INTO renta(empleado, planilla,descripcion_renta,porcentaje_deduccion,aplica) VALUES(@NEWempleado, @NEWplanilla,'Más de 4,337.000 colones mensuales',25,0);

INSERT INTO salario_bruto(empleado, planilla) VALUES (@NEWempleado, @NEWplanilla); 
INSERT INTO salario_neto(empleado, planilla) VALUES (@NEWempleado, @NEWplanilla);
INSERT INTO salarioxHora_Empleado_Planilla(empleado, planilla) VALUES (@NEWempleado, @NEWplanilla);
INSERT INTO monto_beneficios (empleado, planilla) VALUES (@NEWempleado, @NEWplanilla);
INSERT INTO monto_renta(empleado, planilla) VALUES (@NEWempleado, @NEWplanilla);
INSERT INTO monto_categoria_pago(empleado, planilla) VALUES (@NEWempleado, @NEWplanilla);

INSERT INTO tipo_planilla_empleado(empleado, planilla) VALUES (@NEWempleado, @NEWplanilla);
INSERT INTO turno_planilla_empleado(empleado, planilla) VALUES (@NEWempleado, @NEWplanilla);





IF (SELECT t.semanal FROM tipo_empleado t WHERE empleado = @NEWempleado ) =1
BEGIN

UPDATE tipo_planilla_empleado
SET
semanal=1
WHERE planilla = @NEWplanilla and empleado=@NEWempleado;

UPDATE categoria_pago
SET
cantidadhoras = 48
WHERE descripcion_categoria=  'Horas Ordinarias' AND planilla = @NEWplanilla and empleado=@NEWempleado;
END
ELSE

BEGIN


UPDATE tipo_planilla_empleado
SET
quincenal=1
WHERE planilla = @NEWplanilla and empleado=@NEWempleado;


UPDATE categoria_pago
SET
cantidadhoras = 96
WHERE descripcion_categoria=  'Horas Ordinarias' AND planilla = @NEWplanilla and empleado=@NEWempleado;
END;

IF (SELECT e.tipo_jornada FROM empleado e WHERE e.identificacion = @NEWempleado ) ='diurno'
BEGIN
UPDATE turno_planilla_empleado
SET
diurno = 1
WHERE planilla = @NEWplanilla and empleado=@NEWempleado;
END;


IF (SELECT e.tipo_jornada FROM empleado e WHERE e.identificacion = @NEWempleado ) ='nocturno'
BEGIN
UPDATE turno_planilla_empleado
SET
nocturno = 1
WHERE planilla = @NEWplanilla and empleado=@NEWempleado;
END;

IF (SELECT e.tipo_jornada FROM empleado e WHERE e.identificacion = @NEWempleado ) ='mixto'
BEGIN
UPDATE turno_planilla_empleado
SET
mixto = 1
WHERE planilla = @NEWplanilla and empleado=@NEWempleado;
END;
UPDATE salarioxHora_Empleado_Planilla
SET
SalarioxHora = (select salarioXhora from tipo_empleado where empleado=@NEWempleado)
WHERE planilla = @NEWplanilla and empleado=@NEWempleado;
--CALCULO DE SALARIO BRUTO EN TRIGGER
UPDATE salario_bruto
SET
salario_bruto = (select cantidadhoras from categoria_pago where planilla= @NEWplanilla  and empleado=@NEWempleado and descripcion_categoria=  'Horas Ordinarias')
               *(select porcentaje_categoria from categoria_pago where planilla = @NEWplanilla   and empleado=@NEWempleado  and descripcion_categoria=  'Horas Ordinarias') 
               *(select salarioXhora from tipo_empleado where empleado=@NEWempleado)
WHERE  planilla = @NEWplanilla and empleado=@NEWempleado;

UPDATE monto_categoria_pago
SET monto_categoria_pago = (select salario_bruto from salario_bruto WHERE planilla = @NEWplanilla and empleado=@NEWempleado)
WHERE  planilla = @NEWplanilla and empleado=@NEWempleado;

SELECT @salario_bruto =  salario_bruto FROM salario_bruto WHERE planilla = @NEWplanilla and empleado=@NEWempleado;

--CALCULO PARA APLICAR LA RENTA EN TRIGGER

set @renta = 0;
If(@salario_bruto <= 842000)
BEGIN
UPDATE renta
SET
aplica =1
WHERE descripcion_renta = 'Menos de 842.000 colones mensuales' and planilla = @NEWplanilla and empleado=@NEWempleado;
END;

If(@salario_bruto > 842000 and @salario_bruto <= 1236000)
BEGIN
SET @renta = @salario_bruto * ((select porcentaje_deduccion from renta where descripcion_renta = 'De 842.000 hasta 1,236.000 colones mensuales' and planilla = @NEWplanilla and empleado=@NEWempleado)/100);
UPDATE renta
SET
aplica =1
,monto_a_reducir = @renta
WHERE descripcion_renta = 'De 842.000 hasta 1,236.000 colones mensuales' and planilla = @NEWplanilla and empleado=@NEWempleado;
END;

If(@salario_bruto > 1236000 and @salario_bruto <= 2169000)
BEGIN
set @renta = @salario_bruto * ((select porcentaje_deduccion from renta where descripcion_renta = 'De 1,236.000 hasta 2,169.000 colones mensuales' and planilla = @NEWplanilla and empleado=@NEWempleado)/100);
UPDATE renta
SET
aplica =1
,monto_a_reducir = @renta
WHERE descripcion_renta = 'De 1,236.000 hasta 2,169.000 colones mensuales' and planilla = @NEWplanilla and empleado=@NEWempleado;
END;

If(@salario_bruto > 2169000 and @salario_bruto <= 4337000)
BEGIN
set @renta =@salario_bruto * ((select porcentaje_deduccion from renta where descripcion_renta = 'De 2,169.000 hasta 4,337.000 colones mensuales' and planilla = @NEWplanilla and empleado=@NEWempleado)/100);
UPDATE renta
SET
aplica =1
,monto_a_reducir = @renta
WHERE descripcion_renta = 'De 2,169.000 hasta 4,337.000 colones mensuales' and planilla = @NEWplanilla and empleado=@NEWempleado;
END;


If(@salario_bruto > 4337000)
BEGIN
set @renta = @salario_bruto * ((select porcentaje_deduccion from renta where descripcion_renta = 'Más de 4,337.000 colones mensuales' and planilla = @NEWplanilla and empleado=@NEWempleado)/100); 
UPDATE renta
SET
aplica =1
,monto_a_reducir = @renta
WHERE descripcion_renta = 'Más de 4,337.000 colones mensuales' and planilla = @NEWplanilla and empleado=@NEWempleado;
END;

--INSERTAR MONTO DE RENTA
UPDATE monto_renta
SET
monto_renta = @renta
WHERE  planilla = @NEWplanilla and empleado=@NEWempleado;
--CALCULO DE REBAJA DE A CAJA A SER AUTOMATICO PARA TODOS
UPDATE beneficios
SET monto_a_reducir = (select salario_bruto from salario_bruto where  planilla = @NEWplanilla and empleado=@NEWempleado)*((select porcentaje_beneficios from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'ccss')/100)
WHERE planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'ccss';

UPDATE monto_beneficios
SET monto_beneficios = (select monto_a_reducir from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'ccss')
WHERE planilla = @NEWplanilla and empleado=@NEWempleado;

UPDATE categoria_pago
SET
monto_a_pagar = (select salario_bruto from salario_bruto where  planilla = @NEWplanilla and empleado=@NEWempleado)
WHERE descripcion_categoria=  'Horas Ordinarias' AND planilla = @NEWplanilla and empleado=@NEWempleado;

--CALCULO DE SALARIO NETO EN TRIGGER
UPDATE salario_neto
SET
salario_neto = (select salario_bruto from salario_bruto where  planilla = @NEWplanilla and empleado=@NEWempleado)
              -((select salario_bruto from salario_bruto where  planilla = @NEWplanilla and empleado=@NEWempleado)*((select porcentaje_beneficios from beneficios where planilla = @NEWplanilla   and empleado=@NEWempleado  and descripcion_beneficios=  'Caja Costarricense del Seguro Social')/100))
			  -(@renta)
WHERE  planilla = @NEWplanilla and empleado=@NEWempleado;



UPDATE pago_planilla
SET monto_bruto_a_pagar = (select SUM(salario_bruto) from salario_bruto WHERE planilla = @NEWplanilla)
WHERE planilla = @NEWplanilla ;
END;
GO

----------------------------------------------------
---------------AFTER UPDATE BENEFICIOS--------------
----------------------------------------------------
CREATE TRIGGER empleado_planilla_categoria_pago_trigger
ON  beneficios 
AFTER UPDATE 
AS 
BEGIN 
DECLARE @NEWempleado int,
		@NEWplanilla int,
		@salario_bruto money,
		@ccss money,
		@Asociacion money,
		@Pension money,
		@Colegiaturas money,
		@Embargos money,
		@Impuestos money,
		@Soda money,
		@salario_neto money,
		@monto_renta money,
		@monto_beneficios money
SET NOCOUNT ON;
SELECT @NEWempleado= i.empleado from inserted i;
SELECT @NEWplanilla= i.planilla from inserted i;
select @salario_bruto=salario_bruto from salario_bruto where planilla = @NEWplanilla and empleado=@NEWempleado;

Set @ccss = @salario_bruto * ((select porcentaje_beneficios from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'ccss')/100);
set @Asociacion = @salario_bruto * ((select porcentaje_beneficios from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Asociación')/100);
set @Pension = @salario_bruto * ((select porcentaje_beneficios from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Pensión')/100);
set @Colegiaturas =@salario_bruto * ((select porcentaje_beneficios from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Colegiaturas')/100);
set @Embargos =@salario_bruto * ((select porcentaje_beneficios from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Embargos')/100);
set @Impuestos =@salario_bruto * ((select porcentaje_beneficios from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Impuestos')/100);
set @Soda =@salario_bruto * ((select porcentaje_beneficios from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Soda')/100);
set @salario_neto=0;
set @monto_beneficios = 0;

IF(select proceso from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'ccss')=1
BEGIN
set @salario_neto= @salario_neto + @ccss;
set @monto_beneficios = @monto_beneficios + @ccss;
UPDATE beneficios
SET monto_a_reducir=@ccss
WHERE planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'ccss';

END;

IF(select proceso from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Asociación')=1
BEGIN
set @salario_neto= @salario_neto + @Asociacion;
UPDATE beneficios
SET monto_a_reducir=@Asociacion 
WHERE planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Asociación';
set @monto_beneficios = @monto_beneficios + @Asociacion ;
END;
IF(select proceso from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Pensión')=1
BEGIN
set @salario_neto= @salario_neto +@Pension;
UPDATE beneficios
SET monto_a_reducir=@Pension
WHERE planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Pensión';
set @monto_beneficios = @monto_beneficios + @Pension;
END;
IF(select proceso from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Colegiaturas')=1
BEGIN
set @salario_neto= @salario_neto + @Colegiaturas;
UPDATE beneficios
SET monto_a_reducir=@Colegiaturas
WHERE planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Colegiaturas';
set @monto_beneficios = @monto_beneficios + @Colegiaturas;
END;
IF(select proceso from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Embargos')=1
BEGIN
set @salario_neto= @salario_neto + @Embargos;
UPDATE beneficios
SET monto_a_reducir=@Embargos
WHERE planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Embargos';
set @monto_beneficios = @monto_beneficios + @Embargos;
END;
IF(select proceso from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Impuestos')=1
BEGIN
set @salario_neto= @salario_neto + @Impuestos;
UPDATE beneficios
SET monto_a_reducir=@Impuestos
WHERE planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Impuestos';
set @monto_beneficios = @monto_beneficios + @Impuestos;
END;
IF(select proceso from beneficios where planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Soda')=1
BEGIN
set @salario_neto= @salario_neto + @Soda;
UPDATE beneficios
SET monto_a_reducir=@Soda
WHERE planilla = @NEWplanilla and empleado=@NEWempleado and acronimo = 'Soda';
set @monto_beneficios = @monto_beneficios + @Soda;
END;


SET @monto_renta = (select monto_renta from monto_renta  where planilla = @NEWplanilla and empleado=@NEWempleado);

UPDATE salario_neto
SET
salario_neto = @salario_bruto-@salario_neto -@monto_renta 
WHERE planilla = @NEWplanilla and empleado=@NEWempleado;

UPDATE monto_beneficios
SET monto_beneficios = @monto_beneficios
WHERE planilla = @NEWplanilla and empleado=@NEWempleado;
END;
GO


-----------------------------------------------------------------------
---------------AFTER UPDATE CATEGORIA DE PAGO EN EMPLEADO--------------
-----------------------------------------------------------------------
CREATE TRIGGER empleado_planilla_categoria_pago_Updated_trigger
ON  categoria_pago
AFTER UPDATE 
AS 
BEGIN 
DECLARE @NEWempleado int,
		@NEWplanilla int,

		@ordinarias money,
		@extras money,
		@dobles money,
		@incapacidadesCCSS money,
		@incapacidadesINS money,
		@vacaciones money,
		@maternidad money,

		@DiferenciaHoras int,
		@CantidadActualesHorasExtra int,
		@CantidadHorasOrdinarias int,
		@CantidadHorasExtras int,
		@CantidadHorasDobles int,
		@CantidadHorasIncapacidadesCCSS int,
		@CantidadHorasIncapacidadesINS int,
		@CantidadHorasVacaciones int,
		@CantidadHorasMaternidad int,

		@MontoAPagarHorasOrdinarias money,
		@MontoAPagarHorasExtras money,
		@MontoAPagarHorasDobles money,
		@MontoAPagarHorasIncapacidadesCCSS money,
		@MontoAPagarHorasIncapacidadesINS money,
		@MontoAPagarHorasVacaciones money,
		@MontoAPagarHorasMaternidad money
SET NOCOUNT ON;
SELECT @NEWempleado= i.empleado from inserted i;
SELECT @NEWplanilla= i.planilla from inserted i;


SET @ordinarias =(select SalarioxHora from salarioxHora_Empleado_Planilla where empleado = @NEWempleado and planilla = @NEWplanilla)*(select porcentaje_categoria from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias');
SET @extras =(select SalarioxHora from salarioxHora_Empleado_Planilla where empleado = @NEWempleado and planilla = @NEWplanilla)*(select porcentaje_categoria from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Extras');
SET @dobles =(select SalarioxHora from salarioxHora_Empleado_Planilla where empleado = @NEWempleado and planilla = @NEWplanilla)*(select porcentaje_categoria from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Dobles');
SET @incapacidadesCCSS =(select SalarioxHora from salarioxHora_Empleado_Planilla where empleado = @NEWempleado and planilla = @NEWplanilla)*(select porcentaje_categoria from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Incapacidades de C.C.S.S');
SET @incapacidadesINS =(select SalarioxHora from salarioxHora_Empleado_Planilla where empleado = @NEWempleado and planilla = @NEWplanilla)*(select porcentaje_categoria from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Incapacidades del INS');
SET @vacaciones = (select SalarioxHora from salarioxHora_Empleado_Planilla where empleado = @NEWempleado and planilla = @NEWplanilla)*(select porcentaje_categoria from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Vacaciones');
SET @maternidad = (select SalarioxHora from salarioxHora_Empleado_Planilla where empleado = @NEWempleado and planilla = @NEWplanilla)*(select porcentaje_categoria from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Licencias Por Maternidad');


SET @CantidadHorasOrdinarias =(select cantidadhoras from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias');
SET	@CantidadHorasExtras =(select cantidadhoras from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Extras');
SET	@CantidadHorasDobles =(select cantidadhoras from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Dobles');
SET	@CantidadHorasIncapacidadesCCSS =(select cantidadhoras from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Incapacidades de C.C.S.S');
SET	@CantidadHorasIncapacidadesINS =(select cantidadhoras from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Incapacidades del INS');
SET	@CantidadHorasVacaciones =(select cantidadhoras from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Vacaciones');
SET	@CantidadHorasMaternidad =(select cantidadhoras from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Licencias Por Maternidad');

SET @MontoAPagarHorasOrdinarias = @CantidadHorasOrdinarias*@ordinarias;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasOrdinarias
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';


SET	@MontoAPagarHorasExtras = 0;
SET	@MontoAPagarHorasDobles = 0;
SET	@MontoAPagarHorasIncapacidadesCCSS = 0;
SET	@MontoAPagarHorasIncapacidadesINS = 0;
SET	@MontoAPagarHorasVacaciones = 0;
SET	@MontoAPagarHorasMaternidad = 0;



SET @MontoAPagarHorasExtras = @CantidadHorasExtras * @extras;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasExtras
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Extras';




--CALCULO HORAS INCAPACIDADES DE C.C.S.S
if(@CantidadHorasIncapacidadesCCSS>0)
BEGIN
SET @CantidadHorasOrdinarias = @CantidadHorasOrdinarias - @CantidadHorasIncapacidadesCCSS;
UPDATE categoria_pago
SET cantidadhoras = @CantidadHorasOrdinarias
WHERE empleado = @NEWempleado and planilla = @NEWplanilla and descripcion_categoria='Horas Ordinarias';

SET @MontoAPagarHorasOrdinarias = @CantidadHorasOrdinarias*@ordinarias;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasOrdinarias
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';


SET @MontoAPagarHorasIncapacidadesCCSS= @CantidadHorasIncapacidadesCCSS*@incapacidadesCCSS;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasIncapacidadesCCSS
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Incapacidades de C.C.S.S';

END;


--CALCULO HORAS DOBLES
if(@CantidadHorasDobles>0)
BEGIN
SET @CantidadHorasOrdinarias = @CantidadHorasOrdinarias - @CantidadHorasDobles;
UPDATE categoria_pago
SET cantidadhoras = @CantidadHorasOrdinarias
WHERE empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';

SET @MontoAPagarHorasOrdinarias = @CantidadHorasOrdinarias*@ordinarias;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasOrdinarias
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';


SET @MontoAPagarHorasDobles= @CantidadHorasDobles*@dobles;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasDobles
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Dobles';
END;



--CALCULO HORAS INCAPACIDADES DEL INS
if(@CantidadHorasIncapacidadesINS>0)
BEGIN
SET @CantidadHorasOrdinarias = @CantidadHorasOrdinarias - @CantidadHorasIncapacidadesINS;
UPDATE categoria_pago
SET cantidadhoras = @CantidadHorasOrdinarias
WHERE empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';

SET @MontoAPagarHorasOrdinarias = @CantidadHorasOrdinarias*@ordinarias;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasOrdinarias
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';


SET @MontoAPagarHorasIncapacidadesINS= @CantidadHorasIncapacidadesINS*@incapacidadesINS;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasIncapacidadesINS
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Incapacidades del INS';

END;

--CALCULO HORAS VACACIONES
if(@CantidadHorasVacaciones>0)
BEGIN

SET @CantidadHorasOrdinarias = @CantidadHorasOrdinarias - @CantidadHorasVacaciones;
UPDATE categoria_pago
SET cantidadhoras = @CantidadHorasOrdinarias
WHERE empleado = @NEWempleado and planilla = @NEWplanilla and descripcion_categoria='Horas Ordinarias';

SET @MontoAPagarHorasOrdinarias = @CantidadHorasOrdinarias*@ordinarias;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasOrdinarias
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';


SET @MontoAPagarHorasVacaciones= @CantidadHorasVacaciones*@vacaciones;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasVacaciones
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Vacaciones';


END;


--CALCULO HORAS DE LICENCIA DE MATERNIDAD
if(@CantidadHorasMaternidad>0)
BEGIN

SET @CantidadHorasOrdinarias = @CantidadHorasOrdinarias - @CantidadHorasMaternidad;
UPDATE categoria_pago
SET cantidadhoras = @CantidadHorasOrdinarias
WHERE empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';

SET @MontoAPagarHorasOrdinarias = @CantidadHorasOrdinarias*@ordinarias;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasOrdinarias
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';


SET @MontoAPagarHorasMaternidad= @CantidadHorasMaternidad*@maternidad;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasMaternidad
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Licencias Por Maternidad';

END;

--CALCULO DE HORAS EXTRAS PARA EMPLEADO QUINCENAL, SI HAY MAS HORAS DE 96 en total sin contar las extras normales
if((select quincenal from tipo_planilla_empleado where empleado = @NEWempleado and planilla = @NEWplanilla)=1)
BEGIN
if ((@CantidadHorasOrdinarias+@CantidadHorasDobles+@CantidadHorasIncapacidadesCCSS+@CantidadHorasIncapacidadesINS+@CantidadHorasVacaciones+@CantidadHorasMaternidad)>96)
BEGIN
SET @DiferenciaHoras = @CantidadHorasOrdinarias- (96-@CantidadHorasDobles-@CantidadHorasIncapacidadesCCSS-@CantidadHorasIncapacidadesINS-@CantidadHorasVacaciones-@CantidadHorasMaternidad); 
SET @CantidadActualesHorasExtra = @CantidadHorasExtras;

UPDATE categoria_pago
SET cantidadhoras = (@CantidadActualesHorasExtra + @DiferenciaHoras)
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Extras';

SET @MontoAPagarHorasExtras = (select cantidadhoras from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Extras') * @extras;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasExtras
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Extras';

If(@CantidadHorasOrdinarias>96)
BEGIN
UPDATE categoria_pago
SET cantidadhoras=96
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';
SET @MontoAPagarHorasOrdinarias=96*@ordinarias;

UPDATE categoria_pago
SET monto_a_pagar= @MontoAPagarHorasOrdinarias
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';
END;
END;
END;

--CALCULO DE HORAS EXTRAS PARA EMPLEADO SEMANAL, SI HAY MAS HORAS DE 48 en total sin contar las extras normales
if((select semanal from tipo_planilla_empleado where empleado = @NEWempleado and planilla = @NEWplanilla)=1)
BEGIN
if ((@CantidadHorasOrdinarias+@CantidadHorasDobles+@CantidadHorasIncapacidadesCCSS+@CantidadHorasIncapacidadesINS+@CantidadHorasVacaciones+@CantidadHorasMaternidad)>48)
BEGIN
SET @DiferenciaHoras = @CantidadHorasOrdinarias- (48-@CantidadHorasDobles-@CantidadHorasIncapacidadesCCSS-@CantidadHorasIncapacidadesINS-@CantidadHorasVacaciones-@CantidadHorasMaternidad); 
SET @CantidadActualesHorasExtra = @CantidadHorasExtras;

UPDATE categoria_pago
SET cantidadhoras = (@CantidadActualesHorasExtra + @DiferenciaHoras)
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Extras';

SET @MontoAPagarHorasExtras = (select cantidadhoras from categoria_pago where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Extras') * @extras;

UPDATE categoria_pago
SET monto_a_pagar = @MontoAPagarHorasExtras
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Extras';

If(@CantidadHorasOrdinarias>48)
BEGIN
UPDATE categoria_pago
SET cantidadhoras=48
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';
SET @MontoAPagarHorasOrdinarias=48*@ordinarias;

UPDATE categoria_pago
SET monto_a_pagar= @MontoAPagarHorasOrdinarias
where empleado = @NEWempleado and planilla = @NEWplanilla  and descripcion_categoria='Horas Ordinarias';
END;
END;
END;


UPDATE monto_categoria_pago
SET monto_categoria_pago = @MontoAPagarHorasOrdinarias 
						 + @MontoAPagarHorasExtras 
						 + @MontoAPagarHorasDobles 
						 + @MontoAPagarHorasIncapacidadesCCSS 
						 + @MontoAPagarHorasIncapacidadesINS 
						 + @MontoAPagarHorasVacaciones 
						 + @MontoAPagarHorasMaternidad 
where empleado = @NEWempleado and planilla = @NEWplanilla;


--(select salario_bruto from salario_bruto WHERE empleado = @NEWempleado and planilla = @NEWplanilla)-
UPDATE salario_neto
SET salario_neto = (select monto_categoria_pago from monto_categoria_pago WHERE empleado = @NEWempleado and planilla = @NEWplanilla)- (select monto_beneficios from monto_beneficios WHERE empleado = @NEWempleado and planilla = @NEWplanilla) - (select monto_renta from monto_renta WHERE empleado = @NEWempleado and planilla = @NEWplanilla)
WHERE empleado = @NEWempleado and planilla = @NEWplanilla;
END;
GO
---------------------------------------------------------------------------------
---------------Store Procedure para actualizar la categoria de pago--------------
---------------------------------------------------------------------------------
create procedure sp_Actualizar_Cantidad_De_Horas_En_Categoria_De_Pago
(
@empleado int
,@planilla int 
,@descripcion_categoria varchar(255)
,@cantidadhoras int
)
As
Begin
UPDATE categoria_pago
SET cantidadhoras = @cantidadhoras
where empleado = @empleado and planilla = @planilla  and descripcion_categoria = @descripcion_categoria;
End;
GO


/*
EXEC sp_Actualizar_Cantidad_De_Horas_En_Categoria_De_Pago 10220344, 1,'Incapacidades de C.C.S.S',6;

--PRUEBAS AL TRIGGER
UPDATE categoria_pago
SET cantidadhoras =6
where empleado = 10220344 and planilla = 1  and descripcion_categoria='Incapacidades de C.C.S.S';

select * from categoria_pago where empleado = 10220344 and planilla = 1 ;

select * from monto_categoria_pago where empleado = 10220344 and planilla = 1 ;
GO
*/
---------------------------------------------------------------------------
---------------Store Procedure para actualizar los Beneficios--------------
---------------------------------------------------------------------------
create procedure sp_Actualizar_Beneficios
(
@empleado int
,@planilla int 
,@acronimo varchar(255)
,@descripcion_beneficios varchar(255)
,@porcentaje_beneficios float
,@pagaEmpleado int 
,@proceso int 
)
As
Begin
UPDATE beneficios
SET
porcentaje_beneficios =@porcentaje_beneficios
,pagaEmpleado=@pagaEmpleado 
,proceso =@proceso
,descripcion_beneficios = @descripcion_beneficios
WHERE planilla = @planilla and empleado=@empleado and acronimo = @acronimo;
End;
GO
----------------------------------------------------------------------------------------------------------
---------------Store Procedure para Obtener todos los Beneficios de un empleado por planilla--------------
----------------------------------------------------------------------------------------------------------
create procedure sp_Obtener_Beneficios_Empleado_por_Planilla
(
@empleado int
,@planilla int 
)
As
Begin
select b.empleado
,b.planilla 
,b.acronimo 
,b.descripcion_beneficios
,b.porcentaje_beneficios 
,b.pagaEmpleado  
,b.proceso 
,b.monto_a_reducir
from beneficios b 
where empleado = @empleado and planilla =@planilla;
End;
GO

------------------------------------------------------------------------------------------------------------------
---------------Store Procedure para Obtener todas las categorias de pago de un empleado por planilla--------------
------------------------------------------------------------------------------------------------------------------
create procedure sp_Obtener_Categoria_Pago_Empleado_por_Planilla
(
@empleado int
,@planilla int 
)
As
Begin
select c.empleado
,c.planilla 
,c.descripcion_categoria
,c.porcentaje_categoria
,c.cantidadhoras
,c.monto_a_pagar
from categoria_pago c
where empleado = @empleado and planilla =@planilla;
End;
GO
------------------------------------------------------------------------------------------------------------------
---------------------------Store Procedure para Obtener renta de un empleado por planilla-------------------------
------------------------------------------------------------------------------------------------------------------
create procedure sp_Obtener_renta_Empleado_por_Planilla
(
@empleado int
,@planilla int 
)
As
Begin
select r.planilla,
       r.empleado,
	   r.descripcion_renta,
	   r.porcentaje_deduccion,
	   r.aplica,
	   r.monto_a_reducir
from renta r
where empleado = @empleado and planilla =@planilla;
End;
GO

----------------------------------------------------------------------------
---------------Store Procedure de obtener Empleados por planilla----------------
----------------------------------------------------------------------------
create procedure sp_Obtener_Empleados_Por_Planilla
(
@planilla int 
)
As
Begin

select e.identificacion,
	   e.nombre,
	   e.apellido,
	   e.apellido2,
	   e.tipo_jornada,
	   tp.semanal,
	   tp.quincenal,
	   tp.descripcion_puesto,
	   tp.salarioXhora,
	   tp.salario_bruto_actual,
	   e.estado
from  empleado e
	  inner join
	  tipo_empleado tp
	  on e.identificacion = tp.empleado
	  inner join
	  empleado_planilla ep 
	  on e.identificacion = ep.empleado
	  Where  ep.planilla=@planilla;
End;
GO








---Prueba meter un empleado a la primer planilla
insert into empleado_planilla (empleado,planilla) values (10220344,1);
insert into empleado_planilla (empleado,planilla) values (30620937,1);



EXEC sp_Obtener_Empleados_Por_Planilla 1;

Exec sp_Obtener_Plantilla 891426789;
select * from Planilla;


EXEC sp_Obtener_Categoria_Pago_Empleado_por_Planilla 10220344,1;
EXEC sp_Obtener_Categoria_Pago_Empleado_por_Planilla 30620937,1;

Exec sp_Obtener_Beneficios_Empleado_por_Planilla 10220344,1;
Exec sp_Obtener_Beneficios_Empleado_por_Planilla 30620937,1;

EXEC sp_Obtener_renta_Empleado_por_Planilla 10220344,1;
EXEC sp_Obtener_renta_Empleado_por_Planilla 30620937,1;

/*
Exec sp_Actualizar_Beneficios 10220344,1 ,'Asociación','Asociación',8,1,1;  




select * from renta where empleado=10220344 and planilla=1 ;
select * from renta where empleado=30620937 and planilla=1 ;


select * from tipo_empleado where empleado=10220344;
select  * from monto_beneficios where empleado=10220344 and planilla=1 ;
select  * from monto_renta where empleado=10220344 and planilla=1 ;
select * from salarioxHora_Empleado_Planilla where empleado=10220344 and planilla=1 ;

select * from monto_categoria_pago where  empleado=30620937 and planilla=1 ;



--VER Salarios brutos de empleados por planilla
select salarioXhora from tipo_empleado where empleado=10220344;
select salarioXhora from tipo_empleado where empleado=30620937;

select salario_bruto from salario_bruto where empleado = 10220344 and planilla = 1;
select salario_bruto from salario_bruto where empleado = 30620937 and planilla = 1;

--VER Salarios netos de empleados por planilla
select salario_neto from salario_neto where empleado = 10220344 and planilla = 1;
select salario_neto from salario_neto where empleado = 30620937 and planilla = 1;

select * from beneficios where empleado = 10220344 and planilla =1;

Exec sp_Actualizar_Beneficios 10220344,1 ,'Asociación','Asociación',8,1,1;  

update beneficios
set proceso =1
where planilla = 1 and empleado=10220344 and acronimo = 'Asociación'
select * from beneficios where empleado = 10220344 and planilla =1;

update beneficios
set proceso =1
where planilla = 1 and empleado=10220344 and acronimo = 'ccss'

update beneficios
set proceso =1
where planilla = 1 and empleado=10220344 and acronimo = 'Colegiaturas'
select * from beneficios where empleado = 10220344 and planilla =1;

select salario_bruto from salario_bruto where empleado = 10220344 and planilla = 1;
select salario_neto from salario_neto where empleado = 10220344 and planilla = 1;

--CALCULO DE Prueba para SALARIO BRUTO en TRIGGER
UPDATE salario_bruto
SET
salario_bruto = (select porcentaje_categoria from categoria_pago where planilla = 1  and empleado=30620937 and descripcion_categoria=  'Horas Ordinarias') 
              * (select cantidadhoras from categoria_pago where planilla= 1  and empleado=30620937 and descripcion_categoria=  'Horas Ordinarias')
			 *(select salarioXhora from tipo_empleado where empleado=30620937)
WHERE  planilla = 1 and empleado=30620937;

---Prueba ver datos en la planilla por empleado
select * from salario_bruto where empleado= 10220344 and planilla =1;
select * from beneficios where empleado = 10220344 and planilla =1;
select * from categoria_pago where empleado = 10220344 and planilla =1;
select * from salario_neto where empleado = 10220344 and planilla =1;

---Prueba ver datos en la planilla por empleado
select * from salario_bruto where empleado= 30620937 and planilla =1;
select * from beneficios where empleado = 30620937 and planilla =1;
select * from categoria_pago where empleado = 30620937 and planilla =1;
select * from salario_neto where empleado = 30620937 and planilla =1;


select * from tipo_empleado where empleado =10220344;
select * from tipo_planilla_empleado where planilla =1 and empleado =10220344;
select * from  turno_planilla_empleado where planilla=1 and empleado =10220344;

select * from tipo_empleado where empleado =30620937;
select * from  tipo_planilla_empleado where planilla=1 and empleado =30620937;
select * from  turno_planilla_empleado where planilla=1 and empleado =30620937;

select * from categoria_pago where planilla =1 and empleado =10220344;
select * from categoria_pago where planilla =1 and empleado =30620937;

*/




