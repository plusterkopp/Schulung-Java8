package appl;

import util.ThreadUtil;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
	private final TextField textField = new TextField(10);

	private int counter;
	
	public MyFrame() {
		super("Version 1");
		this.setLayout(new FlowLayout());
		this.add(this.textField);
		this.setBounds(100, 100, 200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Thread progressThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					ThreadUtil.sleep(1000);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							MyFrame.this.textField.setText(String.valueOf(++MyFrame.this.counter));
						}
					});
				} 
			}
		};
		
		progressThread.setDaemon(true);
		progressThread.start();
		this.setVisible(true);
	}
}
