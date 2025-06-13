package org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco;

import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscoTest {

    private Disco discoManager = Disco.getManage();
    private CatalogoDaoImpl<Disco> discoDao = new CatalogoDaoImpl<>(Disco.class);
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final Long ID_DISCO_PRUEBA = 2L;
    private static final Integer ID_ARTISTA_PRUEBA = 3;
    private static final Integer ID_DISQUERA_PRUEBA = 2;
    private static final Integer ID_GENERO_PRUEBA = 2;


    @Test
    void altas() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: altas() para Disco (Creación Automática) ---" );
        String nombreNuevoDisco = "Press Play" ; // Nombre único
        Double precio = 27.99;
        Integer existencia = 50;
        Double descuento = 0.02; // 5% de descuento
        String fechaLanzamiento = "06/02/2025";
        String imagen = "ruta/imagen/" + ".jpg";
        System.out.println( "Intentando dar de alta el disco con nombre: '" + nombreNuevoDisco + "'" );
        boolean altaExitosa = discoManager.altas(nombreNuevoDisco, precio, existencia, descuento, fechaLanzamiento, imagen, ID_ARTISTA_PRUEBA, ID_DISQUERA_PRUEBA, ID_GENERO_PRUEBA);
        assertTrue(altaExitosa, "La alta del disco debería haber sido exitosa.");
        List<Disco> discosEnBD = discoDao.findAll();
        boolean encontrada = false;
        for (Disco d : discosEnBD) {
            if (d.getNombre().equals(nombreNuevoDisco)) {
                encontrada = true;
                break;
            }
        }
        assertTrue(encontrada, "El disco con nombre '" + nombreNuevoDisco + "' debería haber sido encontrado en la base de datos.");
        System.out.println("Test de altas() finalizado. Disco '" + nombreNuevoDisco + "' creado y verificado." );
        System.out.println("-------------------------------------------\n" );
    }

    @Test
    void actualizaciones() {
        System.out.println(ANSI_GREEN + "\n--- Ejecutando test: actualizaciones() para Disco ---" );
        Disco discoPreCheck = discoDao.findById(Math.toIntExact(ID_DISCO_PRUEBA));
        assertNotNull(discoPreCheck, "ERROR: El disco con ID " + ID_DISCO_PRUEBA + " NO existe. Asegúrate de que esté en tu DB.");
        System.out.println("Disco con ID " + ID_DISCO_PRUEBA + " encontrado. Nombre actual: " + discoPreCheck.getNombre());
        String nuevoNombre = "Lost & Found" ;
        Double nuevoPrecio = 28.99;
        Integer nuevaExistencia = 70;
        Double nuevoDescuento = 0.10;
        String nuevaFechaLanzamiento = "06/02/2026";
        String nuevaImagen = "nueva/ruta/imagen/" + ".png";
        Integer nuevoIdArtista = ID_ARTISTA_PRUEBA;
        Integer nuevoIdDisquera = ID_DISQUERA_PRUEBA;
        Integer nuevoIdGenero = ID_GENERO_PRUEBA;
        System.out.println("Intentando actualizar Disco con ID: " + ID_DISCO_PRUEBA + " a nombre: " + nuevoNombre );

        discoManager.actualizaciones(ID_DISCO_PRUEBA, nuevoNombre, nuevoPrecio, nuevaExistencia,
                nuevoDescuento, nuevaFechaLanzamiento, nuevaImagen,
                nuevoIdArtista, nuevoIdDisquera, nuevoIdGenero);
        Disco discoActualizadoEnBD = discoDao.findById(Math.toIntExact(ID_DISCO_PRUEBA));
        assertNotNull(discoActualizadoEnBD, "El disco actualizado no debería ser nulo en la BD.");
        assertEquals(nuevoNombre, discoActualizadoEnBD.getNombre(), "El nombre del disco debería haberse actualizado correctamente.");
        assertEquals(nuevoPrecio, discoActualizadoEnBD.getPrecio(), "El precio del disco debería haberse actualizado correctamente.");
        assertEquals(nuevaExistencia, discoActualizadoEnBD.getExistencia(), "La existencia del disco debería haberse actualizado correctamente.");
        assertEquals(nuevoDescuento, discoActualizadoEnBD.getDescuento(), "El descuento del disco debería haberse actualizado correctamente.");
        assertEquals(nuevaFechaLanzamiento, discoActualizadoEnBD.getFechaLanzamiento(), "La fecha de lanzamiento del disco debería haberse actualizado correctamente.");
        assertEquals(nuevaImagen, discoActualizadoEnBD.getImagen(), "La imagen del disco debería haberse actualizado correctamente.");
        assertEquals(nuevoIdArtista, discoActualizadoEnBD.getIdArtista(), "El ID del artista del disco debería haberse actualizado correctamente.");
        assertEquals(nuevoIdDisquera, discoActualizadoEnBD.getIdDisquera(), "El ID de la disquera del disco debería haberse actualizado correctamente.");
        assertEquals(nuevoIdGenero, discoActualizadoEnBD.getIdGeneroMusical(), "El ID del género musical del disco debería haberse actualizado correctamente.");
        System.out.println("Test de actualizaciones() finalizado. Verifica la DB." );
        System.out.println("-----------------------------------------------\n" );
    }

    @Test
    void bajas() {
        System.out.println(ANSI_GREEN+"\n--- Ejecutando test: bajas() para Disco ---" );
        Disco discoPreCheck = discoDao.findById(Math.toIntExact(ID_DISCO_PRUEBA));
        assertNotNull(discoPreCheck, "ERROR: El disco con ID " + ID_DISCO_PRUEBA + " NO existe. Asegúrate de que esté en tu DB antes de ejecutar este test.");
        System.out.println("Disco con ID " + ID_DISCO_PRUEBA + " encontrado. Nombre actual: " + discoPreCheck.getNombre());
        System.out.println("Intentando eliminar Disco con ID: " + ID_DISCO_PRUEBA );
        discoManager.bajas(ID_DISCO_PRUEBA);
        Disco discoEliminado = discoDao.findById(Math.toIntExact(ID_DISCO_PRUEBA));
        assertNull(discoEliminado, "El disco debería haber sido eliminado de la base de datos con éxito.");
        System.out.println( "Test de bajas() finalizado. Por favor, verifica tu DB (TBL_DISCO) si el registro fue eliminado." );
        System.out.println("-------------------------------------------\n" );
    }

    @Test
    void lista() {
        System.out.println(ANSI_GREEN+"\n--- Ejecutando test: lista() para Disco ---" );
        System.out.println("A continuación se mostrará la lista de registros de Disco de tu base de datos real.");
        discoManager.lista();
        System.out.println("Test de lista() finalizado. Revisa la salida de la consola." );
        System.out.println("-----------------------------------\n" );
        List<Disco> discosEnBD = discoDao.findAll();
        assertNotNull(discosEnBD, "La lista de discos no debería ser nula.");
    }
}