package com.grupoq.app.view.xlsx;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import com.grupoq.app.models.entity.CarritoItems;
import com.grupoq.app.models.entity.Facturacion;

@Component("/facturas/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"factura.xlsx\"");
		Facturacion factura = (Facturacion) model.get("facturaciones");	
		Numeros_Letras numeroALetras = new Numeros_Letras();
		Sheet sheet = workbook.createSheet();
		// primera row
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 2));		
		sheet.addMergedRegion(new CellRangeAddress(7, 10, 1, 6));
		sheet.addMergedRegion(new CellRangeAddress(4, 10, 0, 0));
		//C7 a G7
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 6));
		//B6 a G6
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 6));
		//B5 a c5
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 2));
		//D5 a E5
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 4));
		//F5 a F5
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 5, 6));
		//F4 A G4
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 6));
		//b12 a c12
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 1, 2));
		
		sheet.setColumnWidth(0, 10 * 256);
		sheet.setColumnWidth(1, 5 * 256);
		sheet.setColumnWidth(2, 48 * 256);
		sheet.setColumnWidth(3, 8 * 256);
		sheet.setColumnWidth(4, 7 * 256);
		sheet.setColumnWidth(5, 5 * 256);
		sheet.setColumnWidth(6, 10 * 256);

		CellStyle theaderstyle = workbook.createCellStyle();
		theaderstyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderstyle.setBorderRight(BorderStyle.MEDIUM);
		theaderstyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderstyle.setBorderTop(BorderStyle.MEDIUM);
		theaderstyle.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
		theaderstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		//lo gris merged azul claro era
		CellStyle celdasStyleMerged = workbook.createCellStyle();		
		celdasStyleMerged.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		celdasStyleMerged.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		celdasStyleMerged.setAlignment(HorizontalAlignment.CENTER);
		
		//LAS QUET IENEN TEXTO IMPORTANTE
		CellStyle celltext = workbook.createCellStyle();
		celltext.setAlignment(HorizontalAlignment.CENTER);
		
		CellStyle ennegritacell = workbook.createCellStyle();
		ennegritacell.setAlignment(HorizontalAlignment.CENTER);
		
		
		//fechas
		CellStyle celltextLeft = workbook.createCellStyle();
		celltextLeft.setAlignment(HorizontalAlignment.LEFT);
		
		Font font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 8);
		font1.setFontName("Roman 17cpi");
		celltext.setFont(font1);
		
		Font font1left = workbook.createFont();
		font1left.setFontHeightInPoints((short) 8);
		font1left.setFontName("Roman 17cpi");
		celltextLeft.setFont(font1left);
		
		Font ennegrita = workbook.createFont();
		ennegrita.setFontHeightInPoints((short)8);
		ennegrita.setFontName("Roman 17cpi");
		ennegrita.setBold(true);
		ennegritacell.setFont(ennegrita);
		

		CellStyle tbodystyle = workbook.createCellStyle();
		tbodystyle.setBorderBottom(BorderStyle.MEDIUM);
		tbodystyle.setBorderRight(BorderStyle.MEDIUM);
		tbodystyle.setBorderLeft(BorderStyle.MEDIUM);
		tbodystyle.setBorderTop(BorderStyle.MEDIUM); // FIN DE LOS ESTILOS

		// para la primera row A1
		Row row = sheet.createRow(0);
		row.setHeightInPoints(60);	
		row.createCell(0);
		row.createCell(6).setCellValue(factura.getCodigofactura());
		row.getCell(0).setCellStyle(celdasStyleMerged);
		

		// para la 2 y 3
		Row row2 = sheet.createRow(1);
		row2.setHeightInPoints(8);
		row2.createCell(0);		
		row2.getCell(0).setCellStyle(celdasStyleMerged);		
		Row row3 = sheet.createRow(2);
		row3.setHeightInPoints(8);

		// para la 4
		Row row4 = sheet.createRow(3);
		
		row4.createCell(0);
		row4.createCell(3);
		row4.createCell(4);
		row4.createCell(1);
		row4.createCell(5);
		row4.getCell(0).setCellStyle(celdasStyleMerged);
		row4.getCell(3).setCellStyle(celdasStyleMerged);
		row4.getCell(4).setCellStyle(celdasStyleMerged);
		row4.setHeightInPoints(37);
		
		row4.getCell(1).setCellValue(factura.getCliente().getCliente().getNombre());
		System.out.print("La fecha: "+factura.getFecha().toString());
		row4.getCell(5).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(factura.getFecha()).toString());		
		
		row4.getCell(1).setCellStyle(celltext);
		row4.getCell(5).setCellStyle(celltextLeft);

		// para la 5
		Row row5 = sheet.createRow(4);
		row5.setHeightInPoints(25);
		row5.createCell(4);
		row5.createCell(0);
		row5.getCell(0).setCellStyle(celdasStyleMerged);

		// para la 6
		Row row6 = sheet.createRow(5);
		row6.setHeightInPoints(19);
		row6.createCell(1);
		row6.getCell(1).setCellValue(factura.getCliente().getDirecciones().getNombre());
		row6.getCell(1).setCellStyle(celltextLeft);

		// para la 7
		Row row7 = sheet.createRow(6);
		row7.setHeightInPoints(19);
		row7.createCell(1);
		row7.createCell(2);
		
		row7.getCell(1).setCellStyle(celdasStyleMerged);
		row7.getCell(2).setCellStyle(celltextLeft);
		row7.getCell(2).setCellValue(factura.getTipoFactura().getNombre());

		// para la 8 9, 10 y 11
		Row row8 = sheet.createRow(7);
		Row row9 = sheet.createRow(8);
		Row row10 = sheet.createRow(9);
		Row row11 = sheet.createRow(10);
		
		row8.setHeightInPoints(12);
		row9.setHeightInPoints(6);
		row10.setHeightInPoints(19);
		row11.setHeightInPoints(3);
		row8.createCell(0);
		row8.createCell(1);
		row8.getCell(0).setCellStyle(celdasStyleMerged);
		row8.getCell(1).setCellStyle(celdasStyleMerged);
		
		//para el 12 en adelante:
		int itemRows=11;
		Row rowItems = sheet.createRow(11);
		
		Cell cellItems= rowItems.createCell(0);
		for(CarritoItems carrito: factura.getCotizacion().getCarrito()) {
			Row fila = sheet.createRow(itemRows++);
			fila.setHeightInPoints(27);
			cellItems=fila.createCell(0);
			cellItems.setCellStyle(celltext);
			cellItems.setCellValue(carrito.getCantidad());
			
			//codigo del producto y nombre unidos por alguna razon
			cellItems=fila.createCell(1);
			cellItems.setCellStyle(celltextLeft);
			cellItems.setCellValue("CÓDIGO: "+carrito.getProductos().getCodigo()+" " +carrito.getProductos().getNombrep());
			
			//precio
			cellItems=fila.createCell(3);
			cellItems.setCellStyle(celltext);
			cellItems.setCellValue("$  "+String.format("%.2f",(carrito.getPrecio()/((100-carrito.getMargen())/100))));
			// espaciado en color son adelante
			cellItems=fila.createCell(4);
			cellItems.setCellStyle(celdasStyleMerged);
			cellItems=fila.createCell(5);
			cellItems.setCellStyle(celdasStyleMerged);
			
			//total precio x cantidad
			cellItems=fila.createCell(6);
			cellItems.setCellStyle(celltext);
			cellItems.setCellValue("$  "+String.format("%.2f",(carrito.getPrecio()/((100-carrito.getMargen())/100))*carrito.getCantidad()));
			
		}
		System.out.print("donde estara el final es: "+itemRows+1);
		Row rowFinal = sheet.createRow(itemRows+1);
		rowFinal.setHeightInPoints(27);
		rowFinal.createCell(2);		
		rowFinal.getCell(2).setCellValue(numeroALetras.Convertir(String.format("%.2f",factura.getTotaRegistrado()), true));
		rowFinal.getCell(2).setCellStyle(ennegritacell);
		rowFinal.createCell(6);
		rowFinal.getCell(6).setCellValue("$  "+String.format("%.2f",factura.getTotaRegistrado()));
		rowFinal.getCell(6).setCellStyle(ennegritacell);		
		
//		Row row12aleft = sheet.createRow(11);
//		row12aleft.setHeightInPoints(27);

//		Sheet sheet = workbook.createSheet("Detalles"); sheet.autoSizeColumn(0);
//		Sheet sheet2 = workbook2.getSheetAt(0);
//		Cell cell2Update;
//		cell2Update = sheet2.getRow(0).getCell(6);
//		cell2Update.setCellValue(factura.getCodigofactura());
//		inputStream.close();
//		FileOutputStream outputStream = new FileOutputStream("papeleriavacio.xlsx");
//		workbook2.write(outputStream);
//		workbook2.close();
//		outputStream.close();
//		System.out.print("terminado...supongo");

	}
}
