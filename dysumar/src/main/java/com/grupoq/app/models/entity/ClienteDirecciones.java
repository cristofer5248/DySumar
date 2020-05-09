package com.grupoq.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ClienteDirecciones")
public class ClienteDirecciones implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Direccion direcciones;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	/* @JoinColumn(name="producto") */
	private List<Facturacion> facturacion;
	
	public ClienteDirecciones() {
		this.facturacion = new ArrayList<Facturacion>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Direccion getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(Direccion direcciones) {
		this.direcciones = direcciones;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Facturacion> getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(List<Facturacion> facturacion) {
		this.facturacion = facturacion;
	}

}
