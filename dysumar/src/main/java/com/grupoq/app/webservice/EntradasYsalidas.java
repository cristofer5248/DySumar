package com.grupoq.app.webservice;

import java.util.Date;

public class EntradasYsalidas {

	public Long id;
	public String codigo;
	public int movimiento;
	public Date fecha;
	public String color;

	public EntradasYsalidas(Long id, String codigo, int movimiento, Date fecha, String color) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.movimiento = movimiento;
		this.fecha = fecha;
		this.color = color;
	}

	public EntradasYsalidas() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(int movimiento) {
		this.movimiento = movimiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
