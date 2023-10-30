package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import pruebas.Java1416;

public class ReporteAccesos extends JFrame {
	JLabel description, denegado;
	JButton reporte;

	private JTable table;
	private DefaultTableModel tableModel;
	
	String tecCuautla = Img.IMAGEN_1.getRuta();
	String tecNM = Img.IMAGEN_2.getRuta();

	public ReporteAccesos() {
//		Encabezado de la ventana 
		setTitle("Administrador");
		setResizable(false);

		/******************************************************
		 * Panel Izquierdo y su contenido
		 *******************************************/
		JPanel panelTable = new JPanel();
		panelTable.setBounds(340, 150, 600, 300); // Ajusta las coordenadas y el tamaño según tus necesidades
		panelTable.setBackground(new Color(0, 56, 205)); // Establece el color del panel
		panelTable.setLayout(new BorderLayout()); // Puedes utilizar otro layout si lo prefieres

		// Agrega el panel a la ventana
		getContentPane().add(panelTable);
		panelTable.setBackground(new Color(250, 250, 250));
		Java1416 java1416 = new Java1416();
		panelTable.add(java1416, BorderLayout.CENTER);
		/******************************************************
		 * Panel Izquierdo y su contenido
		 *******************************************/

		JLabel imagenLabel = new JLabel();// Se crea el label que almacenara el logo del tec de cuautla
//		Se crea el objeto de tipo imagen pasandole la direccion de donde se encuentra la imagen a utilizar
		ImageIcon imagenIcon = new ImageIcon(tecCuautla);
		imagenLabel.setIcon(imagenIcon);// Se le pasa de parametro al label el objeto que contiene a la imagen a
										// utilizar
		imagenLabel.setBounds(100, 50, 100, 79);// Se definen las dimenciones de la posicion y el tamaño de la imagen

		JLabel imagenLabel2 = new JLabel();
		ImageIcon imagenIcon2 = new ImageIcon(tecNM);
		imagenLabel2.setIcon(imagenIcon2);
		imagenLabel2.setBounds(1060, 50, 190, 79);

		description = new JLabel("Ingrese los datos del profesor");
		description.setBounds(475, 35, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		reporte = new JButton("Imprimir");
		reporte.setBounds(530, 500, 200, 30);

		denegado = new JLabel();
		denegado.setBounds(220, 330, 400, 50);
		denegado.setForeground(Color.WHITE);

//		Se generan los elementos que estaran visibles
		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
		((JComponent) panel).setBorder(borde);
		ImageIcon icono = new ImageIcon(tecCuautla);
		setIconImage(icono.getImage());
		panel.setLayout(null);
		panel.add(imagenLabel);
		panel.add(imagenLabel2);
		panel.add(description);
		panel.add(reporte);
//		Color de fondo del panel
		panel.setBackground(new Color(45, 45, 45));

	}
}
