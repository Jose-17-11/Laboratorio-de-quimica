package controlador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import modelo.PeticionesBD;

// El Modelo de la Tabla es el que controla todos los
// datos que se colocan en ella
public class ModeloDatos extends AbstractTableModel {
	int i;
	String[] columnNames = { "fecha", "hora", "maestro", "salon", "grupo", "materia", "carrera" };
	PeticionesBD accesos = new PeticionesBD();
	List<String[]> listaDatos = accesos.accesos(i); // Obtener la lista desde PeticionesBD;

	// Esta clase imprime los datos en la consola cada vez
	// que se produce un cambio en cualquiera de las
	// casillas de la tabla
	class TablaListener implements TableModelListener {
		public void tableChanged(TableModelEvent evt) {
		}
	}

	// Constructor
	public ModeloDatos(int i) {
		addTableModelListener(new TablaListener());
		this.i = i;
		this.accesos = new PeticionesBD();
		this.listaDatos = accesos.accesos(i);
	}

	public int getColumnCount() {
		return columnNames.length; // Devuelve la cantidad de columnas
	}

	// Devuelve el número de filas de la tabla
	public int getRowCount() {
		return listaDatos.size(); // Usa el tamaño de la lista en lugar del array
	}

	// Devuelve el valor de una determinada casilla de la tabla
	// identificada mediante fila y columna
	public Object getValueAt(int fila, int col) {
		String[] filaDatos = listaDatos.get(fila); // Obtén la fila de la lista
		return filaDatos[col]; // Obtiene el valor de la columna específica
	}

	// Cambia el valor que contiene una determinada casilla de la tabla
	public void setValueAt(Object valor, int fila, int col) {
		String[] filaDatos = listaDatos.get(fila); // Obtén la fila de la lista
		filaDatos[col] = (String) valor; // Actualiza el valor en la fila
		// Indica que se ha cambiado
		fireTableDataChanged();
	}

	// Indica si la casilla identificada por fila y columna es editable
	public boolean isCellEditable(int fila, int col) {
		return true;
	}

	// Sobreescribe el método getColumnName para devolver nombres personalizados
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public List<String[]> getListaDatos() {
		return listaDatos;
	}

	public void excel(int i, String document) throws FileNotFoundException, IOException {
		// Obtener la fecha y hora actual
		LocalDateTime ahora = LocalDateTime.now();

		// Formatear la fecha y hora como una cadena para usar en el nombre del archivo
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mmss");
		String marcaDeTiempo = ahora.format(formatter);

		// Construir el nombre del archivo con la fecha y hora actual
		String nombreArchivo = "C:/Users/Jose Manuel/OneDrive/Desktop/reporte_" + document + marcaDeTiempo + ".xlsx";

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("MiHojaDeCalculo");
		ModeloDatos modelo = new ModeloDatos(i);
		// Ejemplo de datos de una tabla (puedes reemplazarlo con tus propios datos)
		List<String[]> data = modelo.getListaDatos();

		int rowNum = 0;
		for (String[] rowData : data) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (String cellData : rowData) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(cellData);
			}
		}
		try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo)) {

			workbook.write(outputStream);
//            System.out.println("Archivo Excel generado correctamente: " + nombreArchivo);
		}
		workbook.close();
	}

}