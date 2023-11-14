package main;

import javax.swing.JFrame;

import vista.Login;

public class Main {
	/********************************************************
	 * Metodo main que crea el frame principal *
	 ********************************************************/
	public static void main(String[] args) {
		Login sm = new Login();
		sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sm.setVisible(true);
		sm.setBounds(300, 100, 750, 600);
	}
}
