package appl;

public class Withdrawer extends Thread {
	
	private final Account account;
	private final int amount;
	
	public Withdrawer(Account account, int amount) {
		this.account = account;
		this.amount = amount;
	}
	
	@Override
	public void run() {
		try {
			this.account.withdraw(this.amount);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
