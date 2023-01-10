package com.jeanlima.springrestapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jeanlima.springrestapi.model.Cliente;
import com.jeanlima.springrestapi.service.ClienteService;
import com.jeanlima.springrestapi.service.impl.ClienteServiceImpl;

@RestController //anotação especializadas de controller - todos já anotados com response body!
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteServiceImpl clienteService) {
		this.clienteService = clienteService;
	}
    
    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.buscarClientes();
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clienteService.buscarClientePorId(id);
    }
    
    @GetMapping("/pedidos/{id}")
    public Cliente getClientePedidos(@PathVariable Integer id) {
    	return clienteService.buscarClientePedidos(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }
    
    @PutMapping("{id}")
    public Cliente update(@PathVariable Integer id, @RequestBody Cliente cliente) {
    	Cliente clienteExistente = clienteService.buscarClientePorId(id);
    	cliente.setId(clienteExistente.getId());
    	return clienteService.salvar(cliente);
    }
    
    @PatchMapping("{id}")
    public Cliente patch(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
    	Cliente cliente = clienteService.buscarClientePorId(id);
    	return clienteService.editar(cliente, fields);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
    	Cliente cliente = clienteService.buscarClientePorId(id);
    	clienteService.remover(cliente);
    }

}
