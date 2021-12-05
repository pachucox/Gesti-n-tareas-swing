package app_portafolio.controller;


import app_portafolio.model.Tarea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mario
 */
public class TareaController {
    
    public ArrayList<Tarea> leerTareas() throws IOException {
        ArrayList<Tarea> lista = new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpGet request = new HttpGet("http://localhost:8080/tarea/all");
            Gson gson = new Gson();
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    lista = gson.fromJson(result, new TypeToken<ArrayList<Tarea>>() {
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

    public Tarea agregarTarea(Tarea tarea) throws UnsupportedEncodingException, IOException {
        String postUrl = "http://localhost:8080/tarea";// put in your url
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(tarea));//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        String resultado = gson.toJson(EntityUtils.toString(response.getEntity()));

        JsonParser parser = new JsonParser();
        JsonPrimitive object = (JsonPrimitive) parser.parse(resultado);
        Tarea t = gson.fromJson(object.getAsString(), Tarea.class);
        return t;
    }

    public Tarea actualizarTarea(Tarea tarea) throws UnsupportedEncodingException, IOException {
        String url = "http://localhost:8080/tarea";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut put = new HttpPut(url);
        StringEntity putString = new StringEntity(gson.toJson(tarea));
        put.setEntity(putString);
        put.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(put);
        String resultado = gson.toJson(EntityUtils.toString(response.getEntity()));

        JsonParser parser = new JsonParser();
        JsonPrimitive object = (JsonPrimitive) parser.parse(resultado);
        Tarea t = gson.fromJson(object.getAsString(), Tarea.class);
        return t;
    }

    public void eliminarTarea(int id) throws UnsupportedEncodingException, IOException {
        String url = "http://localhost:8080/tarea/" + id + "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete(url);
        HttpResponse response = client.execute(request);
    }
    
}
