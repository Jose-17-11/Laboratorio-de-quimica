package pruebas;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

// El Modelo de la Tabla es el que controla todos los
// datos que se colocan en ella
class ModeloDatos extends AbstractTableModel {
	String[] columnNames = { "Fecha", "Hora", "Maestro", "Grupo", "Hora" };

	Object datos[][] = { { "uno", "dos", "tres", "cuatro", "eewd" }, { "cinco", "seis", "siete", "ocho", "eewd" }, };

	// Esta clase imprime los datos en la consola cada vez
	// que se produce un cambio en cualquiera de las
	// casillas de la tabla
	class TablaListener implements TableModelListener {
		public void tableChanged(TableModelEvent evt) {
			for (int i = 0; i < datos.length; i++) {
				for (int j = 0; j < datos[0].length; j++)
					System.out.print(datos[i][j] + " ");
				System.out.println();
			}
		}
	}

	// Constructor
	ModeloDatos() {
		addTableModelListener(new TablaListener());
	}

	public int getColumnCount() {
		return columnNames.length; // Devuelve la cantidad de columnas
	}

	// Devuelve el número de filas de la tabla
	public int getRowCount() {
		return (datos.length);
	}

	// Devuelve el valor de una determinada casilla de la tabla
	// identificada mediante fila y columna
	public Object getValueAt(int fila, int col) {
		return (datos[fila][col]);
	}

	// Cambia el valor que contiene una determinada casilla de
	// la tabla
	public void setValueAt(Object valor, int fila, int col) {
		datos[fila][col] = valor;
		// Indica que se ha cambiado
		fireTableDataChanged();
	}

	// Indica si la casilla identificada por fila y columna es
	// editable
	public boolean isCellEditable(int fila, int col) {
		return (true);
	}

//Sobreescribe el método getColumnName para devolver nombres personalizados
	public String getColumnName(int column) {
		return columnNames[column];
	}
}