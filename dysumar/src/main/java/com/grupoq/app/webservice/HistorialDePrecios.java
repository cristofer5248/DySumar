package com.grupoq.app.webservice;

import java.util.Date;

public class HistorialDePrecios {

	public Long id;
	public Long idCotizacion;
	public double precio;
	public Date fecha;
	public int cantidad;
	
	public HistorialDePrecios() {
	}
	

	public HistorialDePrecios(Long id, Long idCotizacion, double precio, Date fecha, int cantidad) {
		super();
		this.id = id;
		this.idCotizacion = idCotizacion;
		this.precio = precio;
		this.fecha = fecha;
		this.cantidad =cantidad;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Long idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
		
}
