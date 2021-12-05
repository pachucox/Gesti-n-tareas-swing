/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_portafolio.controller;

import app_portafolio.model.Justificacion;
import com.google.gson.Gson;
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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Mario
 */
public class JustificacionController {
    
     public ArrayList<Justificacion> leerJustificaciones() throws IOException {
        ArrayList<Justificacion> lista = new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpGet request = new HttpGet("http://localhost:8080/justificacion/all");
            Gson gson = new Gson();
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    lista = gson.fromJson(result, new TypeToken<ArrayList<Justificacion>>() {
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
     
       public void eliminarJustificacion(int id) throws UnsupportedEncodingException, IOException {
        String url = "http://localhost:8080/justificacion/" + id + "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete(url);
        HttpResponse response = client.execute(request);
    }
    
}
