package com.grupoq.app.models.service;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoq.app.models.dao.IInventarioDao;
import com.grupoq.app.models.entity.Inventario;

@Service
public class IInventarioServiceImpl implements IInventarioService {

	@Autowired
	private IInventarioDao inventarioDao;

	@Transactional(readOnly = true)
	public Page<Inventario> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return inventarioDao.findAllCustom(pageable);
	}

	@Override
	public void save(Inventario inventario) {
		inventarioDao.save(inventario);

	}

	@Override
	public List<String> sumarStock(Long id) {
		// TODO Auto-generated method stub
		return inventarioDao.sumarStock(id);
	}

	@Override
	public void delete(Long id) {
		inventarioDao.deleteById(id);

	}

	@Override
	public Inventario findById(Long id) {
		// TODO Auto-generated method stub
		return inventarioDao.findById(id).orElse(null);
	}

	@Override
	public List<Inventario> findByIdCodigoProveedor(String id) {
		// TODO Auto-generated method stub
		return inventarioDao.findByIdCodigoProveedor(id);
	}

	@Override
	public Inventario findByIdCodigoProveedorOb(Long id, String idcompra) {
		// TODO Auto-generated method stub
		return inventarioDao.findByIdCodigoProveedorOb(id, idcompra);// aqui
	}

	@Override
	public Inventario findByCodigoProveedor(String id) {
		// TODO Auto-generated method stub
		return inventarioDao.findFirstByCodigoProveedor(id);
	}

	@Override
	public Inventario findByIdCustom(Long id) {
		// TODO Auto-generated method stub
		return inventarioDao.findByIdCustom(id);
	}

	@Override
	public List<Inventario> findByProductoById(Long id) {
		// TODO Auto-generated method stub
		return inventarioDao.findByProductoId(id);
	}

	@Override
	public Page<Inventario> findAllFechas(Pageable pageable, Date date1, Date date2) {
		// TODO Auto-generated method stub
		return inventarioDao.findAllByDates(pageable, date1, date2);
	}

	@Override
	public Page<Inventario> findByCodigoProveedorContaining(String id, Pageable page) {
		// TODO Auto-generated method stub
		return inventarioDao.findByCodigoProveedorLike(id, page);
	}

}
