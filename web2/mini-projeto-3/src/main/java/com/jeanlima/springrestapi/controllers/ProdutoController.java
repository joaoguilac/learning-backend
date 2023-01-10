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

import com.jeanlima.springrestapi.model.Produto;
import com.jeanlima.springrestapi.service.ProdutoService;
import com.jeanlima.springrestapi.service.impl.ProdutoServiceImpl;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoService produtoService;
    
    @Autowired
    public ProdutoController(ProdutoServiceImpl produtoService) {
    	this.produtoService = produtoService;
    }
    
    @GetMapping
    public List<Produto> getProdutos() {
        return produtoService.buscarProdutos();
    }
    
    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @PutMapping("{id}")
    public Produto update(@PathVariable Integer id, @RequestBody Produto produto) {
    	Produto produtoExistente = produtoService.buscarProdutoPorId(id);
    	produto.setId(produtoExistente.getId());
    	return produtoService.salvar(produto);
    }
    
    @PatchMapping("{id}")
    public Produto patch(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
    	Produto produto = produtoService.buscarProdutoPorId(id);
    	return produtoService.editar(produto, fields);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
    	Produto produto = produtoService.buscarProdutoPorId(id);
    	produtoService.remover(produto);
    }
}
