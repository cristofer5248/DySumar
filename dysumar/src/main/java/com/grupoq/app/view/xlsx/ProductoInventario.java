package com.grupoq.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.grupoq.app.models.entity.Inventario;
import com.grupoq.app.models.entity.Producto;

@Component("/inventario/listar")
public class ProductoInventario extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"Inventario_de_productos.xlsx\"");
		@SuppressWarnings("unchecked")
		Page<Inventario> inventario = (Page<Inventario>) model.get("inventarios");
		String rangodeFechas = (String) model.get("rangofechas");
//		List<Producto> producto = extracted(model);
		Sheet sheet = workbook.createSheet("Productos");

		// estilos habran paso!
		CellStyle theaderstyle = workbook.createCellStyle();
		theaderstyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderstyle.setBorderRight(BorderStyle.MEDIUM);
		theaderstyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderstyle.setBorderTop(BorderStyle.MEDIUM);
		theaderstyle.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
		theaderstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle cellStyledate = workbook.createCellStyle();  
		cellStyledate.setDataFormat(  
            createHelper.createDataFormat().getFormat("d/mm/yy h:mm"));
		cellStyledate.setBorderBottom(BorderStyle.MEDIUM);
		cellStyledate.setBorderRight(BorderStyle.MEDIUM);
		cellStyledate.setBorderLeft(BorderStyle.MEDIUM);
		cellStyledate.setBorderTop(BorderStyle.MEDIUM);

		Font font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 12);
		font1.setColor(IndexedColors.WHITE.getIndex());
		font1.setBold(true);
		theaderstyle.setFont(font1);

		CellStyle tbodystyle = workbook.createCellStyle();
		tbodystyle.setBorderBottom(BorderStyle.MEDIUM);
		tbodystyle.setBorderRight(BorderStyle.MEDIUM);
		tbodystyle.setBorderLeft(BorderStyle.MEDIUM);
		tbodystyle.setBorderTop(BorderStyle.MEDIUM);
		
		
		// FIN DE LOS ESTILOS

		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
//
//		cell.setCellValue("Datos del producto");
//		cell.setCellStyle(theaderstyle);
//		
//		row = sheet.createRow(1);
//		cell = row.createCell(0);
//		cell.setCellStyle(tbodystyle);
//		
//		cell.setCellValue(producto.getCodigo());
//		
//		row = sheet.createRow(2);
//		cell = row.createCell(0);
//		cell.setCellValue(producto.getNombrep());
//		cell.setCellStyle(tbodystyle);
//		
//		row = sheet.createRow(3);
//		cell = row.createCell(0);
//		cell.setCellValue("Prov: "+producto.getProveedor().getNombre());
//		cell.setCellStyle(tbodystyle);
//		
//		row = sheet.createRow(4);
//		cell = row.createCell(0);
//		cell.setCellValue("Marca: "+producto.getMarca().getNombrem());
//		cell.setCellStyle(tbodystyle);
//
//		row = sheet.createRow(5);
//		cell = row.createCell(0);
//		cell.setCellValue("Precio U: "+producto.getPrecio());
//		cell.setCellStyle(tbodystyle);
//
//		row = sheet.createRow(6);
//		cell = row.createCell(0);
//		cell.setCellValue("Stock : "+producto.getStock());
//		cell.setCellStyle(tbodystyle);

		Row header_ = sheet.createRow(0);
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9)); // basura me pinta todo
		header_.createCell(5).setCellValue("Inventario entre las de fechas de "+rangodeFechas);
		header_.getCell(5).setCellStyle(theaderstyle);
		header_.createCell(0).setCellStyle(theaderstyle);
		header_.createCell(1).setCellStyle(theaderstyle);
		header_.createCell(2).setCellStyle(theaderstyle);
		header_.createCell(3).setCellStyle(theaderstyle);
		header_.createCell(4).setCellStyle(theaderstyle);
		
		header_.createCell(6).setCellStyle(theaderstyle);
		header_.createCell(7).setCellStyle(theaderstyle);
		header_.createCell(8).setCellStyle(theaderstyle);
		header_.createCell(9).setCellStyle(theaderstyle);
		header_.createCell(10).setCellStyle(theaderstyle);
				
				
		Row header = sheet.createRow(1);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Codigo");
		sheet.autoSizeColumn(0);
		header.createCell(2).setCellValue("Nombre");
		header.createCell(3).setCellValue("Costo");
		header.createCell(4).setCellValue("Marca");
		header.createCell(5).setCellValue("Ubicacion");
		header.createCell(6).setCellValue("Categoria");
		header.createCell(7).setCellValue("Proveedor");
		header.createCell(8).setCellValue("Unidad");
		header.createCell(9).setCellValue("Stock");
		header.createCell(10).setCellValue("Fecha");

		// poniendo los estilos aqua y blanco y bordes
