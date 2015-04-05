package br.edu.integrado.trabalhomisaelrodrigo.model;

import java.io.Serializable;

/**
 * Created by Misael-Ticiane on 04/04/2015.
 */
public class FormaPag implements Serializable{
    private Integer id;
    private String descricao;

    public FormaPag(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
    public FormaPag( String descricao){
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.toUpperCase();
    }

    @Override
    public String toString() {
        return  this.descricao;
    }
}
