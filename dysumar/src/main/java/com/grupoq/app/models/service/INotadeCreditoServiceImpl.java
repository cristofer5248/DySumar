package com.grupoq.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.INotadeCredito;
import com.grupoq.app.models.entity.NotadeCredito;

@Service
public class INotadeCreditoServiceImpl implements INotadeCreditoService {

	@Autowired
	INotadeCredito notadecreditodao;

	@Override
	public void save(NotadeCredito notadecredito) {
		notadecreditodao.save(notadecredito);

	}

}
