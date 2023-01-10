package com.jeanlima.mvcappdatajpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanlima.mvcappdatajpa.model.Curso;

@Service
public interface CursoService {
	
	public void salvarCurso(Curso curso);
	public void deletarCurso(Curso curso);
	public void atualizarCurso(Curso curso);
	public Curso getCursoById(Integer id);
	public List<Curso> getListaCurso();
	
}
