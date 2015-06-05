
<%@ page import="WebForMvn.MyWeb.resources.User" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">

 $(document).ready(function(){
         
	 alert("Oy-oy-oy")
	 
     });

</script>


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