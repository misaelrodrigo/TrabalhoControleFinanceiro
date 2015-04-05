package br.edu.integrado.trabalhomisaelrodrigo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.integrado.trabalhomisaelrodrigo.dao.AppDao;
import br.edu.integrado.trabalhomisaelrodrigo.dao.UsuarioDao;
import br.edu.integrado.trabalhomisaelrodrigo.model.Usuario;


public class MainActivity extends ActionBarActivity {
    private EditText edtUsuario;
    private EditText edtSenha;
    UsuarioDao usuarioDao;
    AppDao appDao;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtPassword);

        usuarioDao = new UsuarioDao(this);
        appDao = new AppDao(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.btnCadUsuario:
                Intent i = new Intent(this, CadUsuarioActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickBtnLogin(View v){
        //Esconde o teclado ao tocar em login
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.edtPassword).getWindowToken(), 0);

        usuario = this.getUsuario();

        if(usuarioDao.verificaLogin(usuario)){
            Usuario usuario2 = usuarioDao.retornadados(usuario);
            Intent i = new Intent(this, TelaDespesaActivity.class); // cria a intensao de abir um nova activi...
            i.putExtra("usuario", usuario2);
            startActivity(i);
        }else{
            Toast toast = Toast.makeText(this, "Falha no Login, usuario ou senha invalido!!!", Toast.LENGTH_LONG);
            toast.show();
        }


    }

    public Usuario getUsuario(){
        return new Usuario(edtUsuario.getText().toString(), edtSenha.getText().toString());
    }

}
