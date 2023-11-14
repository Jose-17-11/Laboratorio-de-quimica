package pruebas;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelo.PeticionesBD;

public class Java1416 extends JPanel {
	
	ModeloDatos modelo;
	JTable tabla;
	
	public Java1416(int i) {
		setLayout(new BorderLayout());
		modelo = new ModeloDatos(i);
		tabla = new JTable(modelo);
		JScrollPane panel = new JScrollPane(tabla);
		add(panel, BorderLayout.CENTER);
	}
	
	public void actualizarTabla(int nuevoI) {
		modelo = new ModeloDatos(nuevoI);
        tabla.setModel(modelo);  // Establece el nuevo modelo en la tabla
        tabla.repaint();  // Refresca la interfaz gráfica
	}
}