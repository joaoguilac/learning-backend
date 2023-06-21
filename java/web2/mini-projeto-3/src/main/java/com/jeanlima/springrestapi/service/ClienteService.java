package com.jeanlima.springrestapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jeanlima.springrestapi.model.Cliente;

@Service
public interface ClienteService {
	
	List<Cliente> buscarClientes();
	Cliente buscarClientePorId(Integer id);
	Cliente buscarClientePedidos(Integer id);
	Cliente salvar(Cliente cliente);
	Cliente editar(Cliente cliente, Map<Object, Object> fields);
	void remover(Cliente cliente);
	
}
