/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_portafolio.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import app_portafolio.model.Perfil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Mario
 */
public class PerfilController {
    
    public ArrayList<Perfil> leerPerfiles() throws IOException {
        ArrayList<Perfil> lista = new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpGet request = new HttpGet("http://localhost:8080/perfil/all");
            Gson gson = new Gson();

            CloseableHttpResponse response = httpClient.execute(request);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    lista = gson.fromJson(result, new TypeToken<ArrayList<Perfil>>() {
                    }.getType());
                }
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        return lista;
    }
    
    public Perfil agregarPerfil(Perfil perfil) throws UnsupportedEncodingException, IOException {
        String postUrl = "http://localhost:8080/perfil";// put in your url
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(perfil));//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        String resultado = gson.toJson(EntityUtils.toString(response.getEntity()));

        JsonParser parser = new JsonParser();
        JsonPrimitive object = (JsonPrimitive) parser.parse(resultado);
        Perfil p = gson.fromJson(object.getAsString(), Perfil.class);
        return p;
    }

 
    public Perfil actualizarPerfil(Perfil perfil) throws UnsupportedEncodingException, IOException {
        String url = "http://localhost:8080/perfil";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut put = new HttpPut(url);
        StringEntity putString = new StringEntity(gson.toJson(perfil));
        put.setEntity(putString);
        put.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(put);
        String resultado = gson.toJson(EntityUtils.toString(response.getEntity()));

        JsonParser parser = new JsonParser();
        JsonPrimitive object = (JsonPrimitive) parser.parse(resultado);
        Perfil p = gson.fromJson(object.getAsString(), Perfil.class);
        return p;
    }

    public void eliminarPerfil(int id) throws UnsupportedEncodingException, IOException {
        String url = "http://localhost:8080/perfil/" + id + "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete(url);
        HttpResponse response = client.execute(request);
    }
    
}
