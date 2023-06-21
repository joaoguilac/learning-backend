package com.jeanlima.mvcapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeanlima.mvcapp.model.Curso;

@Component
public class CursoServiceImpl implements CursoService {
	
	public List<Curso> cursos = new ArrayList<Curso>();
	
	@Override
	public void salvarCurso(Curso curso) {
		System.out.println(curso.toString());
		try {
        	curso.setId(this.cursos.size()+1);
            this.cursos.add(curso);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
	}
	
	@Override
    public List<Curso> getListaCurso() {
    	return this.cursos;
    }
}
