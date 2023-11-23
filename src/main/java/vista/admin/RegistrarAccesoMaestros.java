package vista.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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

import controlador.EliminarMaestros;
import controlador.RegistrarAccesoMaestro;
import vista.rutas.Img;

//Clase funcional con la base de datos, solo falta renombrar variables
public class RegistrarAccesoMaestros extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel description, exitosamente, n1, n2, n3, n4, n5;
	JTextField text1;
	JButton registrarEntrada;
	int i = 0;

	String tecCuautla = Img.IMAGEN_1.getRuta();

	public RegistrarAccesoMaestros() {
		ImageIcon icono = new ImageIcon(tecCuautla);

		setTitle("Registro de accesos");
		setResizable(false);

		/************************************************************
		 * Contenido de listas a seleccionar para hacer el registro *
		 ************************************************************/
		description = new JLabel("Registre los datos del maestro que ingresara al laboratorio.");
		description.setBounds(300, 0, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		n1 = new JLabel("Matricula");
		n1.setBounds(105, 80, 200, 100);
		n1.setForeground(Color.WHITE);
		List<String> maestros = EliminarMaestros.obtenerMaestros();
		JComboBox<String> matricula = new JComboBox<>(maestros.toArray(new String[0]));
		matricula.setBounds(70, 150, 150, 30);

		n2 = new JLabel("Salon");
		n2.setBounds(335, 80, 200, 100);
		n2.setForeground(Color.WHITE);
		JComboBox<String> laboratorio = new JComboBox<>(
				new String[] { "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8" });
		laboratorio.setBounds(300, 150, 100, 30);

		n3 = new JLabel("Grupo");
		n3.setBounds(535, 80, 200, 100);
		n3.setForeground(Color.WHITE);
		JComboBox<String> group = new JComboBox<>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8" });
		group.setBounds(500, 150, 100, 30);

		n4 = new JLabel("Materia");
		n4.setBounds(730, 80, 200, 100);
		n4.setForeground(Color.WHITE);
		JComboBox<String> materias = new JComboBox<>(
				new String[] { "quimica", "fisica" });
		materias.setBounds(700, 150, 100, 30);

		n5 = new JLabel("Carrera");
		n5.setBounds(980, 80, 200, 100);
		n5.setForeground(Color.WHITE);
		JComboBox<String> carreras = new JComboBox<>(
				new String[] { "Sistemas Computacionales", "Mecatronica", "Electrónica", "Industrial", "Cívil" });
		carreras.setBounds(900, 150, 200, 30);
		

		/******************************************************************
		 * Botones para registrar accesos y liberar laboratorios ocupados *
		 ******************************************************************/
		registrarEntrada = new JButton("Registrar entrada");
		registrarEntrada.setBounds(475, 250, 150, 30);
		exitosamente = new JLabel(" ");
		exitosamente.setBounds(475, 280, 400, 50);
		exitosamente.setForeground(Color.WHITE);

		/*******************************************************************
		 * Evento del boton para centrar el JComboBox mediante renderizado *
		 *******************************************************************/
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
		matricula.setRenderer(renderer);
		laboratorio.setRenderer(renderer);
		group.setRenderer(renderer);
		materias.setRenderer(renderer);
		carreras.setRenderer(renderer);


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
		panel.add(n5);
		panel.add(matricula);
		panel.add(laboratorio);
		panel.add(group);
		panel.add(materias);
		panel.add(carreras);
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
					String matriculaM = (String) matricula.getSelectedItem();
					String salon = (String) laboratorio.getSelectedItem();
					int grupo = Integer.parseInt((String) group.getSelectedItem());
					String materia = (String) materias.getSelectedItem();
					String carrera = (String) carreras.getSelectedItem();
					
					
					boolean maestro = acceso.buscarMaestro(matriculaM);
					boolean laboratorio = acceso.buscarSalon(salon, i);

					if (maestro && laboratorio) {
						acceso.registro(matriculaM, salon, grupo, materia, carrera);
						exitosamente.setText("El maestro se registro exitosamente.");
						liberadoL.setText(" " );
						i = 0;
					} else if (!laboratorio) {
						exitosamente.setText("El laboratorio " + salon + " esta ocupado.");
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