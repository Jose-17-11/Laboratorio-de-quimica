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
import javax.swing.border.LineBorder;

import controlador.maestro.DatosMaestros;
import modelo.Profesor;
import vista.rutas.Img;

public class InicioM extends JFrame {
	/**
	 * 
	 */
	JLabel titulo, description, description2, denegado;
	JButton horario, registrarse;

	String tecCuautla = Img.IMAGEN_1.getRuta();
	String tecNM = Img.IMAGEN_2.getRuta();
	String admin = Img.IMAGEN_3.getRuta();
	String usuario;
	public InicioM(String usuario) {
		this.usuario = usuario;
		ImageIcon icono = new ImageIcon(tecCuautla);
		setIconImage(icono.getImage());

		setTitle("Administrador");
		setResizable(false);
		/**********************************
		 * Panel Izquierdo y su contenido *
		 **********************************/
		JLabel nombre, apellido, matricula;
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

		DatosMaestros maestro = new DatosMaestros();
		Profesor datos = maestro.maestro(usuario);

		String name = datos.getNombre();
		nombre = new JLabel("Nombre: " + name);
		nombre.setBounds(10, 280, 400, 100);
		nombre.setForeground(Color.WHITE);

		String apellidos = datos.getApellidos();
		apellido = new JLabel("Apellidos: " + apellidos);
		apellido.setBounds(10, 315, 400, 100);
		apellido.setForeground(Color.WHITE);
//
		String id = usuario;
		matricula = new JLabel("Matricula: " + id);
		matricula.setBounds(10, 350, 400, 100);
		matricula.setForeground(Color.WHITE);
//
		panelIzquierda.add(nombre);
		panelIzquierda.add(apellido);
		panelIzquierda.add(matricula);
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

		horario = new JButton("Horarios");
		horario.setBounds(320, 265, 150, 30);

		registrarse = new JButton("Registrarse");
		registrarse.setBounds(780, 265, 150, 30);

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
		panel.add(horario);
		panel.add(registrarse);
		panel.add(denegado);
		panel.setBackground(new Color(45, 45, 45));

		/*************************************************************************
		 * Conjunto de eventos que ocurren al precionar cualquier boton del menu *
		 *************************************************************************/

		/******* Registrar entrada de maestros a los laboratorios ***************/
		horario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HorariosM sm = new HorariosM(usuario);
				sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sm.setVisible(true);
				sm.setBounds(15, 0, 1350, 700);
			}
		});
//		/******* Agregar un nuevo maestro a la base de datos ***************/
		registrarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrarM sm = new RegistrarM(usuario);
				sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sm.setVisible(true);
				sm.setBounds(80, 250, 1250, 400);
			}
		});
	}
}
