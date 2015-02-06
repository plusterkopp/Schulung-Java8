package appl;

import java.util.concurrent.locks.StampedLock;

import util.ThreadUtil;

public class Account6 implements Account {

	private int balance;

	private final boolean optimistic;
	private final StampedLock lock = new StampedLock();

	private static void check(int amount) {
		if (amount < 0)
			throw new IllegalArgumentException();
	}

	public Account6(int balance, boolean optimistic) {
		check(balance);
		this.balance = balance;
		this.optimistic = optimistic;
	}

	public void deposit(int amount) {
		check(amount);
		final long stamp = lock.writeLock();
		try {
			ThreadUtil.sleep(1000);
			this.balance += amount;
		}
		finally {
			lock.unlockWrite(stamp);
		}
	}

	public void withdraw(int amount) {
		check(amount);
		final long stamp = lock.writeLock();
		try {
			if (amount > this.balance)
				throw new IllegalArgumentException();
			ThreadUtil.sleep(1000);
			this.balance -= amount;
		}
		finally {
			lock.unlockWrite(stamp);
		}
	}
	
	public int getBalance() {
		ThreadUtil.sleep(1000);
		return this.optimistic ? getBalanceOptimistic() : getBalancePessimistic();
	}

	int getBalancePessimistic() {
		final long stamp = lock.readLock();
		try {
			return this.balance;
		}
		finally {
			lock.unlockRead(stamp);
		}
	}

	int getBalanceOptimistic() {
		long stamp = lock.tryOptimisticRead();
		int balance = this.balance;
		if (!lock.validate(stamp)) {
			stamp = lock.readLock();
			try {
				balance = this.balance;
			}
			finally {
				lock.unlockRead(stamp);
			}
		}
		return balance;
	}
}
