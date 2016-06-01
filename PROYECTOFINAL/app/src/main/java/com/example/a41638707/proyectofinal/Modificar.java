package com.example.a41638707.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Modificar extends AppCompatActivity {
    EditText edtId;
    DatePicker date_picker;
    EditText edtDescr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Intent elIntent=getIntent();
        Bundle datos=elIntent.getExtras();
        Evento MiEvento=new Evento();
        MiEvento=(Evento)datos.getSerializable(Listar.PARAMETRO1);
        ObtenerReferencias();
        Calendar cal = Calendar.getInstance();
        cal.setTime(MiEvento.getFecha());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        date_picker.updateDate(year,month,day);
        edtId.setText(String.valueOf(MiEvento.getId()));
        edtDescr.setText(MiEvento.getDescripcion());
        //PONER EN 2 SPINNER LAS MATERIAS Y LOS TIPOS Y QUE ESTE SELECCIONADO EL CORRESPONDIENTE
        //Evento MiEvento=(Evento)elIntent.getExtras().getSerializable(Listar.PARAMETRO1);
        /*
        Evento model = (Evento) getIntent().getSerializableExtra(Listar.PARAMETRO1);
        edtId.setText(model.getId());
       // date_picker.init(model.getFecha());
*/
    }
    private void ObtenerReferencias()
    {
        edtId=(EditText)findViewById(R.id.edtId);
        date_picker=(DatePicker)findViewById(R.id.date_picker);
        edtDescr=(EditText)findViewById(R.id.edtDescr);
    }
}
