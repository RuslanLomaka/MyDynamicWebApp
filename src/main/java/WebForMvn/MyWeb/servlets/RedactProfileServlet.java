package WebForMvn.MyWeb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WebForMvn.MyWeb.resources.Container;
import WebForMvn.MyWeb.resources.User;



public class RedactProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		Statement stmt = null;
		ResultSet accountResultSet = null;
		
		User user = null;
		HashMap<Integer,User> userMap = new HashMap<Integer, User>();
		
		userMap.values();
		
		
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Container.getSqlUrl(),
					Container.getSqlUser(), Container.getSqlPassword());

			stmt = con.createStatement();

			accountResultSet = stmt
					.executeQuery("SELECT * FROM `testhub`.`account`;");
			while(accountResultSet.next()){
				String firstName = accountResultSet.getString("firstName");
				String lastName = accountResultSet.getString("lastName");
				String login = accountResultSet.getString("login");
				String email = accountResultSet.getString("email");
				int idNum = accountResultSet.getInt("accountId");
								
				int roleId = getRoleId(idNum);
				
				user = new User(idNum, firstName, lastName, login, email, roleId);
				System.out.println();
				userMap.put(idNum, user);
			};
			
			
			
			
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
		System.out.println("return user");
		HttpSession session = request.getSession();
		session.setAttribute("userMap", userMap);
		
		//redirection to JSP page
		RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/AllUsersView.jsp");
		reqDispatcher.forward(request,response);
		
		
	}
	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		PrintWriter print = response.getWriter();
		print.println(id+"\n");

		int idNum = Integer.parseInt(id);
		
		User user = getAccountInfo(idNum);
		
			print.print(user);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/UserView.jsp");
			reqDispatcher.forward(request,response);
			
	}
	
	
	
	
	
	public static User getAccountInfo(int idNum) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		User user = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Container.getSqlUrl(),
					Container.getSqlUser(), Container.getSqlPassword());

			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT * FROM `testhub`.`account` where accountId = \'"
							+ idNum + "\';");
			rs.next();
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String login = rs.getString("login");
			String email = rs.getString("email");
			
			System.out.println("GEt DATA");
			System.out.println(email);
			rs = stmt
					.executeQuery("SELECT roleId FROM `testhub`.`account_role` where accountId = \'"
							+ idNum + "\';");
			rs.next();
			int roleId = rs.getInt("roleId");
			System.out.println("creating User");
			user = new User(idNum, firstName, lastName, login, email, roleId);
			System.out.println(user.getLastName()+"from sebya");
			
			
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
		System.out.println("return user");
		return user;

	}
	public int getRoleId(int accountId){
		Connection con = null;
		Statement stmt = null;
		ResultSet account_roleResultSet = null;
		int roleId =-1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Container.getSqlUrl(),
					Container.getSqlUser(), Container.getSqlPassword());

			stmt = con.createStatement();
			account_roleResultSet = stmt.executeQuery("SELECT roleId FROM `testhub`.`account_role` where accountId = \'"+accountId +"\';");
			account_roleResultSet.next();
			roleId = account_roleResultSet.getInt("roleId");
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
		
		
	
		return roleId;
	}

}
