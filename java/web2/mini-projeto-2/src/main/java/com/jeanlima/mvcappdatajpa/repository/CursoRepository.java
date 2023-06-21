package com.jeanlima.mvcappdatajpa.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeanlima.mvcappdatajpa.model.Curso;
import com.jeanlima.mvcappdatajpa.model.Estudante;

public interface CursoRepository extends JpaRepository<Curso,Integer> {
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE curso c SET c.descricao = ?1 WHERE id = ?2", nativeQuery = true)
    void atualizarCurso(String descricao, Integer id);
}
