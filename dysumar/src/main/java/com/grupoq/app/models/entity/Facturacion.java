package com.grupoq.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Facturacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CarritoItems carritoItems;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private TFactura tipoFactura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ClienteDirecciones cliente;
	
	private String aCuentade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CDepago condicionesDPago;
	
	private String detalles;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private FormaDePago formaDePago;
	
	public Facturacion() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CarritoItems getCarritoItems() {
		return carritoItems;
	}

	public void setCarritoItems(CarritoItems carritoItems) {
		this.carritoItems = carritoItems;
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

	public String getaCuentade() {
		return aCuentade;
	}

	public void setaCuentade(String aCuentade) {
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

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}
	
}
