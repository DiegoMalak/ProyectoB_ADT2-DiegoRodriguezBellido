package com.dam.proyectob_adt2_diegorodriguezbellido;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dam.proyectob_adt2_diegorodriguezbellido.dao.PaisesDao;
import com.dam.proyectob_adt2_diegorodriguezbellido.dao.TerremotoDao;
import com.dam.proyectob_adt2_diegorodriguezbellido.datos.ListaDatosPaises;
import com.dam.proyectob_adt2_diegorodriguezbellido.datos.ListaDatosTerremoto;
import com.dam.proyectob_adt2_diegorodriguezbellido.db.TerremotosDB;
import com.dam.proyectob_adt2_diegorodriguezbellido.dialogFrag.FiltroDialog;
import com.dam.proyectob_adt2_diegorodriguezbellido.dialogFrag.FiltroListener;
import com.dam.proyectob_adt2_diegorodriguezbellido.entity.PaisAfectado;
import com.dam.proyectob_adt2_diegorodriguezbellido.entity.Terremoto;
import com.dam.proyectob_adt2_diegorodriguezbellido.rvUtil.TerremotoAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

// Implementamos la interfaz FiltroListener para poder recibir los datos del diálogo, y poder
// comunicarlos con el Activity Main.
public class MainActivity extends AppCompatActivity implements FiltroListener {

    // Creamos los objetos de las clases DAO y del adapter.
    TerremotoAdapter terAdapter;
    TerremotoDao terremotoDao;
    PaisesDao paisesDao;

    // Creamos los objetos de los componentes de la vista.
    Button btnF;
    Button btnC;
    TextView tvF;
    RecyclerView rv;

    // Creamos las variables para los datos del diálogo.
    String mes;
    String anio;
    String pais;
    // Este int lo usaremos para saber qué opción ha 'escogido' el usuario.
    int opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Método para cargar la BBDD con los datos de los ficheros.
        primeraCargaDB();

        // Inicializamos los componentes de la vista y el RecyclerView.
        btnF = findViewById(R.id.btnFiltro);
        btnC = findViewById(R.id.btnConsulta);
        tvF = findViewById(R.id.tvFiltroSelec);
        rv = findViewById(R.id.rvListaConsulta);
        // Inicializamos el RecyclerView.
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Creamos el arrayList de terremotos.
        ArrayList<Terremoto> listaTerremotos = new ArrayList<>();
        // Creamos el adapter.
        terAdapter = new TerremotoAdapter(listaTerremotos);
        // Lo asignamos al RecyclerView.
        rv.setAdapter(terAdapter);

        // Cargamos la lista de terremotos.
        // **************************************************************************************
        // Lo comento pero esto muestra todos los terremotos de la BBDD en el RecyclerView tras
        // iniciar la aplicación, pero como no lo pide el enunciado, lo dejo comentado.
        // terAdapter.setDatosTerremoto(ListaDatosTerremoto.getListaDatosTerremoto());
        // **************************************************************************************

