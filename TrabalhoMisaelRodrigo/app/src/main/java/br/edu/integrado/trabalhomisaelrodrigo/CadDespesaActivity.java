package br.edu.integrado.trabalhomisaelrodrigo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.edu.integrado.trabalhomisaelrodrigo.dao.DespesaDao;
import br.edu.integrado.trabalhomisaelrodrigo.dao.FormaPagDao;
import br.edu.integrado.trabalhomisaelrodrigo.model.Despesa;
import br.edu.integrado.trabalhomisaelrodrigo.model.FormaPag;
import br.edu.integrado.trabalhomisaelrodrigo.model.Usuario;


public class CadDespesaActivity extends ActionBarActivity {
    private EditText edtDescrica;
    private EditText edtValor;
    private EditText edtData;
    private Spinner spnForma;
    private Usuario usuarioLogado;
    private List<FormaPag> formas;

    FormaPagDao formaPagDao;
    DespesaDao despesaDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_despesa);

        usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuario");

        edtDescrica = (EditText) findViewById(R.id.edtNewDespesaDescricao);
        edtValor = (EditText) findViewById(R.id.edtNewDespesaValor);
        edtData = (EditText) findViewById(R.id.edtNewDespesaData);
        spnForma = (Spinner) findViewById(R.id.spnFormaPag);

        despesaDao = new DespesaDao(this);
        formaPagDao = new FormaPagDao(this);
        formas = formaPagDao.listForma();
        ArrayAdapter<FormaPag> aaFormas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,formas);

        spnForma.setAdapter(aaFormas);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cad_despesa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.btnCadDespesaSalvar   :
                onClickSalvar();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public Despesa getDespesa(){
        String descricao = edtDescrica.getText().toString();
        Float valor = Float.parseFloat(edtValor.getText().toString());
        String data = edtData.getText().toString();
        Integer id_usuario = usuarioLogado.getId();
        Integer id_forma = ((FormaPag)spnForma.getSelectedItem()).getId();
        return new Despesa(descricao,valor,data,id_usuario,id_forma);
    }

    public void onClickSalvar(){
        String mensagem = "";

        mensagem = despesaDao.insert(getDespesa());

        Toast toast = Toast.makeText(this, mensagem, Toast.LENGTH_LONG);
        toast.show();
        if(mensagem.equals(this.getString(R.string.MsgCadFormaSucesso))){
            Intent i = new Intent(this.getBaseContext(), TelaDespesaActivity.class); // cria a intensao de abir um nova activi...
            i.putExtra("usuario", usuarioLogado);
            startActivity(i);
        }


    }

}
