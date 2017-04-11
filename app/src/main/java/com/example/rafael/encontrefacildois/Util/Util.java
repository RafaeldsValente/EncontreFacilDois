package com.example.rafael.encontrefacildois.Util;

import com.example.rafael.encontrefacildois.Model.LocalizacaoModel;
import com.example.rafael.encontrefacildois.Model.UsuarioModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Rafael on 10/04/2017.
 */

public class Util {

    public static String webToString(InputStream inputStream) {
        InputStream localStream = inputStream;
        String localString = "";
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            localString = writer.toString();
            writer.close();
            reader.close();
            localStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localString;
    }

    public static UsuarioModel ConvertJsonToUsuario(String json)
    {
        UsuarioModel usuarioModel = new UsuarioModel();
        LocalizacaoModel localizacaoModel = new LocalizacaoModel();
        try {
            JSONObject mainObject = new JSONObject(json);

            JSONObject usuario = mainObject.getJSONObject("usuarioEntity");

            usuarioModel.setEmail(usuario.getString("email"));

            JSONObject localizacao = usuario.getJSONObject("fk_localizacao");
            localizacaoModel.setId_localizacao(localizacao.getInt("id_localizacao"));
            localizacaoModel.setLatitude(localizacao.getString("latitude"));
            localizacaoModel.setLongitude(localizacao.getString("longitude"));

            usuarioModel.setFk_localizacao(localizacaoModel);
            usuarioModel.setId_usuario(usuario.getInt("id_usuario"));
            usuarioModel.setNome(usuario.getString("nome"));
            usuarioModel.setSenha(usuario.getString("senha"));
            usuarioModel.setSobrenome(usuario.getString("sobrenome"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usuarioModel;
    }
}
