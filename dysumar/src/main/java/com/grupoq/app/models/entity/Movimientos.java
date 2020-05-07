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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movimientos")
public class Movimientos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="movimientos", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	/* @JoinColumn(name="producto") */
	private List<Inventario> inventario;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Inventario inventario;
		
	//descuentos o margenes
//	private double precioMargen;
//	private double precioNormal;
//	private double descuento;
	
	private static final long serialVersionUID = 1L;

	public Movimientos() {
		this.inventario = new ArrayList<Inventario>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Inventario> getInventario() {
		return inventario;
	}

	public void setInventario(List<Inventario> inventario) {
		this.inventario = inventario;
	}		

	


}
