package com.grupoq.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Marca implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idm;

	@NotEmpty
	@Column(length = 20)
	@Size(max = 20)
	private String nombrem;

	public Marca() {

	}

	public Long getIdm() {
		return idm;
	}

	public void setIdm(Long idm) {
		this.idm = idm;
	}

	public String getNombrem() {
		return nombrem;
	}

	public void setNombrem(String nombrem) {
		this.nombrem = nombrem;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
