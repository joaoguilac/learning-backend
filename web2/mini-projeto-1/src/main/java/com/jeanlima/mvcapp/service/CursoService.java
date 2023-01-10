package com.jeanlima.mvcapp.service;

import java.util.List;

import com.jeanlima.mvcapp.model.Curso;

public interface CursoService {
	
	public void salvarCurso(Curso curso);
    public List<Curso> getListaCurso();
    
}
