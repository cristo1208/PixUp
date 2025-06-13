package org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco;

import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneroMusicalTest {
    private GeneroMusical generoMusicalManager = GeneroMusical.getManage();
    private CatalogoDaoImpl<GeneroMusical> generoMusicalDao = new CatalogoDaoImpl<>(GeneroMusical.class);
    private static final String ANSI_GREEN = "\u001B[32m";

    @Test
    void altas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: altas() para Género Musical (Creación Automática) ---" );
        String nombreNuevoGenero = "Electronica" ;
        System.out.println("Intentando dar de alta el género musical con nombre: '" + nombreNuevoGenero + "'" );
        boolean altaExitosa = generoMusicalManager.altas(nombreNuevoGenero);
        assertTrue(altaExitosa, "La alta del género musical debería haber sido exitosa.");
        List<GeneroMusical> generosEnBD = generoMusicalDao.findAll();
        boolean encontrada = false;
        for (GeneroMusical g : generosEnBD) {
            if (g.getNombre().equals(nombreNuevoGenero)) {
                encontrada = true;
                break;
            }
        }
        assertTrue(encontrada, "El género musical con nombre '" + nombreNuevoGenero + "' debería haber sido encontrado en la base de datos.");
        System.out.println("Test de altas() finalizado. Género '" + nombreNuevoGenero + "' creado y verificado." );
        System.out.println("-------------------------------------------\n" );
    }

    @Test
    void actualizaciones() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: actualizaciones() para Género Musical ---" );
        GeneroMusical generoPreCheck = generoMusicalDao.findById(Math.toIntExact(3));
        assertNotNull(generoPreCheck, "ERROR: El género musical con ID " + 3 + " NO existe. Asegúrate de que esté en tu DB.");
        System.out.println("Género Musical con ID " + 3 + " encontrado. Nombre actual: " + generoPreCheck.getNombre());
        String nuevoNombre = "Cumbia";
        System.out.println("Intentando actualizar Género Musical con ID: " + 3 + " a nombre: " + nuevoNombre );
        generoMusicalManager.actualizaciones(3L, nuevoNombre);
        GeneroMusical generoActualizadoEnBD = generoMusicalDao.findById(Math.toIntExact(3));
        assertNotNull(generoActualizadoEnBD, "El género musical actualizado no debería ser nulo en la BD.");
        assertEquals(nuevoNombre, generoActualizadoEnBD.getNombre(), "El nombre del género musical debería haberse actualizado correctamente.");
        System.out.println("Test de actualizaciones() finalizado. Verifica la DB." );
        System.out.println("-----------------------------------------------\n" );
    }

    @Test
    void bajas() {
        System.out.println(ANSI_GREEN +"\n--- Ejecutando test: bajas() para Género Musical ---");
        GeneroMusical generoPreCheck = generoMusicalDao.findById(Math.toIntExact(2));
        assertNotNull(generoPreCheck, "ERROR: El género musical con ID " + 2 + " NO existe. Asegúrate de que esté en tu DB antes de ejecutar este test.");
        System.out.println("Género Musical con ID " + 2 + " encontrado. Nombre actual: " + generoPreCheck.getNombre());
        System.out.println("Intentando eliminar Género Musical con ID: " + 2 );
        generoMusicalManager.bajas(2L);
        GeneroMusical generoEliminado = generoMusicalDao.findById(Math.toIntExact(2));
        assertNull(generoEliminado, "El género musical debería haber sido eliminado de la base de datos con éxito.");
        System.out.println("Test de bajas() finalizado. Por favor, verifica tu DB (TBL_GENEROMUSICAL) si el registro fue eliminado." );
        System.out.println("-------------------------------------------\n" );
    }

    @Test
    void lista() {
        System.out.println(ANSI_GREEN +"\n--- Ejecutando test: lista() para Género Musical ---" );
        System.out.println("A continuación se mostrará la lista de registros de Género Musical de tu base de datos real.");
        generoMusicalManager.lista();
        System.out.println("Test de lista() finalizado. Revisa la salida de la consola.");
        System.out.println("-----------------------------------\n" );
        List<GeneroMusical> generosEnBD = generoMusicalDao.findAll();
        assertNotNull(generosEnBD, "La lista de géneros musicales no debería ser nula.");
    }
}