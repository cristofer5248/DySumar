package com.grupoq.app.view.xlsx;

import java.text.DecimalFormat;
//import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// accounting format
		String accountingformat = "_-$* #,##0.00_-;-$* #,##0.00_-;_-$* \"-\"??_-;_-@_-";

		Facturacion factura = (Facturacion) model.get("facturaciones");
		String nombrecliente = factura.getCliente().getCliente().getNombre().replaceAll("\\.", "-");
		String formatonname = (factura.getStatus() != 1) ? "attachment; filename=\"factura.xlsx\""
				: "attachment; filename=\"" + factura.getCodigofactura() + "-" + nombrecliente + ".xlsx\"";
		response.setHeader("Content-Disposition", formatonname);
		boolean agente = (factura.getCliente().getCliente().getAgente()) ? true : false;
		boolean creditoFiscal = (factura.getTipoFactura().getId() == Long.parseLong("1")) ? true : false;
		System.out.print("\n Credito fiscal" + creditoFiscal + "\n");
		System.out.print("\n Agente" + agente + "\n");
		Numeros_Letras numeroALetras = new Numeros_Letras();

		Sheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "FACTURA");

		// zoom and view

		sheet.setAutobreaks(false);
		sheet.setZoom(110);
		sheet.setFitToPage(true);

		if (creditoFiscal) {
			sheet.setMargin(Sheet.TopMargin, 0.866142);
			sheet.setMargin(Sheet.BottomMargin, 0.590551);
			sheet.setMargin(Sheet.HeaderMargin, 0.00);
			sheet.setMargin(Sheet.FooterMargin, 0.00);
			sheet.setMargin(Sheet.RightMargin, 0.19685);
			sheet.setMargin(Sheet.LeftMargin, 0.314961);
		} else {
			sheet.setMargin(Sheet.TopMargin, 0.354331);
			sheet.setMargin(Sheet.BottomMargin, 0.590551);
			sheet.setMargin(Sheet.HeaderMargin, 0.00);
			sheet.setMargin(Sheet.FooterMargin, 0.00);
//		sheet.setMargin(Sheet.RightMargin, 0.0787402);
			sheet.setMargin(Sheet.RightMargin, 0.0000);
			sheet.setMargin(Sheet.LeftMargin, 0.629921);
		}
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
		DataFormat dataFormat = workbook.createDataFormat();

		// LAS QUET IENEN TEXTO IMPORTANTE
		CellStyle celltext = workbook.createCellStyle();
		celltext.setAlignment(HorizontalAlignment.CENTER);

		CellStyle celltextMoney = workbook.createCellStyle();
		celltextMoney.setAlignment(HorizontalAlignment.CENTER);

		celltextMoney.setDataFormat(dataFormat.getFormat(accountingformat));

		CellStyle ennegritacell = workbook.createCellStyle();
		ennegritacell.setAlignment(HorizontalAlignment.CENTER);

		CellStyle ennegritacelltotal = workbook.createCellStyle();
		ennegritacelltotal.setAlignment(HorizontalAlignment.LEFT);
		ennegritacelltotal.setVerticalAlignment(VerticalAlignment.CENTER);
		ennegritacelltotal.setDataFormat(dataFormat.getFormat(accountingformat));

		CellStyle normalCenter = workbook.createCellStyle();
		normalCenter.setAlignment(HorizontalAlignment.CENTER);

		// fechas
		CellStyle celltextLeft = workbook.createCellStyle();
		celltextLeft.setAlignment(HorizontalAlignment.LEFT);

		CellStyle calibriproducs = workbook.createCellStyle();
		calibriproducs.setAlignment(HorizontalAlignment.LEFT);

		CellStyle celltextRight = workbook.createCellStyle();
		celltextRight.setAlignment(HorizontalAlignment.RIGHT);

		CellStyle calibricenteronce = workbook.createCellStyle();
		calibricenteronce.setAlignment(HorizontalAlignment.CENTER);
		calibricenteronce.setVerticalAlignment(VerticalAlignment.CENTER);
