package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import Startup.Constants;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessDb {
	private static AccessDb ACCESS_DB = new AccessDb();
	public Statement stmt = null;
	public  ResultSet result;
	
	//using singleton
	public static AccessDb getInstance(){
		return ACCESS_DB;
	}
	public static Connection getConection(){

		try {
			Class.forName(Constants.JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			System.err.println("Not found the library" + Constants.JDBC_DRIVER);
			e1.printStackTrace();
		}
		try {
	        return DriverManager.getConnection(
	        		Constants.JDBC_URL + Constants.JDBC_DATABASE);
		} catch (SQLException e) {
			System.err.println("Erro to load lib: " +e.getMessage());
		}
		return null;
	}
}
