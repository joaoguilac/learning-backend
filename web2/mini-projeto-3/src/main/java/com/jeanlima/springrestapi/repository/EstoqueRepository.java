package com.jeanlima.springrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.springrestapi.model.Estoque;
import com.jeanlima.springrestapi.model.Produto;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    Estoque findByProduto(Produto produto);
  
}