//		
		CellStyle calibridiezright = workbook.createCellStyle();
		calibridiezright.setAlignment(HorizontalAlignment.RIGHT);

		CellStyle calibridiezcenterright = workbook.createCellStyle();
		calibridiezcenterright.setAlignment(HorizontalAlignment.RIGHT);
		calibridiezcenterright.setVerticalAlignment(VerticalAlignment.CENTER);

		CellStyle calibritopleftonce = workbook.createCellStyle();
		calibritopleftonce.setAlignment(HorizontalAlignment.LEFT);
		calibritopleftonce.setVerticalAlignment(VerticalAlignment.TOP);

		CellStyle calibri10warptext = workbook.createCellStyle();
		calibri10warptext.setAlignment(HorizontalAlignment.RIGHT);
		calibri10warptext.setVerticalAlignment(VerticalAlignment.TOP);
		calibri10warptext.setWrapText(true);

		if (creditoFiscal) {
			sheet.addMergedRegion(new CellRangeAddress(0, 11, 0, 0));
			sheet.addMergedRegion(new CellRangeAddress(0, 29, 1, 1));
			sheet.addMergedRegion(new CellRangeAddress(30, 34, 0, 7));

			sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 5));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 5));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 5));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 2, 5));
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 5));
			sheet.addMergedRegion(new CellRangeAddress(5, 6, 7, 8)); // union de giro

			sheet.addMergedRegion(new CellRangeAddress(7, 7, 4, 5));

			sheet.addMergedRegion(new CellRangeAddress(9, 11, 2, 8));

			// productos
			sheet.addMergedRegion(new CellRangeAddress(12, 12, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(13, 13, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(14, 14, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(15, 15, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(16, 16, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(17, 17, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(18, 18, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(19, 19, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(20, 20, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(21, 21, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(22, 22, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(23, 23, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(24, 24, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(25, 25, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(26, 26, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(27, 27, 2, 4));

			sheet.addMergedRegion(new CellRangeAddress(28, 29, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(28, 29, 5, 7));

			/// aqui voy

			sheet.addMergedRegion(new CellRangeAddress(2, 2, 7, 8));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 7, 8));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 7, 8));
//			sheet.addMergedRegion(new CellRangeAddress(5, 5, 7, 8));
//			sheet.addMergedRegion(new CellRangeAddress(6, 6, 7, 8));
			sheet.addMergedRegion(new CellRangeAddress(7, 7, 7, 8));
			sheet.addMergedRegion(new CellRangeAddress(8, 8, 7, 8));

		} else {
			// primera row
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 6));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(7, 9, 1, 6));
			sheet.addMergedRegion(new CellRangeAddress(4, 9, 0, 0));
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 6));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 6));

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
			sheet.addMergedRegion(new CellRangeAddress(12, 12, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(13, 13, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(14, 14, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(15, 15, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(16, 16, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(17, 17, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(18, 18, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(19, 19, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(20, 20, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(21, 21, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(22, 22, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(23, 23, 1, 2));

			// b25 a c25
			sheet.addMergedRegion(new CellRangeAddress(24, 24, 1, 2));
			// d25 a f25
			sheet.addMergedRegion(new CellRangeAddress(24, 24, 3, 5));
			// a25 a a30
			sheet.addMergedRegion(new CellRangeAddress(24, 29, 0, 0));
			// b26:30 a f26:f30
			sheet.addMergedRegion(new CellRangeAddress(25, 29, 1, 5));

		}

		// primero damos altura

		Row rowvacios_abajo26 = sheet.createRow(25);
		Row rowvacios_abajo27 = sheet.createRow(26);
		Row rowvacios_abajo28 = sheet.createRow(27);
		Row rowvacios_abajo29 = sheet.createRow(28);
		Row rowvacios_abajo30 = sheet.createRow(29);

		Row rowvacios_abajo31 = sheet.createRow(30);
		Row rowvacios_abajo32 = sheet.createRow(31);
		Row rowvacios_abajo33 = sheet.createRow(32);
		Row rowvacios_abajo34 = sheet.createRow(33);
		Row rowvacios_abajo35 = sheet.createRow(34);

		// coloreamos

		if (creditoFiscal) {
			sheet.setColumnWidth(0, 5 * 256);
			sheet.setColumnWidth(1, 3 * 256);
			sheet.setColumnWidth(2, 19 * 256);
			sheet.setColumnWidth(3, 15 * 256);
			sheet.setColumnWidth(4, 21 * 256);
			sheet.setColumnWidth(5, 8 * 256);
			sheet.setColumnWidth(6, 7 * 256);
			sheet.setColumnWidth(7, 11 * 256);
			sheet.setColumnWidth(8, 10 * 256);
			rowvacios_abajo27.createCell(0);

			rowvacios_abajo28.createCell(0);

			rowvacios_abajo29.createCell(0);

			rowvacios_abajo30.createCell(0);

			rowvacios_abajo26.createCell(1);
			rowvacios_abajo26.createCell(3);
			rowvacios_abajo26.createCell(4);

			rowvacios_abajo26.setHeightInPoints(20);
			rowvacios_abajo27.setHeightInPoints(22);
			rowvacios_abajo28.setHeightInPoints(18);
			rowvacios_abajo29.setHeightInPoints(Float.parseFloat("22.5"));
			rowvacios_abajo30.setHeightInPoints(Float.parseFloat("11.25"));

			rowvacios_abajo31.setHeightInPoints(14);

			rowvacios_abajo32.setHeightInPoints(14);

			rowvacios_abajo33.setHeightInPoints(14);

			rowvacios_abajo34.setHeightInPoints(14);

			rowvacios_abajo35.setHeightInPoints(15);

		} else {

			sheet.setColumnWidth(0, 9 * 255);
			sheet.setColumnWidth(1, 7 * 255);
			sheet.setColumnWidth(2, 50 * 256);
			sheet.setColumnWidth(3, 9 * 256);
			sheet.setColumnWidth(4, 8 * 256);
			sheet.setColumnWidth(5, 6 * 256);
			sheet.setColumnWidth(6, 11 * 256);
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

			rowvacios_abajo26.setHeightInPoints(24);
			
			rowvacios_abajo27.setHeightInPoints(18);
			rowvacios_abajo28.setHeightInPoints(Float.parseFloat("20.25"));
			rowvacios_abajo29.setHeightInPoints(Float.parseFloat("22.25"));
			rowvacios_abajo30.setHeightInPoints(0);
//			rowvacios_abajo30.setHeightInPoints(22);
		}

		Font font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 8);
		font1.setFontName("Roman 17cpi");
		celltext.setFont(font1);
		celltextMoney.setFont(font1);
		normalCenter.setFont(font1);

		Font font1left = workbook.createFont();
		font1left.setFontHeightInPoints((short) 8);
		font1left.setFontName("Roman 17cpi");
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

		Font calibrinormal11 = workbook.createFont();
		calibrinormal11.setFontHeightInPoints((short) 11);
		calibrinormal11.setFontName("Calibri");
		calibricenteronce.setFont(calibrinormal11);

		Font calibrinormal10Right = workbook.createFont();
		calibrinormal10Right.setFontHeightInPoints((short) 10);
		calibrinormal10Right.setFontName("Calibri");
		calibridiezright.setFont(calibrinormal10Right);

		Font calibridiezcenterrightF = workbook.createFont();
		calibridiezcenterrightF.setFontHeightInPoints((short) 10);
		calibridiezcenterrightF.setFontName("Calibri");
		calibridiezcenterright.setFont(calibridiezcenterrightF);

		Font calibritopleftonceF = workbook.createFont();
		calibritopleftonceF.setFontHeightInPoints((short) 10);
		calibritopleftonceF.setFontName("Calibri");
		calibritopleftonce.setFont(calibritopleftonceF);

		Font calibri10warptextF = workbook.createFont();
		calibri10warptextF.setFontHeightInPoints((short) 10);
		calibri10warptextF.setFontName("Calibri");
		calibri10warptext.setFont(calibritopleftonceF);

		Font calibriproducsf = workbook.createFont();
		calibriproducsf.setFontHeightInPoints((short) 9);
		calibriproducsf.setFontName("Calibri");
		calibriproducs.setFont(calibriproducsf);

		CellStyle tbodystyle = workbook.createCellStyle();
		tbodystyle.setBorderBottom(BorderStyle.MEDIUM);
		tbodystyle.setBorderRight(BorderStyle.MEDIUM);
		tbodystyle.setBorderLeft(BorderStyle.MEDIUM);
		tbodystyle.setBorderTop(BorderStyle.MEDIUM); // FIN DE LOS ESTILOS

		if (creditoFiscal) {
			Row row = sheet.createRow(0);
			row.setHeightInPoints(57);
			row.createCell(0);
			row.createCell(8).setCellValue(factura.getCodigofactura());
			row.getCell(8).setCellStyle(celltextRight);
			row.getCell(0).setCellStyle(celdasStyleMerged);

			// para la 2 y 3
			Row row2 = sheet.createRow(1);
			row2.setHeightInPoints(22);
			row2.createCell(0);
			row2.getCell(0).setCellStyle(celdasStyleMerged);

			Row row3 = sheet.createRow(2);
			row3.setHeightInPoints(14);
			row3.createCell(8);
			row3.createCell(2);
			row3.getCell(2).setCellValue(factura.getCliente().getCliente().getNombre());
			row3.getCell(2).setCellStyle(calibricenteronce);

			// fecha
			row3.createCell(7);
			row3.getCell(7).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(factura.getFecha()).toString());
			row3.getCell(7).setCellStyle(calibridiezright);

			// para la 4 el registro
			Row row4 = sheet.createRow(3);

			row4.createCell(7);
			row4.setHeightInPoints(22);
			row4.getCell(7).setCellValue(factura.getCliente().getCliente().getCodigo());
			row4.getCell(7).setCellStyle(calibridiezright);

			// para la 5
			Row row5 = sheet.createRow(4);
			row5.setHeightInPoints(31);
			row5.createCell(2);
			row5.getCell(2).setCellValue(factura.getCliente().getDirecciones().getNombre());
			row5.getCell(2).setCellStyle(calibricenteronce);
			// nit
			row5.createCell(7);
			row5.getCell(7).setCellValue(factura.getCliente().getCliente().getDui());
			row5.getCell(7).setCellStyle(calibridiezcenterright);

			// para la 6 giro y a unir con la 7
			Row row6 = sheet.createRow(5);
			row6.setHeightInPoints(14);
			row6.createCell(7);
			row6.getCell(7).setCellValue(factura.getCliente().getCliente().getGiro().getDetalles());
			row6.getCell(7).setCellStyle(calibri10warptext);

			// para la 7
			Row row7 = sheet.createRow(6);
			row7.setHeightInPoints(12);

			// para la 8 9, 10 y 11
			Row row8 = sheet.createRow(7);
			Row row9 = sheet.createRow(8);
			Row row10 = sheet.createRow(9);
			Row row11 = sheet.createRow(10);
			Row row12 = sheet.createRow(11);

			row8.setHeightInPoints(21);
			row9.setHeightInPoints(15);
			row10.setHeightInPoints(7);
			row11.setHeightInPoints(1);
			row12.setHeightInPoints(Float.parseFloat("24.75"));

			row8.createCell(2);
			row8.createCell(4);
			row8.createCell(7);

			row8.getCell(2).setCellValue("DEPARTAMENTO");
			row8.getCell(2).setCellStyle(calibritopleftonce);
			row8.getCell(4).setCellStyle(calibricenteronce);
			row8.getCell(4).setCellValue("MUNICIPIO");
			row8.getCell(7).setCellValue(factura.getTipoFactura().getNombre());
			row8.getCell(7).setCellStyle(calibridiezcenterright);

		} else {
			// para la primera row A1
			Row row = sheet.createRow(0);
			row.setHeightInPoints(66);
			row.createCell(0);
			row.createCell(6).setCellValue(factura.getCodigofactura());
			row.getCell(6).setCellStyle(celltextRight);
			row.getCell(0).setCellStyle(celdasStyleMerged);

			// para la 2 y 3
			Row row2 = sheet.createRow(1);
			row2.setHeightInPoints(15);
			row2.createCell(0);
			row2.getCell(0).setCellStyle(celdasStyleMerged);
			Row row3 = sheet.createRow(2);
			row3.setHeightInPoints(Float.parseFloat("8.5"));

			// para la 4
			Row row4 = sheet.createRow(creditoFiscal ? 2 : 3);

			row4.createCell(0);
			row4.createCell(3);
			row4.createCell(4);
			row4.createCell(creditoFiscal ? 2 : 1);
			row4.createCell(5);
			row4.getCell(0).setCellStyle(celdasStyleMerged);
			row4.getCell(3).setCellStyle(celdasStyleMerged);
			row4.getCell(4).setCellStyle(celdasStyleMerged);
			row4.setHeightInPoints(Float.parseFloat("37.5"));

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
			if (creditoFiscal) {
				row6.createCell(2);
			} else {
				row6.createCell(1);
			}
			row6.getCell(1).setCellValue(factura.getCliente().getDirecciones().getNombre());
			row6.getCell(1).setCellStyle(celltextLeft);

			// para la 7
			Row row7 = sheet.createRow(6);
//		row7.setHeightInPoints(19);
			row7.setHeightInPoints(Float.parseFloat("20.75"));
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

			row8.setHeightInPoints(Float.parseFloat("11.75"));
			row9.setHeightInPoints(15);
			row10.setHeightInPoints(15);
			row11.setHeightInPoints(3);
			row8.createCell(0);
			row8.createCell(1);
			row8.getCell(0).setCellStyle(celdasStyleMerged);
			row8.getCell(1).setCellStyle(celdasStyleMerged);

		}
		// para el 12 en adelante:
		int itemRows = creditoFiscal ? 12 : 10;
		Row rowItems = sheet.createRow(11);
		if (creditoFiscal) {
			rowItems.setHeightInPoints(Float.parseFloat("24.75"));
		}

		Cell cellItems = rowItems.createCell(0);

		int contadorProducts = creditoFiscal ? 13 : 12;
		double totalsiniva = 0;
		for (CarritoItems carrito : factura.getCotizacion().getCarrito()) {
			float tamanoaltura = creditoFiscal ? Float.parseFloat("22.5") : 27;
			if (itemRows == 10 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("27.5");
			}
			if (itemRows == 11 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("27.5");
			}
			if (itemRows == 12 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("27.5");
			}
			if (itemRows == 11 && creditoFiscal) {
				tamanoaltura = Float.parseFloat("24.75");
			}
			if (itemRows == 12 && creditoFiscal) {
				tamanoaltura = Float.parseFloat("26.25");
			}
			if (itemRows == 23 && creditoFiscal) {
				tamanoaltura = 18;
			}
			if (itemRows == 16 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("28");
			}
			if (itemRows == 18 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("28.5");
			}
			if (itemRows == 21 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("28.5");
			}

			Row fila = sheet.createRow(itemRows++);
			fila.setHeightInPoints(tamanoaltura);

			cellItems = fila.createCell(0);
			cellItems.setCellStyle(celltext);
//			double preciofinalP = (creditoFiscal)?carrito.getPrecio()/1.13:carrito.getPrecio(); 
			cellItems.setCellValue(carrito.getCantidad());

			// codigo del producto y nombre unidos por alguna razon
			cellItems = creditoFiscal ? fila.createCell(2) : fila.createCell(1);
			cellItems.setCellStyle(calibriproducs);
			cellItems.setCellValue(
					carrito.getProductos().getMarca().getNombrem() + " " + carrito.getProductos().getNombrep() + " "
							+ carrito.getProductos().getPresentacion().getDetalle());

			// precio
			cellItems = creditoFiscal ? fila.createCell(5) : fila.createCell(3);
			cellItems.setCellStyle(celltext);
//			"$  " + String.format("%.2f", (carrito.getPrecio() / ((100 - carrito.getMargen()) / 100)))
			double precioUnitario_temp = (creditoFiscal) ? carrito.getPrecio() : carrito.getPrecio() * 1.13;
			cellItems.setCellStyle(celltextMoney);
//			cellItems.setCellValue(String.format("%.2f", precioUnitario_temp));
			cellItems.setCellValue(precioUnitario_temp);
//			cellItems.setCellValue("$  " + String.format("%.2f", precioUnitario_temp));

			// espaciado en color son adelante
			cellItems = creditoFiscal ? fila.createCell(4) : fila.createCell(4);
			cellItems.setCellStyle(celdasStyleMerged);
			cellItems = creditoFiscal ? fila.createCell(6) : fila.createCell(5);
			cellItems.setCellStyle(celdasStyleMerged);

			// total precio x cantidad
			cellItems = creditoFiscal ? fila.createCell(8) : fila.createCell(6);
			cellItems.setCellStyle(celltextMoney);
//			double precioUnitarioXcantidad = precioUnitario_temp * carrito.getCantidad(); DESCOMENTAR PARA SIN FORMULA
			String precioUnitarioXcantidad = creditoFiscal ? ("(F" + contadorProducts + "*A" + contadorProducts + ")")
					: ("(D" + contadorProducts + "*A" + contadorProducts + ")");
			cellItems.setCellFormula(precioUnitarioXcantidad);
			totalsiniva += carrito.getCantidad() * carrito.getPrecio();
			contadorProducts++;

		}
		// para ir llenando en vacio las demas con formato
		int whilefin = creditoFiscal ? 27 : 24;
		while (contadorProducts > whilefin) {
			System.out.print("Voy por la celda: " + contadorProducts);
			if (creditoFiscal) {
				Row fila = sheet.createRow(contadorProducts);
				float tamanoaltura = Float.parseFloat("22.5");
				if (itemRows == 12) {
					tamanoaltura = 26;
				}
				if (itemRows == 23) {
					tamanoaltura = 18;
				}
				fila.setHeightInPoints(contadorProducts == 23 && creditoFiscal ? 18 : 28);
				fila.setHeightInPoints(tamanoaltura);
				cellItems = fila.createCell(0);
				cellItems.setCellStyle(celltext);
				cellItems = fila.createCell(2);
				cellItems.setCellStyle(celltextLeft);
				cellItems = fila.createCell(8);
				cellItems.setCellStyle(celltextMoney);

				contadorProducts++;
			} else {
				float tamanoaltura = creditoFiscal ? Float.parseFloat("22.5") : 27;
				if (itemRows == 10) {
					tamanoaltura = Float.parseFloat("27.5");
				}
				if (itemRows == 11) {
					tamanoaltura = Float.parseFloat("27.5");
				}
				if (itemRows == 12) {
					tamanoaltura = Float.parseFloat("27.5");
				}
				if (itemRows == 16) {
					tamanoaltura = Float.parseFloat("28");
				}
				if (itemRows == 18) {
					tamanoaltura = Float.parseFloat("28.5");
				}
				if (itemRows == 21) {
					tamanoaltura = Float.parseFloat("28.5");
				}
				if (itemRows == 16 && !creditoFiscal) {
					tamanoaltura = Float.parseFloat("28");
				}
				if (itemRows == 18 && !creditoFiscal) {
					tamanoaltura = Float.parseFloat("28.5");
				}
				if (itemRows == 21 && !creditoFiscal) {
					tamanoaltura = Float.parseFloat("28.5");
				}

				Row fila = sheet.createRow(contadorProducts);
				fila.setHeightInPoints(tamanoaltura);
				cellItems = fila.createCell(0);
				cellItems.setCellStyle(celltext);
				cellItems = fila.createCell(1);
				cellItems.setCellStyle(celltextLeft);
				cellItems = fila.createCell(6);
				cellItems.setCellStyle(celltextMoney);
				contadorProducts++;

			}
		}
		System.out.print("donde estara el final es: " + itemRows);
		// rellenamos con vacio lo que resta mas altura

		int dondeterminoPro = creditoFiscal ? 27 : 23;
		for (int i_filler = itemRows; itemRows < dondeterminoPro; itemRows++) {
			Row fila = sheet.createRow(i_filler++);
//			float tamanoaltura = Float.parseFloat("22.5"); borrar si da error de tamano
			float tamanoaltura = creditoFiscal ? Float.parseFloat("22.5") : 27;
			if (itemRows == 12) {
				tamanoaltura = 26;
			}
			if (itemRows == 23) {
				tamanoaltura = 18;
			}
			if (itemRows == 10 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("27.5");
			}
			if (itemRows == 11 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("27.5");
			}
			if (itemRows == 12 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("27.5");
			}
			if (itemRows == 16 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("28");
			}
			if (itemRows == 18 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("28.5");
			}
			if (itemRows == 21 && !creditoFiscal) {
				tamanoaltura = Float.parseFloat("28.5");
			}
			fila.setHeightInPoints(tamanoaltura);
			fila.createCell(4);
			fila.createCell(5);
			fila.getCell(4).setCellStyle(celdasStyleMerged);
			fila.getCell(5).setCellStyle(celdasStyleMerged);

			cellItems = fila.createCell(0);
		}

		double retenido = 0;
		Row rowFinal = sheet.createRow(dondeterminoPro);
		String retenidoformula = "0";
		// mostrar iva ahi
		if (creditoFiscal) {
			double sumasPrecios = factura.getTotaRegistrado();
			rowvacios_abajo29.createCell(2);
			rowvacios_abajo29.getCell(2).setCellValue(" " + numeroALetras.Convertir(df.format(sumasPrecios), true));
			rowvacios_abajo29.getCell(2).setCellStyle(ennegritacell);

			rowvacios_abajo29.createCell(8);
			rowvacios_abajo29.getCell(8).setCellFormula("SUM(I11:I28)");
			rowvacios_abajo29.getCell(8).setCellStyle(celltextMoney);
			// retenindo 0 para restar si no es agente
			if (totalsiniva > 113 && agente) {
				retenido = (totalsiniva * 0.01);
				retenidoformula = "I29*0.01";
			}
			rowvacios_abajo30.createCell(8);
			rowvacios_abajo30.getCell(8).setCellFormula("I29*0.13");
			rowvacios_abajo30.getCell(8).setCellStyle(celltextMoney);

			rowvacios_abajo31.createCell(8);
			rowvacios_abajo31.getCell(8).setCellFormula("SUM(I30+I29 )");
			rowvacios_abajo31.getCell(8).setCellStyle(celltextMoney);

			rowvacios_abajo32.createCell(8);
			rowvacios_abajo32.getCell(8).setCellFormula(retenidoformula);
			rowvacios_abajo32.getCell(8).setCellStyle(celltextMoney);

			rowvacios_abajo35.createCell(8);
			rowvacios_abajo35.getCell(8).setCellFormula("I31-I32");
			rowvacios_abajo35.getCell(8).setCellStyle(celltextMoney);

		} else {
			Row rowFinalabajo = sheet.createRow(24);
			rowFinalabajo.createCell(1);
			rowFinalabajo.setHeightInPoints(Float.parseFloat("19.75"));
			rowFinal.setHeightInPoints(33);
			rowFinal.createCell(0);
			rowFinal.createCell(3);
			rowFinal.getCell(0).setCellStyle(celdasStyleMerged);
			rowFinal.getCell(3).setCellStyle(celdasStyleMerged);
			rowFinal.createCell(1);
			rowFinal.getCell(1)
					.setCellValue(" " + numeroALetras.Convertir(df.format(factura.getTotaRegistrado()), true));
			rowFinal.getCell(1).setCellStyle(ennegritacell);
			rowFinal.createCell(6);
			rowFinal.getCell(6).setCellStyle(celltextMoney);
//			rowFinal.getCell(6).setCellValue(sumasPrecios); //sin formula a lo hombre
			rowFinal.getCell(6).setCellFormula("SUM(G11:G23)");
			// retenindo 0 para restar si no es agente

//			double ivado = factura.getTotaRegistrado() + (factura.getTotaRegistrado() * 0.13);
			if (totalsiniva > 113 && agente) {
				retenido = (totalsiniva * 0.01);
			}
			rowvacios_abajo26.createCell(6);
			rowvacios_abajo26.getCell(6).setCellStyle(celltextMoney);
//			rowvacios_abajo27.getCell(6).setCellValue(df.format(ivado)); a lo hombre sin formula voy
			rowvacios_abajo26.getCell(6).setCellFormula("G24");

//			no deberia de usar la 30 si hasta la 29 llega :o
//			Row rowFinal_total = sheet.createRow(29);	
			rowvacios_abajo29.createCell(6);
//			rowFinal_total.getCell(6).setCellValue(ivado - retenido); a lo hombre sin formula
			rowvacios_abajo29.getCell(6).setCellFormula("G26-G28");
			rowvacios_abajo29.getCell(6).setCellStyle(ennegritacelltotal);
			rowvacios_abajo29.setHeightInPoints(Float.parseFloat("22.25"));
//			rowvacios_abajo26.getCell(6).setCellFormula("SUM(G25:G26)");

			// retencion datos set
			rowvacios_abajo28.createCell(6);
			if (retenido == 0) {
				rowvacios_abajo28.getCell(6).setCellValue("0");
			} else {
				rowvacios_abajo28.getCell(6).setCellValue(df.format(retenido));
				rowvacios_abajo28.getCell(6).setCellStyle(celltextMoney);
				System.out.print("\nretenido" + retenido);
			}

			rowvacios_abajo28.getCell(6).setCellStyle(celltextMoney);
//			rowFinalabajo.getCell(1)
//					.setCellValue(" " + numeroALetras.Convertir(df.format(factura.getTotaRegistrado()), true));
		}

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
