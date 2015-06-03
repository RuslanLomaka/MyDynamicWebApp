
<%@ page import="WebForMvn.MyWeb.resources.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="css/style.css">
</head>
<body>





<%
 User user = (User)session.getAttribute("user");

String view  = "Hello from userView JSP";

%>



<h1>
<%=view %>
</h1>


<h4>
<%=user %>
</h4>



</body>
</html>