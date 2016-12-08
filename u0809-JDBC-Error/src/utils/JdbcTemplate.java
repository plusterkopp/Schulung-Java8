package utils;

import util.Catcher;

import java.sql.Connection;
import java.util.function.Consumer;
import java.util.function.Function;

public class JdbcTemplate {
	
	private final Connection con;
	public JdbcTemplate(Connection con) {
		this.con = con;
	}
	
	public void exec(Consumer<Connection> tx) {
		boolean autoCommitOld = Catcher.eval(() -> this.con.getAutoCommit());
		Catcher.exec(() -> this.con.setAutoCommit(false));
		try {
			tx.accept(this.con);
			Catcher.exec(() -> this.con.commit());
		}
		catch(Exception e) {
			Catcher.exec(() -> this.con.rollback());
			throw new RuntimeException(e);
		}
		finally {
			// only this call is critical
			Catcher.exec(() -> this.con.setAutoCommit(autoCommitOld));
		}
	}
	
	public <T> T eval(Function<Connection,T> tx) {
		boolean autoCommitOld = Catcher.eval(() -> this.con.getAutoCommit());
		Catcher.exec(() -> this.con.setAutoCommit(false));
		try {
			T result = tx.apply(this.con);
			Catcher.exec(() -> this.con.commit());
			return result;
		}
		catch(Exception e) {
			Catcher.exec(() -> this.con.rollback());
			throw new RuntimeException(e);
		}
		finally {
			// only this call is critical
			Catcher.exec(() -> this.con.setAutoCommit(autoCommitOld));
		}
	}
}
