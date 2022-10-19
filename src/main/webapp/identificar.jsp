<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="./style/style.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Votar</title>
</head>
<body>
	<h1>Identifica��o</h1>
	<form class="formNumeroEleitor" action="verificaEleitorController" method="post">
		<div>
			<%
			if (request.getAttribute("message") != null){
				String message = (String) request.getAttribute("message");
			%>	
			<h4><%out.print(message);%></h4>
			<%
			}
			%>
			<label>Titulo de Eleitor</label>
			<input type="text" maxlength=6 name="tituloEleitor">
		</div>
		<button id="botaoVotar" type="submit" name="votar">Votar</button>
		<button id="botaoEncerra" type="submit" name="encerrar">Encerrar Vota��o</button>
	</form>
</body>
</html>