package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controlador.TablaHorarios;
import controlador.horarios.AsignarHorario;
import vista.rutas.Img;

public class HorariosM extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel description, denegado, matricula, matriculaLabel, diaLabel, entradaLabel, salidaLabel, salonLabel;
	JButton apartar;
	int i = 0;

	String tecCuautla = Img.IMAGEN_1.getRuta();
	String tecNM = Img.IMAGEN_2.getRuta();
	String document = "total_";
	String usuario;

	public HorariosM(String usuario) {
		this.usuario = usuario;
//		Encabezado de la ventana 
		setTitle("Administrador");
		setResizable(false);

		/************************************
		 * Panel de la tabla y su contenido *
		 ************************************/
		JPanel panelTable = new JPanel();
		panelTable.setBounds(200, 245, 950, 300);
		panelTable.setBackground(new Color(0, 0, 0)); // Establece el color del panel
		panelTable.setLayout(new BorderLayout());

		getContentPane().add(panelTable);
		panelTable.setBackground(new Color(250, 250, 250));
		TablaHorarios java1416 = new TablaHorarios();
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

		description = new JLabel("Seleccione correctamente los datos para asignar laboratorio.");
		description.setBounds(350, 15, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		matricula = new JLabel("Matricula");
		matricula.setBounds(265, 120, 200, 100);
		matricula.setForeground(Color.WHITE);
		
		matriculaLabel = new JLabel(usuario);
		matriculaLabel.setBounds(265, 150, 200, 100);
		matriculaLabel.setForeground(Color.WHITE);
		
		diaLabel = new JLabel("Día");
		diaLabel.setBounds(475, 120, 200, 100);
		diaLabel.setForeground(Color.WHITE);
		JComboBox<String> dia = new JComboBox<>(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES" });
		dia.setBounds(435, 190, 100, 30);

		entradaLabel = new JLabel("Entrada");
		entradaLabel.setBounds(675, 120, 200, 100);
		entradaLabel.setForeground(Color.WHITE);
		String[] horas = generarHoras(7, 18);
		JComboBox<String> entrada = new JComboBox<>(horas);
		entrada.setBounds(635, 190, 100, 30);

		salidaLabel = new JLabel("Salida");
		salidaLabel.setBounds(875, 120, 200, 100);
		salidaLabel.setForeground(Color.WHITE);
		String[] horasS = generarHoras(8, 19);
		JComboBox<String> salida = new JComboBox<>(horasS);
		salida.setBounds(835, 190, 100, 30);

		salonLabel = new JLabel("Salon");
		salonLabel.setBounds(1075, 120, 200, 100);
		salonLabel.setForeground(Color.WHITE);
		JComboBox<String> salon = new JComboBox<>(new String[] { "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8" });
		salon.setBounds(1035, 190, 100, 30);

		apartar = new JButton("Asignar laboratorio");
		apartar.setBounds(200, 570, 200, 30);

		denegado = new JLabel(" ");
		denegado.setBounds(450, 560, 600, 50);
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
		panel.add(matricula);
		panel.add(matriculaLabel);
		panel.add(diaLabel);
		panel.add(entradaLabel);
		panel.add(salidaLabel);
		panel.add(salonLabel);
		panel.add(dia);
		panel.add(entrada);
		panel.add(salida);
		panel.add(salon);
		panel.add(apartar);
		panel.add(denegado);
//		Color de fondo del panel
		panel.setBackground(new Color(45, 45, 45));

		apartar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String matriculaID = usuario;
				String day = (String) dia.getSelectedItem();
				String horaEntrada = (String) entrada.getSelectedItem();
				String horaSalida = (String) salida.getSelectedItem();
				String lab = (String) salon.getSelectedItem();
				AsignarHorario asignarSalon = new AsignarHorario();
				String resultado = asignarSalon.asignar(matriculaID, day, horaEntrada, horaSalida, lab);
				denegado.setText(resultado);
			}
		});
	}

	private String[] generarHoras(int j, int k) {
		String[] horas = new String[12];

		for (int i = j; i <= k; i++) {
			horas[i - j] = String.format("%02d:00", i);
		}

		return horas;
	}
}
