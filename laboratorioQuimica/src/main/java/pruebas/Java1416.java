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
	public Java1416() {
		setLayout(new BorderLayout());
		JTable tabla = new JTable(new ModeloDatos());
		JScrollPane panel = new JScrollPane(tabla);
		add(panel, BorderLayout.CENTER);
	}
}