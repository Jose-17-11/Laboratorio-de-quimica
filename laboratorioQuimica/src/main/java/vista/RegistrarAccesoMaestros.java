package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controlador.RegistrarAccesoMaestro;

//Clase funcional con la base de datos, solo falta renombrar variables
public class RegistrarAccesoMaestros extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel description, exitosamente, n1, n2, n3, n4;
	JTextField text1, text2, text3, text4;
	JButton registrarEntrada;

	public RegistrarAccesoMaestros() {
		ImageIcon icono = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
 
		setTitle("Registro de accesos");
		setResizable(false);

		/***********************
		 * Contenido del panel *
		 ***********************/
		description = new JLabel("Registre los datos del maestro que ingresara al laboratorio.");
		description.setBounds(220, 0, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		n1 = new JLabel("Matricula");
		n1.setBounds(120, 80, 200, 100);
		n1.setForeground(Color.WHITE);
		text1 = new JTextField(10);
		text1.setBounds(50, 150, 200, 30);

		n2 = new JLabel("Salon");
		n2.setBounds(390, 80, 200, 100);
		n2.setForeground(Color.WHITE);
		text2 = new JTextField(10);
		text2.setBounds(300, 150, 200, 30);

		n3 = new JLabel("Grupo");
		n3.setBounds(620, 80, 200, 100);
		n3.setForeground(Color.WHITE);
		text3 = new JTextField(10);
		text3.setBounds(550, 150, 200, 30);

		n4 = new JLabel("Materia");
		n4.setBounds(880, 80, 200, 100);
		n4.setForeground(Color.WHITE);
		text4 = new JTextField(10);
		text4.setBounds(800, 150, 200, 30);

		registrarEntrada = new JButton("Registrar entrada");
		registrarEntrada.setBounds(450, 250, 150, 30);

		exitosamente = new JLabel();
		exitosamente.setBounds(360, 280, 400, 50);
		exitosamente.setForeground(Color.WHITE);

		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
		((JComponent) panel).setBorder(borde);

		/********************************************************
		 * Creacion del panel y agregacion de todo el contenido *
		 ********************************************************/
		setIconImage(icono.getImage());
		panel.setLayout(null);
		panel.add(description);
		panel.add(n1);
		panel.add(n2);
		panel.add(n3);
		panel.add(n4);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(text4);
		panel.add(exitosamente);
		panel.add(registrarEntrada);
		panel.setBackground(new Color(45, 45, 45));

		/*********************************************************
		 * Evento del boton para registrar el acceso del maestro *
		 *********************************************************/
		registrarEntrada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrarAccesoMaestro acceso = new RegistrarAccesoMaestro();
				try {
					boolean registro = acceso.registro(text1.getText(), text2.getText(), text3.getText(),
							text4.getText());
					if (registro) {
						exitosamente.setText("El maestro se registro exitosamente.");
					} else {
						exitosamente.setText("El maestro no esta dado de alta.");
					}
				} catch (SQLIntegrityConstraintViolationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}
}