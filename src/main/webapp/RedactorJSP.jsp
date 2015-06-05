<%@page import="java.util.*"%>
<%@ page import="WebForMvn.MyWeb.resources.User"%>



<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Redactor JSP</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
<!--
function sayHello() {
   alert("Hello World")
}
//-->
</script>
</head>
<body>


	<%
		int accountId = Integer.parseInt(request.getParameter("AccountId"));
		@SuppressWarnings("unchecked")
		HashMap<Integer, User> userMap = (HashMap<Integer, User>) session
				.getAttribute("userMap");
		
		 
		
	%>

	<%= userMap.get(accountId)%>
	
	
	

<input type="button" onclick="sayHello()" value="Say Hello" />
<h1>Текущее время: <%= new java.util.Date() %></h1>
<h1>Имя вашего хоста: <%= request.getRemoteHost() %></h1>
	
	
	
	
</body>
</html>