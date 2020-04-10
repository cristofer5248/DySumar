package com.grupoq.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Marca;

public interface IMarcaDao extends PagingAndSortingRepository<Marca, Long> {

}
