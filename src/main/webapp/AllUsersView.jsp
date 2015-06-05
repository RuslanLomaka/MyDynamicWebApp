<%@page import="java.util.*"%>
<%@page import="java.util.Iterator"%>
<%@ page import="WebForMvn.MyWeb.resources.User"%>
<%@ page import="java.util.LinkedList"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="utf-8"%>
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
@SuppressWarnings("unchecked")
HashMap<Integer, User> userMap= (HashMap<Integer, User>)session.getAttribute("userMap");
%>

	<h3>Look at those people? they all are in your RDB</h3>
	
	<ul class="menu">
		<%  Iterator<Map.Entry<Integer,User>> iter = userMap.entrySet().iterator();
			while(iter.hasNext()){  
			Map.Entry<Integer,User> mapEntry = (Map.Entry<Integer,User>) iter.next();
			User user = (User) mapEntry.getValue();
			%>
		<li>User login : <a
			href="RedactorJSP.jsp?AccountId=<%=user.getAccountId() %>"><%=user.getLogin() %>
		</a> <%=";   User first name: " +user.getFirstName()+";   User last name: " + user.getLastName()%>
		</li>

		<%}; %>
	</ul>
</body>
</html>