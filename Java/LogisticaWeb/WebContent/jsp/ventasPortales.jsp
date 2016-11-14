<%@page import="com.logistica.dtos.ResumenPortalDTO"%>
<%@page import="com.logistica.dtos.VentaDTO"%>
<%@page import="java.util.List"%>
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
<title>Resumen Portales</title>
</head>
<body>
<%
	List<ResumenPortalDTO> resumenPortales = (List<ResumenPortalDTO>) request.getAttribute("resumenPortal");	
%>
	<!-- Always shows a header, even in smaller screens. -->
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header">
		<div class="mdl-layout__header-row">
			<!-- Title -->
			<span class="mdl-layout-title">Resumen Portales</span>
			<!-- Add spacer, to align navigation to the right -->
			<div class="mdl-layout-spacer"></div>
			<!-- Navigation. We hide it in small screens. -->
			<nav class="mdl-navigation mdl-layout--large-screen-only"> <!-- 					<a class="mdl-navigation__link" onclick="window.location.href='Test'">Ordenes de Despacho</a> -->
			<!-- 					<a class="mdl-navigation__link" onclick="window.location.href='Test'">Best Sellers</a> -->
			<!-- 					<a class="mdl-navigation__link" onclick="window.location.href='Test'">Reporte de ventas</a> -->
			<!-- 					<a class="mdl-navigation__link" onclick="window.location.href='Test'">Reporte de auditoria</a> -->
			</nav>
		</div>
		</header>
		<div class="mdl-layout__drawer">
			<span class="mdl-layout-title">Menu</span>
			<nav class="mdl-navigation"> <a class="mdl-navigation__link"
				onclick="window.location.href='Test'">Ordenes de Despacho</a> <a
				class="mdl-navigation__link"
				onclick="window.location.href='BestSellers'">Best Sellers</a> <a
				class="mdl-navigation__link" onclick="window.location.href='Test'">Reporte
				de ventas</a> <a class="mdl-navigation__link"
				onclick="window.location.href='Test'">Reporte de auditoria</a> </nav>
		</div>
		<main class="mdl-layout__content table-responsive"> 
		<!-- Your content goes here -->
		<% for(ResumenPortalDTO resumen: resumenPortales){%>
			<br>
			<br>
			<br>
			<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp"
				style="margin: 0 auto; top: 40px">
				<thead>
					<tr>
						<th class="mdl-data-table__cell--non-numeric">Portal</th>
						<th class="mdl-data-table__cell--non-numeric">Total</th>
					</tr>
				</thead>
				<tbody>
				<tr>
							<td class="mdl-data-table__cell--non-numeric"><%=resumen.getPortal().getNombre()%></td>
							<td class="mdl-data-table__cell--non-numeric"><%=resumen.getMontoTotal()%></td>
				</tr>	
				</tbody>
				</table>		
				<%for(VentaDTO venta: resumen.getVentas()){%>
				
				<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp"
				style="margin: 0 auto; top: 40px">
				<thead>
					<tr>
						<th class="mdl-data-table__cell--non-numeric">IDVenta</th>
						<th class="mdl-data-table__cell--non-numeric">Visualizar</th>
					</tr>
				</thead>
						<tr>
						
						<form action="VisualizarVenta" method="post">
						<input type="text" name="idVenta" value="<%=venta.getId()%>" hidden>
						<input type="text" name="nombrePortal" value="<%=venta.getNombrePortal()%>" hidden>
						<td class="mdl-data-table__cell--non-numeric"><%=venta.getNombrePortal()+venta.getId()%></td>
						
						<td class="mdl-data-table__cell--non-numeric">
							<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" type="submit">Visualizar</button>
						</td>
						
					</form>
						</tr>
						<%}}%>
				</tbody>
			</table>
	</main>
	<footer style="line-height: 50px;padding: 0 20px;margin-bottom: 40px;">
	<form action="EnviarBestSellers" method="post">
		<button id="tt3" style="float: right" type="submit"
			class="mdl-button mdl-js-button mdl-button--fab mdl-button--colored">
			<i class="material-icons">add</i>
		</button>
	</form>
	<div class="mdl-tooltip" data-mdl-for="tt3">Volver al Menu</div>
	</footer>
	</div>
</body>
</html>