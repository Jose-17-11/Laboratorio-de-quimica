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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controlador.AccesoAdmin;

//Clase funcional con la base de datos, solo falta renombrar variables
public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String usuario, password;
	JLabel titulo, n1, n2, denegado;
	JTextField text1, text2;
	JButton acceder;

	// Metodo constructor principal que mostrara todo el formulario
	public Login() {
		ImageIcon icono = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
		setIconImage(icono.getImage());
		setTitle("Login");
		// Logo del tec de cuautla
		JLabel imagenLabel = new JLabel();
		ImageIcon imagenIcon = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
		imagenLabel.setIcon(imagenIcon);
		imagenLabel.setBounds(75, 50, 100, 79);

		// Logo del Instituto Tecnologico Nacional de Mexico
		JLabel imagenLabel2 = new JLabel();
		ImageIcon imagenIcon2 = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\TecNM.png");
		imagenLabel2.setIcon(imagenIcon2);
		imagenLabel2.setBounds(500, 50, 190, 79);

		/***********************
		 * Contenido del login *
		 ***********************/

		titulo = new JLabel("Iniciar sesión");
		titulo.setBounds(280, 0, 400, 100);
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setForeground(Color.WHITE);

		n1 = new JLabel("Usuario");
		n1.setBounds(330, 85, 200, 100);
		n1.setForeground(Color.WHITE);
		text1 = new JTextField(10);
		text1.setBounds(240, 150, 220, 30);

		n2 = new JLabel("Contraseña");
		n2.setForeground(Color.WHITE);
		n2.setBounds(320, 175, 200, 100);
		text2 = new JPasswordField();
		text2.setBounds(240, 240, 220, 30);

		acceder = new JButton("Iniciar sesión");
		acceder.setBounds(275, 350, 150, 30);
		denegado = new JLabel();
		denegado.setBounds(250, 260, 400, 50);
		denegado.setFont(new Font("Arial", Font.BOLD, 12));
		denegado.setForeground(new Color(0, 0, 0));

		/********************************************************
		 * Creacion del panel y agregacion de todo el contenido *
		 ********************************************************/

		Container panel = getContentPane();
		panel.setBackground(new Color(0, 56, 205));
		LineBorder borde = new LineBorder(Color.BLACK, 2);
		((JComponent) panel).setBorder(borde);
		panel.setLayout(null);
		panel.add(imagenLabel);
		panel.add(imagenLabel2);
		panel.add(titulo);
		panel.add(n1);
		panel.add(n2);
		panel.add(text1);
		panel.add(text2);
		panel.add(denegado);
		panel.add(acceder);

		/****************************************
		 * Evento del boton para iniciar sesion *
		 ****************************************/

		acceder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				usuario = text1.getText();
				password = text2.getText();
				AccesoAdmin login = new AccesoAdmin();
				boolean controler = login.acceso(usuario, password);
				if (controler) {
					Inicio sm = new Inicio();
					sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					sm.setVisible(true);
					sm.setBounds(200, 0, 1010, 550);
					denegado.setText(" ");
				} else {
					denegado.setText("Usuario o contraseña incorrectos.");
				}
			}
		});
	}
}
