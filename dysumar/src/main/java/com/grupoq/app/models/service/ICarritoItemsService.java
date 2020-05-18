package com.grupoq.app.models.service;

import java.util.List;

import com.grupoq.app.models.entity.CarritoItems;

public interface ICarritoItemsService {

	public void save(CarritoItems carritoitems);
	public void delete(Long id);
	public List<CarritoItems> findAll();
	public CarritoItems findById(Long id);
}
