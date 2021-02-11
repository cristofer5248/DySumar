package com.grupoq.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductosModify implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto productomodi;
	
	
	private Double precio;
	private Date fecha;
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}





	public Producto getProductomodi() {
		return productomodi;
	}


	public void setProductomodi(Producto productomodi) {
		this.productomodi = productomodi;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	private static final long serialVersionUID = 1L;

}
