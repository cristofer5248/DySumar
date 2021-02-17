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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Facturacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String codigofactura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cotizacion cotizacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private TFactura tipoFactura;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private ClienteDirecciones cliente;
	//manytoOne pero desde cliente sera OnetoMany!
//	private String aCuentade;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario aCuentade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CDepago condicionesDPago;
	
	private String detalles;
	//
	@ManyToOne(fetch = FetchType.LAZY)
	private Formadepago formadepago;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;
	@Column(length = 1)
	private int status;
	
	private double totaRegistrado;
	public Facturacion() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public TFactura getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(TFactura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public ClienteDirecciones getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDirecciones cliente) {
		this.cliente = cliente;
	}



	public Usuario getaCuentade() {
		return aCuentade;
	}

	public void setaCuentade(Usuario aCuentade) {
		this.aCuentade = aCuentade;
	}

	public CDepago getCondicionesDPago() {
		return condicionesDPago;
	}

	public void setCondicionesDPago(CDepago condicionesDPago) {
		this.condicionesDPago = condicionesDPago;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Formadepago getFormadepago() {
		return formadepago;
	}

	public void setFormadepago(Formadepago formadepago) {
		this.formadepago = formadepago;
	}

	public String getCodigofactura() {
		return codigofactura;
	}

	public void setCodigofactura(String codigofactura) {
		this.codigofactura = codigofactura;
	}

	public Date getFecha() {		
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getTotaRegistrado() {
		return totaRegistrado;
	}

	public void setTotaRegistrado(double totaRegistrado) {
		this.totaRegistrado = totaRegistrado;
	}
	
	public double getVentastotales() {
//		Double totales = getTotaRegistrado()/1.13;
		Double totales = getTotaRegistrado();
		return totales;
	}



//	public FormaDePago getFormaDePago() {
//		return formaDePago;
//	}
//
//	public void setFormaDePago(FormaDePago formaDePago) {
//		this.formaDePago = formaDePago;
//	}
	
	
}
