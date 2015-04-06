package br.edu.integrado.trabalhomisaelrodrigo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        Date dataAtual = despesa.getData().getTime();
        SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ContentValues cv = new ContentValues();

        cv.put(TB_FIELDS[1], despesa.getDescricao());
        cv.put(TB_FIELDS[2], despesa.getValor());
        //cv.put(TB_FIELDS[3], despesa.getData());
        cv.put(TB_FIELDS[3], formata.format(dataAtual));
        cv.put(TB_FIELDS[4], despesa.getFormaPag());
        cv.put(TB_FIELDS[5], despesa.getUsuario());
        getWritableDatabase().insert(TB_NAME, null, cv);
        return  context.getString(R.string.MsgCadDespesaSucesso);
    }

    public List<Despesa> listDespesa(Usuario usuario, Date dataReferencia) {

        SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd HH:mm");//Cria a mascara de data
        Calendar calendario = Calendar.getInstance(); //cria um calendario
        calendario.setTime(dataReferencia); //seta a data de referencia no calendario
        calendario.set(Calendar.DAY_OF_MONTH, 1); //volta o calendario para o dia 1.
        // Seta a hora para 00:00:00
        calendario.set(Calendar.HOUR,0);
        calendario.set(Calendar.MINUTE,0);
        calendario.set(Calendar.SECOND,0);

        dataReferencia = calendario.getTime(); //pega a data do calendario, agora no 1 e as 00:00:00
        String strInicial = formata.format(dataReferencia); //passa para uma string com base na mascara
        calendario = GregorianCalendar.getInstance(); //modifica o calendario pra greorian pra saber o ultimo dia do mes.
        calendario.setTime(dataReferencia);//seta a data no calendario grego....
        // seta o calendario no ultimo do mes
        calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));

        dataReferencia = calendario.getTime();// pega a data do calendario, agora já no ultimo dia do mes.
        String strFinal = formata.format(dataReferencia); // passa para uma string com base na mascara.

        List<Despesa> despesas = new ArrayList<>();
        //efetua a pesquisa no banco usando o usuario e a data inicial e final.(select foi modificado)
        Cursor list = getReadableDatabase().rawQuery(context.getString(R.string.SQL_SELECT_DESPESA), new String[]{usuario.getId().toString(), strInicial, strFinal});
        while(list.moveToNext()){
            try {

                Date data = formata.parse(list.getString(2)); //pega a data em string e passa para Date
                calendario.setTime(data); // converte Date em Calendar.

                despesas.add(new Despesa(list.getString(0),list.getString(1), calendario, list.getFloat(3)));
            }catch (Exception e){}
        }
        list.close();
        return despesas;
    }

    public Float listDespesaMes(Usuario usuario , Date dataReferencia) {
        Double valorTotal = 0.0;
        //retorna lista com base no usuario e na data solicitada.
        List<Despesa> despesas = this.listDespesa(usuario,dataReferencia);
        Despesa despesa;

        //percorre o array List para somar valores.
        for( int i = 0; i <despesas.size();i++){
            despesa = despesas.get(i);
            valorTotal += despesa.getValor();
        }
        //retorna soma.
        return  valorTotal.floatValue();
    }


}
