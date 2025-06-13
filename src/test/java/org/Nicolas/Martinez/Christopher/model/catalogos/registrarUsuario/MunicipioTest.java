package org.Nicolas.Martinez.Christopher.model.catalogos.registrarUsuario;

import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioTest {

    private Municipio municipioManager = Municipio.getManage();
    private CatalogoDaoImpl<Municipio> municipioDao = new CatalogoDaoImpl<>(Municipio.class);
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final Long ID_MUNICIPIO_PRUEBA = 2L;
    private static final Integer ID_ESTADO_PRUEBA = 5;


    @Test
    void altas() {
        System.out.println(ANSI_CYAN + "\n--- Ejecutando test: altas() para Municipio (Creación Automática) ---" + ANSI_RESET);
        String nombreNuevoMunicipio = "Iztapalacra" ;
        System.out.println(ANSI_CYAN + "Intentando dar de alta el municipio con nombre: '" + nombreNuevoMunicipio + "'" + ANSI_RESET);
        boolean altaExitosa = municipioManager.altas(nombreNuevoMunicipio, ID_ESTADO_PRUEBA);
        assertTrue(altaExitosa, "La alta del municipio debería haber sido exitosa.");
        List<Municipio> municipiosEnBD = municipioDao.findAll();
        boolean encontrada = false;
        for (Municipio m : municipiosEnBD) {
            if (m.getNombre().equals(nombreNuevoMunicipio)) {
                encontrada = true;
                break;
            }
        }
        assertTrue(encontrada, "El municipio con nombre '" + nombreNuevoMunicipio + "' debería haber sido encontrado en la base de datos.");
        System.out.println(ANSI_CYAN + "Test de altas() finalizado. Municipio '" + nombreNuevoMunicipio + "' creado y verificado." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "-------------------------------------------\n" + ANSI_RESET);
    }

    @Test
    void actualizaciones() {
        System.out.println(ANSI_CYAN + "\n--- Ejecutando test: actualizaciones() para Municipio ---" + ANSI_RESET);
        Municipio municipioPreCheck = municipioDao.findById(Math.toIntExact(ID_MUNICIPIO_PRUEBA));
        assertNotNull(municipioPreCheck, "ERROR: El municipio con ID " + ID_MUNICIPIO_PRUEBA + " NO existe. Asegúrate de que esté en tu DB.");
        System.out.println("Municipio con ID " + ID_MUNICIPIO_PRUEBA + " encontrado. Nombre actual: " + municipioPreCheck.getNombre());
        String nuevoNombre = "Iztapalapa" ;
        Integer nuevoIdEstado = ID_ESTADO_PRUEBA;
        System.out.println(ANSI_CYAN + "Intentando actualizar Municipio con ID: " + ID_MUNICIPIO_PRUEBA + " a nombre: " + nuevoNombre + ANSI_RESET);
        municipioManager.actualizaciones(ID_MUNICIPIO_PRUEBA, nuevoNombre, nuevoIdEstado);
        Municipio municipioActualizadoEnBD = municipioDao.findById(Math.toIntExact(ID_MUNICIPIO_PRUEBA));
        assertNotNull(municipioActualizadoEnBD, "El municipio actualizado no debería ser nulo en la BD.");
        assertEquals(nuevoNombre, municipioActualizadoEnBD.getNombre(), "El nombre del municipio debería haberse actualizado correctamente.");
        assertEquals(nuevoIdEstado, municipioActualizadoEnBD.getIdEstado(), "El ID del estado del municipio debería haberse actualizado correctamente.");
        System.out.println(ANSI_CYAN + "Test de actualizaciones() finalizado. Verifica la DB." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "-----------------------------------------------\n" + ANSI_RESET);
    }

    @Test
    void bajas() {
        System.out.println(ANSI_CYAN + "\n--- Ejecutando test: bajas() para Municipio ---" + ANSI_RESET);
        Municipio municipioPreCheck = municipioDao.findById(Math.toIntExact(ID_MUNICIPIO_PRUEBA));
        assertNotNull(municipioPreCheck, "ERROR: El municipio con ID " + ID_MUNICIPIO_PRUEBA + " NO existe. Asegúrate de que esté en tu DB antes de ejecutar este test.");
        System.out.println("Municipio con ID " + ID_MUNICIPIO_PRUEBA + " encontrado. Nombre actual: " + municipioPreCheck.getNombre());
        System.out.println(ANSI_CYAN + "Intentando eliminar Municipio con ID: " + ID_MUNICIPIO_PRUEBA + ANSI_RESET);
        municipioManager.bajas(ID_MUNICIPIO_PRUEBA);
        Municipio municipioEliminado = municipioDao.findById(Math.toIntExact(ID_MUNICIPIO_PRUEBA));
        assertNull(municipioEliminado, "El municipio debería haber sido eliminado de la base de datos con éxito.");
        System.out.println(ANSI_CYAN + "Test de bajas() finalizado. Por favor, verifica tu DB (TBL_MUNICIPIO) si el registro fue eliminado." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "-------------------------------------------\n" + ANSI_RESET);
    }

    @Test
    void lista() {
        System.out.println(ANSI_CYAN + "\n--- Ejecutando test: lista() para Municipio ---" + ANSI_RESET);
        System.out.println("A continuación se mostrará la lista de registros de Municipio de tu base de datos real.");
        municipioManager.lista();
        System.out.println(ANSI_CYAN + "Test de lista() finalizado. Revisa la salida de la consola." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "-----------------------------------\n" + ANSI_RESET);
        List<Municipio> municipiosEnBD = municipioDao.findAll();
        assertNotNull(municipiosEnBD, "La lista de municipios no debería ser nula.");
    }
}