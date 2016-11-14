-- DROP DATABASE TP_IntegracionDeAplicaciones
-- CREATE DATABASE TP_IntegracionDeAplicaciones

SELECT * FROM Modulos

SELECT L.*, M.nombre, M.descripcion FROM Logs L INNER JOIN Modulos M ON L.idModulo = M.idModulo

SELECT V.*, M.nombre, M.descripcion FROM Ventas V INNER JOIN Modulos M ON V.idModulo = M.idModulo
SELECT * FROM ItemsVenta

SELECT * FROM Articulos

SELECT * FROM OrdenesDespacho
-- DELETE FROM OrdenesDespacho

SELECT * FROM Clientes C
	INNER JOIN Direcciones D ON C.idDireccion = D.idDireccion
	INNER JOIN Coordenadas Co ON D.idCoordenada = Co.idCoordenada
WHERE C.dni = 35139935

SELECT * FROM Direcciones WH