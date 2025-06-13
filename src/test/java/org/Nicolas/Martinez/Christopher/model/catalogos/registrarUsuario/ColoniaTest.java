package org.Nicolas.Martinez.Christopher.model.catalogos.registrarUsuario;

import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColoniaTest {

    private Colonia coloniaManager = Colonia.getManage();
    private CatalogoDaoImpl<Colonia> coloniaDao = new CatalogoDaoImpl<>(Colonia.class);
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final Long ID_COLONIA_PRUEBA = 2L;
    private static final Integer ID_MUNICIPIO_PRUEBA = 2;


    @Test
    void altas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: altas() para Colonia (Creación Automática) ---" + ANSI_RESET);
        String nombreNuevaColonia = "Santa Cecilia";
        System.out.println(ANSI_GREEN + "Intentando dar de alta la colonia con nombre: '" + nombreNuevaColonia + "'" + ANSI_RESET);
        boolean altaExitosa = coloniaManager.altas(nombreNuevaColonia, ID_MUNICIPIO_PRUEBA);
        assertTrue(altaExitosa, "La alta de la colonia debería haber sido exitosa.");
        List<Colonia> coloniasEnBD = coloniaDao.findAll();
        boolean encontrada = false;
        for (Colonia c : coloniasEnBD) {
            if (c.getNombre().equals(nombreNuevaColonia)) {
                encontrada = true;
                break;
            }
        }
        assertTrue(encontrada, "La colonia con nombre '" + nombreNuevaColonia + "' debería haber sido encontrada en la base de datos.");
        System.out.println(ANSI_GREEN + "Test de altas() finalizado. Colonia '" + nombreNuevaColonia + "' creada y verificada." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "-------------------------------------------\n" + ANSI_RESET);
    }

    @Test
    void actualizaciones() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: actualizaciones() para Colonia ---" + ANSI_RESET);
        Colonia coloniaPreCheck = coloniaDao.findById(Math.toIntExact(ID_COLONIA_PRUEBA));
        assertNotNull(coloniaPreCheck, "ERROR: La colonia con ID " + ID_COLONIA_PRUEBA + " NO existe. Asegúrate de que esté en tu DB.");
        System.out.println("Colonia con ID " + ID_COLONIA_PRUEBA + " encontrada. Nombre actual: " + coloniaPreCheck.getNombre());
        String nuevoNombre = "Santa Cruz";
        Integer nuevoIdMunicipio = ID_MUNICIPIO_PRUEBA;
        System.out.println(ANSI_GREEN + "Intentando actualizar Colonia con ID: " + ID_COLONIA_PRUEBA + " a nombre: " + nuevoNombre + ANSI_RESET);
        coloniaManager.actualizaciones(ID_COLONIA_PRUEBA, nuevoNombre, nuevoIdMunicipio);
        Colonia coloniaActualizadaEnBD = coloniaDao.findById(Math.toIntExact(ID_COLONIA_PRUEBA));
        assertNotNull(coloniaActualizadaEnBD, "La colonia actualizada no debería ser nula en la BD.");
        assertEquals(nuevoNombre, coloniaActualizadaEnBD.getNombre(), "El nombre de la colonia debería haberse actualizado correctamente.");
        assertEquals(nuevoIdMunicipio, coloniaActualizadaEnBD.getIdMunicipio(), "El ID del municipio de la colonia debería haberse actualizado correctamente.");
        System.out.println(ANSI_GREEN + "Test de actualizaciones() finalizado. Verifica la DB." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "-----------------------------------------------\n" + ANSI_RESET);
    }

    @Test
    void bajas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: bajas() para Colonia ---" + ANSI_RESET);
        Colonia coloniaPreCheck = coloniaDao.findById(Math.toIntExact(ID_COLONIA_PRUEBA));
        assertNotNull(coloniaPreCheck, "ERROR: La colonia con ID " + ID_COLONIA_PRUEBA + " NO existe. Asegúrate de que esté en tu DB antes de ejecutar este test.");
        System.out.println("Colonia con ID " + ID_COLONIA_PRUEBA + " encontrada. Nombre actual: " + coloniaPreCheck.getNombre());
        System.out.println(ANSI_GREEN + "Intentando eliminar Colonia con ID: " + ID_COLONIA_PRUEBA + ANSI_RESET);
        coloniaManager.bajas(ID_COLONIA_PRUEBA);
        Colonia coloniaEliminada = coloniaDao.findById(Math.toIntExact(ID_COLONIA_PRUEBA));
        assertNull(coloniaEliminada, "La colonia debería haber sido eliminada de la base de datos con éxito.");
        System.out.println(ANSI_GREEN + "Test de bajas() finalizado. Por favor, verifica tu DB (TBL_COLONIA) si el registro fue eliminado." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "-------------------------------------------\n" + ANSI_RESET);
    }

    @Test
    void lista() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: lista() para Colonia ---" + ANSI_RESET);
        System.out.println("A continuación se mostrará la lista de registros de Colonia de tu base de datos real.");
        coloniaManager.lista();
        System.out.println(ANSI_GREEN + "Test de lista() finalizado. Revisa la salida de la consola." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "-----------------------------------\n" + ANSI_RESET);
        List<Colonia> coloniasEnBD = coloniaDao.findAll();
        assertNotNull(coloniasEnBD, "La lista de colonias no debería ser nula.");
    }
}