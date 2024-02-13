package backend;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DbConn {
	Connection conn = null;
	
	public static Connection dbConn() {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 
			 String url = "jdbc:mysql://localhost:3306/cms_pleasework ";
			 String username = "root";
			 String password = "";

			 Connection conn = DriverManager.getConnection(url, username, password);
	         
//			 JOptionPane.showMessageDialog(null, "Successful Connection");
			 return conn;
		 } catch (Exception dbConnFail) {
			 JOptionPane.showMessageDialog(null, dbConnFail);
			 return null;
		 }
	 }
}
