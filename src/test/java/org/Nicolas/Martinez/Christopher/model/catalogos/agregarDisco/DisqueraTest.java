package org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco;

import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisqueraTest {

    private Disquera disqueraManager = Disquera.getManage();
    private CatalogoDaoImpl<Disquera> disqueraDao = new CatalogoDaoImpl<>(Disquera.class);
    private static final String ANSI_GREEN = "\u001B[32m";

    @Test
    void altas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: altas() para Disquera (Creación Automática) ---" );
        String nombreNuevaDisquera = "BMG" ;
        System.out.println("Intentando dar de alta la disquera con nombre: '" + nombreNuevaDisquera + "'" );
        boolean altaExitosa = disqueraManager.altas(nombreNuevaDisquera);
        assertTrue(altaExitosa, "La alta de la disquera debería haber sido exitosa.");
        List<Disquera> disquerasEnBD = disqueraDao.findAll();
        boolean encontrada = false;
        for (Disquera d : disquerasEnBD) {
            if (d.getNombre().equals(nombreNuevaDisquera)) {
                encontrada = true;
                break;
            }
        }
        assertTrue(encontrada, "La disquera con nombre '" + nombreNuevaDisquera + "' debería haber sido encontrada en la base de datos.");
        System.out.println("Test de altas() finalizado. Disquera '" + nombreNuevaDisquera + "' creada y verificada.");
        System.out.println( "-------------------------------------------\n" );
    }

    @Test
    void actualizaciones() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: actualizaciones() para Disquera ---" );
        Disquera disqueraPreCheck = disqueraDao.findById(Math.toIntExact(2));
        assertNotNull(disqueraPreCheck, "ERROR: La disquera con ID " + 2 + " NO existe. Asegúrate de que esté en tu DB.");
        System.out.println("Disquera con ID " + 2 + " encontrada. Nombre actual: " + disqueraPreCheck.getNombre());
        String nuevoNombre = "Beggars Group";
        System.out.println("Intentando actualizar Disquera con ID: " + 2 + " a nombre: " + nuevoNombre );
        disqueraManager.actualizaciones(2L, nuevoNombre);
        Disquera disqueraActualizadaEnBD = disqueraDao.findById(Math.toIntExact(2));
        assertNotNull(disqueraActualizadaEnBD, "La disquera actualizada no debería ser nula en la BD.");
        assertEquals(nuevoNombre, disqueraActualizadaEnBD.getNombre(), "El nombre de la disquera debería haberse actualizado correctamente.");
        System.out.println( "Test de actualizaciones() finalizado. Verifica la DB." );
        System.out.println( "-----------------------------------------------\n" );
    }

    @Test
    void bajas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: bajas() para Disquera ---" );
        Disquera disqueraPreCheck = disqueraDao.findById(Math.toIntExact(2));
        assertNotNull(disqueraPreCheck, "ERROR: La disquera con ID " + 2 + " NO existe. Asegúrate de que esté en tu DB antes de ejecutar este test.");
        System.out.println("Disquera con ID " + 2 + " encontrada. Nombre actual: " + disqueraPreCheck.getNombre());
        System.out.println("Intentando eliminar Disquera con ID: " + 2 );
        disqueraManager.bajas(2L);
        Disquera disqueraEliminada = disqueraDao.findById(Math.toIntExact(2));
        assertNull(disqueraEliminada, "La disquera debería haber sido eliminada de la base de datos con éxito.");
        System.out.println("Test de bajas() finalizado. Por favor, verifica tu DB (TBL_DISQUERA) si el registro fue eliminado." );
        System.out.println( "-------------------------------------------\n");
    }

    @Test
    void lista() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: lista() para Disquera ---" );
        System.out.println("A continuación se mostrará la lista de registros de Disquera de tu base de datos real.");
        disqueraManager.lista();
        System.out.println("Test de lista() finalizado. Revisa la salida de la consola." );
        System.out.println( "-----------------------------------\n" );
        List<Disquera> disquerasEnBD = disqueraDao.findAll();
        assertNotNull(disquerasEnBD, "La lista de disqueras no debería ser nula.");
    }
}