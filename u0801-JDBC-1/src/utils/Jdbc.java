package utils;

import java.sql.Connection;
import java.sql.SQLException;

public class Jdbc {
	
	public static void setAutoCommit(Connection con, boolean autoCommit) {
		try {
			con.setAutoCommit(autoCommit);
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean getAutoCommit(Connection con) {
		try {
			return con.getAutoCommit();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void commit(Connection con) {
		try {
			con.commit();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
