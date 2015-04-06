package br.edu.integrado.trabalhomisaelrodrigo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.EditText;

import java.util.List;

import br.edu.integrado.trabalhomisaelrodrigo.R;
import br.edu.integrado.trabalhomisaelrodrigo.model.Usuario;
import br.edu.integrado.trabalhomisaelrodrigo.util.Validacoes;

/**
 * Created by Misael-Ticiane on 03/04/2015.
 */
public class UsuarioDao extends AppDao {

    private static final String TB_NAME = "Usuario";
    private static final String[] TB_FIELDS = new String[]{"id", "email", "usuario", "senha"};
    private Validacoes valida;

    public UsuarioDao(Context context) {
        super(context);
        valida = new Validacoes();
    }

    public String insert(Usuario usuario){
        ContentValues cv = new ContentValues();

        if(!verificaNovoUsuario(usuario.getUsuario())) return "Usuario já cadatrado";
        if(!valida.verificaEmail(usuario.getEmail())) return "E-mail invalido";
        if(!verificaNovoEmail(usuario.getEmail())) return "E-mail já cadatrado";

        cv.put(TB_FIELDS[1], usuario.getEmail());
        cv.put(TB_FIELDS[2], usuario.getUsuario());
        cv.put(TB_FIELDS[3], usuario.getSenha());
        getWritableDatabase().insert(TB_NAME, null, cv);

        return "Usuario cadastrado com sucesso!!";
    }


    public boolean verificaNovoUsuario(String usuario){
        String lineSql = "SELECT * FROM "+TB_NAME + " WHERE "+ TB_FIELDS[2] +"=?";
        Cursor query = getReadableDatabase().rawQuery(lineSql, new String[]{usuario});
        if(query.getCount() == 0) return true;
        else return false;
    }
    public boolean verificaNovoEmail(String email){
        String lineSql = "SELECT * FROM "+TB_NAME + " WHERE "+ TB_FIELDS[1] +"=?";
        Cursor query = getReadableDatabase().rawQuery(lineSql, new String[]{email});
        if(query.getCount() == 0) return true;
        else return false;
    }

    public boolean verificaLogin(Usuario usuario){
        String lineSql = "SELECT * FROM "+TB_NAME +
                " WHERE "+ TB_FIELDS[2] +"=? AND "+ TB_FIELDS[3]+"=?";
        Cursor query = getReadableDatabase().rawQuery(lineSql, new String[]{usuario.getUsuario(), usuario.getSenha()});

        if(query.getCount() == 1) return true;
        else return false;
    }

    public Usuario retornadados(Usuario usuario){
        String lineSql = "SELECT * FROM "+TB_NAME +
                " WHERE "+ TB_FIELDS[2] +"=? AND "+ TB_FIELDS[3]+"=?";
        Cursor query = getReadableDatabase().rawQuery(lineSql, new String[]{usuario.getUsuario(), usuario.getSenha()});
        query.moveToNext();
        Usuario usuarioCompleto = new Usuario(query.getInt(0), query.getString(2));
        return usuarioCompleto;
    }
}
