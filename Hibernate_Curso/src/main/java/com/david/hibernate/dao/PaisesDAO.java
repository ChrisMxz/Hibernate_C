package com.david.hibernate.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.david.hibernate.entidades.Pais;


public class PaisesDAO {
	private List<Pais> paises;

	public PaisesDAO() {
		System.out.println("Carga datos");
		paises = new ArrayList<>();
	}

	public void listar() {

		String nombreArchivo = "catCFDI_V_4_09112022.xls";
		String rutaArchivo = "C:\\" + nombreArchivo;
		int numFilas;
		Pais p;

		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			Workbook wb = WorkbookFactory.create(file);
			// obtener la hoja que se va leer 11(pais)
			HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(11);

			// obtener todas las filas de la hoja excel
			numFilas = sheet.getLastRowNum();

			// comienza desde la fila del titulo
			for (int i = 5; i < numFilas; i++) {
				Row fila = sheet.getRow(i);
				// solo las 2 columnas que me interesan
				p = new Pais(fila.getCell(0).getStringCellValue(), fila.getCell(1).getStringCellValue());
				paises.add(p);
			}

		} catch (Exception e) {
			System.out.println("Error:" + e);
			e.getMessage();
		}

	}

	public List<Pais> getPaises() {
		return paises;
	}

}
