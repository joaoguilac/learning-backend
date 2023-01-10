package com.jeanlima.mvcapp.service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeanlima.mvcapp.model.Curso;
import com.jeanlima.mvcapp.model.Estudante;

@Component
public class EstudanteServiceImpl implements EstudanteService {

    public List<Estudante> estudantes = new ArrayList<Estudante>();

    @Override
    public void salvarEstudante(Estudante estudante) {
        System.out.println(estudante.toString());
        try {
        	estudante.setId(this.estudantes.size()+1);
            this.estudantes.add(estudante);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    @Override
    public void deletarEstudante(Estudante estudante) {
    	try {
    		this.estudantes.remove(estudante);			
		} catch (Exception e) {
			e.printStackTrace();
            System.out.println(e.toString());
		}
    }

    @Override
    public Estudante getEstudanteById(Integer id) {
        for (Estudante estudante : estudantes) {
            if (estudante.getId() == id) {
                return estudante;
            }
        }
        return null;
    }

    @Override
    public List<Estudante> getListaEstudante() {
        return this.estudantes;
    }

    @Override
    public List<Estudante> getEstudantesPorCurso(String curso) {
		List<Estudante> estudantesCurso = new ArrayList<Estudante>();
		for (Estudante estudante : estudantes) {
            if (estudante.getCurso() == curso) {
                estudantesCurso.add(estudante);
            }
        }
    	return estudantesCurso;
    }
    
    @Override
    public List<Estudante> getEstudantesPorLinguagem(String linguagem) {
    	List<Estudante> estudantesLinguagem = new ArrayList<Estudante>();
		for (Estudante estudante : estudantes) {
            if (estudante.getLinguagem() == linguagem) {
            	estudantesLinguagem.add(estudante);
            }
        }
    	return estudantesLinguagem;
    }
    
}
