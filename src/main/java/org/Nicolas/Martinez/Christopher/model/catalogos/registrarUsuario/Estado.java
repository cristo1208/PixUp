package org.Nicolas.Martinez.Christopher.model.catalogos.registrarUsuario;

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
@Table( name = "TBL_ESTADO" )
public class Estado extends ClaseCatalogo {
    protected static CatalogoDaoImpl<Estado> catalogoDaoImpl = new CatalogoDaoImpl<>(Estado.class);
    private static Estado manage = null;
    private Estado(){

    }
    public static Estado getManage(){
        if(manage == null){
            manage = new Estado();
        }
        return manage;
    }

    @Override
    public void altas() {
        Estado estado = new Estado();
        estado.setNombre(ReadUtil.read(ConsolaPrinter.leerNombre));
        catalogoDaoImpl.guardar(estado);
    }
    public boolean altas(String nombreEstado) {
        System.out.println("-ALTA DE ESTADO (Automática para Test)-");
        Estado estado = new Estado();
        estado.setNombre(nombreEstado);
        boolean exito = catalogoDaoImpl.guardar(estado);
        System.out.println("Estado '" + nombreEstado + "' guardado automáticamente. Éxito: " + exito);
        return exito;
    }

    @Override
    public void actualizaciones() {
        Estado estado = (Estado) buscarCatalogo(catalogoDaoImpl);
        if(estado != null){
            estado.setNombre(ReadUtil.read(ConsolaPrinter.nuevoNombre));
            catalogoDaoImpl.actualizar(estado);
        }
    }
    public void actualizaciones(Long idEstado, String nuevoNombre) {
        System.out.println("-MODIFICACIÓN DE ESTADO (Automática para Test)-");
        // Asegúrate de que tu findById de CatalogoDaoImpl acepte Integer o cástalo a int
        Estado estado = catalogoDaoImpl.findById(Math.toIntExact(idEstado));
        if(estado != null){
            estado.setNombre(nuevoNombre);
            catalogoDaoImpl.actualizar(estado);
            System.out.println("Estado con ID " + idEstado + " actualizado a '" + nuevoNombre + "' automáticamente.");
        } else {
            System.out.println("Estado con ID " + idEstado + " no encontrado para actualización automática.");
        }
    }

    @Override
    public void bajas() {
        realizarBaja(Estado.class);
    }
    public void bajas(Long idEstado) {
        System.out.println("-BAJA DE ESTADO (Automática para Test)-");
        Estado estadoParaEliminar = catalogoDaoImpl.findById(Math.toIntExact(idEstado));
        if (estadoParaEliminar != null) {
            catalogoDaoImpl.eliminar(estadoParaEliminar);
            System.out.println("Estado con ID " + idEstado + " eliminado automáticamente.");
        } else {
            System.out.println("Estado con ID " + idEstado + " no encontrado para baja automática.");
        }
    }
    @Override
    public void lista() {
        System.out.println(realizarVista(catalogoDaoImpl));
}
}