package com.grupoq.app.models.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.NotadeCredito;

public interface INotadeCreditoService {
	public void save(NotadeCredito notadecredito);
	public Page<NotadeCredito> findAll(Pageable page);
	public Page<NotadeCredito> findByCodigodoc(String codigodoc, Pageable page);
	public Page<NotadeCredito> findByDates(Date fecha1, Date fecha2 ,Pageable page);
}
