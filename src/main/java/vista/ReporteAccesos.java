package vista;

import java.awt.BorderLayout;
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
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import modelo.PeticionesBD;
import pruebas.Java1416;
import pruebas.ModeloDatos;

public class ReporteAccesos extends JFrame {
	JLabel description, denegado;
	JButton reporteMensual, reporteSemanal, reporteDia, imprimir;

	private JTable table;
	private DefaultTableModel tableModel;
	
	int i = 0;
	
	String tecCuautla = Img.IMAGEN_1.getRuta();
	String tecNM = Img.IMAGEN_2.getRuta();

	public ReporteAccesos() {
//		Encabezado de la ventana 
		setTitle("Administrador");
		setResizable(false);

		/******************************************************
		 * Panel de la tabla y su contenido
		 *******************************************/
		JPanel panelTable = new JPanel();
		panelTable.setBounds(200, 150, 950, 300); // Ajusta las coordenadas y el tamaño según tus necesidades
		panelTable.setBackground(new Color(0, 0, 0)); // Establece el color del panel
		panelTable.setLayout(new BorderLayout()); // Puedes utilizar otro layout si lo prefieres
		
		// Agrega el panel a la ventana
		getContentPane().add(panelTable);
		panelTable.setBackground(new Color(250, 250, 250));
		Java1416 java1416 = new Java1416(i);
        panelTable.add(java1416, BorderLayout.CENTER);/******************************************************
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
		
		
		imprimir = new JButton("Imprimir");
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
			}
		});
		reporteSemanal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					i = 2;
					java1416.actualizarTabla(i);
				});
			}
		});
		reporteDia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					i = 3;
					java1416.actualizarTabla(i);
				});
			}
		});
		imprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModeloDatos modelo = new ModeloDatos(i);
				try {
					modelo.excel(i);
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
