<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.2.1/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.2.1/material.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function testModal(){
		$('#table1').hide();
	}
</script>
<script>
		      function initMap() {
				var bounds = new google.maps.LatLngBounds();
		        var coordenadasDespacho = {lat: ${despachoCercano.direccion.coordenada.latitud}, lng: ${despachoCercano.direccion.coordenada.latitud}};
		        var coordenadasCliente = {lat: ${ventaSeleccionada.cliente.direccion.coordenada.latitud}, lng: ${ventaSeleccionada.cliente.direccion.coordenada.longitud}};
		        var map = new google.maps.Map(document.getElementById('map'), {
		          zoom: 15,
// 		          center: coordenadasDespacho
		        });
		        var markerDespacho = new google.maps.Marker({
		          position: coordenadasDespacho,
		          map: map
		        });
		        var markerCliente = new google.maps.Marker({
			      position: coordenadasCliente,
			      map: map
			    });
		        bounds.extend(markerDespacho.position);
		        bounds.extend(markerCliente.position);
				map.fitBounds(bounds);
		      }
		    </script>
<script async defer
src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAgyt5OuhzUnYTYVaYC0TZDwXjN_Ha4uH8&callback=initMap">
</script>
<style>
	#map {
	  height: 400px;
	  width: 100%;
	 }
</style>
<title>Asignar Despacho</title>
</head>
<body>
	<!-- Always shows a header, even in smaller screens. -->
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header">
		<div class="mdl-layout__header-row">
			<!-- Title -->
			<span class="mdl-layout-title">Asignar Despacho</span>
			<!-- Add spacer, to align navigation to the right -->
			<div class="mdl-layout-spacer"></div>
		</div>
		</header>
		<div class="mdl-layout__drawer">
			<span class="mdl-layout-title">Menu</span>
			<nav class="mdl-navigation">
				<a class="mdl-navigation__link" onclick="window.location.href='Test'">Ordenes de Despacho</a> 
				<a class="mdl-navigation__link" onclick="window.location.href='BestSellers'">Best Sellers</a>
				<a class="mdl-navigation__link" onclick="window.location.href='Test'">Reporte de ventas</a>
				<a class="mdl-navigation__link" onclick="window.location.href='Test'">Reporte de auditoria</a>
			</nav>
		</div>
		<main class="mdl-layout__content table-responsive"> 
		<!-- Your content goes here -->
			<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp"
				style="margin: 0 auto; top: 40px" id="table1">
				<thead>
					<tr>
						<th class="mdl-data-table__cell--non-numeric">Id</th>
						<th class="mdl-data-table__cell--non-numeric">Cliente</th>
						<th class="mdl-data-table__cell--non-numeric">Portal</th>
						<th class="mdl-data-table__cell--non-numeric">Despacho</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<form action="ConfirmarAsignacionDespacho" method="post">
						<input type="text" name="idVenta" value="${ventaSeleccionada.id}" hidden>
						<input type="text" name="nombrePortal" value="${ventaSeleccionada.nombrePortal}" hidden>
						<td class="mdl-data-table__cell--non-numeric">${ventaSeleccionada.id}</td>
						<td class="mdl-data-table__cell--non-numeric">${ventaSeleccionada.cliente.nombre}</td>
						<td class="mdl-data-table__cell--non-numeric">${ventaSeleccionada.nombrePortal}</td>
						<td class="mdl-data-table__cell--non-numeric">
							<select name="combo" onchange="">
								<c:forEach var="despacho" items="${comboDespachos}">
										<option value="${despacho.nombre}">${despacho.descripcion} - ${despacho.direccion.calle} ${despacho.direccion.altura}</option>
								</c:forEach>
							</select>
						</td>
						<td class="mdl-data-table__cell--non-numeric">
							<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" type="submit">Asociar Despacho</button>
						</td>
					</form>
					</tr>
				</tbody>
			</table>
			<br>
			<br>
			<br>
			<h3>Ubicacion origen venta y despacho seleccionado</h3>
		    <div id="map"></div>
	</main>
	<footer style="line-height: 50px;padding: 0 20px;margin-bottom: 60px;">
		<button id="tt3" style="float: right; margin-top: 10px" type="submit"
			class="mdl-button mdl-js-button mdl-button--fab mdl-button--colored">
			<i class="material-icons">add</i>
		</button>
	</form>
	<div class="mdl-tooltip" data-mdl-for="tt3">Establecer Despacho</div>
	</footer>
	</div>
</body>
</html>