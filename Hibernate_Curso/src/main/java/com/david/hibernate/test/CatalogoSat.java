package com.david.hibernate.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CatalogoSat {

	public static void main(String[] args) {

		System.out.println("Contenido");
		//leerCatalogo();
		leerCatalogo2();

	}

	private static void leerCatalogo() {
		String nombreArchivo = "catCFDI_V_4_09112022.xls";
		String rutaArchivo = "C:\\" + nombreArchivo;

		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			Workbook wb = WorkbookFactory.create(file);
			// obtener la hoja que se va leer 11(pais)
			HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(11);
			// obtener todas las filas de la hoja excel

			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			rowIterator.next();
			rowIterator.next();
			Row row;

			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				// se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				// se recorre cada celda
				while (cellIterator.hasNext()) {
					// se obtiene la celda en específico y se la imprime
					cell = cellIterator.next();
					int col = cell.getColumnIndex();
					if (col < 2) {
						System.out.print(cell.getStringCellValue() + " | ");
					}

				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Error:" + e);
			e.getMessage();
		}
	}

	private static void leerCatalogo2() {
		String nombreArchivo = "catCFDI_V_4_09112022.xls";
		String rutaArchivo = "C:\\" + nombreArchivo;
		int numFilas;

		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			Workbook wb = WorkbookFactory.create(file);
			// obtener la hoja que se va leer 11(pais)
			HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(11);

			// obtener todas las filas de la hoja excel
			numFilas = sheet.getLastRowNum();
			//comienza desde la fila del titulo 
			for (int i = 4; i < numFilas; i++) {

				Row fila = sheet.getRow(i);
				//solo las 2 columnas que me interesan
				System.out.print(fila.getCell(0).getStringCellValue() + " | ");
				System.out.print(fila.getCell(1).getStringCellValue());
				System.out.println();

			}

		} catch (Exception e) {
			System.out.println("Error:" + e);
			e.getMessage();
		}
	}

}
