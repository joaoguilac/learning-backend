package com.jeanlima.mvcappdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanlima.mvcappdatajpa.model.Curso;
import com.jeanlima.mvcappdatajpa.service.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	@Qualifier("cursoServiceImpl")
	CursoService cursoService;
	
	@RequestMapping("/showForm")
	public String showFormCurso(Model model) {
		model.addAttribute("curso", new Curso());
		return "curso/formCurso";
	}
	
	@RequestMapping("/addCurso")
	public String showFormCurso(@ModelAttribute("Curso") Curso curso, Model model) {
		cursoService.salvarCurso(curso);
		List<Curso> cursos = cursoService.getListaCurso();
		model.addAttribute("cursos", cursos);
		return "curso/listaCursos";
	}
	
	@RequestMapping(value = "/deleteCurso/{id}")
    public String showDeleteCurso(@PathVariable Integer id, Model model) {
    	Curso curso = cursoService.getCursoById(id);
        cursoService.deletarCurso(curso);
        List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("cursos", cursos);
        return "curso/listaCursos";
    }
	
	@RequestMapping(value = "/showFormUpdateCurso/{id}")
    public String showFormUpdateCurso(@PathVariable Integer id, Model model) {
		Curso curso = cursoService.getCursoById(id);
        model.addAttribute("curso", curso);
        return "curso/formUpdateCurso";
    }
	
	@RequestMapping("/showFormUpdate")
    public String showFormUpdateCurso(@ModelAttribute("curso") Curso curso, Model model) {
		cursoService.atualizarCurso(curso);
		List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("cursos", cursos);
        return "curso/listaCursos";
    }
	
	@RequestMapping("/getListaCursos")
	public String showListaCurso(Model model) {
		List<Curso> cursos = cursoService.getListaCurso();
		model.addAttribute("cursos", cursos);
		return "curso/listaCursos";
	}

}
