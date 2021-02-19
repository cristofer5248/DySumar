package com.grupoq.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.NotadeCredito;

public interface INotadeCreditoDao extends PagingAndSortingRepository<NotadeCredito, Long> {
	public Page<NotadeCredito> findByCodigodoc(String codigodoc, Pageable page);

	@Query("select n from NotadeCredito n inner join n.giro g where n.fecha between ?1 and ?2 ")
	public Page<NotadeCredito> findByDates(Date fecha1, Date fecha2, Pageable page);

	@Query("select n from NotadeCredito n inner join n.carrito co inner join co.carrito ca inner join ca.productos pro where pro.id=?1")
	public List<NotadeCredito> findByCodigodoc(Long codigodoc);

}
