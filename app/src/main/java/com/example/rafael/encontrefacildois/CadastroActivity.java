package com.example.rafael.encontrefacildois;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rafael.encontrefacildois.Model.Localizacao;
import com.example.rafael.encontrefacildois.Model.Usuario;
import com.example.rafael.encontrefacildois.Model.UsuarioModel;
import com.example.rafael.encontrefacildois.Util.HttpMetods;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroActivity extends Activity {

    EditText editNome;
    EditText editSobrenome;
    EditText editEmail;
    EditText editSenha;

    private String teste;
    static String nome;
    static String sobrenome;
    static String email;
    static String senha;

    private Usuario usuario;

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

        usuario = new Usuario();

        nome = editNome.getText().toString();
        sobrenome = editSobrenome.getText().toString();
        email = editEmail.getText().toString();
        senha = editSenha.getText().toString();

        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        Localizacao localizacao = new Localizacao();
        localizacao.setLongitude("1233");
        localizacao.setLatitude("123");
        usuario.setFk_localizacao(localizacao);

        Toast.makeText(this,usuario.toString(),Toast.LENGTH_LONG).show();

       new CadastroUsuario().execute();
    }

    private class CadastroUsuario extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            InputStream inputS = null;
            Gson gson = new Gson();
            String result = HttpMetods.POST("Usuario/Cadastrar", gson.toJson(usuario));
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null)
            {
                try{
                    JSONObject obj = new JSONObject(s);
                    if(obj.getBoolean("ok"))
                    {
                        Toast.makeText(CadastroActivity.this, obj.getString("mensagem"),Toast.LENGTH_LONG).show();
                        Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
                        startActivity(i);
                    }

                }catch (Exception e)
                {

                }
            }
        }
    }
}