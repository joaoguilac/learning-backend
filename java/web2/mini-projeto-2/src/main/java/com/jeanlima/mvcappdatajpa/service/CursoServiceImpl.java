package com.jeanlima.mvcappdatajpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanlima.mvcappdatajpa.model.Curso;
import com.jeanlima.mvcappdatajpa.repository.CursoRepository;

@Component
public class CursoServiceImpl implements CursoService {
	
	@Autowired
	CursoRepository cursoRepository;
	
	@Override
	public void salvarCurso(Curso curso) {
		cursoRepository.save(curso);
	}
	
	@Override
	public void deletarCurso(Curso curso) {
		cursoRepository.delete(curso);
	}
	
	@Override
	public void atualizarCurso(Curso curso) {
		cursoRepository.atualizarCurso(curso.getDescricao(), curso.getId());
	}
	
	@Override
	public Curso getCursoById(Integer id) {
		return cursoRepository.findById(id).map(curso -> {
            return curso;
        }).orElseThrow(() -> null);
	}
	
	@Override
	public List<Curso> getListaCurso() {
		return cursoRepository.findAll();
	}

}
