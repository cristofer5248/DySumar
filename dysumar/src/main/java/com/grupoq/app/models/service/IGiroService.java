package com.grupoq.app.models.service;

import java.util.List;

import com.grupoq.app.models.entity.Giro;

public interface IGiroService {

	public List<Giro> listAll();
	public List<Giro> findByNombre(String term);
	public void delete(Long id);
	public Giro findBy(Long id);
}