package com.dam.proyectob_adt2_diegorodriguezbellido.dialogFrag;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.dam.proyectob_adt2_diegorodriguezbellido.R;
import com.dam.proyectob_adt2_diegorodriguezbellido.dao.PaisesDao;
import com.dam.proyectob_adt2_diegorodriguezbellido.db.TerremotosDB;

import java.util.ArrayList;

// Clase que crea el dialogo para filtrar los terremotos, extiende de DialogFragment para poder
// crear un dialogo y implementa la interfaz FiltroListener para poder comunicar el dialogo con
// la actividad principal.
public class FiltroDialog extends DialogFragment {

    // Declaramos la clase FiltroListener para poder comunicar el dialogo con la actividad principal.
    // Y también declaramos PaisesDao para poder coger los paises de la base de datos.
    FiltroListener listener;
    PaisesDao paisesDao;

    // Declaramos los elementos de la vista.
    Spinner spnMes;
    EditText etAnio;
    Spinner spnPais;

    // Método que se ejecuta cuando se crea el dialogo.
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Usamos el inflater para crear la vista.
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_filtro, null);

        // Referenciamos el contenido de la vista.
        spnMes = v.findViewById(R.id.spFiltroMes);
        etAnio = v.findViewById(R.id.etFiltroAnio);
        spnPais = v.findViewById(R.id.spFiltroPais);
        // Creamos el PaisesDao para poder coger los paises de la base de datos.
        paisesDao = TerremotosDB.getDatabase(getActivity()).paisesDao();

        // CARGAR EN EL SPINNER LOS PAISES DE LA BASE DE DATOS. Con la clase PaisDAO y la clase de la base de datos.
        // Creamos un ArrayList con los paises, para meterlos en el spinner y así poder meter
        // un elemento 'todos' en la primera posición para hacer el getAll.
        ArrayList<String> paises = (ArrayList<String>) paisesDao.getAllPaisesSinRep();
        // Añadimos el elemento 'todos' en la primera posición, con el método add y así poder
        // hacer el getAll.
        paises.add(0, "Todos");
        // Vamos a coger los paises y meterlos en el spinner.
        // Como hemos hecho el ArrayList de paises ya solo hay que pasarselo al spinner.
        spnPais.setAdapter(new android.widget.ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item,
                paises));

        // Construimos el diálogo que retorna el método.
        // El AlertDialog que se importa es el androidx.appcompat.app.AlertDialog. (el segundo que sale)
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        // En el botón de aceptar, se comunicarán los datos introducidos con el Activity Main.
        // En el botón de cancelar, cerramos el diálogo.
        builder.setTitle(R.string.title_dialog)
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener() {
                    // Este es el método que se ejecuta cuando se pulsa el botón de aceptar.
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cogemos los datos introducidos.
                        String mes = spnMes.getSelectedItem().toString();
                        String anio = etAnio.getText().toString();
                        String pais = spnPais.getSelectedItem().toString();
                        // Le pasamos los datos al Activity Main con el método onFiltro de la interfaz FiltroListener.
                        listener.onFiltro(mes, anio, pais);
                    }
                })
                .setNegativeButton(R.string.btn_cancelar, new DialogInterface.OnClickListener() {
                    // Este es el método que se ejecuta cuando se pulsa el botón de cancelar.
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Cerramos el diálogo.
                        dialogInterface.dismiss();
                    }
                });

        // Creamos el AlertDialog, que es el que se va a mostrar.
        AlertDialog ad = builder.create();
        //ad.setCanceledOnTouchOutside(false);
        ad.setCancelable(false);
        // Retornamos el AlertDialog.
        return ad;
    }

    // Método que se ejecuta cuando se crea el dialogo.
    public void onAttach(@NonNull android.app.Activity activity) {
        // No olvidar llamar al metodo de la superclase.
        super.onAttach(activity);
        // Comprobamos que el contexto implementa la interfaz.
        // Si no implementa la interfaz, lanzamos una excepción.
        // Si implementa la interfaz, la asignamos a la variable listener.
        listener = (FiltroListener) activity;
    }

    // El método onDetach se ejecuta cuando el fragmento se desata de su actividad.
    // En este método, ponemos la variable listener a null para que no se quede con una referencia
    // al contexto.
    public void onDetach() {
        // Si la variable listener no es null, la ponemos a null.
        if (listener != null) {
            listener = null;
        }
        // No olvidar llamar al método de la superclase.
        super.onDetach();
    }
}

// *************************************************************************************************
// ERROR ENCONTRADO POR EL QUE NO SE MOSTRABA EL DIALOG FRAGMENT.
// *************************************************************************************************
/*
        // Esto es para que el boton Aceptar sea escuchado, pero si los datos son incorrectos,
        // no se cierre el dialogo.
        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnAceptar = ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_POSITIVE);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Si se pulsa el botón de aceptar, se comunicarán los datos introducidos con el Activity Main.
                        // Si el año no es correcto, es decir, es mayor que el año actual, se mostrará un mensaje de error.
                        if (Integer.parseInt(etAnio.getText().toString()) > 2023) {
                            Snackbar.make(view, R.string.error_anio, Snackbar.LENGTH_SHORT).show();
                        } else {
                            // Se comunican los datos introducidos con el Activity Main.
                            listener.onFiltro(spnMes.getSelectedItem().toString(),
                                                etAnio.getText().toString().trim(),
                                                spnPais.getSelectedItem().toString());
                            // Se cierra el diálogo.
                            dialogInterface.dismiss();
                        }
                    }
                });
            }
        });*/
// return super.onCreateDialog(savedInstanceState);