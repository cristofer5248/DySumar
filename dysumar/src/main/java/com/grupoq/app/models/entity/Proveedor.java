package com.grupoq.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Proveedor implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Column(length = 80)
	@Size(max = 80)
	private String nombre;
	
	@NotEmpty
	@Column(length = 20)
	@Size(max = 20)
	private String nit;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Giro giro;
	
	@NotEmpty
	@Column(length= 90)
	@Size(max = 90)
	private String direccion;
	
	@NotEmpty
	@Column(length= 8)
	@Size(max = 8)
	private String telefono;
	
	
	@Column(length= 40)
	@Email
	private String email;
	
	@NotEmpty
	@Column(length= 20)
	@Size(max = 20)
	private String codigoP;
	
	@NotEmpty
	@Column(length= 80)
	@Size(max = 80)
	private String razonsocial;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Proveedor() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public Giro getGiro() {
		return giro;
	}
	public void setGiro(Giro giro) {
		this.giro = giro;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direcccion) {
		this.direccion = direcccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodigoP() {
		return codigoP;
	}
	public void setCodigoP(String codigoP) {
		this.codigoP = codigoP;
	}
	public String getRazonsocial() {
		return razonsocial;
	}
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	
	
}
