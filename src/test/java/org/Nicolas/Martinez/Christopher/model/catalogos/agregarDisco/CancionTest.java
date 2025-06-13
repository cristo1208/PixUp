package org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco;

import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancionTest {

    private Cancion cancionManager = Cancion.getManage();
    private CatalogoDaoImpl<Cancion> cancionDao = new CatalogoDaoImpl<>(Cancion.class);
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final Long ID_CANCION_PRUEBA = 2L;
    private static final Integer ID_DISCO_PRUEBA = 2;

    @Test
    void altas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: altas() para Cancion (Creación Automática) ---" );
        String nombreNuevaCancion = "Mia" ; // Nombre único
        Double duracion = 4.25;
        System.out.println( "Intentando dar de alta la canción con nombre: '" + nombreNuevaCancion + "'" );
        boolean altaExitosa = cancionManager.altas(nombreNuevaCancion, duracion, ID_DISCO_PRUEBA);
        assertTrue(altaExitosa, "La alta de la canción debería haber sido exitosa.");
        List<Cancion> cancionesEnBD = cancionDao.findAll();
        boolean encontrada = false;
        for (Cancion c : cancionesEnBD) {
            if (c.getNombre().equals(nombreNuevaCancion)) {
                encontrada = true;
                break;
            }
        }
        assertTrue(encontrada, "La canción con nombre '" + nombreNuevaCancion + "' debería haber sido encontrada en la base de datos.");
        System.out.println( "Test de altas() finalizado. Canción '" + nombreNuevaCancion + "' creada y verificada." );
        System.out.println( "-------------------------------------------\n" );
    }

    @Test
    void actualizaciones() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: actualizaciones() para Cancion ---" );
        Cancion cancionPreCheck = cancionDao.findById(Math.toIntExact(ID_CANCION_PRUEBA));
        assertNotNull(cancionPreCheck, "ERROR: La canción con ID " + ID_CANCION_PRUEBA + " NO existe. Asegúrate de que esté en tu DB.");
        System.out.println("Canción con ID " + ID_CANCION_PRUEBA + " encontrada. Nombre actual: " + cancionPreCheck.getNombre());
        String nuevoNombre = "La cura" ;
        Double nuevaDuracion = 4.15;
        Integer nuevoIdDisco = ID_DISCO_PRUEBA;
        System.out.println( "Intentando actualizar Cancion con ID: " + ID_CANCION_PRUEBA + " a nombre: " + nuevoNombre );
        cancionManager.actualizaciones(ID_CANCION_PRUEBA, nuevoNombre, nuevaDuracion, nuevoIdDisco);
        Cancion cancionActualizadaEnBD = cancionDao.findById(Math.toIntExact(ID_CANCION_PRUEBA));
        assertNotNull(cancionActualizadaEnBD, "La canción actualizada no debería ser nula en la BD.");
        assertEquals(nuevoNombre, cancionActualizadaEnBD.getNombre(), "El nombre de la canción debería haberse actualizado correctamente.");
        assertEquals(nuevaDuracion, cancionActualizadaEnBD.getDuracion(), "La duración de la canción debería haberse actualizado correctamente.");
        assertEquals(nuevoIdDisco, cancionActualizadaEnBD.getIdDisco(), "El ID del disco de la canción debería haberse actualizado correctamente.");
        System.out.println(  "Test de actualizaciones() finalizado. Verifica la DB." );
        System.out.println(  "-----------------------------------------------\n" );
    }

    @Test
    void bajas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: bajas() para Cancion ---" );
        Cancion cancionPreCheck = cancionDao.findById(Math.toIntExact(ID_CANCION_PRUEBA));
        assertNotNull(cancionPreCheck, "ERROR: La canción con ID " + ID_CANCION_PRUEBA + " NO existe. Asegúrate de que esté en tu DB antes de ejecutar este test.");
        System.out.println("Canción con ID " + ID_CANCION_PRUEBA + " encontrada. Nombre actual: " + cancionPreCheck.getNombre());
        System.out.println( "Intentando eliminar Cancion con ID: " + ID_CANCION_PRUEBA );
        cancionManager.bajas(ID_CANCION_PRUEBA);
        Cancion cancionEliminada = cancionDao.findById(Math.toIntExact(ID_CANCION_PRUEBA));
        assertNull(cancionEliminada, "La canción debería haber sido eliminada de la base de datos con éxito.");
        System.out.println(  "Test de bajas() finalizado. Por favor, verifica tu DB (TBL_CANCION) si el registro fue eliminado." );
        System.out.println(  "-------------------------------------------\n" );
    }

    @Test
    void lista() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: lista() para Cancion ---" );
        System.out.println("A continuación se mostrará la lista de registros de Cancion de tu base de datos real.");
        cancionManager.lista();
        System.out.println(  "Test de lista() finalizado. Revisa la salida de la consola." );
        System.out.println(  "-----------------------------------\n" );
        List<Cancion> cancionesEnBD = cancionDao.findAll();
        assertNotNull(cancionesEnBD, "La lista de canciones no debería ser nula.");
    }
}