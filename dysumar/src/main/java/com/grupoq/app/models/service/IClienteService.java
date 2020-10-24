package com.grupoq.app.models.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Cliente;
import com.grupoq.app.models.entity.ClienteDirecciones;
import com.grupoq.app.models.entity.Direccion;




public interface IClienteService {

	public List<Cliente> findAll();
	public List<Direccion> findAlld(String term);
	public void save(Direccion direccion);
	public Direccion findByidDireccion(Long id);
	
	public List<Cliente> findAllByUsuario(String usuario);
	public Page <Cliente> findAllByUsuarioPage(String usuario, Pageable pageable);
	
	public Page<Cliente> findAll(Pageable pageable);

	public void save(Cliente cliente);
	
	public void savecd(ClienteDirecciones cd);
	
	public Cliente findOne(Long id);
	
	public List<Cliente> findByNombre(String term);
	
	//buscar por nombre pero tambien like
	public List<Cliente> findByUsuarioLike(String term, String nombre);
	
	public Cliente fetchByIdWithTallerWithFactura(Long id);
	
	public void delete(Long id);		
	
	
	public void deleteFactura(Long id);
	public ClienteDirecciones findByIdByFacturacion(Long id);
	//buscar el id para jalarlo y poner al cliente
	
	public ClienteDirecciones findByIdDireccionOnly(Long id);
	
	public List<ClienteDirecciones> findByCliente(Long id);
	public Page <Cliente> findAllByGiro(Long id, String user, Pageable pageable);
	public Page <Cliente> findAllByGiroAdmin(Long id, Pageable pageable);
	
	public void deleteDireccionC(Long id);
	
}