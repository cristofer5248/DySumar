package com.grupoq.app.models.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.grupoq.app.models.entity.Facturacion;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "movimientos")
public class Movimientos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Facturacion Facturacion;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date fecha;
	//descuentos o margenes
	private double precioMargen;
	private double precioNormal;
	private double descuento;
	
	private static final long serialVersionUID = 1L;

	public Movimientos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Facturacion getFacturacion() {
		return Facturacion;
	}

	public void setFacturacion(Facturacion facturacion) {
		Facturacion = facturacion;
	}

	public double getPrecioMargen() {
		return precioMargen;
	}

	public void setPrecioMargen(double precioMargen) {
		this.precioMargen = precioMargen;
	}

	public double getPrecioNormal() {
		return precioNormal;
	}

	public void setPrecioNormal(double precioNormal) {
		this.precioNormal = precioNormal;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

}
