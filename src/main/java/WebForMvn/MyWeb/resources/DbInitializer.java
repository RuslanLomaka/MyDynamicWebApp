package WebForMvn.MyWeb.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInitializer {
	static Connection con = null;
	static Statement stmt = null;
	
	//static String createSchemaStatement = "CREATE SCHEMA `"+Container.getSqlSchema()+"`;";
	static String createSchemaStatement = "CREATE SCHEMA `testhub`;";
	static String createRoleTableStatement = " CREATE TABLE `"+Container.getSqlSchema()+"`.`role` "
			+ "( `roleId` int(11) NOT NULL, "
			+ "`name` varchar(50) NOT NULL, "
			+ "PRIMARY KEY (`roleId`) ) DEFAULT CHARSET=utf8;";
	
	static String createAccountTableStatement = "CREATE TABLE `"+Container.getSqlSchema()+"`.`account`"
			+ " ( `accountId` INT NOT NULL AUTO_INCREMENT, "
			+ "`firstName` VARCHAR(50) NOT NULL, "
			+ "`lastName`  VARCHAR(50) NOT NULL, "
			+ "`login`     VARCHAR(50) NOT NULL, "
			+ "`email`     VARCHAR(255) NOT NULL, "
			+ "`password`  VARCHAR(255) NOT NULL, "
			+ "`createDate`DATETIME NULL, "
			+ " PRIMARY KEY (`accountId`));";
	
	static String createAccountRoleStatement = "CREATE TABLE `"+Container.getSqlSchema()+"`.`account_role` "
			+ "( `accountId` int(11) NOT NULL, "
			+ "`roleId` int(11) NOT NULL ) DEFAULT CHARSET=utf8";

	static String createTestTableStatement = "CREATE TABLE `"+Container.getSqlSchema()+"`.`test` "
			+ "( `testId`     INT NOT NULL, "
			+ "  `name`       VARCHAR(255) NOT NULL, "
			+ "  `author`     INT NOT NULL, "
			+ "  `createDate` TIMESTAMP(6) NOT NULL, "
			+ "  `testcol`    VARCHAR(45) NOT NULL, "
			+ "PRIMARY KEY (`testId`));";
	
	static String createHistoryTableStatement = " CREATE TABLE `"+Container.getSqlSchema()+"`.`test_history` "
			+ "( `accountId` INT NOT NULL, "
			+ "`passingDate` DATETIME NULL, "
			+ "`testId`      INT NOT NULL, "
			+ "`score`       INT NOT NULL);";
	
	
	static void executeStatement(String statementString) {
		try {
			Class.forName(Container.getJbdcDriver());

			con = DriverManager.getConnection(Container.getSqlUrl(),
					Container.getSqlUser(), Container.getSqlPassword());
			
			stmt = con.createStatement();
			stmt.execute(statementString);
			
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
	};
	public void generateDb(){
		
		//executeStatement(createSchemaStatement);
		
		executeStatement(createAccountTableStatement);
		executeStatement(createRoleTableStatement);
		executeStatement(createAccountRoleStatement);
		executeStatement(createTestTableStatement);
		executeStatement(createHistoryTableStatement);
	}
	
}
/*CREATE TABLE `firsttest` (
`question` varchar(255) NOT NULL,
`var1` varchar(45) NOT NULL,
`var1ans` tinyint(1) NOT NULL,
`var2` varchar(45) NOT NULL,
`var2ans` tinyint(1) NOT NULL,
`var3` varchar(45) DEFAULT NULL,
`var3ans` tinyint(1) DEFAULT NULL,
`var4` varchar(45) DEFAULT NULL,
`var4ans` tinyint(1) DEFAULT NULL,
PRIMARY KEY (`question`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8*/