package org.Nicolas.Martinez.Christopher.generalUtil;

public class ConsolaPrinter {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_MAGENTA = "\u001B[35m";// Este es el que ya tenías

    public static String menuPrincipal =
            ANSI_BLUE +
                    "Bienvenido al programa!\n" +
                    "SELECCIONA UNA OPCION\n" +
                    "1.- Consola\n" +
                    "2.- Ventana\n" +
                    "3.- Salir" +
                    ANSI_RESET;

    public static String menuSeccion2 =
            ANSI_BLUE +
                    "---- Menú ----\n" +
                    "¿Qué operación desea realizar?:\n" +
                    "1. Catálogo\n" +
                    "2. Pendiente\n" +
                    "3. Salir" +
                    ANSI_RESET;

    public static String menuUbicacion =
            ANSI_BLUE +
                    "1. Estado\n" +
                    "2. Municipio\n" +
                    "3. Colonia\n" +
                    "4. Artista\n" +
                    "5. Disquera\n" +
                    "6. Género musical\n" +
                    "7. Canción\n" +
                    "8. Disco\n" +
                    "9. Salir" +
                    ANSI_RESET;

    public static String menuABM =
            ANSI_BLUE +
                    "1. Alta\n" +
                    "2. Baja\n" +
                    "3. Ver\n" +
                    "4. Actualizar\n" +
                    "5. Salir" +
                    ANSI_RESET;

    public static String leerId = ANSI_GREEN + "Ingresa el id" + ANSI_RESET;
    public static String leerNombre = ANSI_GREEN + "Ingresa el nombre " + ANSI_RESET;
    public static String leerCP = ANSI_GREEN + "Ingresa el codigo postal " + ANSI_RESET;
    public static String nuevoNombre = ANSI_GREEN + "Ingresa el nuevo nombre" + ANSI_RESET;
    public static String seleccionarOpcion = ANSI_MAGENTA + "Selecciona una opción: " + ANSI_RESET;
    public static String listaVacia = ANSI_RED + "No hay estados registrados" + ANSI_RESET;
    public static String elementoNoEncontrado = ANSI_RED + "No se encontró el elemento" + ANSI_RESET;
    public static void opcionInvalida() {
        System.out.println(ANSI_RED + "La opción no es correcta" + ANSI_RESET);
    }
    public static void errorDato() {
        System.out.println(ANSI_RED + "No es un dato valido" + ANSI_RESET);
    }
    public static void terminarPrograma() {
        System.out.println(ANSI_YELLOW + "Finalizando PixUp..." + ANSI_RESET);
    }
}