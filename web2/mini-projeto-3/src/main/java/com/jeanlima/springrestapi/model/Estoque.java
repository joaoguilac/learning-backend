package com.jeanlima.springrestapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Produto produto;
    
    @Column
    private Integer quantidade;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Produto getProduto() {
      return produto;
    }

    public void setProduto(Produto produto) {
      this.produto = produto;
    }

    public Integer getQuantidade() {
      return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
      this.quantidade = quantidade;
    }
    
}
