package com.dam.proyectob_adt2_diegorodriguezbellido.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dam.proyectob_adt2_diegorodriguezbellido.entity.Terremoto;

import java.util.List;

// Aqui le digo que es un DAO, porque es una interfaz que va a tener metodos que van a ser
// consultas a la base de datos.
@Dao
public interface TerremotoDao {

    // Metodo para insertar los datos.
    @Insert
    void insertAll(List<Terremoto> terremoto);

    // Query para mostrar todos los terremotos y ordenarlos por magnitud.
    @Query("SELECT * FROM TERREMOTOS ORDER BY magnitud DESC")
    public List<Terremoto> getAll();

    // Query para buscar por mes, año y pais y ordenarlos por magnitud.
    @Query("SELECT TERRE.fechaHora, TERRE.nombre, TERRE.magnitud, TERRE.coordEpicentro, TERRE.lugar, TERRE.muertos " +
            "FROM TERREMOTOS AS TERRE, PAISES_AFECTADOS AS PAAF " +
            "WHERE PAAF.fechaHora AND TERRE.fechaHora LIKE :mes AND TERRE.fechaHora LIKE :anio " +
            "AND PAAF.pais LIKE :pais ORDER BY MAGNITUD DESC")
    // Hago un List<Terremoto> porque es lo que me devuelve el query y lo que voy a mostrar en el RecyclerView.
    public List<Terremoto> selectXMesAnioPais(String mes, String anio, String pais);

    // Query para buscar por mes y año y ordenarlos por magnitud.
    @Query("SELECT * FROM TERREMOTOS WHERE fechaHora LIKE :mes AND fechaHora LIKE :anio ORDER BY MAGNITUD DESC")
    // Hago un List<Terremoto> porque es lo que me devuelve el query y lo que voy a mostrar en el RecyclerView.
    public List<Terremoto> selectXMesAnio(String mes, String anio);

    // Query para buscar por mes y ordenarlos por magnitud.
    @Query("SELECT * FROM TERREMOTOS WHERE fechaHora LIKE :mes ORDER BY MAGNITUD DESC")
    // Hago un List<Terremoto> porque es lo que me devuelve el query y lo que voy a mostrar en el RecyclerView.
    public List<Terremoto> selectXMes(String mes);

    // Query para buscar por año y ordenarlos por magnitud.
    @Query("SELECT * FROM TERREMOTOS WHERE fechaHora LIKE :anio ORDER BY MAGNITUD DESC")
    // Hago un List<Terremoto> porque es lo que me devuelve el query y lo que voy a mostrar en el RecyclerView.
    public List<Terremoto> selectXAnio(String anio);

    // Query para buscar por pais y ordenarlos por magnitud.
    @Query("SELECT TERRE.fechaHora, TERRE.nombre, TERRE.magnitud, TERRE.coordEpicentro, TERRE.lugar, TERRE.muertos " +
            "FROM TERREMOTOS AS TERRE, PAISES_AFECTADOS AS PAAF " +
            "WHERE TERRE.fechaHora = PAAF.fechaHora AND TERRE.fechaHora LIKE :pais ORDER BY MAGNITUD DESC")
    // Hago un List<Terremoto> porque es lo que me devuelve el query y lo que voy a mostrar en el RecyclerView.
    public List<Terremoto> selectXPais(String pais);

    // Query para buscar por año y pais y ordenarlos por magnitud.
    @Query("SELECT TERRE.fechaHora, TERRE.nombre, TERRE.magnitud, TERRE.coordEpicentro, " +
            "TERRE.lugar, TERRE.muertos FROM TERREMOTOS AS TERRE, PAISES_AFECTADOS AS PAAF " +
            "WHERE TERRE.fechaHora = PAAF.fechaHora AND TERRE.fechaHora LIKE :anio AND PAAF.pais " +
            "LIKE :pais ORDER BY MAGNITUD DESC")
    // Hago un List<Terremoto> porque es lo que me devuelve el query y lo que voy a mostrar en el RecyclerView.
    public List<Terremoto> selectXAnioPais(String anio, String pais);

    // Query para buscar por mes y pais y ordenarlos por magnitud.
    @Query("SELECT TERRE.fechaHora, TERRE.nombre, TERRE.magnitud, TERRE.coordEpicentro, TERRE.lugar, TERRE.muertos " +
            "FROM TERREMOTOS AS TERRE, PAISES_AFECTADOS AS PAAF " +
            "WHERE TERRE.fechaHora = PAAF.fechaHora AND TERRE.fechaHora LIKE :mes AND PAAF.pais " +
            "LIKE :pais ORDER BY MAGNITUD DESC")
    // Hago un List<Terremoto> porque es lo que me devuelve el query y lo que voy a mostrar en el RecyclerView.
    public List<Terremoto> selectXMesPais(String mes, String pais);
}