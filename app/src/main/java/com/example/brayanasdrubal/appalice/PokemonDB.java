package com.example.brayanasdrubal.appalice;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class PokemonDB {
    private static ArrayList<Pokemon> listaPokemon = new ArrayList<>(Arrays.asList(
            new Pokemon(0, "aletas","aletas",  false)
            ,new Pokemon(1, "almohada", "almohada",false)
            ,new Pokemon(2, "anillo", "anillo",false)
            ,new Pokemon(3, "arbol", "arbol",false)
            ,new Pokemon(4, "arcoiris", "arcoiris",false)
            ,new Pokemon(5, "balon", "balon",false)
            ,new Pokemon(6, "billete", "billete",false)
            ,new Pokemon(7, "bufanda", "bufanda",false)
            ,new Pokemon(8, "caballo", "caballo",false)
            ,new Pokemon(9, "calculadora", "calculadora",false)
            ,new Pokemon(10, "cama", "cama",false)
            ,new Pokemon(11, "camara", "camara",false)
            ,new Pokemon(12, "carro", "carro",false)
            ,new Pokemon(13, "cepillo", "cepillo",false)
            ,new Pokemon(14, "corazon", "corazon",false)
            ,new Pokemon(15, "cuchara", "cuchara",false)
            ,new Pokemon(16, "estrella", "estrella",false)
            ,new Pokemon(17, "fresa", "fresa",false)
            ,new Pokemon(18, "gafas", "gafas",false)
            ,new Pokemon(19, "lapiz", "lapiz",false)
            ,new Pokemon(20, "libro", "libro",false)
            ,new Pokemon(21, "mano", "mano",false)
            ,new Pokemon(22, "mariposa", "mariposa",false)
            ,new Pokemon(23, "martillo", "martillo",false)
            ,new Pokemon(24, "mesa", "mesa",false)
            ,new Pokemon(25, "ojo", "ojo",false)
            ,new Pokemon(26, "pala", "pala",false)
            ,new Pokemon(27, "perro", "perro",false)
            ,new Pokemon(28, "puerta", "puerta",false)
            ,new Pokemon(29, "pulpo", "pulpo",false)
            ,new Pokemon(30, "reloj", "reloj",false)
            ,new Pokemon(31, "rosa", "rosa",false)
            ,new Pokemon(32, "silla", "silla",false)
            ,new Pokemon(33, "sombrero", "sombrero",false)
            ,new Pokemon(34, "sombrilla", "sombrilla",false)
            ,new Pokemon(35, "telefono", "telefono",false)
            ,new Pokemon(36, "televisor", "televisor",false)
            ,new Pokemon(37, "tren", "tren",false)
            ,new Pokemon(38, "uvas", "uvas",false)
            ,new Pokemon(39, "vaso", "vaso",false)
             ));

    public static int ADIVINADOS=0;
    public static int INTENTOS =3;
    public static int NUMEROGENERADO =0;
    private static boolean ACTIVATE_SOUND=true;

    public static String getNombre(int id)
    {
        return listaPokemon.get(id).getNombre().toLowerCase().replace(" ", "_");
    }

    public static String getSombra(int id)
    {
        return listaPokemon.get(id).getSombra().toLowerCase().replace(" ", "_");
    }

    public static Pokemon getPokemon(int id)
    {
        return listaPokemon.get(id);
    }
    public static int getTamaño()
    {
        return listaPokemon.size();
    }
    public static boolean isAdivinado(int id)
    {
        return listaPokemon.get(id).isAdivinado();
    }

    public static void setAdivinado(int id, boolean valor)
    {
        listaPokemon.get(id).setAdivinado(valor);
    }

    public static void cargarDatos(Context c)
    {
        SharedPreferences mispreferencias = c.getSharedPreferences("DatosJuego", Context.MODE_PRIVATE);
        INTENTOS =mispreferencias.getInt("INTENTOS",3);
        ADIVINADOS=mispreferencias.getInt("ADIVINADOS",0);
        ACTIVATE_SOUND=mispreferencias.getBoolean("SONIDO",true);
        for (Pokemon elemento : listaPokemon)
        {
            elemento.setAdivinado(mispreferencias.getBoolean(elemento.getNombre(), false));
        }
    }

    public static void guardarDatos(Context c)
    {
        SharedPreferences mispreferencias = c.getSharedPreferences("DatosJuego", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mispreferencias.edit();
        editor.putInt("INTENTOS", INTENTOS);
        editor.putInt("ADIVINADOS",ADIVINADOS);
        editor.putBoolean("SONIDO",ACTIVATE_SOUND);
        for (Pokemon elemento : listaPokemon)
        {
            editor.putBoolean(elemento.getNombre(), elemento.isAdivinado());
        }
        editor.apply();
    }

    public static void cargarConfiguracion(Context c)
    {
        SharedPreferences mispreferencias = c.getSharedPreferences("DatosConfiguracion", Context.MODE_PRIVATE);
        ACTIVATE_SOUND=mispreferencias.getBoolean("SONIDO",true);
    }

    public static void guardarConfiguracion(Context c)
    {
        SharedPreferences mispreferencias = c.getSharedPreferences("DatosConfiguracion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mispreferencias.edit();
        editor.putBoolean("SONIDO",ACTIVATE_SOUND);
        editor.apply();
    }

    public static void removerConfiguracion(Context c)
    {
        SharedPreferences settings = c.getSharedPreferences("DatosConfiguracion", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }

    public static void removerDatos(Context c)
    {
        SharedPreferences settings = c.getSharedPreferences("DatosJuego", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }

    public static boolean isPokemon(String x)
    {
        return listaPokemon.get(NUMEROGENERADO).getNombre().equalsIgnoreCase(x);
    }

    public static void DisminuirIntentos()
    {
        INTENTOS--;
    }
    public static boolean isGameOver()
    {
        return INTENTOS==0;
    }
    public static boolean isWin()
    {
        return ADIVINADOS==getTamaño();
    }
    public static boolean isACTIVATE_SOUND() {
        return ACTIVATE_SOUND;
    }

    public static void setACTIVATE_SOUND(boolean ACTIVATE_SOUND) {
        PokemonDB.ACTIVATE_SOUND = ACTIVATE_SOUND;
    }
}
