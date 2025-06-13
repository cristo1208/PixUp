package org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.Nicolas.Martinez.Christopher.generalUtil.ReadUtil;
import org.Nicolas.Martinez.Christopher.generalUtil.ConsolaPrinter;
import org.Nicolas.Martinez.Christopher.model.catalogos.ClaseCatalogo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "TBL_CANCION")
public class Cancion extends ClaseCatalogo {

    @Column(name = "duracion")
    private Double duracion;

    @Column(name = "idDisco")
    private Integer idDisco;

    protected static CatalogoDaoImpl<Cancion> catalogoDaoImpl = new CatalogoDaoImpl<>(Cancion.class);
    private static Cancion manage = null;

    public static Cancion getManage() {
        if (manage == null) {
            manage = new Cancion();
        }
        return manage;
    }

    @Override
    public void altas() {
        Cancion cancion = new Cancion();
        cancion.setNombre(ReadUtil.read(ConsolaPrinter.leerNombre));
        cancion.setDuracion(Double.valueOf(ReadUtil.read("Duración (en minutos):")));
        System.out.println("Seleccione el Disco al que pertenece:");
        Disco disco = (Disco) buscarCatalogo(Disco.catalogoDaoImpl);
        if (disco != null) cancion.setIdDisco(disco.getId());
        catalogoDaoImpl.guardar(cancion);
    }
    public boolean altas(String nombre, Double duracion, Integer idDisco) {
        System.out.println("-ALTA DE CANCION (Automática para Test)-");
        Cancion cancion = new Cancion();
        cancion.setNombre(nombre);
        cancion.setDuracion(duracion);
        cancion.setIdDisco(idDisco);

        boolean exito = catalogoDaoImpl.guardar(cancion);
        System.out.println("Canción '" + nombre + "' guardada automáticamente. Éxito: " + exito);
        return exito;
    }

    @Override
    public void actualizaciones() {
        Cancion cancion = (Cancion) buscarCatalogo(catalogoDaoImpl);
        if (cancion != null) {
            cancion.setNombre(ReadUtil.read(ConsolaPrinter.nuevoNombre));
            cancion.setDuracion(Double.valueOf(ReadUtil.read("Nueva duración (en minutos):")));

            System.out.println("Seleccione el nuevo Disco:");
            Disco disco = (Disco) buscarCatalogo(Disco.catalogoDaoImpl);
            if (disco != null) cancion.setIdDisco(disco.getId());

            catalogoDaoImpl.actualizar(cancion);
        }
    }
    public void actualizaciones(Long idCancion, String nuevoNombre, Double nuevaDuracion, Integer nuevoIdDisco) {
        System.out.println("-MODIFICACIÓN DE CANCION (Automática para Test)-");
        Cancion cancion = catalogoDaoImpl.findById(Math.toIntExact(idCancion));
        if (cancion != null) {
            cancion.setNombre(nuevoNombre);
            cancion.setDuracion(nuevaDuracion);
            cancion.setIdDisco(nuevoIdDisco);
            catalogoDaoImpl.actualizar(cancion);
            System.out.println("Canción con ID " + idCancion + " actualizada automáticamente.");
        } else {
            System.out.println("Canción con ID " + idCancion + " no encontrada para actualización automática.");
        }
    }

    @Override
    public void bajas() {
        realizarBaja(Cancion.class);
    }
    public void bajas(Long idCancion) {
        System.out.println("-BAJA DE CANCION (Automática para Test)-");
        Cancion cancionParaEliminar = catalogoDaoImpl.findById(Math.toIntExact(idCancion));
        if (cancionParaEliminar != null) {
            catalogoDaoImpl.eliminar(cancionParaEliminar);
            System.out.println("Canción con ID " + idCancion + " eliminada automáticamente.");
        } else {
            System.out.println("Canción con ID " + idCancion + " no encontrada para baja automática.");
        }
    }

    @Override
    public void lista() {
        System.out.println(realizarVista(catalogoDaoImpl));
    }
    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public void setIdDisco(Integer idDisco) {
        this.idDisco = idDisco;
    }
}
