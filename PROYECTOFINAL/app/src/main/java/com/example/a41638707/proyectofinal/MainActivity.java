package com.example.a41638707.proyectofinal;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



private class GeolocalizacionTask extends AsyncTask<String, Void, ArrayList<Evento>> {
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onPostExecute(ArrayList<Evento> direcciones) {
        super.onPostExecute(direcciones);

    }

    @Override
    protected ArrayList<Evento> doInBackground(String... params) {
        String url = params[0];

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();  // Llamado al Google API
            return parsearResultado(response.body().string());      // Convierto el resultado en ArrayList<Direccion>

        } catch (IOException | JSONException e) {
            Log.d("Error",e.getMessage());                          // Error de Network o al parsear JSON
            return new ArrayList<Evento>();
        }
    }


    // Convierte un JSON en un ArrayList de Direccion
    ArrayList<Evento> parsearResultado(String JSONstr) throws JSONException {
        ArrayList<Evento> eventos = new ArrayList<>();
        JSONObject json = new JSONObject(JSONstr);                 // Convierto el String recibido a JSONObject
        JSONArray jsonEventos = json.getJSONArray("results");  // Array - una busqueda puede retornar varios resultados
        for (int i=0; i<jsonEventos.length(); i++) {
            // Recorro los resultados recibidos
            JSONObject jsonResultado = jsonEventos.getJSONObject(i);
            String jsonAddress = jsonResultado.getString("formatted_address");  // Obtiene la direccion formateada

            JSONObject jsonGeometry = jsonResultado.getJSONObject("geometry");
            JSONObject jsonLocation = jsonGeometry.getJSONObject("location");
            String jsonLat = jsonLocation.getString("lat");                     // Obtiene latitud
            String jsonLng = jsonLocation.getString("lng");                     // Obtiene longitud
            String coord = jsonLat + "," + jsonLng;
//id,mat,tipo,fecha,desc
            Evento d = new Evento(jsonAddress, coord);                    // Creo nueva instancia de direccion
            eventos.add(d);                                                 // Agrego objeto d al array list
        }
        return eventos;
    }

}
}

