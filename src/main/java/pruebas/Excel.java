package pruebas;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	public static void main(String[] args) throws FileNotFoundException, IOException {
        // Obtener la fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();

        // Formatear la fecha y hora como una cadena para usar en el nombre del archivo
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String marcaDeTiempo = ahora.format(formatter);

        // Construir el nombre del archivo con la fecha y hora actual
        String nombreArchivo = "C:/Users/Jose Manuel/OneDrive/Desktop/reporte_" + marcaDeTiempo + ".xlsx";

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("MiHojaDeCalculo");
		int i = 2;
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
            System.out.println("Archivo Excel generado correctamente: " + nombreArchivo);
		}
		workbook.close();
	}
}
