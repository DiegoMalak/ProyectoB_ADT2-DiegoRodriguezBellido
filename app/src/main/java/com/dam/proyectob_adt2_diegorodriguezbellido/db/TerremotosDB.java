package com.dam.proyectob_adt2_diegorodriguezbellido.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dam.proyectob_adt2_diegorodriguezbellido.dao.PaisesDao;
import com.dam.proyectob_adt2_diegorodriguezbellido.dao.TerremotoDao;
import com.dam.proyectob_adt2_diegorodriguezbellido.entity.PaisAfectado;
import com.dam.proyectob_adt2_diegorodriguezbellido.entity.Terremoto;

// Clase que va a contener la base de datos.
// El @Database va a contener las entidades que va a tener la base de datos, y la version.
// La version es para que si cambiamos algo en la base de datos, se actualice.
// Es una public abstract class porque se va a heredar de RoomDatabase y va a tener metodos abstractos.
@Database(entities = {Terremoto.class, PaisAfectado.class}, version = 1)
public abstract class TerremotosDB extends RoomDatabase {

    // Declaramos los metodos abstractos que nos va a devolver los DAOs.
    public abstract TerremotoDao terremotosDao();
    public abstract PaisesDao paisesDao();

    // Creamos una instancia de la base de datos.
    private static TerremotosDB terremotosDB;

    // Metodo para obtener la instancia de la base de datos.
    public static TerremotosDB getDatabase(Context context) {
        // Si la instancia es null, creamos una nueva.
        if (terremotosDB == null) {
            // Con el Room.databaseBuilder creamos la base de datos.
            // Le pasamos el contexto, la clase de la base de datos, el nombre de la base de datos.
            // Y le decimos que nos permita hacer las consultas en el hilo principal.
            // Y por ultimo hacemos el build, para 'construir' la base de datos.
            terremotosDB = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TerremotosDB.class, "TERREMOTOS_DB")
                    .allowMainThreadQueries()
                    .build();
        }
        // Devolvemos la instancia.
        return terremotosDB;
    }
}
