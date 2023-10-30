package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controlador.RegistrarAccesoMaestro;

//Clase funcional con la base de datos, solo falta renombrar variables
public class RegistrarAccesoMaestros extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel description, exitosamente, n1, n2, n3, n4;
	JTextField text1;
	JButton registrarEntrada;

	String tecCuautla = Img.IMAGEN_1.getRuta();
	
	public RegistrarAccesoMaestros() {
		ImageIcon icono = new ImageIcon(tecCuautla);

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

		JComboBox<String> laboratorio = new JComboBox<>(
				new String[] { "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8" });
		n2 = new JLabel("Salon");
		n2.setBounds(390, 80, 200, 100);
		n2.setForeground(Color.WHITE);
		laboratorio.setBounds(300, 150, 200, 30);

		JComboBox<String> group = new JComboBox<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" });
		n3 = new JLabel("Grupo");
		n3.setBounds(620, 80, 200, 100);
		n3.setForeground(Color.WHITE);
		group.setBounds(550, 150, 200, 30);

		JComboBox<String> materias = new JComboBox<>(new String[] { "quimica", "fisica", "biologia" });
		n4 = new JLabel("Materia");
		n4.setBounds(880, 80, 200, 100);
		n4.setForeground(Color.WHITE);
		materias.setBounds(800, 150, 200, 30);

		// Centra el JComboBox mediante renderizado
		DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				label.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto
				return label;
			}
		};
		laboratorio.setRenderer(renderer);
		group.setRenderer(renderer);
		materias.setRenderer(renderer);

		registrarEntrada = new JButton("Registrar entrada");
		registrarEntrada.setBounds(450, 250, 150, 30);

		exitosamente = new JLabel("El maestro no esta dado de alta.");
		exitosamente.setBounds(425, 280, 400, 50);
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
		panel.add(laboratorio);
		panel.add(group);
		panel.add(materias);
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

					String salon = (String) laboratorio.getSelectedItem();
					int grupo = Integer.parseInt((String) group.getSelectedItem());
					String materia = (String) materias.getSelectedItem();

					boolean maestro = acceso.buscarMaestro(text1.getText());
					boolean laboratorio = acceso.buscarSalon(salon);
					
					if (maestro && laboratorio) {
						acceso.registro(text1.getText(), salon, grupo, materia);
						exitosamente.setText("El maestro se registro exitosamente.");
					} else if (!laboratorio) {
						exitosamente.setText("El laboratorio esta ocupado.");
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