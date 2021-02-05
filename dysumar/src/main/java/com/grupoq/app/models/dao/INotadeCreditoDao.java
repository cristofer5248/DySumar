package com.grupoq.app.models.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.NotadeCredito;

public interface INotadeCreditoDao extends PagingAndSortingRepository<NotadeCredito, Long> {
	public Page<NotadeCredito> findByCodigodoc(String codigodoc ,Pageable page);
	
	@Query("select n from NotadeCredito n inner join n.giro g where n.fecha between ?1 and ?2 ")
	public Page<NotadeCredito> findByDates(Date fecha1, Date fecha2 ,Pageable page);
	
}
