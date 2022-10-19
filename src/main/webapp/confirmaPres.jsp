<%@page import="urna.model.Candidato"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="./style/style.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<%
int votoConfirmado = 0;
String type = "submit";
if (request.getAttribute("confirmado") != null){
	votoConfirmado = 1;
	type = "reset";
%>
<meta http-equiv="refresh" content="2; URL='http://localhost:8080/urnaEletronicaWeb/identificar.jsp'"/> <!--Define o redirecionamento, tempo e URL--->
<%
}
%>
<title>Urna</title>
</head>
<body>
	<h1>Confirmar para Presidente</h1>
	<p>Seu voto esta indo para</p>
	<form class="votandoCandidatos" action="confirmaPresController" method="post">
	<%
	int temCandidato = 0;
	if (request.getAttribute("numero") != null){
		int numero = (int) request.getAttribute("numero");
		if (numero != 0 && numero != 99999999){
			temCandidato = 1;
	%>
		<div>
			<label>Numero do Candidato</label>
			<input type="text" name="numCandidato" maxlength=5  value="<%out.print(numero);%>" readonly>
		</div>
	<%
		} else if (numero == 99999999){
		%>
		<h3>VOTANDO NULO</h3>
		<input type="hidden" name="numCandidato" value="<%out.print(numero);%>">
		<%	
		} else if (numero == 0) {
		%>
		<h3>VOTANDO BRANCO</h3>
		<input type="hidden" name="numCandidato" value="<%out.print(numero);%>">
		<%	
		} 
	}
	if (votoConfirmado == 1){
		%>
		<h3>VOTO CONFIRMADO</h3>
		<%
		}
	%>
		<button id="botaoBranco" type="<%out.print(type);%>" name="branco">Branco</button>
		<button id="botaoCorrige" type="<%out.print(type);%>" name="corrige">Corrige</button>
		<button id="botaoConfirma" type="<%out.print(type);%>" name="confimar">Confirma</button>
	</form>
	<%
	if (temCandidato == 1) {
		if (request.getAttribute("candidatos") != null){
			Candidato candidato = (Candidato) request.getAttribute("candidatos");
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