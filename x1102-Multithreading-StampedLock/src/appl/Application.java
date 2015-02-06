// http://www.javaspecialists.eu/archive/Issue215.html
	
package appl;

import util.ThreadUtil;


public class Application {
	public static void main(String[] args) {
		
		Account account = new Account6(5000, true);
		Withdrawer w1 = new Withdrawer(account, 4000);
		Withdrawer w2 = new Withdrawer(account, 3000);
		
		w1.start();
		ThreadUtil.sleep(500);
		w2.start();
		
		ThreadUtil.join(w1);
		ThreadUtil.join(w2);
		
		System.out.println(account.getBalance());
	}
}
