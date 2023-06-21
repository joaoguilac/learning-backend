package com.jeanlima.mvcapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanlima.mvcapp.model.Curso;
import com.jeanlima.mvcapp.service.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

	@Autowired
    @Qualifier("cursoServiceImpl")
	CursoService cursoService;
	
	@RequestMapping(value = "/showForm")
    public String showFormCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso/formCurso";
    }

    @RequestMapping(value = "/addCurso")
    public String showAddCurso(@ModelAttribute("curso") Curso curso,  Model model) {
        cursoService.salvarCurso(curso);
        List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("cursos", cursos);
        return "curso/listaCursos";
    }

    @RequestMapping(value = "/getListaCursos")
    public String showListaCurso(Model model) {
        List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("cursos", cursos);
        return "curso/listaCursos";
    }
}
