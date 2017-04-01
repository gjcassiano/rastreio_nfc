package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ObjetoDaoImp {
	private static String TABLE_NAME = "Objeto";
	private static String COL[] = { "nuid", "name", "info" };
	private static int ID = 1;

	private static void createIfNotExist() {
		try {
			Statement stmt = AccessDb.getInstance().getConection()
					.createStatement();
			String cmd = "CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "' ("
					+ COL[0] + " varchar(250) NOT NULL, " + COL[1]
					+ " varchar(50) NOT NULL , " + COL[2]
					+ " varchar(255) NOT NULL )";
			stmt.executeUpdate(cmd);
			stmt.close();
		} catch (SQLException e) {

		}
	}

	public static void save(ObjetoDao objetoDao) {
		createIfNotExist();
		try {
			Statement stmt = AccessDb.getInstance().getConection()
					.createStatement();
			String sql = "UPDATE " + TABLE_NAME + " SET " + COL[1] + "= '"
					+ objetoDao.getName() + "' , " + COL[2] + "= '"
					+ objetoDao.getInfo() + "'  WHERE " + COL[0] + "= '"
					+ objetoDao.getNuid() + "';";
			stmt.executeUpdate(sql);
			System.out.println(sql);
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void create(ObjetoDao objetoDao) {
		createIfNotExist();
		try {
			Statement stmt = AccessDb.getInstance().getConection()
					.createStatement();

			String sql = "INSERT INTO " + TABLE_NAME + " (" + COL[0] + " , "
					+ COL[1] + " , " + COL[2] + ") values ("
					+ objetoDao.getNuid() + " , '' , '');";
			stmt.executeUpdate(sql);

			stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static ObjetoDao load(String nuid) {
		createIfNotExist();
		
		ObjetoDao objetoDao = null;
		try {
			Statement stmt = AccessDb.getInstance().getConection()
					.createStatement();
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL[0]
					+ "='" + nuid + "';";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				objetoDao = new ObjetoDao();
				objetoDao.setNuid(rs.getString(1));
				objetoDao.setName(rs.getString(2));
				objetoDao.setInfo(rs.getString(3));
			} else {
				objetoDao = new ObjetoDao();
				objetoDao.setNuid(nuid);
				create(objetoDao);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return objetoDao;
	}

}
