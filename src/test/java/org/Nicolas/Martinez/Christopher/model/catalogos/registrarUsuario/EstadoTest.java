package org.Nicolas.Martinez.Christopher.model.catalogos.registrarUsuario;

import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.*;

class EstadoTest {

    // Instancias de los managers, se inicializan una vez cuando se carga la clase de test
    private Estado estadoManager = Estado.getManage();
    private CatalogoDaoImpl<Estado> estadoDao = new CatalogoDaoImpl<>(Estado.class);
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final Long ID_ESTADO_PRUEBA = 7L; // Un ID de un Estado que ya exista


    @Test
    void altas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: altas() para Estado (Creación Automática) ---" + ANSI_RESET);
        String nombreNuevoEstado = "Queretaro";
        System.out.println(ANSI_GREEN + "Intentando dar de alta el estado con nombre: '" + nombreNuevoEstado + "'" + ANSI_RESET);
        boolean altaExitosa = estadoManager.altas(nombreNuevoEstado);
        assertTrue(altaExitosa, "La alta del estado debería haber sido exitosa.");
        List<Estado> estadosEnBD = estadoDao.findAll();
        boolean encontrada = false;
        for (Estado e : estadosEnBD) {
            if (e.getNombre().equals(nombreNuevoEstado)) {
                encontrada = true;
                break;
            }
        }
        assertTrue(encontrada, "El estado con nombre '" + nombreNuevoEstado + "' debería haber sido encontrado en la base de datos.");
        System.out.println(ANSI_GREEN + "Test de altas() finalizado. Estado '" + nombreNuevoEstado + "' creado y verificado." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "-------------------------------------------\n" + ANSI_RESET);
    }

    @Test
    void actualizaciones() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: actualizaciones() para Estado ---" + ANSI_RESET);
        Estado estadoPreCheck = estadoDao.findById(Math.toIntExact(ID_ESTADO_PRUEBA));
        assertNotNull(estadoPreCheck, "ERROR: El estado con ID " + ID_ESTADO_PRUEBA + " NO existe. Asegúrate de que esté en tu DB.");
        System.out.println("Estado con ID " + ID_ESTADO_PRUEBA + " encontrado. Nombre actual: " + estadoPreCheck.getNombre());
        String nuevoNombre = "Michoacan" ;
        System.out.println(ANSI_GREEN + "Intentando actualizar Estado con ID: " + ID_ESTADO_PRUEBA + " a nombre: " + nuevoNombre + ANSI_RESET);
        estadoManager.actualizaciones(ID_ESTADO_PRUEBA, nuevoNombre);
        Estado estadoActualizadoEnBD = estadoDao.findById(Math.toIntExact(ID_ESTADO_PRUEBA));
        assertNotNull(estadoActualizadoEnBD, "El estado actualizado no debería ser nulo en la BD.");
        assertEquals(nuevoNombre, estadoActualizadoEnBD.getNombre(), "El nombre del estado debería haberse actualizado correctamente.");
        System.out.println(ANSI_GREEN + "Test de actualizaciones() finalizado. Verifica la DB." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "-----------------------------------------------\n" + ANSI_RESET);
    }

    @Test
    void bajas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: bajas() para Estado ---" + ANSI_RESET);
        Estado estadoPreCheck = estadoDao.findById(Math.toIntExact(ID_ESTADO_PRUEBA));
        assertNotNull(estadoPreCheck, "ERROR: El estado con ID " + ID_ESTADO_PRUEBA + " NO existe. Asegúrate de que esté en tu DB antes de ejecutar este test.");
        System.out.println("Estado con ID " + ID_ESTADO_PRUEBA + " encontrado. Nombre actual: " + estadoPreCheck.getNombre());
        System.out.println(ANSI_GREEN + "Intentando eliminar Estado con ID: " + ID_ESTADO_PRUEBA + ANSI_RESET);
        estadoManager.bajas(ID_ESTADO_PRUEBA);
        Estado estadoEliminado = estadoDao.findById(Math.toIntExact(ID_ESTADO_PRUEBA));
        assertNull(estadoEliminado, "El estado debería haber sido eliminado de la base de datos con éxito.");
        System.out.println(ANSI_GREEN + "Test de bajas() finalizado. Por favor, verifica tu DB (TBL_ESTADO) si el registro fue eliminado." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "-------------------------------------------\n" + ANSI_RESET);
    }

    @Test
    void lista() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: lista() para Estado ---" + ANSI_RESET);
        System.out.println("A continuación se mostrará la lista de registros de Estado de tu base de datos real.");
        estadoManager.lista();
        System.out.println(ANSI_GREEN + "Test de lista() finalizado. Revisa la salida de la consola." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "-----------------------------------\n" + ANSI_RESET);
        List<Estado> estadosEnBD = estadoDao.findAll();
        assertNotNull(estadosEnBD, "La lista de estados no debería ser nula.");
    }
}
