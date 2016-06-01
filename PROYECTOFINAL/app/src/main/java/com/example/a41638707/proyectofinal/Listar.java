package com.example.a41638707.proyectofinal;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Listar extends AppCompatActivity {
    public static final String PARAMETRO1="com.example.a41638707.proyectofinal.PARAMETRO1";
    ListView lstEventos;
   //ArrayList<Evento> ListadoEventos;
    Button btnAtras;
    int param;
    int bundle;
    //Evento[] vectorEventos=new Evento[]{};
    List<Evento> list = new ArrayList<Evento>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        Evento EventoCambiado=new Evento();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            bundle=1;
             EventoCambiado=(Evento)extras.getSerializable(Modificar.PARAMETRO2);
        }
        for (int i=0; i<6 ; i++) {
            Evento prueba = new Evento();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                date = fmt.parse("2016-06-06");
            } catch (Exception e) {

            }
            if (bundle==0||!(EventoCambiado.getId()==i))
            {
                if (i==0) {
                    prueba.Evento(0, "Ética", "TP", date, "Marxismo");
                }
                else {
                    if (i == 1) {
                        prueba.Evento(1, "SSI", "Tarea", date, "Redes");
                    } else {
                        if (i == 2) {
                            prueba.Evento(2, "Matemática", "TP", date, "Derivadas");
                        } else {
                            if (i == 3) {
                                prueba.Evento(3, "Ética", "Prueba", date, "Definición de Estado");
                            } else {
                                if (i == 4) {
                                    prueba.Evento(4, "Matemática", "Prueba", date, "Función por tramos");
                                } else {
                                    prueba.Evento(5, "SSI", "TP", date, "S.O.");
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                if (EventoCambiado.getId()==i) {
                    prueba.Evento(EventoCambiado.getId(), EventoCambiado.getMateria(), EventoCambiado.getTipo(), EventoCambiado.getFecha(), EventoCambiado.getDescripcion());
                }
            }
            list.add(prueba);
            //actualizarlo en vector
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
                param=position;
                Dialog dialogoElegir=crearDialogoAlerta();
                dialogoElegir.show();
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
        //nuevaActivity.putExtra(Listar.PARAMETRO1,Mio);
        datos.putSerializable(Listar.PARAMETRO1,Mio);
        nuevaActivity.putExtras(datos);
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
            String s="";
            try
            {
                 s= formatter.format(MiEvento.getFecha());
            }
            catch (Exception e) {
                s="Formato de fecha no correcto";
            }
            lblFecha.setText(s);
            return(item);

        }}
        private Dialog crearDialogoAlerta(){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert Dialog");
            builder.setMessage("¿Qué desea hacer?");
            builder.setPositiveButton("Modificar", new  DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Log.i("Diálogos", "Modificar.");
                    IniciarModificarActividad(param);
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    Log.i("Diálogos", "Eliminar.");
                    Dialog dialogo=confirmarEliminar();
                    dialogo.show();
                }
            });
            return builder.create();

        }
        private Dialog confirmarEliminar(){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert Dialog");
            builder.setMessage("¿Está seguro que desea eliminar el evento?");
            builder.setPositiveButton("Eliminar", new  DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Log.i("Diálogos", "Confirmación Aceptada.");
                    list.remove(param);
                    ((BaseAdapter) lstEventos.getAdapter()).notifyDataSetChanged();
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Log.i("Diálogos", "Confirmación Cancelada.");
                    dialog.cancel();
                }
            });
            return builder.create();

        }
}
