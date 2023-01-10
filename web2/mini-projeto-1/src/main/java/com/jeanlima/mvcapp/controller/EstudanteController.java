package com.jeanlima.mvcapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanlima.mvcapp.model.Curso;
import com.jeanlima.mvcapp.model.Estudante;
import com.jeanlima.mvcapp.service.CursoService;
import com.jeanlima.mvcapp.service.EstudanteService;

@Controller
@RequestMapping("/estudante")
public class EstudanteController {


    @Autowired
    @Qualifier("estudanteServiceImpl")
    EstudanteService estudanteService;
    
    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;

    @RequestMapping(value = "/showForm")
    public String showFormEstudante(Model model) {
        model.addAttribute("estudante", new Estudante());
        List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("cursos", cursos);
        return "estudante/formEstudante";
    }

    @RequestMapping(value = "/addEstudante")
    public String showAddEstudante(@ModelAttribute("estudante") Estudante estudante,  Model model) {
        estudanteService.salvarEstudante(estudante);
        model.addAttribute("estudante", estudante);
        return "estudante/paginaEstudante";
    }

    @RequestMapping(value = "/getListaEstudantes")
    public String showListaEstudante(Model model) {
        List<Estudante> estudantes = estudanteService.getListaEstudante();
        model.addAttribute("estudantes", estudantes);
        return "estudante/listaEstudantes";
    }
    
    @RequestMapping(value = "/detalhesEstudante/{id}")
    public String showDetalhesEstudante(@PathVariable int id, Model model) {
    	Estudante estudante = estudanteService.getEstudanteById(id);
    	model.addAttribute("estudante", estudante);
        return "estudante/estudanteDetalhes";
    }

    @RequestMapping(value = "/deleteEstudante/{id}")
    public String showDeleteEstudante(@PathVariable int id, Model model) {
    	Estudante estudante = estudanteService.getEstudanteById(id);
        estudanteService.deletarEstudante(estudante);
        List<Estudante> estudantes = estudanteService.getListaEstudante();
        model.addAttribute("estudantes", estudantes);
        return "estudante/listaEstudantes";
    }
    
    @RequestMapping(value = "/estudantesCurso")
    public String showEstudantesCurso(Model model) {
    	List<Curso> cursos = cursoService.getListaCurso();
        List<Estudante> estudantes = estudanteService.getEstudantesPorCurso(null);
        model.addAttribute("estudantes", estudantes);
    	return "estudante/estudantesCurso";
    }
    
    @RequestMapping(value = "/estudantesLinguagem")
    public String showEstudantesLinguagem(Model model) {
    	List<Estudante> estudantes = estudanteService.getEstudantesPorLinguagem(null);
        model.addAttribute("estudantes", estudantes);
    	return "estudante/estudantesLinguagem";
    }
    
}
