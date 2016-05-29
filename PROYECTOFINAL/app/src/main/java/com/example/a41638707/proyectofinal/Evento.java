package com.example.a41638707.proyectofinal;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 41638707 on 24/5/2016.
 */
public class Evento implements Serializable{
    private int Id;
    private String Materia;
    private String Tipo;
    private Date Fecha;
    private String Descripcion;
    public void Evento(int id,String mat, String tip, Date fec, String descr)
    {
        Id=id;
        Materia=mat;
        Tipo=tip;
        Fecha=fec;
        Descripcion=descr;
    }
    public int getId()
    {
        return Id;
    }
    public String getMateria()
    {
        return Materia;
    }
    public String getTipo()
    {
        return Tipo;
    }
    public Date getFecha()
    {
        return Fecha;
    }
    public String getDescripcion()
    {
        return Descripcion;
    }
}
