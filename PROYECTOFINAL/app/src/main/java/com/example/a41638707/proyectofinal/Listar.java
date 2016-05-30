package com.example.a41638707.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Listar extends AppCompatActivity {
    ListView lstEventos;
    ArrayList<Evento> ListadoEventos;
    Button btnAtras;
    Evento[] vectorEventos=new Evento[]{};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        Intent elIntent=getIntent();
        Bundle datos=elIntent.getExtras();
        ListadoEventos=(ArrayList<Evento>)datos.getSerializable("key");
        for (int i=0; i<ListadoEventos.size();i++)
        {
            Evento MiEvento=ListadoEventos.get(i);
            //Hacer un array y ponerlo en adapter y setearlo en la lista?
            vectorEventos[i]=MiEvento;

        }
        //Mostrar Materia, Tipo y Fecha
        ArrayAdapter<Evento> adaptador=new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_1,vectorEventos);
        ObtenerReferencias();
        lstEventos.setAdapter(adaptador);
        new AdaptadorTitulares(Listar.this);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irAtras();
            }
        });
    }
    private void ObtenerReferencias()
    {
        btnAtras=(Button) findViewById(R.id.btnAtras);
        lstEventos=(ListView)findViewById(R.id.lstEventos);
    }

    private void irAtras()
    {
       this.finish();
    }
    /*lstEventos.setOnItemClickListener(new OnItemClickListener() {

        @Override

        public void onItemClick(AdapterView<?> a, View v, int position, long id) {

//Acciones necesarias al hacer click

        }

    });ERROR*/
    class AdaptadorTitulares extends ArrayAdapter {
        Activity context;

        AdaptadorTitulares(Activity context) {
            super(context, R.layout.listitem, vectorEventos);
            this.context = context;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem, null);
            TextView lblMateria = (TextView)item.findViewById(R.id.LblMateria);
            lblMateria.setText(vectorEventos[position].getMateria());
            TextView lblTipo= (TextView)item.findViewById(R.id.LblTipo);
            lblTipo.setText(vectorEventos[position].getTipo());
            TextView lblFecha=(TextView) item.findViewById(R.id.LblFecha);
            SimpleDateFormat formatter =new  SimpleDateFormat("yyyy-MM-dd'T'HH");
            String s= formatter.format(vectorEventos[position].getFecha());
            lblFecha.setText(s);
            return(item);

        }

    }

}