        // Botón de filtro, que abre el diálogo para seleccionar el filtro.
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se abre la ventana de dialogo.
                FiltroDialog fd = new FiltroDialog();
                // Si se pulsa fuera del diálogo o el botón de volver, no se cierra.
                fd.setCancelable(false);
                // Mostramos el diálogo, pasándole el fragment manager.
                fd.show(getSupportFragmentManager(), "FiltroDialog");
            }
        });

        // Botón de consulta, carga los datos de la BBDD según el filtro seleccionado en el RecyclerView.
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Consultar.
                consultar();
            }
        });
    }

    // Método para cargar los datos de la BBDD y tener contenido en esta.
    private void primeraCargaDB() {
        // Creamos la base de datos.
        TerremotosDB db = TerremotosDB.getDatabase(this);
        // Creamos los DAO.
        terremotoDao = db.terremotosDao();
        paisesDao = db.paisesDao();
        // Creamos los datos de prueba a partir de los arrays creados.
        // Primero los terremotos.
        ArrayList<Terremoto> listaTerremotos = (ArrayList<Terremoto>) terremotoDao.getAll();
        // Segundo los países.
        ArrayList<PaisAfectado> listaPaises = (ArrayList<PaisAfectado>) paisesDao.getAll();

        // Si no hay datos en la base de datos, se cargan los datos mediante los insert.
        if (listaTerremotos.size() == 0) {
            // Insertamos los datos ya que no hay nada en la base de datos.
            terremotoDao.insertAll(ListaDatosTerremoto.getListaDatosTerremoto());
        }
        // Si no hay datos en la base de datos, se cargan los datos mediante los insert.
        if (listaPaises.size() == 0) {
            // Insertamos los datos ya que no hay nada en la base de datos.
            paisesDao.insertAll(ListaDatosPaises.getListaDatosPaises());
        }
        // Sysout para decir que se han cargado los datos.
        System.out.println("----------------------------------------------");
        System.out.println("TRAZA: Datos cargados en la base de datos.");
        System.out.println("----------------------------------------------");
    }

    // Método para consultar los datos de la base de datos.
    private void consultar() {
        // Si se pulsa el botón de consulta y no se ha seleccionado ningún filtro, se mostrarán todos
        // los datos de los terremotos de la base de datos en el RecyclerView.
        // Ahora vamos a cargar el RecyclerView con los datos de la base de datos, con las Querys
        // que hemos creado en los DAO y necesitemos para la consulta.
        // Vamos a hacer un switch para controlar las opciones de consulta.
        switch (opcion) {
            // Opción 0, no le hemos pasado ningún filtro, por lo que se mostrarán todos los datos.
            case 0:
                // Aquí se cargan los datos en el RecyclerView. Se le pasa un List de la consulta y
                // se le pasa al método setDatosTerremotoRoom.
                terAdapter.setDatosTerremotoRoom(terremotoDao.getAll());
                break;
            // Opción 1, se ha seleccionado un mes, un año y un país, por lo que se mostrarán los datos.
            case 1:
                // Aquí se cargan los datos en el RecyclerView. Se le pasa un List de la consulta y
                // se le pasa al método setDatosTerremotoRoom.
                terAdapter.setDatosTerremotoRoom(terremotoDao.selectXMesAnioPais(('%' + mes + '%'), ('%' + anio + '%'), ('%' + pais + '%')));
                break;
            // Opción 2, se ha seleccionado un mes y un año, por lo que se mostrarán los datos.
            case 2:
                // Aquí se cargan los datos en el RecyclerView. Se le pasa un List de la consulta y
                // se le pasa al método setDatosTerremotoRoom.
                terAdapter.setDatosTerremotoRoom(terremotoDao.selectXMesAnio(('%' + mes + '%'), ('%' + anio + '%')));
                break;
            case 3:
                // Aquí se cargan los datos en el RecyclerView. Se le pasa un List de la consulta y
                // se le pasa al método setDatosTerremotoRoom.
                terAdapter.setDatosTerremotoRoom(terremotoDao.selectXMes(('%' + mes + '%')));
                break;
            case 4:
                // Aquí se cargan los datos en el RecyclerView. Se le pasa un List de la consulta y
                // se le pasa al método setDatosTerremotoRoom.
                terAdapter.setDatosTerremotoRoom(terremotoDao.selectXAnio(('%' + anio + '%')));
                break;
            case 5:
                // Aquí se cargan los datos en el RecyclerView. Se le pasa un List de la consulta y
                // se le pasa al método setDatosTerremotoRoom.
                terAdapter.setDatosTerremotoRoom(terremotoDao.selectXPais(('%' + pais + '%')));
                break;
            case 6:
                // Aquí se cargan los datos en el RecyclerView. Se le pasa un List de la consulta y
                // se le pasa al método setDatosTerremotoRoom.
                terAdapter.setDatosTerremotoRoom(terremotoDao.selectXAnioPais(('%' + anio + '%'), ('%' + pais + '%')));
                break;
            case 7:
                // Aquí se cargan los datos en el RecyclerView. Se le pasa un List de la consulta y
                // se le pasa al método setDatosTerremotoRoom.
                terAdapter.setDatosTerremotoRoom(terremotoDao.selectXMesPais(('%' + mes + '%'), ('%' + pais + '%')));
                break;
        }
        // En caso de que no haya datos, se mostrará un mensaje mediante un Snackbar.
        if (terAdapter.getItemCount() == 0) {
            Snackbar.make(findViewById(R.id.rvListaConsulta), "NO HAY DATOS SOBRE ESTA CONSULTA...", Snackbar.LENGTH_LONG).show();
        }
    }

    // Método para mostrar la selección el TV.
    @Override
    public void onFiltro(String mes, String anio, String pais) {
        // Aquí se recogen los datos del diálogo.
        // Se muestra el filtro seleccionado en el TextView.
        this.mes = mes;
        this.anio = anio;
        this.pais = pais;
        // Si mes = TODOS, lo dejamos vacío para poder hacer la consulta.
        if (mes.equalsIgnoreCase("Todos")) {
            this.mes = "";
        }
        // Si pais = TODOS, lo dejamos vacío para poder hacer la consulta.
        if (pais.equalsIgnoreCase("Todos")) {
            this.pais = "";
        }
        // Creamos un método para poner el texto seleccionado en el filtro en el TextView.
        rellenarTituloPaisFiltro();
    }

    // Método para rellenar el TextView con el filtro seleccionado.
    private void rellenarTituloPaisFiltro() {
        // Vamos a rellenar el TextView con el filtro seleccionado.
        // Para ello vamos a controlar que hemos seleccionado dentro del FiltroDialog.
        // Como se puede rellenar hasta con 3 filtros, vamos a controlarlos todos.
        // LAS COMPROBACIONES SE DAN VACíAS NO NULL.
        //------------------------------------------------------------------------------------------
        // Si no se ha seleccionado nada, se mostrarán todos los datos.
        if (mes == null && anio == null && pais == null) {
            // Si no se ha seleccionado nada, se mostrarán todos los datos.
            // Y se mostrará en el tv el texto "Todos los datos".
            tvF.setText("TODOS LOS DATOS");
            opcion = 0;
        // Si se ha seleccionado el mes, el año y el país.
        } else if (mes != null && anio != null && pais != null) {
            // Se mostrará en el tv el texto "Mes, año y país".
            tvF.setText("MES: " + mes + " - AÑO: " + anio + " - PAÍS: " + pais);
            opcion = 1;
        // Si se ha seleccionado el mes y el año.
        } else if (mes != null && anio != null && pais == null) {
            // Se mostrará en el tv el texto "Mes y año".
            tvF.setText("MES: " + mes + " - AÑO: " + anio);
            opcion = 2;
        // Si se ha seleccionado el mes.
        } else if (mes != null && anio == null && pais == null) {
            // Se mostrará en el tv el texto "Mes".
            tvF.setText("MES: " + mes);
            opcion = 3;
        // Si se ha seleccionado el año.
        } else if (mes == null && anio != null && pais == null) {
            // Se mostrará en el tv el texto "Año".
            tvF.setText("AÑO: " + anio);
            opcion = 4;
        // Si se ha seleccionado el país.
        } else if (mes == null && anio == null && pais != null) {
            // Se mostrará en el tv el texto "País".
            tvF.setText("PAÍS: " + pais);
            opcion = 5;
        // Si se ha seleccionado el año y el país.
        } else if (mes == null && anio != null && pais != null) {
            // Se mostrará en el tv el texto "Año y país".
            tvF.setText("AÑO: " + anio + " - PAÍS: " + pais);
            opcion = 6;
        // Si se ha seleccionado el mes y el país.
        } else if (mes != null && anio == null && pais != null) {
            // Se mostrará en el tv el texto "Mes y país".
            tvF.setText("MES: " + mes + " - PAÍS: " + pais);
            opcion = 7;
        }
        // Se muestra por consola el filtro seleccionado y la opción que corresponde.
        System.out.println("====================================");
        System.out.println("TRAZA: FILTRO SELECCIONADO");
        System.out.println("Mes: " + mes);
        System.out.println("Año: " + anio);
        System.out.println("País: " + pais);
        System.out.println("OPCION: " + opcion);
        System.out.println("====================================");
    }
}