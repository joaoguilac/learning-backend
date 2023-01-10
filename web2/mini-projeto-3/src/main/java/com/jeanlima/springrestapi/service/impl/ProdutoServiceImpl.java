package com.jeanlima.springrestapi.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.springrestapi.model.Produto;
import com.jeanlima.springrestapi.repository.ProdutoRepository;
import com.jeanlima.springrestapi.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Override
	public List<Produto> buscarProdutos() {
		return produtoRepository.findAll();
	}
	
	@Override
	public Produto buscarProdutoPorId(Integer id) {
		return produtoRepository.findById(id)
                .orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
	}
	
	@Override
	public Produto buscarProdutoPorNome(String nome) {
	    return produtoRepository.findByDescricao(nome);
	}
	
	@Override
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@Override
	public Produto editar(Produto produto, Map<Object, Object> fields) {
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findRequiredField(Produto.class, (String) key);
	        field.setAccessible(true);
	        if (value instanceof Integer) {
	        	BigDecimal valueBigDecimal = BigDecimal.valueOf((int) value);
	        	ReflectionUtils.setField(field, produto, valueBigDecimal);
	        }
	        else {
	        	ReflectionUtils.setField(field, produto, value);
	        }
		});
		return salvar(produto);
	}
	
	@Override
	public void remover(Produto produto) {
		produtoRepository.delete(produto);
	}

}
