package br.edu.integrado.trabalhomisaelrodrigo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.edu.integrado.trabalhomisaelrodrigo.R;
import br.edu.integrado.trabalhomisaelrodrigo.model.Despesa;
import br.edu.integrado.trabalhomisaelrodrigo.model.Usuario;

/**
 * Created by Misael-Ticiane on 04/04/2015.
 */
public class DespesaDao extends AppDao {
    private static final String TB_NAME = "MovDespesa";
    private static final String[] TB_FIELDS = new String[]{"id", "descricao", "valor","data","formapag", "usuario"};
    private Context context;

    public DespesaDao(Context context) {
        super(context);
        this.context = context;
    }


    public String insert(Despesa despesa){
        ContentValues cv = new ContentValues();

        cv.put(TB_FIELDS[1], despesa.getDescricao());
        cv.put(TB_FIELDS[2], despesa.getValor());
        cv.put(TB_FIELDS[3], despesa.getData());
        cv.put(TB_FIELDS[4], despesa.getFormaPag());
        cv.put(TB_FIELDS[5], despesa.getUsuario());
        getWritableDatabase().insert(TB_NAME, null, cv);
        return  context.getString(R.string.MsgCadDespesaSucesso);
    }



    public List<Despesa> listDespesa(Usuario usuario){

        Cursor list = getReadableDatabase().rawQuery(context.getString(R.string.SQL_SELECT_DESPESA), new String[]{usuario.getId().toString()});

        List<Despesa> despesas = new ArrayList<>();

        while(list.moveToNext()){
            despesas.add(new Despesa(list.getString(0),list.getString(1), list.getString(2), list.getFloat(3)));
        }
        list.close();
        return despesas;
    }
}
