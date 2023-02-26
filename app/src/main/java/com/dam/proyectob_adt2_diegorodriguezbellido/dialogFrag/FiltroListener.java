package com.dam.proyectob_adt2_diegorodriguezbellido.dialogFrag;

public interface FiltroListener {
    // Este método se llama cuando se pulsa el botón de aceptar,
    // pasándole los datos introducidos en el diálogo.
    // Solo sirve para pasar los datos, no para cerrar el diálogo.
    public void onFiltro(String mes, String anio, String pais);
}
