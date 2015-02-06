package appl;

import java.sql.Connection;
import java.util.List;

public interface AccountService {
	
	public abstract List<Account> findAllAccounts(Connection con);
	public abstract Account findAccount(Connection con, int number);
	public abstract void deposit(Connection con, int number, int amount);
	public abstract void withdraw(Connection con, int number, int amount);
	public abstract void transfer(Connection con, int fromNumber, int toNumber, int amount);
}
