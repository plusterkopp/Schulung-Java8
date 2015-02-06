package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Jdbc {

	
	static public <T> List<T> findList(Connection con, Function<ResultSet, T> create, String sql, Object... params) {
		final List<T> list = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			setParams(stmt, params);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					list.add(create.apply(rs));
				}
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	static public <T> T findObject(Connection con, Function<ResultSet, T> create, String sql, Object... params) {
		List<T> list = findList(con, create, sql, params);
		if (list.size() == 0)
			return null;
		if (list.size() > 1)
			throw new RuntimeException("to many results");
		return list.get(0);
	}

	static public int execute(Connection con, String sql, Object... params) {
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			setParams(stmt, params);
			return stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	static private void setParams(PreparedStatement stmt, Object... params) throws SQLException {
		for (int i = 0; i < params.length; i++)
			stmt.setObject(i + 1, params[i]);
	}
}
