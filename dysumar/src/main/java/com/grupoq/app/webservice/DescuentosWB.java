package com.grupoq.app.webservice;

public class DescuentosWB {

	public Long productoid;
	public Long descuentoid;
	public double descuento;
	public int cantidad;

	
	public DescuentosWB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DescuentosWB(Long productoid, Long descuentoid, double descuento, int cantidad) {
		super();
		this.productoid = productoid;
		this.descuentoid = descuentoid;
		this.descuento = descuento;
		this.cantidad = cantidad;
	}

	public Long getProductoid() {
		return productoid;
	}

	public void setProductoid(Long productoid) {
		this.productoid = productoid;
	}

	public Long getDescuentoid() {
		return descuentoid;
	}

	public void setDescuentoid(Long descuentoid) {
		this.descuentoid = descuentoid;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
