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

import controlador.Acceso;
import modelo.Administrador;

public class Login extends JFrame {
//		Variables que tomaran el valor de los inputs
	String usuario, password;
//		Textos label que diran el dato que se debe ingresar en los inputs
	JLabel titulo, n1, n2, denegado;
//		Inputs que almasenaran el dato que se les ingrese
	JTextField text1, text2;
//		Boton que al darle click hara una accion
	JButton boton;

//		Metodo constructor principal que mostrara todo el formulario
	public Login() {
//		Se crea el objeto icono para que el software tenga su icono personalizado
		ImageIcon icono = new ImageIcon(
				// Se le ingresa de parametro la direccion donde esta ubicado el icono a elegir
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
		setIconImage(icono.getImage());
//		Encabezado de la ventana 
		setTitle("Login");
		setResizable(false);// Esta funcion hace que la ventana no pueda minimizarse ni expandirse

		JLabel imagenLabel = new JLabel();// Se crea el label que almacenara el logo del tec de cuautla
//		Se crea el objeto de tipo imagen pasandole la direccion de donde se encuentra la imagen a utilizar
		ImageIcon imagenIcon = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
		imagenLabel.setIcon(imagenIcon);// Se le pasa de parametro al label el objeto que contiene a la imagen a
										// utilizar
		imagenLabel.setBounds(75, 50, 100, 79);// Se definen las dimenciones de la posicion y el tamaño de la imagen

		JLabel imagenLabel2 = new JLabel();
		ImageIcon imagenIcon2 = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\TecNM.png");
		imagenLabel2.setIcon(imagenIcon2);
		imagenLabel2.setBounds(500, 50, 190, 79);

//		Titulo Label descriptivo principal
		titulo = new JLabel("Iniciar sesión");
		titulo.setBounds(280, 0, 400, 100);
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setForeground(Color.WHITE);

//		Texto label que indica que que dato debe ingresarse en la entrada de texto
		n1 = new JLabel("Usuario");
		n1.setBounds(330, 85, 200, 100);// Se definen las dimensiones de la posicion y el tamaño del Label o texto
		n1.setForeground(Color.WHITE);
//		Entrada de texto que extrae el dato que se le ingresa
		text1 = new JTextField(10);
		text1.setBounds(240, 150, 220, 30);

		n2 = new JLabel("Contraseña");
		n2.setForeground(Color.WHITE);
		n2.setBounds(320, 175, 200, 100);
		text2 = new JPasswordField();
		text2.setBounds(240, 240, 220, 30);

//		Boton que al darle click hara todas las acciones que se le indique
		boton = new JButton("Iniciar sesión");
		boton.setBounds(275, 350, 150, 30);
//		JLabel que aparecera cada que el acceso sea denegado 
		denegado = new JLabel();
		denegado.setBounds(250, 260, 400, 50);
		denegado.setFont(new Font("Arial", Font.BOLD, 12));
		denegado.setForeground(new Color(0, 0, 0));

//		Se generan el panel principal
		Container panel = getContentPane();
//		Color de fondo del panel	
		panel.setBackground(new Color(0, 56, 205));
//		Se le puso borde al panel principal para mejor visibilidad
		LineBorder borde = new LineBorder(Color.BLACK, 2);
		((JComponent) panel).setBorder(borde);
//		Primero se pone null el setLayaout y despues se visibilisan todos los componentes del panel
		panel.setLayout(null);
		panel.add(imagenLabel);
		panel.add(imagenLabel2);
		panel.add(titulo);
		panel.add(n1);
		panel.add(n2);
		panel.add(text1);
		panel.add(text2);
		panel.add(denegado);
		panel.add(boton);
//	Acciones que se activaran al darle click al boton calcular
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				usuario = text1.getText();
				password = text2.getText();
				Administrador ingresar = new Administrador(usuario, password);
				Acceso login = new Acceso();
//				Se verifica si el metodo acceso de la clase Acceso devuelve true o false de acuerdo a los datos ingresados
				if (login.acceso(ingresar.getUsuario(), ingresar.getPassword())) {
//					En el caso de que si lo sean se visibiliza o abre la ventana del administrador
					Inicio sm = new Inicio();
					sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					sm.setVisible(true);
					sm.setBounds(200, 0, 1010, 650);
					denegado.setText(" ");
				} else {
					// Si los datos ingresados son incorrectos se le da el siguiente texto a la
					// variable denegado
					denegado.setText("Usuario o contraseña incorrectos.");
				}

			}
		});
	}

//	Por ultimo el metodo main que hara la ventana con la parte visual que sea visible 
	public static void main(String[] args) {
		Login sm = new Login();
		sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sm.setVisible(true);
		sm.setBounds(300, 100, 750, 600);
	}
}
