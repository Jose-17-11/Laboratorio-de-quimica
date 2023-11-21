package controlador;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablaAccesos extends JPanel {

	ModeloDatos modelo;
	JTable tabla;

	public TablaAccesos(int i) {
		setLayout(new BorderLayout());
		modelo = new ModeloDatos(i);
		tabla = new JTable(modelo);
		JScrollPane panel = new JScrollPane(tabla);
		add(panel, BorderLayout.CENTER);
	}

	public void actualizarTabla(int nuevoI) {
		modelo = new ModeloDatos(nuevoI);
		tabla.setModel(modelo); // Establece el nuevo modelo en la tabla
		tabla.repaint(); // Refresca la interfaz gráfica
	}
}