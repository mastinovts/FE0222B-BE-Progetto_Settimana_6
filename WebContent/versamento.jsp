<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="it.data.ContoCorrente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<title>Versamento</title>
</head>
<body>
<% 
	ContoCorrente c = (ContoCorrente)session.getAttribute("versamentoSession");
%>

	<div class="container text-center w-50 h-auto bg-light my-5 p-5">
		<div>
			<h5 class="text-success text-center">Versamento effettuato!</h5>
			<h3 class="text-lg-start">INTESTATARIO</h3>
			<p class="text-lg-start"><%=c.getIntestatario()%></p>
			<h3 class="text-lg-start">CC Num.</h3>
			<p class="text-lg-start"><%=c.getNumero()%></p>
			<h2 class="text-success">NUOVO SALDO DISPONIBILE</h2>
			<p><%=c.getSaldo()%> &euro;</p>
			<button class="btn btn-primary mt-4" onclick="location.href='./'">Indietro</button>
		</div>
		</div>
</body>
</html>