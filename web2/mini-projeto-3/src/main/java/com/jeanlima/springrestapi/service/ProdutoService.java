package com.jeanlima.springrestapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jeanlima.springrestapi.model.Produto;

@Service
public interface ProdutoService {

	List<Produto> buscarProdutos();
	Produto buscarProdutoPorId(Integer id);
	Produto buscarProdutoPorNome(String nome);
	Produto salvar(Produto produto);
	Produto editar(Produto produto, Map<Object, Object> fields);
	void remover(Produto produto);
	
}
