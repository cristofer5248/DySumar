package com.grupoq.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.grupoq.app.models.entity.Inventario;
import com.grupoq.app.models.entity.Producto;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/productos/ver.pdf")
public class InventarioPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Producto producto = (Producto) model.get("producto");
		PdfPTable tabla = new PdfPTable(1);
		tabla.addCell("Datos del producto");
		tabla.addCell(producto.getNombrep());

//		PdfPTable tabla2 = new PdfPTable(1);
//		tabla2.addCell("Datos de inventario");
//		tabla2.addCell("Datos de inventario");
//		tabla2.addCell("Datos de inventario");

		document.add(tabla);
//		document.add(tabla2);
		PdfPTable tabla3 = new PdfPTable(4);
		tabla3.addCell("ID");
		tabla3.addCell("Cod de proveedor");
		tabla3.addCell("Ingreso");
		tabla3.addCell("Fecha");

		for (Inventario inven : producto.getInventarios()) {
			tabla3.addCell(inven.getId().toString());
			tabla3.addCell(inven.getCodigoProveedor());
			tabla3.addCell(String.valueOf(inven.getStock()));
			tabla3.addCell(inven.getFecha().toString());
		}
		PdfPCell cell = new PdfPCell(new Phrase("Total: "));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tabla3.addCell(cell);
		tabla3.addCell("0 por el momento :)");

		document.add(tabla3);
	}

}
