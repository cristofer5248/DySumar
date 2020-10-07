package com.grupoq.app.webservice;

import java.util.Date;

public class HistorialDePrecios {

	public Long id;
	public Long idCotizacion;
	public double precio;
	public Date fecha;
	
	public HistorialDePrecios() {
	}
	

	public HistorialDePrecios(Long id, Long idCotizacion, double precio, Date fecha) {
		super();
		this.id = id;
		this.idCotizacion = idCotizacion;
		this.precio = precio;
		this.fecha = fecha;
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
		
}
