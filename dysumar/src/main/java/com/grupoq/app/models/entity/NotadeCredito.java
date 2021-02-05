package com.grupoq.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class NotadeCredito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(length = 140)
	@Size(min = 7, max = 140)
	private String cliente;

	@NotEmpty
	@Size(min = 2, max = 15)
	@Column(length = 15)
	private String codigodoc;

	@NotEmpty
	@Size(min = 2, max = 30)
	@Column(length = 30)
	private String municipio;

	@NotEmpty
	@Size(min = 2, max = 30)
	@Column(length = 30)
	private String departamento;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;

	@NotEmpty
	@Column(length = 40)
	private String duinit;

	@ManyToOne(fetch = FetchType.LAZY)
	private Giro giro;

	@NotEmpty
	@Column(length = 60)
	private String cdpago;

	@NotEmpty
	@Column(length = 60)
	private String ntr;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cotizacion carrito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCodigodoc() {
		return codigodoc;
	}

	public void setCodigodoc(String codigodoc) {
		this.codigodoc = codigodoc;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDuinit() {
		return duinit;
	}

	public void setDuinit(String duinit) {
		this.duinit = duinit;
	}

	public Giro getGiro() {
		return giro;
	}

	public void setGiro(Giro giro) {
		this.giro = giro;
	}

	public String getCdpago() {
		return cdpago;
	}

	public void setCdpago(String cdpago) {
		this.cdpago = cdpago;
	}

	public String getNtr() {
		return ntr;
	}

	public void setNtr(String ntr) {
		this.ntr = ntr;
	}

	public Cotizacion getCarrito() {
		return carrito;
	}

	public void setCarrito(Cotizacion carrito) {
		this.carrito = carrito;
	}
	
	
}
