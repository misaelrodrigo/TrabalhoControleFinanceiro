package br.edu.integrado.trabalhomisaelrodrigo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Handler;

import br.edu.integrado.trabalhomisaelrodrigo.dao.UsuarioDao;
import br.edu.integrado.trabalhomisaelrodrigo.model.Usuario;
import br.edu.integrado.trabalhomisaelrodrigo.util.Validacoes;


public class CadUsuarioActivity extends ActionBarActivity {
    private Validacoes validacao;
    private EditText edtEmail;
    private EditText edtUsuario;
    private EditText edtSenha;
    private EditText edtSenha2;

    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);


        edtEmail = (EditText) findViewById(R.id.edtNewEmail);
        edtUsuario = (EditText) findViewById(R.id.edtNewUsuario);
        edtSenha = (EditText) findViewById(R.id.edtNewSenha);
        edtSenha2 = (EditText) findViewById(R.id.edtNewSenha2);

        validacao = new Validacoes();

        usuarioDao = new UsuarioDao(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cad_usuario, menu);
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
            case R.id.btnCadUsuarioSalvar   :
                onClickSalvar();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public Usuario getUsuario(){
        return new Usuario(edtEmail.getText().toString(),edtUsuario.getText().toString(), edtSenha.getText().toString() );
    }

    public void onClickSalvar() {
        String mensagem = "";
        //Esconde Teclado ao pedir para salvar.
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.edtNewSenha2).getWindowToken(), 0);

        if (validacao.verificaSenha(edtSenha.getText().toString(), edtSenha2.getText().toString()) == true) {
            mensagem = usuarioDao.insert(getUsuario());
        } else {
           mensagem = "Senha não corresponde à confirmação";
        }

        Toast toast = Toast.makeText(this, mensagem, Toast.LENGTH_LONG);
        toast.show();
        if(mensagem.equals("Usuario cadastrado com sucesso!!")){
            Intent i = new Intent(this.getBaseContext(), MainActivity.class); // cria a intensao de abir um nova activi...
            startActivity(i);
        }
    }

}
