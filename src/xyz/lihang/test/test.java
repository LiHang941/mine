package xyz.lihang.test;

import java.awt.EventQueue;

import xyz.lihang.window.MainWindow;
public class test {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame =MainWindow.getMainWondowInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
