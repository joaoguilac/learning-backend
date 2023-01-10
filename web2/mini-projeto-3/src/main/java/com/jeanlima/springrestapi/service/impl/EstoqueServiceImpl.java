package com.jeanlima.springrestapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.springrestapi.model.Estoque;
import com.jeanlima.springrestapi.model.Produto;
import com.jeanlima.springrestapi.repository.EstoqueRepository;
import com.jeanlima.springrestapi.service.EstoqueService;

@Service
public class EstoqueServiceImpl implements EstoqueService {
  
    @Autowired
    EstoqueRepository estoqueRepository;
    
    @Override
    public List<Estoque> buscarEstoques() {
        return estoqueRepository.findAll();
    }
    
    @Override
    public Estoque buscarEstoquePorId(Integer id) {
        return estoqueRepository.findById(id)
               .orElseThrow(() ->
               new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Estoque não encontrado"));
    }
    
    @Override
    public Estoque buscarEstoquePorProduto(Produto produto) {
        return estoqueRepository.findByProduto(produto);
    }
    
    @Override
    public Estoque salvar(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }
    
    @Override
    public Estoque editarQuantidade(Estoque estoque, Integer quantidade) {
        estoque.setQuantidade(quantidade);
        return salvar(estoque);
    }
    
    @Override
    public void remover(Estoque estoque) {
        estoqueRepository.delete(estoque);
    }
    
}
