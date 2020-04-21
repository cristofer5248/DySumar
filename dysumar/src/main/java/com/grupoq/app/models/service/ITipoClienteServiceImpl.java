package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.ITipoCliente;
import com.grupoq.app.models.entity.TipoCliente;

@Service
public class ITipoClienteServiceImpl implements ITipoClienteService {

	@Autowired
	private ITipoCliente tipoclientedao;
	
	@Override
	public List<TipoCliente> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoCliente>) tipoclientedao.findAll();
	}

}
