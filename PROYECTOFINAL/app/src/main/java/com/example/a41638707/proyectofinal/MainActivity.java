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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
String url="10.152.2.45";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //CAMBIAR NOMBR DE CLASE
private class GeolocalizacionTask extends AsyncTask<String, Void, ArrayList<Evento>> {
    private OkHttpClient client = new OkHttpClient();
    @Override
    protected void onPostExecute(ArrayList<Evento> direcciones) {
        super.onPostExecute(direcciones);
    }
    @Override
    protected ArrayList<Evento> doInBackground(String... params) {

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
    // Convierte un JSON en un ArrayList de Eventos
    //LISTAR EVENTOS
    ArrayList<Evento> parsearResultado(String JSONstr) throws JSONException {
        ArrayList<Evento> eventos = new ArrayList<>();
        JSONObject json = new JSONObject(JSONstr);                 // Convierto el String recibido a JSONObject
        JSONArray jsonEventos = json.getJSONArray("results");
        Date result=(2016-05-12T00:00:00);
        // Array - una busqueda puede retornar varios resultados
        for (int i=0; i<jsonEventos.length(); i++) {
            // Recorro los resultados recibidos
            JSONObject jsonResultado = jsonEventos.getJSONObject(i);
            int jsonId = jsonResultado.getInt("Id");
            String jsonMat = jsonResultado.getString("Materia");
            String jsonTipo = jsonResultado.getString("Tipo");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH");
            String Fecha=jsonResultado.getString("Fecha");

            try {
                result = df.parse(Fecha);
            }catch (Exception e) {

            }
            String jsonDesc = jsonResultado.getString("Descripcion");
            Evento d = new Evento();                    // Creo nueva instancia de direccion
            d.Id=jsonId;
            d.Materia=jsonMat;
            d.Tipo=jsonTipo;
            d.Fecha=result;
            d.Descripcion=jsonDesc;
            eventos.add(d);                                                 // Agrego objeto d al array list
        }
        return eventos;
    }

}
}

