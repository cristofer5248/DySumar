package com.grupoq.app.webservice;

import java.util.Date;

public class ProductosWB {

	public Long id;
	public String nombrep;
	public String codigo;
	public Date create_at;
	public double precio;
	public int stock;
	public String categorianombre;
	public Long categoriaid;
	public Long marcaid;
	public Long margenid;
	public Long presentacionid;
	public Long proveedorid;
	public String marcanombre;
	public Double margen;
	public String presentacionnombre;
	public String proveedornombre;
	
	public ProductosWB() {
		
		
	}
	

	public ProductosWB(Long id, String nombrep, String codigo, Date create_at, double precio, int stock,
			String categorianombre, Long categoriaid, Long marcaid, Long margenid, Long presentacionid,
			Long proveedorid, String marcanombre, String margennombre, String presentacionnombre,
			String proveedornombre) {
		super();
		this.id = id;
		this.nombrep = nombrep;
		this.codigo = codigo;
		this.create_at = create_at;
		this.precio = precio;
		this.stock = stock;
		this.categorianombre = categorianombre;
		this.categoriaid = categoriaid;
		this.marcaid = marcaid;
		this.margenid = margenid;
		this.presentacionid = presentacionid;
		this.proveedorid = proveedorid;
		this.marcanombre = marcanombre;		
		this.presentacionnombre = presentacionnombre;
		this.proveedornombre = proveedornombre;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrep() {
		return nombrep;
	}

	public void setNombrep(String nombrep) {
		this.nombrep = nombrep;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCategorianombre() {
		return categorianombre;
	}

	public void setCategorianombre(String categorianombre) {
		this.categorianombre = categorianombre;
	}

	public Long getCategoriaid() {
		return categoriaid;
	}

	public void setCategoriaid(Long categoriaid) {
		this.categoriaid = categoriaid;
	}

	public Long getMarcaid() {
		return marcaid;
	}

	public void setMarcaid(Long marcaid) {
		this.marcaid = marcaid;
	}

	public Long getMargenid() {
		return margenid;
	}

	public void setMargenid(Long margenid) {
		this.margenid = margenid;
	}

	public Long getPresentacionid() {
		return presentacionid;
	}

	public void setPresentacionid(Long presentacionid) {
		this.presentacionid = presentacionid;
	}

	public Long getProveedorid() {
		return proveedorid;
	}

	public void setProveedorid(Long proveedorid) {
		this.proveedorid = proveedorid;
	}

	public String getMarcanombre() {
		return marcanombre;
	}

	public void setMarcanombre(String marcanombre) {
		this.marcanombre = marcanombre;
	}



	public Double getMargen() {
		return margen;
	}


	public void setMargen(Double margen) {
		this.margen = margen;
	}


	public String getPresentacionnombre() {
		return presentacionnombre;
	}

	public void setPresentacionnombre(String presentacionnombre) {
		this.presentacionnombre = presentacionnombre;
	}

	public String getProveedornombre() {
		return proveedornombre;
	}

	public void setProveedornombre(String proveedornombre) {
		this.proveedornombre = proveedornombre;
	}
	
}
