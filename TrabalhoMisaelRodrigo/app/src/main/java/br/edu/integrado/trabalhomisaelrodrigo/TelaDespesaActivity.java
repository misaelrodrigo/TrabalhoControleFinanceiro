package br.edu.integrado.trabalhomisaelrodrigo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.integrado.trabalhomisaelrodrigo.dao.DespesaDao;
import br.edu.integrado.trabalhomisaelrodrigo.model.Despesa;
import br.edu.integrado.trabalhomisaelrodrigo.model.Usuario;


public class TelaDespesaActivity extends ActionBarActivity {
    private TextView tvSaudacao;
    private Usuario usuarioLogado;
    private ListView lvDespesas;
    private TextView tvTotalAtual;
    private TextView tvTotalAnterior;
    DespesaDao despesaDao;
    List<Despesa> listDespesa;
    Float valorTotalAtual;
    Float valorTotalAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_despesa);


    }

    @Override
    protected void onResume() {
        super.onResume();

        despesaDao = new DespesaDao(this);

        usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuario");
        tvSaudacao = (TextView) findViewById(R.id.tvSaudacao);
        tvTotalAnterior = (TextView)findViewById(R.id.tvSaldoAterior);
        tvTotalAtual = (TextView)findViewById(R.id.tvSaldoAtual);


        tvSaudacao.setText("Bem Vindo, " + usuarioLogado.getUsuario().toUpperCase());

        Calendar calendario = Calendar.getInstance();//instancia com a data atual.
        Date data = calendario.getTime();// pega data do calendario e joga para data.

        ///retorna total de despesas do mes atual com base no usuario e data atual
        valorTotalAtual = despesaDao.listDespesaMes(usuarioLogado,data);

        calendario.add(Calendar.MONTH, -1); //volta o calendario 1 mês.
        data = calendario.getTime(); //pega novamente a data do calendario, agora com do mes passado.

        //retorna total de despesas do mes passado com base no usuario e data mes passado.
        valorTotalAnterior = despesaDao.listDespesaMes(usuarioLogado,data);

        tvTotalAnterior.setText("R$ "+ valorTotalAnterior.toString()); //seta na activity
        tvTotalAtual.setText("R$ "+valorTotalAtual.toString()); //seta na activity

        calendario = Calendar.getInstance(); //pega novamete o calendario com data atual.
        data = calendario.getTime(); //pega a data do calendario.
        lvDespesas = (ListView)findViewById(R.id.lvDespesas);
        listDespesa = despesaDao.listDespesa(usuarioLogado, data ); //lista despesas com base no usuario e data atual.

        ArrayAdapter<Despesa> aaDespesas =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listDespesa);
        lvDespesas.setAdapter(aaDespesas);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_despesa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.btnCadDespesa:
                Intent iDespesa = new Intent(this, CadDespesaActivity.class);
                iDespesa.putExtra("usuario", usuarioLogado);
                startActivity(iDespesa);
                break;
            case R.id.btnCadPagmento:
                Intent iPagamento = new Intent(this, CadPagamentoActivity.class);
                iPagamento.putExtra("usuario", usuarioLogado);
                startActivity(iPagamento);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
