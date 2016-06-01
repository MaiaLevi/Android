package com.example.a41638707.proyectofinal;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.util.Random;
import java.util.StringTokenizer;

public class Agregar extends AppCompatActivity {
    Button btnCancelar,btnGuardar;
    EditText edtDescr;
    Spinner spnMaterias, spnTipos;
    DatePicker date_picker;
    Evento event = new Evento();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        final String [] Materia=new String[]{"Ética", "Matemática", "SSI"};
        final String [] Tipo=new String[]{"Tarea", "TP", "Prueba"};
        //que vaya de aca al listar
        ObtenerReferencias();

        ArrayAdapter<String> adaptadorMat = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Materia);
        ArrayAdapter<String> adaptadorTip = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Tipo);
        adaptadorMat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaterias.setAdapter(adaptadorMat);
        adaptadorTip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipos.setAdapter(adaptadorTip);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String Descripcion = edtDescr.getText().toString();
                String Materia=spnMaterias.getSelectedItem().toString();
                String Tipo = spnTipos.getSelectedItem().toString();
                Random random = new Random();
                int dia=date_picker.getDayOfMonth();
                int mes=date_picker.getMonth();
                int anio=date_picker.getYear();
                Date Fecha =new Date(anio, mes, dia);
                Random r = new Random();
                int idRandom = r.nextInt(20 - 6) + 6;
                event.Evento(idRandom, Materia, Tipo, Fecha, Descripcion);
                Toast.makeText(getApplicationContext(), "Se ha guardado el evento", Toast.LENGTH_SHORT).show();
                GuardarEvento();
                irAtras();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irAtras();
            }});
    }

    private void GuardarEvento()
    {
        Intent nuevaActivity=new Intent(Agregar.this,Listar.class);
        Bundle datos=new Bundle();
        //nuevaActivity.putExtra(Listar.PARAMETRO1,Mio);
        datos.putSerializable(Modificar.PARAMETRO2,event);
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
