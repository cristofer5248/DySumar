package com.grupoq.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.grupoq.app.models.entity.CarritoItems;
import com.grupoq.app.models.entity.Facturacion;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/facturas/ver.pdf")
public class FacturaPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Facturacion facturacion = (Facturacion) model.get("facturaciones");
		PdfPTable tabla = new PdfPTable(1);
		tabla.addCell("Descripcion de la factura");
		tabla.addCell(facturacion.getCliente().getCliente().getNombre());

//		PdfPTable tabla2 = new PdfPTable(1);
//		tabla2.addCell("Datos de inventario");
//		tabla2.addCell("Datos de inventario");
//		tabla2.addCell("Datos de inventario");

		document.add(tabla);
//		document.add(tabla2);
		PdfPTable tabla3 = new PdfPTable(4);
		tabla3.addCell("ID");
		tabla3.addCell("Producto");
		tabla3.addCell("Marca");
		tabla3.addCell("Proveedor");
		tabla3.addCell("Cantidad");

		for (CarritoItems carrito : facturacion.getCotizacion().getCarrito()) {
			tabla3.addCell(carrito.getProductos().getId().toString());
			tabla3.addCell(carrito.getProductos().getNombrep());
			tabla3.addCell(carrito.getProductos().getProveedor().getNombre());
			tabla3.addCell(String.valueOf(carrito.getCantidad()));
		}
		PdfPCell cell = new PdfPCell(new Phrase("Total: "));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tabla3.addCell(cell);
		tabla3.addCell("0 por el momento :)");

		document.add(tabla3);
	}

}
