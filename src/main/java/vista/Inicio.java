package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import controlador.DatosAdmin;

//Clase funcional con la base de datos, solo falta renombrar variables
public class Inicio extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titulo, description, description2, denegado;
	JButton registrarEntrada, altaMaestro, deleteMaestro, reporte;

	String tecCuautla = Img.IMAGEN_1.getRuta();
	String tecNM = Img.IMAGEN_2.getRuta();
	String admin = Img.IMAGEN_3.getRuta();

	public Inicio() {
		ImageIcon icono = new ImageIcon(tecCuautla);
		setIconImage(icono.getImage());
		
		setTitle("Administrador");
		setResizable(false);
		/**********************************
		 * Panel Izquierdo y su contenido *
		 **********************************/
		JLabel nombre, matricula, correo;
		// Crear un panel en la izquierda con un color diferente
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBounds(0, 0, 240, 800);
		panelIzquierda.setBackground(new Color(0, 56, 205));
		panelIzquierda.setLayout(null); // Puedes utilizar otro layout si lo prefieres

		getContentPane().add(panelIzquierda);
		JLabel imagenTecnm = new JLabel();
		ImageIcon imagenIcon2 = new ImageIcon(tecNM);
		imagenTecnm.setIcon(imagenIcon2);
		imagenTecnm.setBounds(27, 7, 185, 79);

		JLabel imagenAdmin = new JLabel();
		ImageIcon imagenIcon3 = new ImageIcon(admin);
		imagenAdmin.setIcon(imagenIcon3);
		imagenAdmin.setBounds(27, 110, 185, 185);

		DatosAdmin datos = new DatosAdmin();
		String name = datos.nombre();
		nombre = new JLabel("Nombre: " + name);
		nombre.setBounds(10, 280, 400, 100);
		nombre.setForeground(Color.WHITE);

		String id = datos.matricula();
		matricula = new JLabel("Matricula: " + id);
		matricula.setBounds(10, 315, 400, 100);
		matricula.setForeground(Color.WHITE);

		String email = datos.correo();
		correo = new JLabel("correo: " + email);
		correo.setBounds(10, 350, 400, 100);
		correo.setForeground(Color.WHITE);

		panelIzquierda.add(nombre);
		panelIzquierda.add(matricula);
		panelIzquierda.add(correo);
		panelIzquierda.add(imagenTecnm);
		panelIzquierda.add(imagenAdmin);

		/*********************************
		 * Contenido del panel principal *
		 *********************************/

		titulo = new JLabel("Bienvenido");
		titulo.setBounds(570, 0, 400, 100);
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setForeground(Color.WHITE);

		description = new JLabel("Seleccione la accion que necesite realizar");
		description.setBounds(420, 60, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		registrarEntrada = new JButton("Registrar entrada");
		registrarEntrada.setBounds(320, 135, 150, 30);

		altaMaestro = new JButton("Nuevo maestro");
		altaMaestro.setBounds(550, 135, 150, 30);

		deleteMaestro = new JButton("Eliminar maestro");
		deleteMaestro.setBounds(780, 135, 150, 30);

		description2 = new JLabel("Consultar el registro de accesos");
		description2.setBounds(535, 200, 700, 100);
		description2.setForeground(Color.WHITE);
		reporte = new JButton("Reporte de accesos");
		reporte.setBounds(530, 265, 200, 30);

		denegado = new JLabel();
		denegado.setBounds(270, 330, 400, 50);
		denegado.setForeground(Color.WHITE);

		/********************************
		 * Creacion del panel principal *
		 ********************************/
		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
		((JComponent) panel).setBorder(borde);
		panel.setLayout(null);
		panel.add(titulo);
		panel.add(description);
		panel.add(registrarEntrada);
		panel.add(altaMaestro);
		panel.add(deleteMaestro);
		panel.add(description2);
		panel.add(reporte);
//		Color de fondo del panel
		panel.setBackground(new Color(45, 45, 45));

		/*************************************************************************
		 * Conjunto de eventos que ocurren al precionar cualquier boton del menu *
		 *************************************************************************/

		/******* Registrar entrada de maestros a los laboratorios ***************/
		registrarEntrada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrarAccesoMaestros sm = new RegistrarAccesoMaestros();
				sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sm.setVisible(true);
				sm.setBounds(200, 250, 1050, 400);
			}
		});
		/******* Agregar un nuevo maestro a la base de datos ***************/
		altaMaestro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NuevoMaestro sm = new NuevoMaestro();
				sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sm.setVisible(true);
				sm.setBounds(15, 0, 1100, 500);
			}
		});

		/******* Eliminar un maestro de la base de datos ***************/
		deleteMaestro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EliminarMaestro sm = new EliminarMaestro();
				sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sm.setVisible(true);
				sm.setBounds(15, 0, 920, 400);
			}
		});

		/*******
		 * Generar un reporte de todos los maestros de accedieron en la semana
		 ***************/
		reporte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					ReporteAccesos sm = new ReporteAccesos();
					sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					sm.setVisible(true);
					sm.setBounds(15, 0, 1350, 600);
				});
			}
		});
	}
}
