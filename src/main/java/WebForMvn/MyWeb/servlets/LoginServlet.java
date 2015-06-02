package WebForMvn.MyWeb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WebForMvn.MyWeb.resources.Container;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(10);
		
		ServletContext context =  session.getServletContext();
		

		resp.setContentType("text/html;charset=UTF-8");

		PrintWriter out = resp.getWriter();
		String password = req.getParameter("password");
		String login = req.getParameter("login");
		//Saving of user ID cures 
		
		
		if((login !="" && login !=null)&&(password !="" && password !=null)){
			session.setAttribute("sessionLogin", login);
			session.setAttribute("sessionPassword", password);
		}else{
			out.println("Problem with authorization");
		}
		
		
		if(this.login(login, password)==1){
			String startPage1=
					"<!DOCTYPE html>"
					+"<html>"
					+"<head>"
					+"<meta charset=\"utf-8\">"
					+"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">"
					+"  <title>Welcome user</title>"
					+"  <link rel=\"stylesheet\" href=\"css/style.css\">"
					+"</head>"
					+"<body>"
					
					+"<h1>Welcome "+(String) session.getAttribute("sessionLogin")
					+".<br> Session hashcode: "+session.hashCode()
					+"<br>"
					+"<h1> Responce hashcode"+resp.hashCode()+" !</h1>"
					+"<br>"
					+"<h1> Request hashcode"+req.hashCode()+" !</h1>"
					+"<br>"
					+"<h1> Context hashcode"+context.hashCode()+" !</h1>"
					+"</body>"
					+"</html>";
			
			out.println(startPage1);
			System.out.println("You have successfully logineg. Welcome "+login+"!");
		}else{
			out.println("wrong password or login :"+login);
			System.out.println("wrong password or login :"+login);
		}
		
		
	}
		
	
public int login(String login, String password) {
		
		String log = login;
		String psw = password;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			con = DriverManager.getConnection(Container.getSqlUrl(), Container.getSqlUser(), Container.getSqlPassword());
			
			//System.out.println("connected successfully");
			
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT login  FROM account;");
						
			while (rs.next()) {
				
				if (log.equals(rs.getString("login"))){
					
					rs.close();
					rs = stmt.executeQuery("SELECT password FROM testhub.account where login=\'"+log+"\';");
					rs.next();
					
					if(psw.equals(rs.getString("password"))){
						
						result=1;
						
						break;
					}
					else{
						System.out.println("wrong password or login "+login);
						break;
					}
				}
			
			}
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (rs != null) {

				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.err.println("Error: " + ex.getMessage());
			}
		}
		return result;
	
}
	
}