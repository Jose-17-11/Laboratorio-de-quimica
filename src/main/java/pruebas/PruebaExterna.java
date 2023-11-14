package pruebas;


import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


//Clase funcional con la base de datos, solo falta renombrar variables
public class PruebaExterna extends JFrame {

	/**
	 * 
	 */
	String usuario, password;
	JLabel titulo, n1, n2;
	JTextField text1, text2;
	JButton acceder;

	// Metodo constructor principal que mostrara todo el formulario
	public PruebaExterna() {

		/***********************
		 * Contenido del login *
		 ***********************/

		titulo = new JLabel("Iniciar sesión");
		titulo.setBounds(280, 0, 400, 100);
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setForeground(Color.WHITE);

		n1 = new JLabel("input 1");
		n1.setBounds(330, 85, 200, 100);
		n1.setForeground(Color.WHITE);
		text1 = new JTextField(10);
		text1.setBounds(240, 150, 220, 30);

		n2 = new JLabel("Input 2");
		n2.setForeground(Color.WHITE);
		n2.setBounds(320, 175, 200, 100);
		text2 = new JPasswordField();
		text2.setBounds(240, 240, 220, 30);

		acceder = new JButton("Generar");
		acceder.setBounds(275, 350, 150, 30);

		/********************************************************
		 * Creacion del panel y agregacion de todo el contenido *
		 ********************************************************/

		Container panel = getContentPane();
		panel.setBackground(new Color(0, 56, 205));
		LineBorder borde = new LineBorder(Color.BLACK, 2);
		((JComponent) panel).setBorder(borde);
		panel.setLayout(null);
		panel.add(titulo);
		panel.add(n1);
		panel.add(n2);
		panel.add(text1);
		panel.add(text2);
		panel.add(acceder);

		/****************************************
		 * Evento del boton para iniciar sesion *
		 ****************************************/

		acceder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	public static void main(String[] args) {
		PruebaExterna sm = new PruebaExterna();
		sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sm.setVisible(true);
		sm.setBounds(300, 100, 750, 600);
	}
}

