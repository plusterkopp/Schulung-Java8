package appl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
	
	private static Account createAccount(ResultSet rs) throws SQLException {
		final Account a = new Account();
		a.setNumber(rs.getInt("number"));
		a.setCredit(rs.getInt("credit"));
		a.setBalance(rs.getInt("balance"));
		return a;
	}
	
	public List<Account> findAllAccounts(Connection con) {
		final List<Account> list = new ArrayList<>();
		final String sql = "select number, credit, balance from account";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					list.add(createAccount(rs));
				}
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public Account findAccount(Connection con, int number) {
		final String sql = "select number, credit, balance from account where number = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, number);
			try (ResultSet rs = stmt.executeQuery()) {
				if (! rs.next()) 
					return null;
				return createAccount(rs);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, account.getBalance() + amount);
			stmt.setInt(2, number);
			if (stmt.executeUpdate() != 1)
				throw new RuntimeException("update failed");
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void withdraw(Connection con, int number, int amount) {
		checkAmount(amount);
		final Account account = this.getAccount(con, number);
		if (amount > account.getBalance() + account.getCredit())
			throw new RuntimeException("not available: " + amount);
		final String sql = "update account set balance = ? where number = ?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, account.getBalance() - amount);
			stmt.setInt(2, number);
			if (stmt.executeUpdate() != 1)
				throw new RuntimeException("update failed");
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void transfer(Connection con, int fromNumber, int toNumber, int amount) {
		// the sequence is stupid, but it must work...
		this.deposit(con, toNumber, amount);
		this.withdraw(con, fromNumber, amount);
	}
	
}
