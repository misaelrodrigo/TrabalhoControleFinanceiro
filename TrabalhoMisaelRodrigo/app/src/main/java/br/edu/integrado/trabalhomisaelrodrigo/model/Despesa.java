package br.edu.integrado.trabalhomisaelrodrigo.model;

import android.provider.ContactsContract;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Misael-Ticiane on 04/04/2015.
 */
public class Despesa implements Serializable{
    private Integer id;
    private String descricao;
    private String dsForma;
    private Float valor;
    private Calendar data;
    private Integer usuario;
    private Integer formaPag;

    public Despesa(String descricao, Float valor, Calendar data, Integer usuario, Integer formaPag){
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.usuario = usuario;
        this.formaPag = formaPag;
    }
    public Despesa(String descricao, String dsForma, Calendar data, Float valor){
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

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    @Override
    public String toString() {
        SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
        Date data = this.data.getTime();

        String linha = "Despesa: $d  ($f)\n R$ $v        Data:$b \n---------------------------------------------------------------- ";
        linha = linha.replace("$d", this.descricao);
        linha = linha.replace("$f", this.dsForma);
        linha = linha.replace("$v", this.valor.toString());
        linha = linha.replace("$b", formata.format(data));
        //return this.descricao + "| " + this.dsForma+" | "+this.valor.toString()+" | "+this.data;
        return linha;

    }
}
