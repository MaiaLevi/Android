package com.example.a41638707.proyectofinal;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Agregar extends AppCompatActivity {
    Button btnCancelar,btnGuardar;
    EditText edtDescr;
    Spinner spnMaterias, spnTipos;
    DatePicker date_picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        //que vaya de aca al listar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int dia=date_picker.getDayOfMonth();
                int mes=date_picker.getMonth();
                int anio=date_picker.getYear();
                //GUARDAR EN VARIABLE CADA DATOS Y PARA EL DATE COPIAR EL TRY CATCH
                // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH");
                //String Fecha=dia+"/"+mes+"/"+anio;
                Date result=new Date(anio, mes, dia);
                /*try {
                    result = df.parse(Fecha);
                }catch (Exception e) {

                }*/
                String strMate=spnMaterias.getSelectedItem().toString();
                String strTipo=spnTipos.getSelectedItem().toString();
                String strDescr=edtDescr.getText().toString();
               // MiEvento.Evento(id ,strMate,strTipo,result ,strDescr);
                Toast.makeText(getApplicationContext(), "Se ha guardado el evento", Toast.LENGTH_SHORT).show();
                GuardarEvento();
                irAtras();
            }
        });
    }

    private void GuardarEvento()
    {
        Intent nuevaActivity=new Intent(Agregar.this,Listar.class);
        Bundle datos=new Bundle();
        //nuevaActivity.putExtra(Listar.PARAMETRO1,Mio);
       //CAMBIAR datos.putSerializable(Modificar.PARAMETRO2,MiEvento);
        nuevaActivity.putExtras(datos);
        startActivity(nuevaActivity);
    }
    private void ObtenerReferencias()
    {
        btnCancelar=(Button) findViewById(R.id.btnCancelar);
        btnGuardar=(Button)findViewById(R.id.btnGuardar);
        spnMaterias=(Spinner)findViewById(R.id.spnMaterias);
        spnTipos=(Spinner)findViewById(R.id.spnTipos);
        date_picker=(DatePicker)findViewById(R.id.date_picker);
        edtDescr=(EditText)findViewById(R.id.edtDescr);
    }
    private void irAtras()
    {
        this.finish();
    }

}
