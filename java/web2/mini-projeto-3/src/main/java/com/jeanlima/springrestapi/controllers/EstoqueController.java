package com.jeanlima.springrestapi.controllers;

import java.util.List;

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

import com.jeanlima.springrestapi.model.Estoque;
import com.jeanlima.springrestapi.model.Produto;
import com.jeanlima.springrestapi.service.EstoqueService;
import com.jeanlima.springrestapi.service.ProdutoService;
import com.jeanlima.springrestapi.service.impl.EstoqueServiceImpl;
import com.jeanlima.springrestapi.service.impl.ProdutoServiceImpl;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    
    private EstoqueService estoqueService;
    private ProdutoService produtoService;
    
    @Autowired
    public EstoqueController(EstoqueServiceImpl estoqueService, ProdutoServiceImpl produtoService) {
      this.estoqueService = estoqueService;
      this.produtoService = produtoService;
    }
    
    @GetMapping
    public List<Estoque> getEstoques() {
        return estoqueService.buscarEstoques();
    }

    @GetMapping("{nomeProduto}")
    public Estoque filterEstoqueNomeProduto(@PathVariable String nomeProduto) {
        Produto produto = produtoService.buscarProdutoPorNome(nomeProduto);
        return estoqueService.buscarEstoquePorProduto(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estoque save(@RequestBody Estoque estoque) {
        return estoqueService.salvar(estoque);
    }
    
    @PutMapping("{id}")
    public Estoque update(@PathVariable Integer id, @RequestBody Estoque estoque) {
        Estoque estoqueExistente = estoqueService.buscarEstoquePorId(id);
        estoque.setId(estoqueExistente.getId());
        return estoqueService.salvar(estoque);
    }
    
    @PatchMapping("{id}")
    public Estoque patch(@PathVariable Integer id, @RequestBody Integer quantidade) {
        Estoque estoque = estoqueService.buscarEstoquePorId(id);
        return estoqueService.editarQuantidade(estoque, quantidade);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Estoque estoque = estoqueService.buscarEstoquePorId(id);
        estoqueService.remover(estoque);
    }
    
}
