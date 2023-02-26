package com.dam.proyectob_adt2_diegorodriguezbellido.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

// El onDelete no es necesario pero lo pongo por si en un futuro quiero borrar un terremoto y
// que se borren los paises afectados.
@Entity(tableName = "PAISES_AFECTADOS", primaryKeys = {"fechaHora", "pais"},
        foreignKeys = @ForeignKey(entity = Terremoto.class, parentColumns = "fechaHora",
                                  childColumns = "fechaHora", onDelete = ForeignKey.CASCADE))
public class PaisAfectado {

    // Se le pone nonNull porque no puede ser null.
    @ColumnInfo(name = "fechaHora")
    @NonNull
    public String fechaHora;

    // Se le pone nonNull porque no puede ser null.
    @ColumnInfo(name = "pais")
    @NonNull
    public String pais;

    // Constructor, con los parametros que tiene la tabla.
    // Lo ponemos public para que se pueda acceder desde fuera.
    public PaisAfectado(@NonNull String fechaHora, @NonNull String pais) {
        this.fechaHora = fechaHora;
        this.pais = pais;
    }

    // Getters de los atributos de la clase para poder acceder a ellos desde fuera.
    public String getFechaHora() {
        return fechaHora;
    }

    public String getPais() {
        return pais;
    }
}
