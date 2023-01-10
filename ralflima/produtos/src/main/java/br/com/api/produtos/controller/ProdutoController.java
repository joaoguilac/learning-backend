package br.com.api.produtos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.produtos.model.ProdutoModel;
import br.com.api.produtos.model.RespostaModel;
import br.com.api.produtos.service.ProdutoService;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoService ps;

	@DeleteMapping("/remover/{codigo}")
	public ResponseEntity<RespostaModel> remover(@PathVariable Long codigo) {
		return ps.remover(codigo);
	}

	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody ProdutoModel pm) {
		return ps.cadastrarAlterar(pm, "alterar");
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody ProdutoModel pm) {
		return ps.cadastrarAlterar(pm, "cadastrar");
	}
	
	@GetMapping("/listar")
	public Iterable<ProdutoModel> listar() {
		return ps.listar();
	}

	@GetMapping("/")
	public String rota() {
		return "API de produtos funcionando!";
	}
}
