package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controlador.EliminarMaestros;

//Clase completamente funcional con base de datos
public class EliminarMaestro extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel description, denegado, n1;
	JTextField text1;
	JButton eliminar;

	String tecCuautla = Img.IMAGEN_1.getRuta();
	String tecNM = Img.IMAGEN_2.getRuta();

	public EliminarMaestro() {
		ImageIcon icono = new ImageIcon(tecCuautla);
		setIconImage(icono.getImage());

		setTitle("Administrador");
		setResizable(false);

		JLabel imagenLabel = new JLabel();
		ImageIcon imagenIcon = new ImageIcon(tecCuautla);
		imagenLabel.setIcon(imagenIcon);
		imagenLabel.setBounds(50, 50, 100, 79);

		JLabel imagenLabel2 = new JLabel();
		ImageIcon imagenIcon2 = new ImageIcon(tecNM);
		imagenLabel2.setIcon(imagenIcon2);
		imagenLabel2.setBounds(670, 50, 190, 79);

		/***********************
		 * Contenido del panel *
		 ***********************/
		description = new JLabel("Ingrese la matricula del profesor");
		description.setBounds(265, 35, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		n1 = new JLabel("Matricula");
		n1.setBounds(395, 110, 200, 100);
		n1.setForeground(Color.WHITE);
		List<String> maestros = EliminarMaestros.obtenerMaestros();
		JComboBox<String> comboBox = new JComboBox<>(maestros.toArray(new String[0]));
		comboBox.setBounds(317, 170, 220, 30);

		eliminar = new JButton("Eliminar");
		eliminar.setBounds(325, 250, 200, 30);

		denegado = new JLabel();
		denegado.setBounds(310, 300, 400, 50);
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
		panel.add(n1);
		panel.add(comboBox);
		panel.add(description);
		panel.add(eliminar);
		panel.add(denegado);
		panel.setBackground(new Color(45, 45, 45));

		/*******************************************************************
		 * Evento del boton para eliminar a un maestro de la base de datos *
		 *******************************************************************/
		eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EliminarMaestros borrar = new EliminarMaestros();
				boolean autenticacion;
				try {
					String eliminar = (String) comboBox.getSelectedItem();
					autenticacion = borrar.eliminar(eliminar);
					if (autenticacion) {
						denegado.setText("El maestro a sido eliminado exitosamente.");
					} else {
						denegado.setText("El maestro no se encuentra registrado.");
					}
				} catch (SQLIntegrityConstraintViolationException e1) {
					denegado.setText("Error inesperado.");
				}
			}
		});
	}
}
