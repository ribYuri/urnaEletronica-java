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
	<h1>Votando para Presidente</h1>
	<form class="votandoCandidatos" action="votacaoPresController" method="post">
		<div>
			<%
			if (request.getAttribute("message") != null){
				String message = (String) request.getAttribute("message");
			%>	
			<h4><%out.print(message);%></h4>
			<%
			}
			%>
			<label>Numero do Candidato</label>
			<input type="text" name="numCandidato" maxlength=2>
		</div>
		<button id="botaoBranco" type="submit" name="branco">Branco</button>
		<button id="botaoCorrige" type="reset" name="corrige">Corrige</button>
		<button id="botaoConfirma" type="submit" name="confimar">Confirma</button>
		<button id="mostraCandidatos" type="submit" name="mostraCandidatos">Candidatos</button>
	</form>
	<%
		if (request.getAttribute("candidatos") != null){
			List<?> candidatos = (List<?>) request.getAttribute("candidatos");
			for (int contador = 0; contador <= (candidatos.size()-1); contador++){
				Candidato candidato = (Candidato) candidatos.get(contador);
				if (candidato.getNumeroIdentificador() == 0 || candidato.getNumeroIdentificador() == 99999999)
					continue;
	%>
	<div class="divMother">
		<div class="item">
			<div>
				<!--<span style="font-weight: bold">Numero: </span>-->
				<span><%out.print(candidato.getNumeroIdentificador()); %></span>
				<!--<span style="font-weight: bold">Nome: </span>-->
				<span><%out.print(candidato.getNome() + " " + candidato.getSobrenome()); %></span>
			</div>
			<!-- <div>
				<span style="font-weight: bold">Partido: </span>
				<span><%out.print(candidato.getPartido()); %></span>
			</div>
			<div>
				<span style="font-weight: bold">Numero: </span>
				<span><%out.print(candidato.getNumeroIdentificador()); %></span>
			</div>
			-->
		</div>
	</div>
	<%
			}
		}
	%>
</body>
</html>