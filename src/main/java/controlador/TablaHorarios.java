package controlador;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablaHorarios extends JPanel {
	ModeloDatosHorarios modelo;
	JTable tabla;
	
	public TablaHorarios() {
		setLayout(new BorderLayout());
		modelo = new ModeloDatosHorarios();
		tabla = new JTable(modelo);
		JScrollPane panel = new JScrollPane(tabla);
		add(panel, BorderLayout.CENTER);
	}
}
