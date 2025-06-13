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
@Table( name = "TBL_DISQUERA" )
public class Disquera extends ClaseCatalogo {
    protected static CatalogoDaoImpl<Disquera> catalogoDaoImpl = new CatalogoDaoImpl<>(Disquera.class);

    private static Disquera manage = null;
    private Disquera(){

    }
    public static Disquera getManage(){
        if(manage == null){
            manage = new Disquera();
        }
        return manage;
    }

    @Override
    public void altas() {
        Disquera disquera = new Disquera();
        disquera.setNombre(ReadUtil.read(ConsolaPrinter.leerNombre));

        catalogoDaoImpl.guardar(disquera);
    }
    public boolean altas(String nombreDisquera) { // <-- Ahora devuelve boolean
        System.out.println("-ALTA DE DISQUERA (Automática para Test)-");
        Disquera disquera = new Disquera();
        disquera.setNombre(nombreDisquera);
        boolean exito = catalogoDaoImpl.guardar(disquera); // <-- Captura el boolean
        System.out.println("Disquera '" + nombreDisquera + "' guardada automáticamente. Éxito: " + exito);
        return exito; // Devuelve el resultado booleano
    }

    @Override
    public void actualizaciones() {
        Disquera disquera = (Disquera) buscarCatalogo(catalogoDaoImpl);
        if(disquera != null){
            disquera.setNombre(ReadUtil.read(ConsolaPrinter.nuevoNombre));
            catalogoDaoImpl.actualizar(disquera);
        }
    }
    public void actualizaciones(Long idDisquera, String nuevoNombre) {
        System.out.println("-MODIFICACIÓN DE DISQUERA (Automática para Test)-");
        Disquera disquera = catalogoDaoImpl.findById(Math.toIntExact(idDisquera));
        if(disquera != null){
            disquera.setNombre(nuevoNombre);
            catalogoDaoImpl.actualizar(disquera);
            System.out.println("Disquera con ID " + idDisquera + " actualizada a '" + nuevoNombre + "' automáticamente.");
        } else {
            System.out.println("Disquera con ID " + idDisquera + " no encontrada para actualización automática.");
        }
    }

    @Override
    public void bajas() {
        realizarBaja(Disquera.class);
    }
    public void bajas(Long idDisquera) {
        System.out.println("-BAJA DE DISQUERA (Automática para Test)-");
        Disquera disqueraParaEliminar = catalogoDaoImpl.findById(Math.toIntExact(idDisquera));
        if (disqueraParaEliminar != null) {
            catalogoDaoImpl.eliminar(disqueraParaEliminar);
            System.out.println("Disquera con ID " + idDisquera + " eliminada automáticamente.");
        } else {
            System.out.println("Disquera con ID " + idDisquera + " no encontrada para baja automática.");
        }
    }

    @Override
    public void lista() {
        System.out.println(realizarVista(catalogoDaoImpl));
    }
}