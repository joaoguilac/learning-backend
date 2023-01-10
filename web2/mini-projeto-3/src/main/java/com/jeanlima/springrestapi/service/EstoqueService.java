package com.jeanlima.springrestapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanlima.springrestapi.model.Estoque;
import com.jeanlima.springrestapi.model.Produto;

@Service
public interface EstoqueService {

  List<Estoque> buscarEstoques();
  Estoque buscarEstoquePorId(Integer id);
  Estoque buscarEstoquePorProduto(Produto produto);
  Estoque salvar(Estoque estoque);
  Estoque editarQuantidade(Estoque estoque, Integer quantidade);
  void remover(Estoque estoque);

}
