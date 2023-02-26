package com.dam.proyectob_adt2_diegorodriguezbellido.datos;

import com.dam.proyectob_adt2_diegorodriguezbellido.entity.PaisAfectado;

import java.util.ArrayList;

public class ListaDatosPaises {

    // Creamos un ArrayList de tipo PaisAfectado.
    private static ArrayList<PaisAfectado> listaDatosPaises = null;

    // Modificamos el método para que devuelva un ArrayList de tipo PaisAfectado.
    public static ArrayList<PaisAfectado> getListaDatosPaises() {
        // Ponemos un if para comprobar si la lista esta vacia, si lo esta, la rellenamos.
        if (listaDatosPaises != null)
            return listaDatosPaises;
        // Si está vacío, lo rellenamos, para ello rellenamos el ArrayList con los datos
        // que queremos mostrar mediante el método add.
        listaDatosPaises = new ArrayList<>();
        listaDatosPaises.add(new PaisAfectado("22 de mayo de 1960, 15:11", "Chile"));
        listaDatosPaises.add(new PaisAfectado("26 de diciembre de 2004, 07:58", "Indonesia"));
        listaDatosPaises.add(new PaisAfectado("27 de marzo de 1964, 17:36", "Estados Unidos"));
        listaDatosPaises.add(new PaisAfectado("11 de marzo de 2011, 14:46", "Japón"));
        listaDatosPaises.add(new PaisAfectado("4 de noviembre de 1952, 16:58", "Rusia"));
        listaDatosPaises.add(new PaisAfectado("13 de agosto de 1868, 21:30", "Chile"));
        listaDatosPaises.add(new PaisAfectado("28 de octubre de 1746, 22:30", "Perú"));
        listaDatosPaises.add(new PaisAfectado("26 de enero de 1700, 21:30", "Estados Unidos"));
        listaDatosPaises.add(new PaisAfectado("27 de febrero de 2010, 03:34", "Chile"));
        listaDatosPaises.add(new PaisAfectado("6 de febrero de 2023, 01:17", "Turquía"));
        listaDatosPaises.add(new PaisAfectado("6 de febrero de 2023, 01:17", "Siria"));
        listaDatosPaises.add(new PaisAfectado("31 de enero de 1906, 15:36", "Ecuador"));
        listaDatosPaises.add(new PaisAfectado("31 de enero de 1906, 15:36", "Colombia"));
        listaDatosPaises.add(new PaisAfectado("25 de noviembre de 1833, 20:00", "Indonesia"));
        listaDatosPaises.add(new PaisAfectado("1 de noviembre de 1755, 10:16", "Portugal"));
        listaDatosPaises.add(new PaisAfectado("8 de julio de 1730, 04:45", "Chile"));
        listaDatosPaises.add(new PaisAfectado("11 de abril de 2012, 15:38", "Indonesia"));
        listaDatosPaises.add(new PaisAfectado("28 de marzo de 2005, 23:09", "Indonesia"));
        listaDatosPaises.add(new PaisAfectado("9 de marzo de 1957, 14:22", "Estados Unidos"));
        listaDatosPaises.add(new PaisAfectado("15 de agosto de 1950", "India"));
        listaDatosPaises.add(new PaisAfectado("15 de agosto de 1950", "China"));
        listaDatosPaises.add(new PaisAfectado("10 de noviembre de 1922, 23:53", "Argentina"));
        listaDatosPaises.add(new PaisAfectado("28 de marzo de 1787, 11:30", "México"));
        listaDatosPaises.add(new PaisAfectado("3 de febrero de 1923, 04:58", "Rusia"));
        listaDatosPaises.add(new PaisAfectado("20 de octubre de 1687, 09:15", "Perú"));
        listaDatosPaises.add(new PaisAfectado("16 de diciembre de 1575, 14:30", "Chile"));
        listaDatosPaises.add(new PaisAfectado("16 de septiembre de 2015, 19:54", "Chile"));
        listaDatosPaises.add(new PaisAfectado("23 de junio de 2001, 15:33", "Perú"));

        // Se retorna la lista de datos de paises.
        return listaDatosPaises;
    }
}