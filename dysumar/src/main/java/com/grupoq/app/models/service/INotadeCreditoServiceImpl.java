package com.grupoq.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.INotadeCreditoDao;
import com.grupoq.app.models.entity.NotadeCredito;

@Service
public class INotadeCreditoServiceImpl implements INotadeCreditoService {

	@Autowired
	INotadeCreditoDao notadecreditodao;

	@Override
	public void save(NotadeCredito notadecredito) {
		notadecreditodao.save(notadecredito);

	}

	@Override
	public Page<NotadeCredito> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return notadecreditodao.findAll(page);
	}

	@Override
	public Page<NotadeCredito> findByCodigodoc(String codigodoc, Pageable page) {
		// TODO Auto-generated method stub
		return notadecreditodao.findByCodigodoc(codigodoc, page);
	}

	@Override
	public Page<NotadeCredito> findByDates(Date fecha1, Date fecha2, Pageable page) {
		// TODO Auto-generated method stub
		return notadecreditodao.findByDates(fecha1, fecha2, page);
	}

	@Override
	public NotadeCredito findOne(Long id) {
		// TODO Auto-generated method stub
		return notadecreditodao.findById(id).orElse(null);
	}

	@Override
	public List<NotadeCredito> findByCodigodoc(Long codigodoc) {
		// TODO Auto-generated method stub
		return notadecreditodao.findByCodigodoc(codigodoc);
	}

	@Override
	public List<NotadeCredito> findByCodigodocNDC(String codigodoc) {
		// TODO Auto-generated method stub
		return notadecreditodao.findByCodigodocNDC(codigodoc);
	}

}
