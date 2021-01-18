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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "inventario")
public class Inventario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="inventario_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	private Producto producto;
	private int stock;
	
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date fecha;
	
	
	@Column(unique = false, length = 30)
	private String codigoProveedor;
	//descuentos y margenes
	@ManyToOne(fetch = FetchType.LAZY)
	private Movimientos movimientos;
	
	@Column(unique=false, length=25, name = "zanombrede")
	private String zaNombrede;
	
	@Column(length=120)
	
	private String comentario;
	
	private static final long serialVersionUID = 1L;

	public Inventario() {
		
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getFecha() {
//		System.out.print("\n"+fecha);
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public Movimientos getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Movimientos movimientos) {
		this.movimientos = movimientos;
	}

	public String getZaNombrede() {
		return zaNombrede;
	}

	public void setZaNombrede(String zaNombrede) {
		this.zaNombrede = zaNombrede;
	}


}
