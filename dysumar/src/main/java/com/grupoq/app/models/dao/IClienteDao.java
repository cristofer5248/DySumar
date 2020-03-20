package com.grupoq.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Cliente;


public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

	
}