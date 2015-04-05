package br.edu.integrado.trabalhomisaelrodrigo.util;

/**
 * Created by Misael-Ticiane on 03/04/2015.
 */
public class Validacoes {

    public boolean verificaEmail(String email){
        //implementar verificação: de espacos e se depois do '@' existe um '.'
        if(email.indexOf('@') > 0) return true;
        else return false;
    }

    public boolean verificaSenha(String senha1, String senha2){
        if(senha1.equals(senha2)) return true;
        else return false;
    }

}
