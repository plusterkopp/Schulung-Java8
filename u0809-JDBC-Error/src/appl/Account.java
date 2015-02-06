package appl;

import java.io.Serializable;

public class Account implements Serializable {
	
	private int number;
	private int credit;
	private int balance;
	
	public Account() {
	}
	public Account(int number, int credit, int balance) {
		this.number = number;
		this.credit = credit;
		this.balance = balance;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [" + number + ", " + credit + ", " + balance + "]";
	}
	
}
