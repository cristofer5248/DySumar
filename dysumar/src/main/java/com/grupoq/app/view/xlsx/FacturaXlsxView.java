package com.grupoq.app.view.xlsx;

import java.text.DecimalFormat;
//import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import com.grupoq.app.models.entity.CarritoItems;
import com.grupoq.app.models.entity.Facturacion;

@Component("/facturas/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {
	public static DecimalFormat df = new DecimalFormat("0.00");

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		df.setRoundingMode(RoundingMode.DOWN);
		response.setHeader("Content-Disposition", "attachment; filename=\"factura.xlsx\"");
		Facturacion factura = (Facturacion) model.get("facturaciones");
		boolean agente = (factura.getCliente().getCliente().getAgente()) ? true : false;
		boolean creditoFiscal = (factura.getTipoFactura().getId() == Long.parseLong("1")) ? true : false;
		System.out.print("\n Credito fiscal" + creditoFiscal + "\n");
		System.out.print("\n Agente" + agente + "\n");
		Numeros_Letras numeroALetras = new Numeros_Letras();
		Sheet sheet = workbook.createSheet();
		//zoom and view
		
		sheet.setAutobreaks(false);
		sheet.setZoom(110);
		sheet.setFitToPage(true);

		sheet.setMargin(Sheet.TopMargin, 0.354331);
		sheet.setMargin(Sheet.BottomMargin, 0.590551);
		sheet.setMargin(Sheet.HeaderMargin, 0.00);
		sheet.setMargin(Sheet.FooterMargin, 0.00);
//		sheet.setMargin(Sheet.RightMargin, 0.0787402);
		sheet.setMargin(Sheet.RightMargin, 0.0000);
		sheet.setMargin(Sheet.LeftMargin, 0.629921);
		
		
		
		
		

		CellStyle theaderstyle = workbook.createCellStyle();
		theaderstyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderstyle.setBorderRight(BorderStyle.MEDIUM);
		theaderstyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderstyle.setBorderTop(BorderStyle.MEDIUM);
		theaderstyle.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
		theaderstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// lo gris merged azul claro era
		CellStyle celdasStyleMerged = workbook.createCellStyle();
		celdasStyleMerged.setFillForegroundColor(IndexedColors.WHITE.index);
		celdasStyleMerged.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		celdasStyleMerged.setAlignment(HorizontalAlignment.CENTER);

		// LAS QUET IENEN TEXTO IMPORTANTE
		CellStyle celltext = workbook.createCellStyle();
		celltext.setAlignment(HorizontalAlignment.CENTER);
		
		CellStyle celltextMoney = workbook.createCellStyle();
		celltextMoney.setAlignment(HorizontalAlignment.CENTER);
//		DataFormat dataFormat = workbook.createDataFormat();
		celltextMoney.setDataFormat((short)8);

		CellStyle ennegritacell = workbook.createCellStyle();
		ennegritacell.setAlignment(HorizontalAlignment.CENTER);
		
		CellStyle ennegritacelltotal = workbook.createCellStyle();
		ennegritacelltotal.setAlignment(HorizontalAlignment.LEFT);
		ennegritacelltotal.setVerticalAlignment(VerticalAlignment.TOP);

		CellStyle normalCenter = workbook.createCellStyle();
		normalCenter.setAlignment(HorizontalAlignment.CENTER);

		// fechas
		CellStyle celltextLeft = workbook.createCellStyle();
		celltextLeft.setAlignment(HorizontalAlignment.LEFT);

		CellStyle celltextRight = workbook.createCellStyle();
		celltextRight.setAlignment(HorizontalAlignment.RIGHT);

		// primera row
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 2));
		if (creditoFiscal) {
			sheet.addMergedRegion(new CellRangeAddress(7, 10, 1, 4));
			sheet.addMergedRegion(new CellRangeAddress(7, 7, 5, 6));
			sheet.addMergedRegion(new CellRangeAddress(8, 8, 5, 6));
			sheet.addMergedRegion(new CellRangeAddress(9, 9, 5, 6));
			sheet.addMergedRegion(new CellRangeAddress(10, 10, 5, 6));
		} else {
			sheet.addMergedRegion(new CellRangeAddress(7, 10, 1, 6));
		}
		sheet.addMergedRegion(new CellRangeAddress(4, 10, 0, 0));
		// C7 a G7
		if (creditoFiscal) {
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 5, 6));
		} else {
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 6));
		}
		// B6 a G6
		if (creditoFiscal) {
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 4));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 5, 6));
		} else {
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 6));
		}
		// B5 a c5
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 2));
		// D5 a E5
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 4));
		// F5 a F5
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 5, 6));
		// F4 A G4
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 6));
		// b12 a c12
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 1, 2));
		// b25 a c25
		sheet.addMergedRegion(new CellRangeAddress(24, 24, 1, 2));
		// d25 a f25
		sheet.addMergedRegion(new CellRangeAddress(24, 24, 3, 5));
		// a25 a a30
		sheet.addMergedRegion(new CellRangeAddress(24, 29, 0, 0));
		// b26:30 a f26:f30
		sheet.addMergedRegion(new CellRangeAddress(25, 29, 1, 5));

		// primero damos altura

		Row rowvacios_abajo26 = sheet.createRow(25);
		Row rowvacios_abajo27 = sheet.createRow(26);
		Row rowvacios_abajo28 = sheet.createRow(27);
		Row rowvacios_abajo29 = sheet.createRow(28);
		Row rowvacios_abajo30 = sheet.createRow(29);

		// coloreamos

		rowvacios_abajo27.createCell(0);
		rowvacios_abajo27.getCell(0).setCellStyle(celdasStyleMerged);
		rowvacios_abajo28.createCell(0);
		rowvacios_abajo28.getCell(0).setCellStyle(celdasStyleMerged);
		rowvacios_abajo29.createCell(0);
		rowvacios_abajo29.getCell(0).setCellStyle(celdasStyleMerged);
		rowvacios_abajo30.createCell(0);
		rowvacios_abajo30.getCell(0).setCellStyle(celdasStyleMerged);
		rowvacios_abajo26.createCell(1);
		rowvacios_abajo26.createCell(3);
		rowvacios_abajo26.createCell(4);
		rowvacios_abajo26.getCell(1).setCellStyle(celdasStyleMerged);

		rowvacios_abajo26.setHeightInPoints(20);
		rowvacios_abajo27.setHeightInPoints(22);
		rowvacios_abajo28.setHeightInPoints(18);
		rowvacios_abajo29.setHeightInPoints(22);
		rowvacios_abajo30.setHeightInPoints(20);
		rowvacios_abajo30.setHeightInPoints(22);

		sheet.setColumnWidth(0, 10 * 256);
		sheet.setColumnWidth(1, 5 * 256);
