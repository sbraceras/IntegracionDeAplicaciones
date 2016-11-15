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
		crossorigin="anonymous">
	</script>

	<title>Informes Auditoria</title>

	<script type="text/javascript" src="../scripts/ajax.js"></script>

	<script type="text/javascript">

		setInterval(function() { listarInformes() }, 3000);

		function listarInformes() {
			sendRequestGet("/LogisticaWeb/InformesAuditoria");
		}

	</script>

</head>

<body>
	<!-- Always shows a header, even in smaller screens. -->
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header">
		<div class="mdl-layout__header-row">
			<!-- Title -->
			<span class="mdl-layout-title">Informes Auditoria</span>
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
			<nav class="mdl-navigation"> 
				<a class="mdl-navigation__link" onclick="window.location.href='Test'">Ordenes de Despacho</a>
				<a class="mdl-navigation__link" onclick="window.location.href='BestSellers'">Best Sellers</a>
				<a class="mdl-navigation__link" onclick="window.location.href='Test'">Reporte de ventas</a>
				<a class="mdl-navigation__link"	onclick="window.location.href='Test'">Reporte de auditoria</a>
			</nav>
		</div>
		<main class="mdl-layout__content table-responsive"> 

		<!-- Your content goes here -->

		<div id="contenedorTabla">

		</div>

		</main>
	<footer style="line-height: 50px;padding: 0 20px;margin-bottom: 40px;">
	<form action="InformesAuditoria" method="post">
		<button id="tt3" style="float: right" type="submit"
			class="mdl-button mdl-js-button mdl-button--fab mdl-button--colored">
			<i class="material-icons">add</i>
		</button>
	</form>
	<div class="mdl-tooltip" data-mdl-for="tt3">Enviar e-mail</div>
	</footer>
	</div>
</body>
</html>