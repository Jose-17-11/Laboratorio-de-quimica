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

import controlador.AgregarNuevoMaestro;

//Clase funcional con la base de datos, solo falta renombrar variables
public class NuevoMaestro extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel description, denegado, n1, n2, n3, n4;
	JTextField text1, text2, text3, text4;
	JButton crear;

	public NuevoMaestro() {
		ImageIcon icono = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
		setIconImage(icono.getImage());

		setTitle("Administrador");
		setResizable(false);

		JLabel imagenLabel = new JLabel();
		ImageIcon imagenIcon = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
		imagenLabel.setIcon(imagenIcon);
		imagenLabel.setBounds(50, 50, 100, 79);

		JLabel imagenLabel2 = new JLabel();
		ImageIcon imagenIcon2 = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\TecNM.png");
		imagenLabel2.setIcon(imagenIcon2);
		imagenLabel2.setBounds(860, 50, 190, 79);

		/***********************
		 * Contenido del panel *
		 ***********************/
		description = new JLabel("Registre los datos del nuevo maestro que agrgara.");
		description.setBounds(250, 20, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		n1 = new JLabel("Matricula");
		n1.setBounds(180, 135, 200, 100);
		n1.setForeground(Color.WHITE);
		text1 = new JTextField(10);
		text1.setBounds(90, 200, 220, 30);

		n2 = new JLabel("Nombre");
		n2.setBounds(510, 135, 200, 100);
		n2.setForeground(Color.WHITE);
		text2 = new JTextField(10);
		text2.setBounds(420, 200, 220, 30);

		n3 = new JLabel("Apellido");
		n3.setBounds(810, 135, 200, 100);
		n3.setForeground(Color.WHITE);
		text3 = new JTextField(10);
		text3.setBounds(720, 200, 220, 30);

		crear = new JButton("Crear");
		crear.setBounds(430, 325, 200, 30);

		denegado = new JLabel("");
		denegado.setBounds(430, 270, 400, 50);
		denegado.setForeground(Color.WHITE);

		/********************************************************
		 * Creacion del panel y agregacion de todo el contenido *
		 ********************************************************/
		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
		((JComponent) panel).setBorder(borde);
		panel.setLayout(null);
		panel.add(imagenLabel);
		panel.add(imagenLabel2);
		panel.add(description);
		panel.add(n1);
		panel.add(n2);
		panel.add(n3);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(crear);
		panel.add(denegado);
		panel.setBackground(new Color(45, 45, 45));

		/******************************************************************
		 * Evento del boton para agregar nuevo maestro a la base de datos *
		 ******************************************************************/
		crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarNuevoMaestro nuevoMaestro = new AgregarNuevoMaestro();
				try {
					boolean agregar = nuevoMaestro.registroNuevoMaestro(text1.getText(), text2.getText(),
							text3.getText());
					if (agregar) {
						denegado.setText("El maestro se a registrado exitosamente.");
					} else {
						denegado.setText("El maestro ya se encuentra registrado.");
					}
				} catch (SQLIntegrityConstraintViolationException e1) {
					denegado.setText("El maestro que intenta ingresar ya se encuentra registrado.");
				}

			}
		});

	}
}