//		recortamos el tamaño si es credito fiscal
		int tamanoCWith = (creditoFiscal) ? 43 : 46;
		sheet.setColumnWidth(2, tamanoCWith * 256);
		sheet.setColumnWidth(3, 15 * 256);
		sheet.setColumnWidth(4, 7 * 256);		
		sheet.setColumnWidth(5, 5 * 256);
		sheet.setColumnWidth(6, 10 * 256);

		Font font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 8);
		font1.setFontName("Roman 17cpi");
		celltext.setFont(font1);
		celltextMoney.setFont(font1);
		normalCenter.setFont(font1);

		Font font1left = workbook.createFont();
		font1left.setFontHeightInPoints((short) 8);
		font1left.setFontName("Roman 15cpi");
		celltextLeft.setFont(font1left);

		Font ennegrita = workbook.createFont();
		ennegrita.setFontHeightInPoints((short) 8);
		ennegrita.setFontName("Roman 17cpi");
		ennegrita.setBold(true);
		ennegritacell.setFont(ennegrita);
		ennegritacelltotal.setFont(ennegrita);

		Font celltextRightF = workbook.createFont();
		celltextRightF.setFontHeightInPoints((short) 8);
		celltextRightF.setFontName("Roman 17cpi");
		celltextRightF.setBold(true);
		celltextRight.setFont(ennegrita);

		CellStyle tbodystyle = workbook.createCellStyle();
		tbodystyle.setBorderBottom(BorderStyle.MEDIUM);
		tbodystyle.setBorderRight(BorderStyle.MEDIUM);
		tbodystyle.setBorderLeft(BorderStyle.MEDIUM);
		tbodystyle.setBorderTop(BorderStyle.MEDIUM); // FIN DE LOS ESTILOS

		// para la primera row A1
		Row row = sheet.createRow(0);
		row.setHeightInPoints(82);
		row.createCell(0);
		row.createCell(6).setCellValue(factura.getCodigofactura());
		row.getCell(6).setCellStyle(celltextRight);
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
		row4.getCell(1).setCellStyle(normalCenter);
		row4.getCell(5).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(factura.getFecha()).toString());

		row4.getCell(1).setCellStyle(celltext);
		row4.getCell(5).setCellStyle(celltextLeft);

		// para la 5
		Row row5 = sheet.createRow(4);
		row5.setHeightInPoints(25);
		row5.createCell(4);
		row5.createCell(0);
		row5.getCell(0).setCellStyle(celdasStyleMerged);
		row5.createCell(5);
		row5.getCell(5).setCellStyle(celltext);
		row5.getCell(5).setCellValue(factura.getCliente().getCliente().getDui());

		// para la 6
		Row row6 = sheet.createRow(5);
		row6.setHeightInPoints(19);
		row6.createCell(1);
		row6.getCell(1).setCellValue(factura.getCliente().getDirecciones().getNombre());
		row6.getCell(1).setCellStyle(celltextLeft);

		// para la 7
		Row row7 = sheet.createRow(6);
