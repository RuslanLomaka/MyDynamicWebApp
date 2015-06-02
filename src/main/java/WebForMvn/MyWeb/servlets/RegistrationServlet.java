package WebForMvn.MyWeb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebForMvn.MyWeb.resources.Container;


public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String registrationForm = "<!DOCTYPE html>"
			+ "<html>"
			+ "<head>"
			+ "  <meta charset=\"UTF-8\">"
			+ "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">"
			+ " <title>Registration Form</title>"
			+ " <link rel=\"stylesheet\" href=\"css/style.css\">"
			+ "</head>"
			+ "<body>"

			+ " <section class=\"container\">"
			+ "  <div class=\"login\">"
			+ "    <h1>Registration form</h1>"

			+ "<form action=\"RegistrationServlet\" method=\"POST\">"
			+ "  <meta charset=\"UTF-8\">"

			+ "   <p><input type=\"text\" name=\"firstName\" value=\"\" placeholder=\"First name\"></p>"
			+ "   <p><input type=\"text\" name=\"lastName\" value=\"\" placeholder=\"Last name\"></p>"

			+ "   <p><input type=\"text\" name=\"email\" value=\"\" placeholder=\"email\"></p>"

			+ "   <p><input type=\"text\" name=\"login\" value=\"\" placeholder=\"Username (login)\"></p>"

			+ "   <p><input type=\"password\" name=\"password1\" value=\"\" placeholder=\"Password\"></p>"
			+ "   <p><input type=\"password\" name=\"password2\" value=\"\" placeholder=\"Repeat assword\"></p>"

			+ "    <p class=\"remember_me\">"
			+ "     <label>"
			+ "       <input type=\"checkbox\" name=\"remember_me\" id=\"remember_me\">"
			+ "       Remember me on this computer"
			+ "    </label>"
			+ "   </p>"
			+ "    <input type=\"submit\" value=\"Registrate Me\">"
			+ "  </form>"

			+ "  </div>"

			+ "  <div class=\"login-help\">"
			+ "    <p>Forgot your password? <a href=\"index.html\">Click here to reset it</a>.</p>"
			+ "  </div>" + " </section>"

			+ "</body>" + "</html>";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(registrationForm);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String login = request.getParameter("login");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		//DbInitializer dbIni = new DbInitializer();///////////////////////////////////////////////////////////
		//dbIni.generateDb();/////////////////////////////////////////////////////////////////////////////////////

		if (checkPass(password1, password2) && checkLog(login)) {
			creationAccount(login, email, firstName, lastName, password2);
		} else {
			
		};

	}

	public boolean checkPass(String pass1, String pass2) {
		if (pass1.equals(pass2)) {
			return true;
		} else {
			System.out.println("Bad Passwords");
			return false;
		}
	}

	public boolean checkLog(String login) {

		String log = login;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(Container.getSqlUrl(), Container.getSqlUser(), Container.getSqlPassword());

			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT login FROM testhub.account;");

			while (rs.next()) {
				if (!(log.equals(rs.getString("login")))) {
				} else {
					System.out.println("User already exist");
					stmt.close();
					con.close();
					return false;

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
		
		return true;
		
		
	}

	public void creationAccount(String login, String email, String firstName,
			String lastName, String password) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					(Container.getSqlUrl(), 
					 Container.getSqlUser(), 
					 Container.getSqlPassword());

			

			stmt = (PreparedStatement) con.prepareStatement("INSERT INTO `testhub`.`account` "
					+ "(`firstName`, `lastName`, `login`, `email`, `password`) "
					+ "VALUES (?,?,?,?,?);");
			
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, login);
			stmt.setString(4, email);
			stmt.setString(5, password);
				
			stmt.executeUpdate();
			
			//Lets get The ID of just created account
			rs = stmt.executeQuery("SELECT accountId FROM `testhub`.`account` where login = \'"+login+"\';");
			rs.next();
			int accountId = rs.getInt("accountId");
			
			System.out.println("New account ID: "+accountId);
			
			//And just set for those ID role "1", which means 'student'
			stmt.execute("INSERT INTO `testhub`.`account_role` (`accountId`, `roleId`) VALUES ('"+accountId+"', '"+1+"');");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
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

	}

}