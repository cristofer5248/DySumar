package com.grupoq.app.view.xlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUpdateExample1 {
	public static void main(String[] args) {
		String excelFilePath = "./papeleriavacio.xlsx";

		try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = WorkbookFactory.create(inputStream);

			Sheet sheet = workbook.getSheetAt(0);
			Cell cell2Update = sheet.getRow(0).getCell(6);
			cell2Update.setCellValue("499999");

		

//			int rowCount = sheet.getLastRowNum();

			/*
			 * for (Object[] aBook : bookData) { Row row = sheet.createRow(++rowCount);
			 * 
			 * int columnCount = 0;
			 * 
			 * Cell cell = row.createCell(columnCount); cell.setCellValue(rowCount);
			 * 
			 * for (Object field : aBook) { cell = row.createCell(++columnCount); if (field
			 * instanceof String) { cell.setCellValue((String) field); } else if (field
			 * instanceof Integer) { cell.setCellValue((Integer) field); } }
			 * 
			 * }
			 */

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream("papeleriavacio.xlsx");
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			System.out.print("terminado...supongo");

		} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
			ex.printStackTrace();
		}
	}
}
