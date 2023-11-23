package vista.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controlador.EliminarMaestros;
import controlador.ModeloDatos;
import controlador.TablaHorarios;
import controlador.horarios.AsignarHorario;
import modelo.PeticionesBD;
import vista.rutas.Img;

public class Horarios extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel description, denegado, matriculaLabel, diaLabel, entradaLabel, salidaLabel, salonLabel;
	JButton apartar;
	int i = 0;

	String tecCuautla = Img.IMAGEN_1.getRuta();
	String tecNM = Img.IMAGEN_2.getRuta();
	String document = "total_";

	public Horarios() {
//		Encabezado de la ventana 
		setTitle("Administrador");
		setResizable(false);

		/************************************
		 * Panel de la tabla y su contenido *
		 ************************************/
		JPanel panelTable = new JPanel();
		panelTable.setBounds(200, 175, 950, 300);
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

		description = new JLabel("Seleccione la accion que desea realizar");
		description.setBounds(475, 15, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		matriculaLabel = new JLabel("Matricula");
		matriculaLabel.setBounds(265, 50, 200, 100);
		matriculaLabel.setForeground(Color.WHITE);
		List<String> maestros = EliminarMaestros.obtenerMaestros();
		JComboBox<String> matricula = new JComboBox<>(maestros.toArray(new String[0]));
		matricula.setBounds(250, 120, 100, 30);

		diaLabel = new JLabel("Día");
		diaLabel.setBounds(475, 50, 200, 100);
		diaLabel.setForeground(Color.WHITE);
		JComboBox<String> dia = new JComboBox<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" });
		dia.setBounds(435, 120, 100, 30);
		
		entradaLabel = new JLabel("Entrada");
		entradaLabel.setBounds(675, 50, 200, 100);
		entradaLabel.setForeground(Color.WHITE);
		String[] horas = generarHoras(7, 18);
        JComboBox<String> entrada = new JComboBox<>(horas);
        entrada.setBounds(635, 120, 100, 30);

        salidaLabel = new JLabel("Salida");
		salidaLabel.setBounds(875, 50, 200, 100);
		salidaLabel.setForeground(Color.WHITE);
		String[] horasS = generarHoras(8, 19);
        JComboBox<String> salida = new JComboBox<>(horasS);
        salida.setBounds(835, 120, 100, 30);
        
        salonLabel = new JLabel("Salon");
		salonLabel.setBounds(1075, 50, 200, 100);
		salonLabel.setForeground(Color.WHITE);
		JComboBox<String> salon = new JComboBox<>(new String[] { "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8" });
		salon.setBounds(1035, 120, 100, 30);
        
		apartar = new JButton("Apartar laboratorio");
		apartar.setBounds(200, 500, 200, 30);

		denegado = new JLabel(" ");
		denegado.setBounds(450, 490, 600, 50);
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
		panel.add(matriculaLabel);
		panel.add(diaLabel);
		panel.add(entradaLabel);
		panel.add(salidaLabel);
		panel.add(salonLabel);
		panel.add(matricula);
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
				String matriculaID = (String) matricula.getSelectedItem();
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
