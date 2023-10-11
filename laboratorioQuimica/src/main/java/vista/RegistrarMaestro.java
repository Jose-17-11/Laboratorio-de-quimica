package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

import modelo.Profesor;

	public class RegistrarMaestro extends JFrame{
//		Textos label que diran el dato que se debe ingresar en los inputs
		JLabel description,exitosamente,n1,n2,n3;
		JTextField text1,text2,text3;
//		Boton que al darle click hara una accion
		JButton registrarEntrada;
		
//		Metodo principal que mostrara todo el formulario
	public RegistrarMaestro() { 
//		Encabezado de la ventana 
        setTitle("Registro de accesos");
		setResizable(false);

		description = new JLabel("Registre los datos del maestro que ingresara al laboratorio.");
		description.setBounds(190,0,700,100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

//		Texto label que indica que hay que entrada es
		n1 = new JLabel("Matricula");
		n1.setBounds(150, 80,200,100);
		n1.setForeground(Color.WHITE);
//		Input que extrae el dato que se le ingresa
		text1 = new JTextField(10);
		text1.setBounds(60,150,220,30);
		
//		Texto label que indica que hay que entrada es
		n2 = new JLabel("Grupo");
		n2.setBounds(450, 80,200,100);
		n2.setForeground(Color.WHITE);
//		Input que extrae el dato que se le ingresa
		text2 = new JTextField(10);
		text2.setBounds(360,150,220,30);

		n3 = new JLabel("Materia");
		n3.setBounds(750, 80,200,100);
		n3.setForeground(Color.WHITE);
		text3 = new JTextField(10);
		text3.setBounds(660, 150, 220, 30);
        
//		Boton que al darle click hara todas las acciones que se le indique
		registrarEntrada = new JButton("Registrar entrada");
		registrarEntrada.setBounds(400, 250, 150, 30);
		
		exitosamente = new JLabel();
		exitosamente.setBounds(360,280,400,50);
		exitosamente.setForeground(Color.WHITE);
		
//		Se generan los elementos que estaran visibles
		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
        ((JComponent) panel).setBorder(borde);

        ImageIcon icono = new ImageIcon("C:\\Users\\Jose\\eclipse-workspace-poo\\12345\\src\\main\\resources\\img\\tecCuautla.png");
        setIconImage(icono.getImage());
		panel.setLayout(null);
		panel.add(description);
		panel.add(n1);
		panel.add(n2);
		panel.add(n3);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(exitosamente);
		panel.add(registrarEntrada);
//		Color de fondo del panel
		panel.setBackground(Color.BLUE);
//	Acciones que se activaran al darle click al boton calcular
	registrarEntrada.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Profesor dates = new Profesor(text1.getText(), text2.getText());
			if(dates.getGrupo().equals(text1.getText()) && dates.getGrupo().equals(text2.getText()) && dates.getMateria().equals(text3.getText())) {
				exitosamente.setText("Se registraron los datos exitosamente");
			}else {
				
			}
		}
	});
	}
}
