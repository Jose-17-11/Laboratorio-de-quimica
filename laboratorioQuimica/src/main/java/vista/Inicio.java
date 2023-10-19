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

import controlador.Filtros;

public class Inicio extends JFrame {
//		Textos label que diran el dato que se debe ingresar en los inputs
	JLabel titulo, description, description2, denegado;
//		Boton que al darle click hara una accion
	JButton registrarEntrada, altaMaestro, deleteMaestro, updateMaestro, reporte;

//		Metodo principal que mostrara todo el formulario
	public Inicio() {
//		Encabezado de la ventana 
		setTitle("Administrador");
		setResizable(false);

		/*************************************************************************************************************************************/
		/*******************************************
		 * Panel Izquierdo y su contenido
		 *******************************************/
		JLabel nombre, apellido, matricula, correo;
		// Crear un panel en la izquierda con un color diferente
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBounds(0, 0, 190, 800); // Ajusta las coordenadas y el tamaño según tus necesidades
		panelIzquierda.setBackground(new Color(0, 56, 205)); // Establece el color del panel izquierdo
		panelIzquierda.setLayout(null); // Puedes utilizar otro layout si lo prefieres

		// Agrega el panel izquierdo a la ventana
		getContentPane().add(panelIzquierda);
		JLabel imagenTecnm = new JLabel();
		ImageIcon imagenIcon2 = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\TecNM.png");
		imagenTecnm.setIcon(imagenIcon2);
		imagenTecnm.setBounds(2, 1, 185, 79);

		JLabel imagenAdmin = new JLabel();
		ImageIcon imagenIcon3 = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\Admin.png");
		imagenAdmin.setIcon(imagenIcon3);
		imagenAdmin.setBounds(2, 110, 185, 185);
		
		Filtros datos = new Filtros();
		String name = datos.nombreMatricula(true);
		nombre = new JLabel("Nombre: " + name);
		nombre.setBounds(20, 280, 400, 100);
		nombre.setForeground(Color.WHITE);


		apellido = new JLabel("Arias Hernandez");
		apellido.setBounds(20, 300, 400, 100);
		apellido.setForeground(Color.WHITE);
		
		String id = datos.nombreMatricula(false);
		matricula = new JLabel("Matricula: " + id);
		matricula.setBounds(20, 330, 400, 100);
		matricula.setForeground(Color.WHITE);

		correo = new JLabel("21680037@cuautla.tecnm.mx");
		correo.setBounds(10, 350, 400, 100);
		correo.setForeground(Color.WHITE);

		
		panelIzquierda.add(nombre);
		panelIzquierda.add(matricula);
		panelIzquierda.add(apellido);
		panelIzquierda.add(correo);
		panelIzquierda.add(imagenTecnm);
		panelIzquierda.add(imagenAdmin);
		/*************************************************************************************************************************************/

		/*******************************************
		 * Panel Principal y su contenido
		 *******************************************/

//		Titulo Label descriptivo
		titulo = new JLabel("Bienvenido");
		titulo.setBounds(520, 0, 400, 100);
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setForeground(Color.WHITE);

		description = new JLabel("Seleccione la accion que necesite realizar");
		description.setBounds(370, 60, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

//		Boton que al darle click hara todas las acciones que se le indique
		registrarEntrada = new JButton("Registrar entrada");
		registrarEntrada.setBounds(220, 135, 150, 30);

		altaMaestro = new JButton("Nuevo maestro");
		altaMaestro.setBounds(400, 135, 150, 30);

		deleteMaestro = new JButton("Eliminar maestro");
		deleteMaestro.setBounds(580, 135, 150, 30);

		updateMaestro = new JButton("Actualizar informacion");
		updateMaestro.setBounds(760, 135, 200, 30);

		description2 = new JLabel("Consultar el registro de accesos");
		description2.setBounds(505, 200, 700, 100);
		description2.setForeground(Color.WHITE);
		reporte = new JButton("Reporte de accesos");
		reporte.setBounds(500, 265, 200, 30);

		denegado = new JLabel();
		denegado.setBounds(220, 330, 400, 50);
		denegado.setForeground(Color.WHITE);

//		Se generan los elementos que estaran visibles
		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
		((JComponent) panel).setBorder(borde);
		ImageIcon icono = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
		setIconImage(icono.getImage());
		panel.setLayout(null);
		panel.add(titulo);
		panel.add(description);
		panel.add(registrarEntrada);
		panel.add(altaMaestro);
		panel.add(deleteMaestro);
		panel.add(updateMaestro);
		panel.add(description2);
		panel.add(reporte);
//		Color de fondo del panel
		panel.setBackground(new Color(45, 45, 45));
		/*************************************************************************************************************************************/
		/*********************************************************
		 * Conjunto de eventos que ocurren al precionar cualquier boton del menu
		 **********************************************************/
		/******* Registrar entrada de maestros a los laboratorios ***************/
		registrarEntrada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrarMaestro sm = new RegistrarMaestro();
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

		/******* Actualizar la informacion de algun maestro en especifico ***************/
		updateMaestro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActualizarInfo sm = new ActualizarInfo();
				sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sm.setVisible(true);
				sm.setBounds(15, 0, 1350, 600);
			}
		});

		/******* Generar un reporte de todos los maestros de accedieron en la semana ***************/
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
