package vista.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import controlador.ModeloDatos;
import controlador.TablaAccesos;
import vista.rutas.Img;

public class ReporteAccesos extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel description, denegado;
	JButton reporteMensual, reporteSemanal, reporteDia, imprimir;
	int i = 0;

	String tecCuautla = Img.IMAGEN_1.getRuta();
	String tecNM = Img.IMAGEN_2.getRuta();
	String document = "total_";
	
	public ReporteAccesos() {
//		Encabezado de la ventana 
		setTitle("Administrador");
		setResizable(false);

		/************************************
		 * Panel de la tabla y su contenido	*
		 ************************************/
		JPanel panelTable = new JPanel();
		panelTable.setBounds(200, 150, 950, 300); 
		panelTable.setBackground(new Color(0, 0, 0)); // Establece el color del panel
		panelTable.setLayout(new BorderLayout());

		getContentPane().add(panelTable);
		panelTable.setBackground(new Color(250, 250, 250));
		TablaAccesos java1416 = new TablaAccesos(i);
		panelTable.add(java1416, BorderLayout.CENTER);

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

		description = new JLabel("Seleccione la accion que desea realizar");
		description.setBounds(475, 35, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		reporteMensual = new JButton("Mes");
		reporteMensual.setBounds(230, 100, 200, 30);

		reporteSemanal = new JButton("Semana");
		reporteSemanal.setBounds(530, 100, 200, 30);

		reporteDia = new JButton("Dia");
		reporteDia.setBounds(830, 100, 200, 30);

		imprimir = new JButton("Descargar Excel");
		imprimir.setBounds(530, 500, 200, 30);

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
		panel.add(reporteMensual);
		panel.add(reporteSemanal);
		panel.add(reporteDia);
		panel.add(imprimir);
//		Color de fondo del panel
		panel.setBackground(new Color(45, 45, 45));
		reporteMensual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i = 1;
				java1416.actualizarTabla(i);
				document = "mensual_";
			}
		});
		reporteSemanal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					i = 2;
					java1416.actualizarTabla(i);
					document = "semanal_";
				});
			}
		});
		reporteDia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					i = 3;
					java1416.actualizarTabla(i);
					document = "dia_";
				});
			}
		});
		imprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModeloDatos modelo = new ModeloDatos(i);
				try {
					modelo.excel(i, document);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
