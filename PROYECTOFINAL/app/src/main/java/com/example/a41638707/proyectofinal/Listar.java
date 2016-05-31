package com.example.a41638707.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Listar extends AppCompatActivity {
    public static final String PARAMETRO1="com.example.a41638707.proyectofinal.PARAMETRO1";;
    ListView lstEventos;
    ArrayList<Evento> ListadoEventos;
    Button btnAtras;
    //Evento[] vectorEventos=new Evento[]{};
    List<Evento> list = new ArrayList<Evento>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        /*Intent elIntent=getIntent();
        Bundle datos=elIntent.getExtras();
        ListadoEventos=(ArrayList<Evento>)datos.getSerializable("key");*/
        for (int i=0; i<5;i++) {
            Evento prueba = new Evento();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Random rand = new Random();
            try {
                date = fmt.parse("2016-06-06");
            } catch (Exception e) {

            }
            int randomNum = rand.nextInt((10 - 4) + 1) + 4;
            if (i==1)
            {
                prueba.Evento(randomNum, "SSI", "Tarea", date, "Redes");
            }
            else
            {
                if (i==2||i==4)
                {
                    prueba.Evento(randomNum, "Matemática", "TP", date, "Derivadas");
                }
                else
                {
                    prueba.Evento(randomNum, "Ética", "Prueba", date, "Definición de Estado");
                }
            }
            list.add(prueba);
        }
       /* for (int i=0; i<list.size();i++)
        {
            Evento MiEvento=list.get(i);
            //Hacer un array y ponerlo en adapter y setearlo en la lista?
            vectorEventos[i]=MiEvento;

        }*/
        //Mostrar Materia, Tipo y Fecha
       // ArrayAdapter<Evento> adaptador=new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_1,list);
        ObtenerReferencias();
        //lstEventos.setAdapter(adaptador);
        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);

        lstEventos.setAdapter(adaptador);
        //new AdaptadorTitulares(Listar.this);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irAtras();
            }
        });
        lstEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //Desea eliminar o modificar?
                //Modificar
                IniciarModificarActividad(position);
                //Acciones necesarias al hacer click
            }
        });
    }
    private void ObtenerReferencias()
    {
        btnAtras=(Button) findViewById(R.id.btnAtras);
        lstEventos=(ListView)findViewById(R.id.lstEventos);
    }
    private void IniciarModificarActividad(int i)
    {
        Intent nuevaActivity=new Intent(this,Modificar.class);
        Bundle datos=new Bundle();
        Evento Mio=list.get(i);
        nuevaActivity.putExtra(Listar.PARAMETRO1,Mio);
        startActivity(nuevaActivity);
    }
    private void irAtras()
    {
       this.finish();
    }

    class AdaptadorTitulares extends ArrayAdapter {
        Activity context;

        AdaptadorTitulares(Activity context) {
            super(context, R.layout.listitem, list);
            this.context = context;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Evento MiEvento=list.get(position);
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem, null);
            TextView lblMateria = (TextView)item.findViewById(R.id.LblMateria);
            lblMateria.setText(MiEvento.getMateria());
            TextView lblTipo= (TextView)item.findViewById(R.id.LblTipo);
            lblTipo.setText(MiEvento.getTipo());
            TextView lblFecha=(TextView) item.findViewById(R.id.LblFecha);
            SimpleDateFormat formatter =new  SimpleDateFormat("yyyy-MM-dd'T'HH");
            String s= formatter.format(MiEvento.getFecha());
            lblFecha.setText(s);
            return(item);

        }

    }

}
