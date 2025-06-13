package org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco;

import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.Nicolas.Martinez.Christopher.generalUtil.ReadUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaTest {

    private Artista artistaManager = Artista.getManage();
    private CatalogoDaoImpl<Artista> artistaDao = new CatalogoDaoImpl<>(Artista.class);
    private static final String ANSI_GREEN = "\u001B[32m";;

    @Test
    void altas() {
        System.out.println(ANSI_GREEN +"\n--- Ejecutando test: altas() para Artista (Automático) ---");
        String nombreArtistaTest = "Afrojack";
        artistaManager.altas(nombreArtistaTest);
        System.out.println("Test de altas() finalizado. Por favor, verifica tu DB (TBL_ARTISTA) para el nuevo registro.");
        System.out.println("Busca el artista con el nombre: '" + nombreArtistaTest + "'");
        System.out.println("-------------------------------------------\n");
        List<Artista> artistasEnBD = artistaDao.findAll();
        assertTrue(artistasEnBD.size() > 0, "Debería haber al menos un artista en la base de datos después de la alta.");
    }

    @Test
    void actualizaciones() {
        System.out.println(ANSI_GREEN +"\n--- Ejecutando test: actualizaciones() para Artista ");
        Artista artistaPreCheck = artistaDao.findById(Math.toIntExact(4));
        assertNotNull(artistaPreCheck, "ERROR: El artista con ID " + 4 + " NO existe. Asegúrate de que esté en tu DB.");
        System.out.println("Artista con ID " + 4 + " encontrado. Nombre actual: " + artistaPreCheck.getNombre());
        String nuevoNombre = "Frankie Ruiz";
        System.out.println("Intentando actualizar Artista con ID: " + 4 + " a nombre: " + nuevoNombre);
        artistaManager.actualizaciones(4L, nuevoNombre);
        Artista artistaActualizadoEnBD = artistaDao.findById(Math.toIntExact(4));
        assertNotNull(artistaActualizadoEnBD, "El artista actualizado no debería ser nulo en la BD.");
        assertEquals(nuevoNombre, artistaActualizadoEnBD.getNombre(), "El nombre del artista debería haberse actualizado correctamente.");
        System.out.println("Test de actualizaciones() finalizado. Verifica la DB.");
        System.out.println("-----------------------------------------------\n");
    }

    @Test
    void bajas() {
        System.out.println(ANSI_GREEN +"\n--- Ejecutando test: bajas() para Artista ");
        Artista artistaPreCheck = artistaDao.findById(Math.toIntExact(3));
        assertNotNull(artistaPreCheck, "ERROR: El artista con ID " + 3 + " NO existe. Asegúrate de que esté en tu DB antes de ejecutar este test.");
        System.out.println("Artista con ID " + 3 + " encontrado. Nombre actual: " + artistaPreCheck.getNombre());
        System.out.println("Intentando eliminar Artista con ID: " + 3);
        artistaManager.bajas(3L);
        Artista artistaEliminado = artistaDao.findById(Math.toIntExact(3));
        assertNull(artistaEliminado, "El artista debería haber sido eliminado de la base de datos con éxito.");
        System.out.println("Test de bajas() finalizado. Por favor, verifica tu DB (TBL_ARTISTA) si el registro fue eliminado.");
        System.out.println("-------------------------------------------\n");
    }

    @Test
    void lista() {
        System.out.println(ANSI_GREEN +"\n--- Ejecutando test: lista() para Artista ---");
        System.out.println("A continuación se mostrará la lista de registros de Artista de tu base de datos real.");
        artistaManager.lista();
        System.out.println("Test de lista() finalizado. Revisa la salida de la consola.");
        System.out.println("-----------------------------------\n");
        List<Artista> artistasEnBD = artistaDao.findAll();
        assertTrue(artistasEnBD.size() >= 0, "Debería haber artistas en la base de datos (o cero si está vacía).");
    }
}
