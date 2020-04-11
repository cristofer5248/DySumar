package com.grupoq.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productos")

public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "idp")
	private Long id;
	
	@NotEmpty
	@Column(unique = true)
	@Length (min = 2, max = 10)
	private String codigo;
	private String nombrep;
	
	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Marca marca;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Proveedor proveedor;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Presentacion presentacion;
	private double precio;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Margen margen;
	
	@OneToMany(mappedBy="producto", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	/* @JoinColumn(name="producto") */
	private List<Inventario> inventarios;
	
	private int stock;
	
	public Producto() {
		this.inventarios = new ArrayList<Inventario>();
		
	}
	
	public void addProductoInventario(Inventario inventario) {
		this.inventarios.add(inventario);
		}
	private static final long serialVersionUID = 1L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombrep() {
		return nombrep;
	}
	public void setNombrep(String nombrep) {
		this.nombrep = nombrep;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Presentacion getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Margen getMargen() {
		return margen;
	}
	public void setMargen(Margen margen) {
		this.margen = margen;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public List<Inventario> getInventarios() {
		return inventarios;
	}
	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	
}
