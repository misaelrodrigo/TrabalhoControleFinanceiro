package br.edu.integrado.trabalhomisaelrodrigo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.integrado.trabalhomisaelrodrigo.dao.FormaPagDao;
import br.edu.integrado.trabalhomisaelrodrigo.model.FormaPag;
import br.edu.integrado.trabalhomisaelrodrigo.model.Usuario;


public class CadPagamentoActivity extends ActionBarActivity {
    private EditText edtDescricao;
    private Usuario usuarioLogado;
    FormaPagDao formaPagDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_pagamento);


        usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuario");

        edtDescricao =(EditText) findViewById(R.id.edtNewFormaPag);

        formaPagDao = new FormaPagDao(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cad_pagamento, menu);
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
            case R.id.btnCadFormaPagSalvar:
                onClickSalvar();
                break;
            case R.id.btnCadFormaPagCancelar:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public FormaPag getFormaPag(){
        return new FormaPag(edtDescricao.getText().toString());

    }

    public void onClickSalvar() {
        String mensagem = "";
        //Esconde Teclado ao pedir para salvar.
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.edtNewFormaPag).getWindowToken(), 0);

        mensagem = formaPagDao.insert(getFormaPag());

        Toast toast = Toast.makeText(this, mensagem, Toast.LENGTH_LONG);
        toast.show();
        if(mensagem.equals(this.getString(R.string.MsgCadFormaSucesso))){
            Intent i = new Intent(this.getBaseContext(), TelaDespesaActivity.class); // cria a intensao de abir um nova activi...
            i.putExtra("usuario", usuarioLogado);
            startActivity(i);
        }
    }
}
