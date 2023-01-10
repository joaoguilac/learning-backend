package com.jeanlima.mvcappdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanlima.mvcappdatajpa.model.Curso;
import com.jeanlima.mvcappdatajpa.model.Estudante;
import com.jeanlima.mvcappdatajpa.service.CursoService;
import com.jeanlima.mvcappdatajpa.service.EstudanteService;


@Controller
@RequestMapping("/estudante")
public class EstudanteController {

    @Autowired
    @Qualifier("estudanteServiceImpl")
    EstudanteService estudanteService;
    
    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;

    @RequestMapping("/showForm")
    public String showFormEstudante(Model model){
    	List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("estudante", new Estudante());
        model.addAttribute("cursos", cursos);
        return "estudante/formEstudante";
    }

    @RequestMapping("/addEstudante")
    public String showFormEstudante(@ModelAttribute("estudante") Estudante estudante,  Model model){
        estudanteService.salvarEstudante(estudante);
        List<Estudante> estudantes = estudanteService.getListaEstudante();
        model.addAttribute("estudantes",estudantes);
        return "estudante/listaEstudantes";
    }
    
    @RequestMapping(value = "/deleteEstudante/{id}")
    public String showDeleteEstudante(@PathVariable int id, Model model) {
    	Estudante estudante = estudanteService.getEstudanteById(id);
        estudanteService.deletarEstudante(estudante);
        List<Estudante> estudantes = estudanteService.getListaEstudante();
        model.addAttribute("estudantes",estudantes);
        return "estudante/listaEstudantes";
    }

    @RequestMapping("/getListaEstudantes")
    public String showListaEstudante(Model model){
        List<Estudante> estudantes = estudanteService.getListaEstudante();
        model.addAttribute("estudantes",estudantes);
        return "estudante/listaEstudantes";
    }

    @GetMapping("/getEstudanteById/{id}")
    public String getEstudanteById( @PathVariable Integer id, Model model){
        Estudante estudante = estudanteService.getEstudanteById(id);
        model.addAttribute("estudante",estudante);
        return "estudante/paginaEstudante";
    }
    
}
