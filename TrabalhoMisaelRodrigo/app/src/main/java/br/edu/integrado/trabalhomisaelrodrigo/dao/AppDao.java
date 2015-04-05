package br.edu.integrado.trabalhomisaelrodrigo.dao;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.edu.integrado.trabalhomisaelrodrigo.R;

/**
 * Created by Misael-Ticiane on 03/04/2015.
 */
public class AppDao extends SQLiteOpenHelper{

    private static final String BD_NAME = "ControleFinaceiro";
    private static final int BD_VERSION = 1;
    private Resources res;

    public AppDao(Context context){
        super(context, BD_NAME, null, BD_VERSION);
        res = context.getResources();//pŕa pegar os valores em string.

        /*getWritableDatabase().execSQL(res.getString(R.string.SQL_DROP_DESPESA));
        getWritableDatabase().execSQL(res.getString(R.string.SQL_DROP_FORMAPAG));
        getWritableDatabase().execSQL(res.getString(R.string.SQL_DROP_USUARIO));
        onCreate(getWritableDatabase());*/

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(res.getString(R.string.SQL_CREATE_USUARIO));
        db.execSQL(res.getString(R.string.SQL_CREATE_FORMAPAG));
        db.execSQL(res.getString(R.string.SQL_CREATE_DESPESA));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
