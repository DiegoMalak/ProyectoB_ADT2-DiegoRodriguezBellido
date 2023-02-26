package com.dam.proyectob_adt2_diegorodriguezbellido.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

// Con room el unique se pone en el indice, no en la columna.
@Entity(tableName = "TERREMOTOS",
        indices = {@Index(value = {"nombre"}, unique = true)})
public class Terremoto {

    // Pongo fechaHora como String porque la da como: '23 de mayo de 1998, 12:00:00'
    @PrimaryKey
    @NonNull
    public String fechaHora;

    // Le damos el nombre de la columna para que no se llame igual que el atributo.
    @ColumnInfo(name = "nombre")
    public String nombre;

    // Le damos el nombre de la columna para que no se llame igual que el atributo.
    @ColumnInfo(name = "magnitud")
    public double magnitud;

    // Le damos el nombre de la columna para que no se llame igual que el atributo.
    @ColumnInfo(name = "coordEpicentro")
    public String coordEpicentro;

    // Le damos el nombre de la columna para que no se llame igual que el atributo.
    @ColumnInfo(name = "lugar")
    public String lugar;

    // Le damos el nombre de la columna para que no se llame igual que el atributo.
    // Pongo muertos como String porque la da como un rango de números: 'de 700 a 1000'
    @ColumnInfo(name = "muertos")
    public String muertos;

    // Modificamos el constructor ya que la lista de datos está con el orden puesto de esta forma, y
    // no podemos cambiarlo porque si no no nos funcionaría y nos daría error.
    public Terremoto(String fechaHora, double magnitud, String nombre, String lugar, String coordEpicentro,  String muertos) {
        this.fechaHora = fechaHora;
        this.magnitud = magnitud;
        this.nombre = nombre;
        this.coordEpicentro = coordEpicentro;
        this.lugar = lugar;
        this.muertos = muertos;
    }

    // Getters de los atributos de la clase para poder acceder a ellos desde fuera.
    public String getFechaHora() {
        return fechaHora;
    }

    public String getNombre() {
        return nombre;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public String getCoordEpicentro() {
        return coordEpicentro;
    }

    public String getLugar() {
        return lugar;
    }

    public String getMuertos() {
        return muertos;
    }
}
