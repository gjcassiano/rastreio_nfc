package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigDaoImp {
	private static String TABLE_NAME = "Config";
	private static String COL[] = {"id","port","data_rate"};
	private static int ID = 1; 
	
	private static void createIfNotExist(){
		try {
			Statement stmt = AccessDb.getInstance().getConection().createStatement();
			String cmd = "CREATE TABLE IF NOT EXISTS '"+TABLE_NAME+"' ("+COL[0]+" int(11) NOT NULL, "+COL[1]+" varchar(255) NOT NULL , " +COL[2] + " varchar(255) NOT NULL )";
			stmt.executeUpdate(cmd);	
			stmt.close();
		} catch (SQLException e) {
			
		}
	}
	public static void save(ConfigDao configDao){
		createIfNotExist();
		try {
			Statement stmt = AccessDb.getInstance().getConection().createStatement();
			String cmd = "UPDATE " + TABLE_NAME
					+ " SET " + COL[1] + "= '"+configDao.getPort()+ "' , "+ COL[2] + "= '" + configDao.getData_rate() +"'  WHERE "+ COL[0] + "= " + ID + ";";
			stmt.executeUpdate(cmd);	
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	public static void create(){
		createIfNotExist();
		try {
			Statement stmt = AccessDb.getInstance().getConection().createStatement();
			
			String cmd = "INSERT INTO " + TABLE_NAME
					+ " (" + COL[0] + " , "+ COL[1] + " , " +  COL[2] + ") values (" + ID + " , 'COM10' , 9600);";
			stmt.executeUpdate(cmd);	
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	public static ConfigDao load(){
		createIfNotExist();
		ConfigDao configDao = null;
		try {
			Statement stmt = AccessDb.getInstance().getConection().createStatement();
			String sql = "SELECT * FROM " + TABLE_NAME
					+ " WHERE " + COL[0] + "='" + ID + "';";
		
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				configDao = new ConfigDao();
				configDao.setPort(rs.getString(2));
				configDao.setData_rate(Integer.parseInt(rs.getString(3)));
			}else{
				create();
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return configDao;
	}
	
}