//		row7.setHeightInPoints(19);
		row7.setHeightInPoints(28);
		row7.createCell(1);
		row7.createCell(2);

		row7.getCell(1).setCellStyle(celdasStyleMerged);
		row7.getCell(2).setCellStyle(celltextLeft);
		row7.getCell(2).setCellValue(factura.getCondicionesDPago().getNombre());

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

		// para el 12 en adelante:
		int itemRows = 11;
		Row rowItems = sheet.createRow(11);

		Cell cellItems = rowItems.createCell(0);
		if (creditoFiscal) {
			row6.createCell(5).setCellStyle(celltext);
			row6.getCell(5).setCellValue(factura.getCliente().getCliente().getDui());
			row7.createCell(5).setCellStyle(celltext);
			row7.getCell(5).setCellValue(factura.getCliente().getCliente().getGiro().getDetalles());

			row8.createCell(5).setCellStyle(celltext);
			row8.getCell(5).setCellValue(factura.getFormadepago().getNombre());
			row9.createCell(5).setCellStyle(celltext);
			row9.getCell(5).setCellValue(factura.getCondicionesDPago().getNombre());

		}
		for (CarritoItems carrito : factura.getCotizacion().getCarrito()) {
			Row fila = sheet.createRow(itemRows++);
			fila.setHeightInPoints(28);
			cellItems = fila.createCell(0);
			cellItems.setCellStyle(celltext);
//			double preciofinalP = (creditoFiscal)?carrito.getPrecio()/1.13:carrito.getPrecio(); 
			cellItems.setCellValue(carrito.getCantidad());

			// codigo del producto y nombre unidos por alguna razon
			cellItems = fila.createCell(1);
			cellItems.setCellStyle(celltextLeft);
			cellItems.setCellValue(carrito.getProductos().getMarca().getNombrem() + " "
					+ carrito.getProductos().getNombrep() + " " + carrito.getProductos().getPresentacion().getUnidad());

			// precio
			cellItems = fila.createCell(3);
			cellItems.setCellStyle(celltext);
//			"$  " + String.format("%.2f", (carrito.getPrecio() / ((100 - carrito.getMargen()) / 100)))
			double precioUnitario_temp = (creditoFiscal) ? carrito.getPrecio() : carrito.getPrecio() * 1.13;
			cellItems.setCellStyle(celltextMoney);
//			cellItems.setCellValue(String.format("%.2f", precioUnitario_temp));
			cellItems.setCellValue(precioUnitario_temp);
//			cellItems.setCellValue("$  " + String.format("%.2f", precioUnitario_temp));
			

			// espaciado en color son adelante
			cellItems = fila.createCell(4);
			cellItems.setCellStyle(celdasStyleMerged);
			cellItems = fila.createCell(5);
			cellItems.setCellStyle(celdasStyleMerged);

			// total precio x cantidad
			cellItems = fila.createCell(6);
			cellItems.setCellStyle(celltextMoney);
			double precioUnitarioXcantidad = precioUnitario_temp * carrito.getCantidad();
			cellItems.setCellValue(precioUnitarioXcantidad);

		}
		System.out.print("donde estara el final es: " + itemRows);
		// rellenamos con vacio lo que resta mas altura

		for (int i_filler = itemRows; itemRows < 24; itemRows++) {
			Row fila = sheet.createRow(i_filler++);
			int altoprecios = (creditoFiscal) ? 25 : 28;
			fila.setHeightInPoints(altoprecios);
			fila.createCell(4);
			fila.createCell(5);
			fila.getCell(4).setCellStyle(celdasStyleMerged);
			fila.getCell(5).setCellStyle(celdasStyleMerged);

			cellItems = fila.createCell(0);
		}

		Row rowFinal = sheet.createRow(24);
		rowFinal.setHeightInPoints(50);
		rowFinal.createCell(0);
		rowFinal.createCell(3);
		rowFinal.getCell(0).setCellStyle(celdasStyleMerged);
		rowFinal.getCell(3).setCellStyle(celdasStyleMerged);
		rowFinal.createCell(1);
		double sumasPrecios = (creditoFiscal) ? factura.getTotaRegistrado() : factura.getTotaRegistrado() * 1.13;
		rowFinal.getCell(1).setCellValue("$ " + numeroALetras.Convertir(df.format(sumasPrecios), true));
		rowFinal.getCell(1).setCellStyle(ennegritacell);
		rowFinal.createCell(6);
		rowFinal.getCell(6).setCellStyle(celltext);
		rowFinal.getCell(6).setCellValue("$" + df.format(sumasPrecios));
		// retenindo 0 para restar si no es agente
		double retenido = 0;
		double ivado = factura.getTotaRegistrado() + (factura.getTotaRegistrado() * 0.13);
		if (factura.getTotaRegistrado() > 113 && agente) {
			retenido = factura.getTotaRegistrado() * 0.01;
		}

		// mostrar iva ahi
		if (creditoFiscal) {
			rowvacios_abajo26.createCell(6);
			rowvacios_abajo26.getCell(6).setCellValue("$ " + df.format(factura.getTotaRegistrado() * 0.13));
			rowvacios_abajo26.getCell(6).setCellStyle(celltext);
			rowvacios_abajo27.createCell(6);
			rowvacios_abajo27.getCell(6).setCellStyle(celltext);
			// espacio para poner credito fiscal solo datos que solo el vera

			// fin

		} else {
			rowvacios_abajo27.createCell(6);
			rowvacios_abajo27.getCell(6).setCellStyle(celltext);
			rowvacios_abajo27.getCell(6).setCellValue("$ " + df.format(ivado));
		}

		// retencion datos set
		String retenido_string = (retenido == 0) ? "" : "$ " + df.format(retenido);
		rowvacios_abajo28.createCell(6);
		rowvacios_abajo28.getCell(6).setCellStyle(celltext);
		rowvacios_abajo28.getCell(6).setCellValue(retenido_string);
		rowvacios_abajo27.getCell(6).setCellValue("$ " + df.format(ivado));
		// termina la retencion

		// ponemos el credito fiscal o vacio

		Row rowFinal_total = sheet.createRow(29);
		rowFinal_total.createCell(6);
		rowFinal_total.getCell(6).setCellValue("$  " + df.format(ivado - retenido));
		rowFinal_total.getCell(6).setCellStyle(ennegritacelltotal);
		rowFinal_total.setHeightInPoints(23);
		
		rowFinal.getCell(1).setCellValue("$ " + numeroALetras.Convertir(df.format(ivado - retenido), true));
		// hacer el cambio en esto porque lo tomamos del final

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
