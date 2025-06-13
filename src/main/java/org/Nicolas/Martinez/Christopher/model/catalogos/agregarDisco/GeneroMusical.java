package org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.Nicolas.Martinez.Christopher.generalUtil.ReadUtil;
import org.Nicolas.Martinez.Christopher.generalUtil.ConsolaPrinter;
import org.Nicolas.Martinez.Christopher.model.catalogos.ClaseCatalogo;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table( name = "TBL_GENEROMUSICAL" )
public class GeneroMusical extends ClaseCatalogo {
    protected static CatalogoDaoImpl<GeneroMusical> catalogoDaoImpl = new CatalogoDaoImpl<>(GeneroMusical.class);

    private static GeneroMusical manage = null;
    private GeneroMusical(){

    }
    public static GeneroMusical getManage(){
        if(manage == null){
            manage = new GeneroMusical();
        }
        return manage;
    }


    @Override
    public void altas() {
        GeneroMusical generoMusical = new GeneroMusical();
        generoMusical.setNombre(ReadUtil.read(ConsolaPrinter.leerNombre));

        catalogoDaoImpl.guardar(generoMusical);
    }
    public boolean altas(String nombreGenero) { // <-- Ahora devuelve boolean
        System.out.println("-ALTA DE GÉNERO MUSICAL (Automática para Test)-");
        GeneroMusical generoMusical = new GeneroMusical();
        generoMusical.setNombre(nombreGenero);
        boolean exito = catalogoDaoImpl.guardar(generoMusical); // <-- Captura el boolean
        System.out.println("Género Musical '" + nombreGenero + "' guardado automáticamente. Éxito: " + exito);
        return exito; // Devuelve el resultado booleano
    }

    @Override
    public void actualizaciones() {
        GeneroMusical generoMusical = (GeneroMusical) buscarCatalogo(catalogoDaoImpl);
        if(generoMusical != null){
            generoMusical.setNombre(ReadUtil.read(ConsolaPrinter.nuevoNombre));
            catalogoDaoImpl.actualizar(generoMusical);
        }
    }
    public void actualizaciones(Long idGenero, String nuevoNombre) {
        System.out.println("-MODIFICACIÓN DE GÉNERO MUSICAL (Automática para Test)-");
        // Aquí se usa findById(int), por eso Math.toIntExact(idGenero) es necesario.
        GeneroMusical generoMusical = catalogoDaoImpl.findById(Math.toIntExact(idGenero));
        if(generoMusical != null){
            generoMusical.setNombre(nuevoNombre);
            catalogoDaoImpl.actualizar(generoMusical);
            System.out.println("Género Musical con ID " + idGenero + " actualizado a '" + nuevoNombre + "' automáticamente.");
        } else {
            System.out.println("Género Musical con ID " + idGenero + " no encontrado para actualización automática.");
        }
    }

    @Override
    public void bajas() {
        realizarBaja(GeneroMusical.class);
    }
    public void bajas(Long idGenero) {
        System.out.println("-BAJA DE GÉNERO MUSICAL (Automática para Test)-");
        // Aquí se usa findById(int), por eso Math.toIntExact(idGenero) es necesario.
        GeneroMusical generoMusicalParaEliminar = catalogoDaoImpl.findById(Math.toIntExact(idGenero));

        if (generoMusicalParaEliminar != null) {
            catalogoDaoImpl.eliminar(generoMusicalParaEliminar);
            System.out.println("Género Musical con ID " + idGenero + " eliminado automáticamente.");
        } else {
            System.out.println("Género Musical con ID " + idGenero + " no encontrado para baja automática.");
        }
    }
    @Override
    public void lista() {
        System.out.println(realizarVista(catalogoDaoImpl));
    }

}