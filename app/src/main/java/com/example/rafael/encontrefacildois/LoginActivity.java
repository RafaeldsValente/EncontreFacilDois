package com.example.rafael.encontrefacildois;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rafael.encontrefacildois.Model.UsuarioModel;
import com.example.rafael.encontrefacildois.Util.Util;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends Activity {

    EditText editUsuario;
    EditText editSenha;

    static String usuario;
    static String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editSenha = (EditText) findViewById(R.id.txtSenha);
        editUsuario = (EditText) findViewById(R.id.txtUsuario);

    }

    public void Logar(View v){
        usuario = editUsuario.getText().toString();
        senha = editSenha.getText().toString();

        if(!usuario.isEmpty() &&  !senha.isEmpty())
        {
            new DownloadFromMyAPI().execute();

        }else{
            Toast.makeText(this, "Digite seu email e senha!", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, "usuario: " + usuario + " e senha: " + senha, Toast.LENGTH_LONG).show();
    }

    public void StartTelaCadastro(View v) {

        Intent c = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(c);
    }

    private class DownloadFromMyAPI extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String contentAsString = null;
            HttpURLConnection urlConnection = null;
            InputStream is = null;
            int length = 500;

            try {
                URL url = new URL("http://104.198.246.43/EncontreFacilWs/rest/Usuario/VerificarUsuario/" + usuario + "/" + senha);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.connect();

                int response = urlConnection.getResponseCode();
                is = urlConnection.getInputStream();

                // Convert the InputStream into a string
                contentAsString = Util.webToString(is);

                JSONObject mainObject = new JSONObject(contentAsString);
                String msg = mainObject.getString("mensagem");

            } catch (Exception e) {
                Log.e("Error", "Error ", e);
               return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return contentAsString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            UsuarioModel usuarioModel = Util.ConvertJsonToUsuario(s);
            if(usuarioModel != null){
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("Usuario",usuarioModel.getNome());
                i.putExtra("Email",usuarioModel.getEmail());
                startActivity(i);
            }else{
                Toast.makeText(LoginActivity.this, "Usuario nao consta no sistema!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
