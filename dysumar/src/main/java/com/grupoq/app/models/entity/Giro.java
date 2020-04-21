package com.grupoq.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "giro")
public class Giro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 200)
	private String detalles;

	@ManyToOne(fetch = FetchType.LAZY)
	private CategoriaGiro categoriagiro; 
	
	public Giro() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public CategoriaGiro getCategoriagiro() {
		return categoriagiro;
	}

	public void setCategoriagiro(CategoriaGiro categoriagiro) {
		this.categoriagiro = categoriagiro;
	}
	
}
