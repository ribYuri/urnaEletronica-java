<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio</title>
</head>
<body>
	<h1>Urna</h1>
	<form class="formNumeroEleitor" action="homeController" method="post">
		<button id="botaoVotar" type="submit" name="votar">Votar</button>
		<button id="encerraVotacao" type="submit" name="encerrar">Resultados</button>
	</form>
</body>
</html>