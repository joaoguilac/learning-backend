package com.jeanlima.springrestapi.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.springrestapi.model.Cliente;
import com.jeanlima.springrestapi.repository.ClienteRepository;
import com.jeanlima.springrestapi.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> buscarClientes() {
		return clienteRepository.findAll();
	}
	
	@Override
	public Cliente buscarClientePorId(Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> //se nao achar lança o erro!
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
	}
	
	@Override
	public Cliente buscarClientePedidos(Integer id) {
		return clienteRepository.findClienteFetchPedidos(id);
	}
	
	@Override
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Override
	public Cliente editar(Cliente cliente, Map<Object, Object> fields) {
		fields.forEach((key, value) -> {
	        Field field = ReflectionUtils.findRequiredField(Cliente.class, (String) key);
	        field.setAccessible(true);
	        ReflectionUtils.setField(field, cliente, value);
        });
        return salvar(cliente);
	}
	
	@Override
	public void remover(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
}
