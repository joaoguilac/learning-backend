package com.jeanlima.springrestapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jeanlima.springrestapi.model.Pedido;

@Service
public interface PedidoService {

	List<Pedido> buscarPedidos();
	Pedido buscarPedidoPorId(Integer id);
	Pedido salvar(Pedido pedido);
	Pedido editar(Pedido pedido, Map<Object, Object> fields);
	void remover(Pedido pedido);
	
}
