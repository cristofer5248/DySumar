package com.grupoq.app.models.service;

import java.util.Date;

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

}
