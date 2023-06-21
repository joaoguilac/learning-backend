
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

import com.jeanlima.springrestapi.model.Pedido;
import com.jeanlima.springrestapi.service.PedidoService;
import com.jeanlima.springrestapi.service.impl.PedidoServiceImpl;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;
    
    @Autowired
    public PedidoController(PedidoServiceImpl pedidoService) {
    	this.pedidoService = pedidoService;
    }
    
    @GetMapping
    public List<Pedido> getPedidos() {
        return pedidoService.buscarPedidos();
    }
    
    @GetMapping("{id}")
    public Pedido getPedidoById(@PathVariable Integer id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido save(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }
    
    @PutMapping("{id}")
    public Pedido update(@PathVariable Integer id, @RequestBody Pedido pedido) {
    	Pedido pedidoExistente = pedidoService.buscarPedidoPorId(id);
    	pedido.setId(pedidoExistente.getId());
    	return pedidoService.salvar(pedido);
    }
    
    @PatchMapping("{id}")
    public Pedido patch(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
    	Pedido pedido = pedidoService.buscarPedidoPorId(id);
    	return pedidoService.editar(pedido, fields);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
    	Pedido pedido = pedidoService.buscarPedidoPorId(id);
    	pedidoService.remover(pedido);
    }
    
}
