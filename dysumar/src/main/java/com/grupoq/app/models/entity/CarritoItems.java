package com.grupoq.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class CarritoItems implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double margen;
	private double descuento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto productos;
	
	@NotEmpty
	@Column(length = 10)
	private String codigo;
	
	
	private int cantidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cotizacion cotizacionid;
	private boolean status=true;
	
	public CarritoItems() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProductos() {
		return productos;
	}

	public void setProductos(Producto productos) {
		this.productos = productos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cotizacion getCotizacionid() {
		return cotizacionid;
	}

	public void setCotizacionid(Cotizacion cotizacionid) {
		this.cotizacionid = cotizacionid;
	}

	public double getMargen() {
		return margen;
	}

	public void setMargen(double margen) {
		this.margen = margen;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
