package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grupoq.app.models.dao.IClienteDao;
import com.grupoq.app.models.dao.IClienteDirecciones;
import com.grupoq.app.models.dao.IDireccionesDao;
import com.grupoq.app.models.entity.Cliente;
import com.grupoq.app.models.entity.ClienteDirecciones;
import com.grupoq.app.models.entity.Direccion;


@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IDireccionesDao direccionesdao;
	
	@Autowired
	private IClienteDirecciones clientedireccionesdao;
	

	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	public Cliente fetchByIdWithTallerWithFactura(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFactura(Long id) {
		clienteDao.deleteById(id);
		
	}

	@Override
	public List<Cliente> findByNombre(String term) {
		// TODO Auto-generated method stub
		return clienteDao.findByNombre(term);
	}

	@Override
	public void save(Direccion direccion) {
	direccionesdao.save(direccion);
		
	}

	@Override
	public List<Direccion> findAlld(String term) {
		// TODO Auto-generated method stub
		return (List<Direccion>) direccionesdao.findallBy(term);
	}

	@Override
	public void savecd(ClienteDirecciones cd) {
		clientedireccionesdao.save(cd);
		
	}

	@Override
	public List<Cliente> findAllByUsuario(String usuario) {
		// TODO Auto-generated method stub
		return clienteDao.findByUsuario(usuario);
	}

	@Override
	public Page<Cliente> findAllByUsuarioPage(String usuario, Pageable pageable) {
		// TODO Auto-generated method stub
		return clienteDao.findAllByUsuarioPage(usuario, pageable);
	}

	@Override
	public ClienteDirecciones findByIdByFacturacion(Long id) {
		// TODO Auto-generated method stub
		return clientedireccionesdao.findByIdByFacturacion(id);
	}

	@Override
	public List<ClienteDirecciones> findByCliente(Long id) {
		// TODO Auto-generated method stub
		return clientedireccionesdao.findByCliente(id);
	}

	@Override
	public List<Cliente> findByUsuarioLike(String term, String nombre) {
		// TODO Auto-generated method stub
		return clienteDao.findByUsuarioLike(term, nombre);
	}

	@Override
	public Direccion findByidDireccion(Long id) {
		return direccionesdao.findById(id).orElse(null);
		
	}

	

	

	
}
