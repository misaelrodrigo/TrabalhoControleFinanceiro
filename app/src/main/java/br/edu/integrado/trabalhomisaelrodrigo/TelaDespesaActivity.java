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
import java.util.List;

import br.edu.integrado.trabalhomisaelrodrigo.dao.DespesaDao;
import br.edu.integrado.trabalhomisaelrodrigo.model.Despesa;
import br.edu.integrado.trabalhomisaelrodrigo.model.Usuario;


public class TelaDespesaActivity extends ActionBarActivity {
    private TextView tvSaudacao;
    private Usuario usuarioLogado;
    DespesaDao despesaDao;
    ListView lvDespesas;
    List<Despesa> listDespesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_despesa);

        usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuario");
        tvSaudacao = (TextView) findViewById(R.id.tvSaudacao);
        //Intent i = getIntent();
       // usuarioLogado = i.getStringExtra("usuario");

        tvSaudacao.setText("Bem Vindo, " + usuarioLogado.getUsuario().toUpperCase());

    }

    @Override
    protected void onResume() {
        super.onResume();
        lvDespesas = (ListView)findViewById(R.id.lvDespesas);
        despesaDao = new DespesaDao(this);
        listDespesa = despesaDao.listDespesa(usuarioLogado);
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
