package com.example.a41638707.proyectofinal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
/*
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
*/
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by 42038123 on 31/5/2016.
 *//*
    class ProgressTask extends AsyncTask<String, Void, List<Evento>> {
        private ProgressDialog dialog;

        public ProgressTask(Context activity) {

            Log.i("1", "Called");
            context = activity;
            dialog = new ProgressDialog(context);
        }

        private Context context;

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        List<Evento> jsonlist = new ArrayList<Evento>();

        //Spinner spinner ;
        protected void onPostExecute(  List<Evento> lislin) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
           // spinner = (Spinner) spinner.findViewById(R.id.spinner);
           // spinner.setAdapter(new SocialNetworkSpinnerAdapter(MainActivity.ct,lislin));
            //lv = getListView();


        }
        private OkHttpClient client = new OkHttpClient();
        @Override
        protected List<Evento> doInBackground(String... params) {
            String url = params[0];

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                List<Evento> li=parsearResultado(response.body().string());
                return li;
            } catch (IOException | JSONException e) {
                Log.d("Error", e.getMessage());
                return new ArrayList<Evento>();
            }
        }

        List<Evento> parsearResultado(String JSONstr) throws JSONException {
            ArrayList<Evento> eventos = new ArrayList<>();
            JSONArray jsonEventos = new JSONArray(JSONstr);
            List<Evento> ev= new ArrayList<Evento>();
            for (int i = 0; i < jsonEventos.length(); i++) {
                JSONObject jsonResultado = jsonEventos.getJSONObject(i);
                int id = jsonResultado.getInt("Id");
                String Materia = jsonResultado.getString("IdMateria");
                String Tipo = jsonResultado.getString("IdTipo");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH");
                String Fecha=jsonResultado.getString("Fecha");
                Date result=new Date();
                try {
                    result = df.parse(Fecha);
                }catch (Exception e) {

                }
                String Descripcion=jsonResultado.getString("Descripcion");
                //eventos.add(e);
                //   SocialNetwork e = new SocialNetwork(Numero,Icono);
               // lin.add(new Evento(Numero, "@R.drawable."+Icono));
                //   eventos.add(e);
            }
            return ev;
        }


    }*/

