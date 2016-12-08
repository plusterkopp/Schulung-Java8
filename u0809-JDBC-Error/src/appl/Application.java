package appl;

import db.util.appl.Db;
import util.Log;
import utils.JdbcProperties;
import utils.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.System.out;

public class Application {

	public static void main(String[] args) {
		Db.aroundAppl();
		try {
			AccountService service = new AccountServiceImpl();
			JdbcProperties props = new JdbcProperties("db.properties");
			Class.forName(props.getDriver());
			try (Connection con = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
				JdbcTemplate jdbc = new JdbcTemplate(con);
				run(jdbc, service);
			}
		}
		catch (Exception e) {
			out.println(e);
		}
	}

	public static void run(JdbcTemplate jdbc, AccountService service) {
		Log.header("findAllAccounts");
		jdbc.eval(con -> service.findAllAccounts(con)).forEach(a -> out.println(a));
//		Log.header("findAccount(4712)");
//		out.println(jdbc.eval(con -> service.findAccount(con, 4712)));
//		Log.header("deposit(4711, 5000)");
//		jdbc.exec(con -> service.deposit(con, 4711, 5000));
//		Log.header("transfer(4711, 4712, 3000)");
//		jdbc.exec(con -> service.transfer(con, 4711, 4712, 3000));
//		Log.header("findAllAccounts");
//		jdbc.eval(con -> service.findAllAccounts(con)).forEach(a -> out.println(a));
	}

}
