package appl;

import utils.Jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountServiceImpl implements AccountService {

	private static Account createAccount(ResultSet rs) {
		try {
			final Account a = new Account();
			a.setNumber(rs.getInt("number"));
			a.setCredit(rs.getInt("credit"));
			a.setBalance(rs.getInt("balance"));
			return a;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Account> findAllAccounts(Connection con) {
		final String sql = "select number, credit, balance from account";
		return Jdbc.findList(con, rs -> createAccount(rs), sql);
	}

	public Account findAccount(Connection con, int number) {
		final String sql = "select number, credit, balance from account where number = ?";
		return Jdbc.findObject(con, rs -> createAccount(rs), sql, number);
	}

	private Account getAccount(Connection con, int number) {
		Account account = this.findAccount(con, number);
		if (account == null)
			throw new RuntimeException("account " + number + " not found");
		return account;
	}

	private static void checkAmount(int amount) {
		if (amount <= 0)
			throw new RuntimeException("amount must be positive");
	}

	public void deposit(Connection con, int number, int amount) {
		checkAmount(amount);
		final Account account = this.getAccount(con, number);
		final String sql = "update account set balance = ? where number = ?";
		Jdbc.execute(con, sql, account.getBalance() + amount, number);
	}

	public void withdraw(Connection con, int number, int amount) {
		checkAmount(amount);
		final Account account = this.getAccount(con, number);
		if (amount > account.getBalance() + account.getCredit())
			throw new RuntimeException("not available: " + amount);
		final String sql = "update account set balance = ? where number = ?";
		Jdbc.execute(con, sql, account.getBalance() - amount, number);
	}

	public void transfer(Connection con, int fromNumber, int toNumber, int amount) {
		// the sequence is stupid, but it must work...
		this.deposit(con, toNumber, amount);
		this.withdraw(con, fromNumber, amount);
	}

}
