package com.jeanlima.springrestapi.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.springrestapi.model.ItemPedido;
import com.jeanlima.springrestapi.model.Pedido;
import com.jeanlima.springrestapi.repository.PedidoRepository;
import com.jeanlima.springrestapi.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
    
	@Autowired
    PedidoRepository pedidoRepository;

	@Override
	public List<Pedido> buscarPedidos() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido buscarPedidoPorId(Integer id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Pedido não encontrado"));
	}

	@Override
	public Pedido salvar(Pedido pedido) {
	    List<ItemPedido> itens = pedido.getItens();
	    BigDecimal total = pedido.getTotal();
	    for (Iterator iterator = itens.iterator(); iterator.hasNext();) {
  	        ItemPedido itemPedido = (ItemPedido) iterator.next();
  	        BigDecimal itemQuantidade = BigDecimal.valueOf((int) itemPedido.getQuantidade());
  	        BigDecimal precoProduto = itemPedido.getProduto().getPreco();
  	        BigDecimal valorItem = precoProduto.multiply(itemQuantidade);
  	        total = total.add(valorItem);
        }
	    pedido.setTotal(total);
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido editar(Pedido pedido, Map<Object, Object> fields) {
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findRequiredField(Pedido.class, (String) key);
	        field.setAccessible(true);
	        if (value instanceof Integer) {
	        	BigDecimal valueBigDecimal = BigDecimal.valueOf((int) value);
	        	ReflectionUtils.setField(field, pedido, valueBigDecimal);
	        }
	        else {
	        	ReflectionUtils.setField(field, pedido, value);
	        }
		});
		return salvar(pedido);
	}

	@Override
	public void remover(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}
	
}
