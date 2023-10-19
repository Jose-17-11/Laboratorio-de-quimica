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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import conexion_jdbc.Peticiones;
//Clase completamente funcional con base de datos
public class NuevoMaestro extends JFrame {
	JLabel description, denegado, n1, n2, n3, n4;
	JTextField text1, text2, text3, text4;
	JButton crear;

	private JTable table;
	private DefaultTableModel tableModel;

	public NuevoMaestro() {
//		Encabezado de la ventana 
		setTitle("Administrador");
		setResizable(false);

		JLabel imagenLabel = new JLabel();// Se crea el label que almacenara el logo del tec de cuautla
//		Se crea el objeto de tipo imagen pasandole la direccion de donde se encuentra la imagen a utilizar
		ImageIcon imagenIcon = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
		imagenLabel.setIcon(imagenIcon);// Se le pasa de parametro al label el objeto que contiene a la imagen a
										// utilizar
		imagenLabel.setBounds(50, 50, 100, 79);// Se definen las dimenciones de la posicion y el tamaño de la imagen

		JLabel imagenLabel2 = new JLabel();
		ImageIcon imagenIcon2 = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\TecNM.png");
		imagenLabel2.setIcon(imagenIcon2);
		imagenLabel2.setBounds(860, 50, 190, 79);

		/*********************************************************
		 * Entradas de texto
		 **********************************************************/
		description = new JLabel("Registre los datos del nuevo maestro que agrgara.");
		description.setBounds(250, 20, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

//		Texto label que indica que hay que entrada es
		n1 = new JLabel("Matricula");
		n1.setBounds(180, 135, 200, 100);
		n1.setForeground(Color.WHITE);
//		Input que extrae el dato que se le ingresa
		text1 = new JTextField(10);
		text1.setBounds(90, 200, 220, 30);

//		Texto label que indica que hay que entrada es
		n2 = new JLabel("Nombre");
		n2.setBounds(510, 135, 200, 100);
		n2.setForeground(Color.WHITE);
//		Input que extrae el dato que se le ingresa
		text2 = new JTextField(10);
		text2.setBounds(420, 200, 220, 30);

		n3 = new JLabel("Apellido");
		n3.setBounds(810, 135, 200, 100);
		n3.setForeground(Color.WHITE);
		text3 = new JTextField(10);
		text3.setBounds(720, 200, 220, 30);

		crear = new JButton("Crear");
		crear.setBounds(430, 325, 200, 30);

		denegado = new JLabel("Maestro agregado con éxito.");
		denegado.setBounds(450, 270, 400, 50);
		denegado.setForeground(Color.WHITE);

//		Se generan los elementos que estaran visibles
		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
		((JComponent) panel).setBorder(borde);
		ImageIcon icono = new ImageIcon(
				"C:\\Users\\Jose\\git\\laboratorioQuimica\\laboratorioQuimica\\src\\main\\resources\\img\\tecCuautla.png");
		setIconImage(icono.getImage());
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
//		Color de fondo del panel
		panel.setBackground(new Color(45, 45, 45));

		crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Peticiones nuevoMaestro = new Peticiones();
				try {
					String aviso = nuevoMaestro.nuevoMaestro(text1.getText(), text2.getText(), text3.getText());
					denegado.setText(aviso);
				} catch (SQLIntegrityConstraintViolationException e1) {
					denegado.setText("El maestro que intenta ingresar ya se encuentra registrado.");
				}

			}
		});

	}
}
