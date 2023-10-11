package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

	public class Inicio extends JFrame{
//		Textos label que diran el dato que se debe ingresar en los inputs
		JLabel titulo,description,description2,denegado;
//		Boton que al darle click hara una accion
		JButton registrarEntrada, altaMaestro, deleteMaestro, updateMaestro, reporte;
		
//		Metodo principal que mostrara todo el formulario
	public Inicio() { 
//		Encabezado de la ventana 
        setTitle("Administrador");
		setResizable(false);
//		Titulo Label descriptivo
		titulo = new JLabel("Bienvenido");
		titulo.setBounds(400,0,400,100);
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setForeground(Color.WHITE);


		description = new JLabel("Seleccione la accion que necesite realizar");
		description.setBounds(250, 60,700,100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);
		
//		Boton que al darle click hara todas las acciones que se le indique
		registrarEntrada = new JButton("Registrar entrada");
		registrarEntrada.setBounds(70, 135, 150, 30);
		
		altaMaestro = new JButton("Nuevo maestro");
		altaMaestro.setBounds(270, 135, 150, 30);
		
		deleteMaestro = new JButton("Eliminar maestro");
		deleteMaestro.setBounds(480, 135, 150, 30);
		
		updateMaestro = new JButton("Actualizar informacion");
		updateMaestro.setBounds(700, 135, 200, 30);
		
		description2 = new JLabel("Consultar el registro de accesos");
		description2.setBounds(385, 200,700,100);
		description2.setForeground(Color.WHITE);
		reporte = new JButton("Reporte de accesos");
		reporte.setBounds(380, 265, 200, 30);
		
		denegado = new JLabel();
		denegado.setBounds(220,330,400,50);
		denegado.setForeground(Color.WHITE);
		
//		Se generan los elementos que estaran visibles
		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
        ((JComponent) panel).setBorder(borde);

        ImageIcon icono = new ImageIcon("C:\\Users\\Jose\\eclipse-workspace-poo\\12345\\src\\main\\resources\\img\\tecCuautla.png");
        setIconImage(icono.getImage());
		panel.setLayout(null);
		panel.add(titulo);
		panel.add(description);
		panel.add(registrarEntrada);
		panel.add(altaMaestro);
		panel.add(deleteMaestro);
		panel.add(updateMaestro);
		panel.add(description2);
		panel.add(reporte);
//		Color de fondo del panel
		panel.setBackground(Color.BLUE);
//	Acciones que se activaran al darle click al boton calcular
	registrarEntrada.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			RegistrarMaestro sm = new RegistrarMaestro();
			sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			sm.setVisible(true);
			sm.setBounds(200, 380,950,400);
		}
	});
	altaMaestro.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	deleteMaestro.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	updateMaestro.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	reporte.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	}
}
