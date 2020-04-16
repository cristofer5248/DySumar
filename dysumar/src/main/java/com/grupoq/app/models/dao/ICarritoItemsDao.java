package com.grupoq.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.CarritoItems;

public interface ICarritoItemsDao extends CrudRepository<CarritoItems, Long>{

}
