package com.example.rafael.encontrefacildois;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rafael.encontrefacildois.Model.UsuarioModel;
import com.google.gson.Gson;


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

        Toast.makeText(this,usuario.toString(),Toast.LENGTH_LONG).show();

       new CadastroUsuario().execute();
    }

    private class CadastroUsuario extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            InputStream inputS = null;
            Gson gson = new Gson();

            try {
                URL url = new URL("http://104.198.246.43/EncontreFacilWs/rest/Usuario/Cadastrar");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                String jsonUsuario = gson.toJson(usuario);

                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(jsonUsuario.toString());
                writer.flush();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            }catch (Exception e){
                e.printStackTrace();
                Log.e("Error", "Error ", e);
                return null;
            }finally {
                urlConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {


        }
    }
}