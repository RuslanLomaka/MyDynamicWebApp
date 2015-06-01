package WebForMvn.MyWeb.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Test {
	public ArrayList<Question> questtionStorage = new ArrayList<Question>();
	
	
	
	public void fetchQuestions() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		

		try {
			Class.forName(Container.getJbdcDriver());
			
			con = DriverManager.getConnection(Container.getSqlUrl(), Container.getSqlUser(), Container.getSqlPassword());
			
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT *  FROM firsttest");

			while (rs.next()) {
				Question tmpQuestion = new Question();
				
				//First column is question itself. For example:
				//"Who is the president of USA today?"
				tmpQuestion.setQuestion(rs.getString(1));
				
			/*	Next, the variants of answers is received:
			 *  first two variants is necessary, because question must have at least two variants 
			 * 	Other variants is option.
			 *  If the table contains it, they will be received too.
			 */
				//The put method adds one variant with answer for this variant(true or false)
				tmpQuestion.put(rs.getString(2), rs.getBoolean(3));
				
				tmpQuestion.put(rs.getString(4), rs.getBoolean(5));
				
				if(rs.getString(6)!=null){
					tmpQuestion.put(rs.getString(6), rs.getBoolean(7));
					tmpQuestion.put(rs.getString(8), rs.getBoolean(9));
				}
				
				this.questtionStorage.add(tmpQuestion);
				

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
				
/*CREATE TABLE `testhub`.`firsttest` (
  `question` VARCHAR(255) NOT NULL,
  `var1` VARCHAR(45) NOT NULL,
  `var1ans` TINYINT(1) NOT NULL,
  `var2` VARCHAR(45) NOT NULL,
  `var2ans` TINYINT(1) NOT NULL,
  `var3` VARCHAR(45) ,
  `var3ans` TINYINT(1) ,
  `var4` VARCHAR(45) ,
  `var4ans` TINYINT(1) ,
  
  PRIMARY KEY (`question`));*/
	}

}
