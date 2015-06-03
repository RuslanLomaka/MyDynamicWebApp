
<%@page import="java.util.Iterator"%>
<%@ page import="WebForMvn.MyWeb.resources.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<title>All users</title>
</head>
<body>
<h1>All users list</h1>


<%
 LinkedList<User> userList = (LinkedList<User>)session.getAttribute("userList");

String view  = "Look at thos people? they all are in your RDB";

%>



<h3>
<%=view %>
</h3>

<%  Iterator<User> iter = userList.iterator();
		while(iter.hasNext()){  %>
	
	
	<h4>
		<%=iter.next()  %>
		
		</h4>
		<form action="RedactProfileServlet" method="POST">
    		<input type="submit" value="Redacting User Profile">
    	</form>
		
		
		<br>
	
	
<%}; %>	



</body>
</html>