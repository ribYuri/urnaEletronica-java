<%@page import="urna.model.Candidato"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="./style/style.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Urna</title>
</head>
<body>
	<h1>Resultados</h1>
	<form class="mostrandoResultados" action="resultadosController" method="post">
		<button id="resultadoDeps" type="submit" name="resultDeputado">Deputado</button>
		<button id="resultadoPres" type="submit" name="resultPresidente">Presidente</button>
		<button id="resultadoNuloBranco" type="submit" name="resultNuloBranco">Nulos/Brancos</button>
		<button id="reiniciarVotacao" type="submit" name="resetar">Nova Votacao</button>
	</form>
	<%
	if (request.getAttribute("candidatos") != null){
	%>
	<div class="divTabelaResultados">
		<table border="1">
			<tr>
				<th>Nome</th>
				<th>Partido</th>
				<th>Numero</th>
				<th>Votos Recebidos</th>
			</tr>
	<%
		if (request.getAttribute("candidatos") != null){
			List<?> candidatos = (List<?>) request.getAttribute("candidatos");
			for (int contador = 0; contador <= (candidatos.size()-1); contador++){
				Candidato candidato = (Candidato) candidatos.get(contador);
				if (candidato.getNumeroIdentificador() == 0 || candidato.getNumeroIdentificador() == 99999999)
					continue;
	%>
			<tr>
				<td><%out.print(candidato.getNome() + " " + candidato.getSobrenome()); %></td>
				<td><%out.print(candidato.getPartido());%></td>
				<td><%out.print(candidato.getNumeroIdentificador()); %></td>
				<td><%out.print(candidato.getVotos()); %></td>
			</tr>
			
	<%
			}
		}
	%>
		</table>
	</div>
	<%
	} else if (request.getAttribute("NuloBranco") != null) {
	%>
	<!-- Resultados Brancos e Nulos -->
	<div class="divTabelaResultados">
		<table border="1">
			<tr>
				<th>Nome</th>
				<th>Votos Recebidos</th>
			</tr>
	<%  	
		if (request.getAttribute("NuloBranco") != null){
			List<?> candidatos = (List<?>) request.getAttribute("NuloBranco");
			for (int contador = 0; contador <= (candidatos.size()-1); contador++){
				Candidato candidato = (Candidato) candidatos.get(contador);
	%>
			<tr>
				<td><%out.print(candidato.getNome()); %></td>
				<td><%out.print(candidato.getVotos()); %></td>
			</tr>
	<%
			}
		}
	%>
		</table>
	</div>
	<%
	}
	%>
</body>
</html>