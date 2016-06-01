package com.example.a41638707.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Modificar extends AppCompatActivity {
    TextView tvwId;
    DatePicker date_picker;
    EditText edtDescr;
    Spinner spnMaterias, spnTipos;
    Button btnCancelar, btnGuardar;
    final String [] Materia=new String[]{"Ética", "Matemática", "SSI"};
    final String [] Tipo=new String[]{"Tarea", "TP", "Prueba"};
    Evento MiEvento=new Evento();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Intent elIntent=getIntent();
        Bundle datos=elIntent.getExtras();
        MiEvento=(Evento)datos.getSerializable(Listar.PARAMETRO1);
        ObtenerReferencias();
        Calendar cal = Calendar.getInstance();
        cal.setTime(MiEvento.getFecha());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        date_picker.updateDate(year,month,day);
        tvwId.setText(String.valueOf(MiEvento.getId()));
        edtDescr.setText(MiEvento.getDescripcion());
        //PONER EN 2 SPINNER LAS MATERIAS Y LOS TIPOS Y QUE ESTE SELECCIONADO EL CORRESPONDIENTE
        ArrayAdapter<String> adaptadorMat = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                Materia);
        ArrayAdapter<String> adaptadorTip = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                Tipo);
        adaptadorMat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaterias.setAdapter(adaptadorMat);
        adaptadorTip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipos.setAdapter(adaptadorTip);
        int positionMateria=0;
        for (int i=0;i<Materia.length;i++)
        {
            if (Materia[i]==MiEvento.getMateria())
            {
                positionMateria=i;
            }
        }
        int positionTipo=0;
        for (int i=0;i<Tipo.length;i++)
        {
            if (Tipo[i]==MiEvento.getTipo())
            {
                positionTipo=i;
            }
        }
        spnTipos.setSelection(positionTipo);
        spnMaterias.setSelection(positionMateria);
        //Evento MiEvento=(Evento)elIntent.getExtras().getSerializable(Listar.PARAMETRO1);
        /*
        Evento model = (Evento) getIntent().getSerializableExtra(Listar.PARAMETRO1);
        edtId.setText(model.getId());
       // date_picker.init(model.getFecha());
*/
        spnMaterias.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               irAtras();
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id=MiEvento.getId();
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
                MiEvento.Evento(id ,strMate,strTipo,result ,strDescr);
                irAtras();
                Toast.makeText(getApplicationContext(), "Se ha guardado el evento", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ObtenerReferencias()
    {
        btnCancelar=(Button) findViewById(R.id.btnCancelar);
        btnGuardar=(Button)findViewById(R.id.btnGuardar);
        spnMaterias=(Spinner)findViewById(R.id.spnMaterias);
        spnTipos=(Spinner)findViewById(R.id.spnTipos);
        tvwId=(TextView) findViewById(R.id.tvwId);
        date_picker=(DatePicker)findViewById(R.id.date_picker);
        edtDescr=(EditText)findViewById(R.id.edtDescr);
    }
    private void irAtras()
    {
        this.finish();
    }
}
