package br.edu.integrado.trabalhomisaelrodrigo.model;

import java.io.Serializable;

/**
 * Created by Misael-Ticiane on 04/04/2015.
 */
public class Despesa implements Serializable{
    private Integer id;
    private String descricao;
    private String dsForma;
    private Float valor;
    private String data;
    private Integer usuario;
    private Integer formaPag;

    public Despesa(String descricao, Float valor, String data, Integer usuario, Integer formaPag){
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.usuario = usuario;
        this.formaPag = formaPag;
    }
    public Despesa(String descricao, String dsForma, String data, Float valor){
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.dsForma = dsForma;
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
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(Integer formaPag) {
        this.formaPag = formaPag;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String linha = "$d  |$f |$v |$b ";
        linha = linha.replace("$d", this.descricao);
        linha = linha.replace("$f", this.dsForma);
        linha = linha.replace("$v", this.valor.toString());
        linha = linha.replace("$b", this.data);
        //return this.descricao + "| " + this.dsForma+" | "+this.valor.toString()+" | "+this.data;
        return linha;

    }
}
