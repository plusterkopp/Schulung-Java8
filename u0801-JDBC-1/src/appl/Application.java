package appl;

import db.util.appl.Db;
import util.Log;
import utils.Jdbc;
import utils.JdbcProperties;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.System.out;

public class Application {

	public static void main(String[] args) {
		Db.aroundAppl();
		try {
			final AccountService service = new AccountServiceImpl();
			final JdbcProperties props = new JdbcProperties("db.properties");
			Class.forName(props.getDriver());
			try (Connection con = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
				run(con, service);
			}
		}
		catch (Exception e) {
			out.println(e);
		}
	}

	public static void run(Connection con, AccountService service) {
		Log.header("findAllAccounts");
		service.findAllAccounts(con).forEach(a -> out.println(a));
		Log.header("findAccount(4712)");
		out.println(service.findAccount(con, 4712));
		Log.header("deposit(4711, 5000)");
		service.deposit(con, 4711, 5000);
		Log.header("transfer(4711, 4712, 3000)");
		
		Jdbc.setAutoCommit(con, false);
		try {
			service.transfer(con, 4711, 4712, 3000);
			// service.transfer(con, 4711, 4712, 300000);
			Jdbc.commit(con);
		}
		catch(Exception e) {
			Jdbc.rollback(con);
			System.out.println(e);
		}
		Jdbc.setAutoCommit(con, true);

		Log.header("findAllAccounts");
		service.findAllAccounts(con).forEach(a -> out.println(a));
	}
}
