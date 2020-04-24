package com.grupoq.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;

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
	
	@NotNull
	private int codigofactura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cotizacion cotizacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private TFactura tipoFactura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ClienteDirecciones cliente;
	
//	private String aCuentade;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario aCuentade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CDepago condicionesDPago;
	
	private String detalles;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Formadepago formadepago;
	
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

	public int getCodigofactura() {
		return codigofactura;
	}

	public void setCodigofactura(int codigofactura) {
		this.codigofactura = codigofactura;
	}
	

//	public FormaDePago getFormaDePago() {
//		return formaDePago;
//	}
//
//	public void setFormaDePago(FormaDePago formaDePago) {
//		this.formaDePago = formaDePago;
//	}
	
}
