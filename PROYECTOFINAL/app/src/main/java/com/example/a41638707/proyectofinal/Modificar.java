package com.example.a41638707.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Modificar extends AppCompatActivity {
    EditText edtId;
    DatePicker date_picker;
    EditText edtDescr;
    int dia;
    int mes;
    int año;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);/*
        setContentView(R.layout.activity_modificar);
        Evento model = (Evento) getIntent().getSerializableExtra(Listar.PARAMETRO1);
        ObtenerReferencias();
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
