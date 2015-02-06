package appl;

public interface Account {
	public abstract void deposit(int amount);
	public abstract void withdraw(int amount);
	public abstract int getBalance();
}
