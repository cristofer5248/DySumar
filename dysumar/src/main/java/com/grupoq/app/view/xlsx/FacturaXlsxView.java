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
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.grupoq.app.models.entity.CarritoItems;
import com.grupoq.app.models.entity.Facturacion;

@Component("/facturas/ver")
public class FacturaXlsxView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Detalles_de_factura.xlsx\"");
		Facturacion factura = (Facturacion) model.get("facturaciones");
		Sheet sheet = workbook.createSheet("Detalles");
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0,256*33);
		sheet.setColumnWidth(1,256*33);
		

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

		cell.setCellValue("Factura con ID "+factura.getId());
		cell.setCellStyle(theaderstyle);
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(tbodystyle);
		cell.setCellValue("ID: " +factura.getId());
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("Nombres: " +factura.getCliente().getCliente().getNombre());
		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Apellidos: " +factura.getCliente().getCliente().getApellido());
		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("NIT:" +factura.getCliente().getCliente().getDui());
		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(5);
		cell = row.createCell(0);
		cell.setCellValue("Giro: " +factura.getCliente().getCliente().getGiro().getDetalles());
		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(6);
		cell = row.createCell(0);
		cell.setCellValue("Direccion: "+factura.getCliente().getDirecciones().getNombre());
		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(7);
		cell = row.createCell(0);
		cell.setCellValue("A cuenta de: "+factura.getaCuentade());
		cell.setCellStyle(tbodystyle);

		row = sheet.createRow(8);
		cell = row.createCell(0);
		cell.setCellValue("Factura: " +factura.getTipoFactura().getNombre());
		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(9);
		cell = row.createCell(0);
		cell.setCellValue("Condicion:" +factura.getCondicionesDPago().getNombre());
		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(10);
		cell = row.createCell(0);
		cell.setCellValue("Pago: " +factura.getFormadepago().getNombre());
		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(11);
		cell = row.createCell(0);
		cell.setCellValue("Detalle: "+factura.getDetalles());
		cell.setCellStyle(tbodystyle);
		
		row = sheet.createRow(12);
		cell = row.createCell(0);
		cell.setCellValue("Fecha: " +factura.getCotizacion().getFecha());
		cell.setCellStyle(tbodystyle);
		
		/*
		 * PARA PONERLE MAS sheet.createRow(4).createCell(0).setCellValue("");
		 * sheet.createRow(5).createCell(0).setCellValue("");
		 * sheet.createRow(6).createCell(0).setCellValue("");
		 * sheet.createRow(7).createCell(0).setCellValue("");
		 */

		Row header = sheet.createRow(14);		
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Producto");
		sheet.autoSizeColumn(0);
		header.createCell(2).setCellValue("Marca");
		header.createCell(3).setCellValue("Proveedor");
		header.createCell(4).setCellValue("Cantidad");

		// poniendo los estilos aqua y blanco y bordes
		cell.setCellStyle(tbodystyle);

		header.getCell(0).setCellStyle(theaderstyle);
		header.getCell(1).setCellStyle(theaderstyle);
		header.getCell(2).setCellStyle(theaderstyle);
		header.getCell(3).setCellStyle(theaderstyle);
		header.getCell(4).setCellStyle(theaderstyle);

		int rownum = 15;
		for (CarritoItems carrito : factura.getCotizacion().getCarrito()) {
			Row fila = sheet.createRow(rownum++);
			cell = fila.createCell(0);
			cell.setCellValue(carrito.getId());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(1);
			cell.setCellValue(carrito.getProductos().getNombrep());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(2);
			cell.setCellValue(carrito.getProductos().getMarca().getNombrem());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(3);
			cell.setCellValue(carrito.getProductos().getProveedor().getNombre());
			cell.setCellStyle(tbodystyle);
			
			cell = fila.createCell(4);
			cell.setCellValue(carrito.getCantidad());
			cell.setCellStyle(tbodystyle);
		}

		Row filatotal = sheet.createRow(rownum);
		filatotal.createCell(3).setCellValue("Gran total:  ");
		filatotal.createCell(4).setCellValue("Aun no calculado, wait :)");
	}

}
