package com.dam.proyectob_adt2_diegorodriguezbellido.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dam.proyectob_adt2_diegorodriguezbellido.entity.PaisAfectado;

import java.util.List;

// Aqui le digo que es un DAO, porque es una interfaz que va a tener metodos que van a ser
// consultas a la base de datos.
@Dao
public interface PaisesDao {

    // Metodo para insertar los datos.
    @Insert
    void insertAll(List<PaisAfectado> paisAfectado);

    @Query("SELECT * FROM PAISES_AFECTADOS")
    public List<PaisAfectado> getAll();

    @Query("SELECT * FROM PAISES_AFECTADOS WHERE pais = :pais")
    public PaisAfectado selectByPais(String pais);

    // Hago este para que coja los paises sin repetir, ya que el DISTINCT lo que va a hacer es
    // que si hay dos paises iguales, solo coja uno.
    @Query("SELECT DISTINCT pais FROM PAISES_AFECTADOS")
    public List<String> getAllPaisesSinRep();
}