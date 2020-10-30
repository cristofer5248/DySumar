package com.grupoq.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.grupoq.app.models.entity.Inventario;
import com.grupoq.app.models.entity.Producto;

@Component("/productos/ver")
public class InventarioXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Detalles_de_producto.xlsx\"");
		Producto producto = (Producto) model.get("producto");
		Sheet sheet = workbook.createSheet("Detalles");
		
		
		
		

		//estilos habran paso!
		CellStyle theaderstyle = workbook.createCellStyle();
		theaderstyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderstyle.setBorderRight(BorderStyle.MEDIUM);
		theaderstyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderstyle.setBorderTop(BorderStyle.MEDIUM);		
		theaderstyle.setFillForegroundColor(IndexedColors.DARK_BLUE.index);		
		theaderstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		Font font1 = workbook.createFont();
		font1.setFontHeightInPoints((short)12);
		font1.setColor(IndexedColors.WHITE.getIndex());
		font1.setBold(true);
		theaderstyle.setFont(font1);
		
		
		CellStyle tbodystyle = workbook.createCellStyle();
		tbodystyle.setBorderBottom(BorderStyle.MEDIUM);
		tbodystyle.setBorderRight(BorderStyle.MEDIUM);
		tbodystyle.setBorderLeft(BorderStyle.MEDIUM);
		tbodystyle.setBorderTop(BorderStyle.MEDIUM);
		//FIN DE LOS ESTILOS
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);

		cell.setCellValue("Datos del producto");
		cell.setCellStyle(theaderstyle);
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
//		cell.setCellStyle(tbodystyle);
		
		cell.setCellValue(producto.getCodigo());
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(producto.getNombrep());
//		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Prov: "+producto.getProveedor().getNombre());
//		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("Marca: "+producto.getMarca().getNombrem());
//		cell.setCellStyle(tbodystyle);

		row = sheet.createRow(5);
		cell = row.createCell(0);
		cell.setCellValue("Precio U: "+producto.getPrecio());
//		cell.setCellStyle(tbodystyle);

		row = sheet.createRow(6);
		cell = row.createCell(0);
		cell.setCellValue("Stock : "+producto.getStock());
//		cell.setCellStyle(tbodystyle);
		/*
		 * PARA PONERLE MAS sheet.createRow(4).createCell(0).setCellValue("");
		 * sheet.createRow(5).createCell(0).setCellValue("");
		 * sheet.createRow(6).createCell(0).setCellValue("");
		 * sheet.createRow(7).createCell(0).setCellValue("");
		 */


		
		Row header = sheet.createRow(8);		
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Fecha de ingreso");
		sheet.autoSizeColumn(0);
		header.createCell(3).setCellValue("Precio");
		header.createCell(2).setCellValue("Ingreso");

		// poniendo los estilos aqua y blanco y bordes
		cell.setCellStyle(tbodystyle);

		header.getCell(0).setCellStyle(theaderstyle);
		header.getCell(1).setCellStyle(theaderstyle);
		header.getCell(2).setCellStyle(theaderstyle);
		header.getCell(3).setCellStyle(theaderstyle);

		int rownum = 9;
		double total=0;
		for (Inventario inven : producto.getInventarios()) {
			Row fila = sheet.createRow(rownum++);
			cell = fila.createCell(0);
			cell.setCellValue(inven.getId());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(3);
			cell.setCellValue(producto.getPrecio());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(2);
			cell.setCellValue(inven.getStock());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(1);
			cell.setCellValue(inven.getFecha().toString());
			cell.setCellStyle(tbodystyle);
			
			total+= inven.getProducto().getPrecio()*inven.getStock();
		}

		Row filatotal = sheet.createRow(rownum);
		filatotal.createCell(2).setCellValue("Total:  ");
		filatotal.createCell(3).setCellValue(total);
		filatotal.createCell(0).setCellStyle(tbodystyle);
		filatotal.createCell(1).setCellStyle(tbodystyle);
		filatotal.getCell(2).setCellStyle(tbodystyle);
		filatotal.getCell(3).setCellStyle(tbodystyle);
		
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,3));
		sheet.addMergedRegion(new CellRangeAddress(2,2,0,3));
		sheet.addMergedRegion(new CellRangeAddress(3,3,0,3));
		sheet.addMergedRegion(new CellRangeAddress(4,4,0,3));
		sheet.addMergedRegion(new CellRangeAddress(5,5,0,3));
		sheet.addMergedRegion(new CellRangeAddress(6,6,0,3));
		
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0,256*7);
		sheet.setColumnWidth(1,256*18);
	}
	

}
