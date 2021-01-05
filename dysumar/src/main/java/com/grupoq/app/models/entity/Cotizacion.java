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
//import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cotizacion")
public class Cotizacion implements Serializable {

	@Id
	@Column(length = 15)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	@OneToMany(mappedBy = "cotizacionid", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name="cotizacionid")
	private List<CarritoItems> carrito;

	// si algo se arruina quitemos esto
	@OneToMany(mappedBy = "cotizacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Facturacion> factura;

	public boolean aprobado = true;

	public Cotizacion() {
		this.carrito = new ArrayList<CarritoItems>();
		this.factura = new ArrayList<Facturacion>();
	}

	public void addProductoCotizacion(CarritoItems carritos) {
		this.carrito.add(carritos);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<CarritoItems> getCarrito() {
		return carrito;
	}

	public CarritoItems getCarrito_objeto() {
		for (CarritoItems car : getCarrito()) {
			System.out.print(car.getId() + " ");
			return car;
		}
		return null;

	}

	public void setCarrito(List<CarritoItems> carrito) {
		this.carrito = carrito;
	}

	public boolean isAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}

	public List<Facturacion> getFactura() {
		return factura;
	}

	public void setFactura(List<Facturacion> factura) {
		this.factura = factura;
	}

}
