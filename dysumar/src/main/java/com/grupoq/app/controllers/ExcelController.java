package com.grupoq.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupoq.app.models.entity.Categoria;
import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.service.ICategoriaService;
import com.grupoq.app.models.service.IProductoService;

@Controller
@RequestMapping("/excel")
@SessionAttributes("producto")
public class ExcelController {

	@Autowired
	private ICategoriaService categoryservice;

	@Autowired
	private IProductoService productoservice;

	@PostMapping("/excelimport")
	public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile, RedirectAttributes flash)
			throws IOException {

		List<Producto> productos_xls = new ArrayList<Producto>();
		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			// creando un producto vacio desde 0
			Producto producto = new Producto();

			XSSFRow row = worksheet.getRow(i);
			try {
				// proceso de verificacion de foraneas
				String codigopro = row.getCell(0).getStringCellValue();
				String categoriString = row.getCell(5).getStringCellValue();

				// si es el codigo ya existe
				codigopro = (productoservice.findByCodigo(codigopro) != null) ? codigopro : codigopro + "1";

				// vemos si esta o no la categoria agregada antes.
				Categoria categoria_xls = categoryservice.findByNombre(categoriString);
				if (categoria_xls != null) {
					producto.setCategoria(categoria_xls);
				} else {
					producto.setCategoria(insertCategory(categoriString));
				}

			} catch (Exception e) {
				flash.addFlashAttribute("error", "Hay un error en su archivo.");
			}

//			String mensaje =
//			System.out.print(mensaje);

		}
		return "redirect:/producto/nuevo";
	}

	public Categoria insertCategory(String nombre) {
		Categoria categoria = new Categoria();
		categoria.setNombre(nombre);
		categoryservice.save(categoria);
		return categoria;
	}

}
