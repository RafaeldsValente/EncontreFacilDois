package com.example.rafael.encontrefacildois;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rafael.encontrefacildois.Model.UsuarioModel;

public class CadastroActivity extends Activity {

    EditText editNome;
    EditText editSobrenome;
    EditText editEmail;
    EditText editSenha;

    static String nome;
    static String sobrenome;
    static String email;
    static String senha;

    private UsuarioModel usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editNome = (EditText) findViewById(R.id.editNome);
        editSobrenome = (EditText) findViewById(R.id.editSobrenome);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
    }

    public void Cadastrar(View v){

        usuario = new UsuarioModel();

        nome = editNome.getText().toString();
        sobrenome = editSobrenome.getText().toString();
        email = editEmail.getText().toString();
        senha = editSenha.getText().toString();

        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        //Toast.makeText(this,"Nome: " + nome + "\nSobrenome: " + sobrenome + "\nEmail: " + email + "\nSenha: " + senha,Toast.LENGTH_LONG).show();
        Toast.makeText(this,usuario.toString(),Toast.LENGTH_LONG).show();
    }
}