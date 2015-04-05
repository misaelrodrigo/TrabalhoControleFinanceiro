package br.edu.integrado.trabalhomisaelrodrigo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.edu.integrado.trabalhomisaelrodrigo.R;
import br.edu.integrado.trabalhomisaelrodrigo.model.FormaPag;

/**
 * Created by Misael-Ticiane on 04/04/2015.
 */
public class FormaPagDao extends AppDao {
    private static final String TB_NAME = "FormaPag";
    private static final String[] TB_FIELDS = new String[]{"id", "descricao"};
    private Context context;

    public FormaPagDao(Context context) {
        super(context);
        this.context = context;
    }

    public String insert(FormaPag formaPag){
        ContentValues cv = new ContentValues();

        if(!verificaNovaForma(formaPag.getDescricao())) return context.getString(R.string.MsgCadFormaJaExiste);

        cv.put(TB_FIELDS[1], formaPag.getDescricao());
        getWritableDatabase().insert(TB_NAME, null, cv);

        return  context.getString(R.string.MsgCadFormaSucesso);
    }

    public boolean verificaNovaForma(String forma){
        String lineSql = "SELECT * FROM "+TB_NAME + " WHERE "+ TB_FIELDS[1] +"=?";
        Cursor query = getReadableDatabase().rawQuery(lineSql, new String[]{forma});
        if(query.getCount() == 0) return true;
        else return false;
    }

    public List<FormaPag> listForma(){
        Cursor c = getReadableDatabase().query(TB_NAME,
                new String[]{TB_FIELDS[0], TB_FIELDS[1]}, null, null, null, null, null);

        List<FormaPag> formas = new ArrayList<>();

        while(c.moveToNext()){
            formas.add(new FormaPag(c.getInt(0),c.getString(1)));
            //formas.add(new FormaPag(c.getInt(0),c.getString(1)));
            //formas.add(new FormaPag(c.getInt(c.getColumnIndex(TB_FIELDS[0])),c.getString(c.getColumnIndex(TB_FIELDS[1]))));
        }
        c.close();
        return formas;
    }
}
