package br.edu.integrado.trabalhomisaelrodrigo.model;

import java.io.Serializable;

/**
 * Created by Misael-Ticiane on 03/04/2015.
 */
public class Usuario implements Serializable {
    private Integer id;
    private String email;
    private String usuario;
    private String senha;

    public Usuario(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }
    public Usuario(Integer id, String usuario){
        this.id = id;
        this.usuario = usuario;
    }

    public Usuario(String email, String usuario, String senha){
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