//		cell.setCellStyle(tbodystyle);

		header.getCell(0).setCellStyle(theaderstyle);
		header.getCell(1).setCellStyle(theaderstyle);
		header.getCell(2).setCellStyle(theaderstyle);
		header.getCell(3).setCellStyle(theaderstyle);
		header.getCell(4).setCellStyle(theaderstyle);
		header.getCell(5).setCellStyle(theaderstyle);
		header.getCell(6).setCellStyle(theaderstyle);
		header.getCell(7).setCellStyle(theaderstyle);
		header.getCell(8).setCellStyle(theaderstyle);
		header.getCell(9).setCellStyle(theaderstyle);
		header.getCell(10).setCellStyle(theaderstyle);

		int rownum = 2;
		double total = 0;
		for (Inventario pro_ : inventario) {
			
			Producto pro = pro_.getProducto();
			Row fila = sheet.createRow(rownum++);
			cell = fila.createCell(0);
			cell.setCellValue(pro.getId());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(1);
			cell.setCellValue(pro.getCodigo());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(2);
			cell.setCellValue(pro.getNombrep());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(3);
			cell.setCellValue(pro.getPrecio());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(4);
			cell.setCellValue(pro.getMarca().getNombrem());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(5);
			cell.setCellValue(pro.getBodega());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(6);
			cell.setCellValue(pro.getCategoria().getNombre());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(7);
			cell.setCellValue(pro.getProveedor().getNombre());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(8);
			cell.setCellValue(pro.getPresentacion().getUnidad() + " " + pro.getPresentacion().getDetalle());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(9);
			cell.setCellValue(pro.getStock());
			cell.setCellStyle(tbodystyle);

			cell = fila.createCell(10);			
			cell.setCellValue(pro_.getFecha());
			
			cell.setCellStyle(cellStyledate);

			total += pro.getStock();

		}
		Row filatotal = sheet.createRow(rownum);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 8));
		filatotal.createCell(2).setCellValue("Total:  ");
//		filatotal.createCell(10).setCellType(CellType.STRING);
		filatotal.createCell(10).setCellValue(total);
		filatotal.createCell(0).setCellStyle(tbodystyle);
		filatotal.createCell(1).setCellStyle(tbodystyle);

		filatotal.createCell(1).setCellStyle(tbodystyle);
		filatotal.createCell(2).setCellStyle(tbodystyle);
		filatotal.createCell(3).setCellStyle(tbodystyle);
		filatotal.createCell(4).setCellStyle(tbodystyle);
		filatotal.createCell(5).setCellStyle(tbodystyle);
		filatotal.createCell(6).setCellStyle(tbodystyle);

		filatotal.getCell(2).setCellStyle(tbodystyle);
		filatotal.getCell(10).setCellStyle(tbodystyle);
		filatotal.createCell(8).setCellValue("Total:  ");
		filatotal.getCell(8).setCellStyle(tbodystyle);

//		sheet.addMergedRegion(new CellRangeAddress(1,1,0,3));
//		sheet.addMergedRegion(new CellRangeAddress(2,2,0,3));
//		sheet.addMergedRegion(new CellRangeAddress(3,3,0,3));
//		sheet.addMergedRegion(new CellRangeAddress(4,4,0,3));
//		sheet.addMergedRegion(new CellRangeAddress(5,5,0,3));
//		sheet.addMergedRegion(new CellRangeAddress(6,6,0,3));

		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0, 256 * 7);
		sheet.setColumnWidth(2, 256 * 50);
		sheet.setColumnWidth(5, 256 * 30);
		sheet.setColumnWidth(6, 256 * 30);
		sheet.setColumnWidth(7, 256 * 30);
		sheet.setColumnWidth(7, 256 * 20);
		sheet.setColumnWidth(10, 256 * 20);
	}

}
