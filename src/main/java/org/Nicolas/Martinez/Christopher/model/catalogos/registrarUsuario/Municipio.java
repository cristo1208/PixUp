package org.Nicolas.Martinez.Christopher.model.catalogos.registrarUsuario;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

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
@Table( name = "TBL_MUNICIPIO" )
public class Municipio extends ClaseCatalogo {
    @Column( name = "idEstado" )
    private Integer idEstado;
    protected static CatalogoDaoImpl<Municipio> catalogoDaoImpl = new CatalogoDaoImpl<>(Municipio.class);
    private static Municipio manage = null;
    public static Municipio getManage(){
        if(manage == null){
            manage = new Municipio();
        }
        return manage;
    }
    @Override
    public void altas() {
        Municipio municipio = new Municipio();
        municipio.setNombre(ReadUtil.read(ConsolaPrinter.leerNombre));
        Estado estado = (Estado) buscarCatalogo(Estado.catalogoDaoImpl);
        if(estado != null) municipio.setIdEstado(estado.getId());
        catalogoDaoImpl.guardar(municipio);
    }
    public boolean altas(String nombreMunicipio, Integer idEstado) {
        System.out.println("-ALTA DE MUNICIPIO (Automática para Test)-");
        Municipio municipio = new Municipio();
        municipio.setNombre(nombreMunicipio);
        municipio.setIdEstado(idEstado);
        boolean exito = catalogoDaoImpl.guardar(municipio);
        System.out.println("Municipio '" + nombreMunicipio + "' guardado automáticamente. Éxito: " + exito);
        return exito;
    }
    @Override
    public void actualizaciones() {
        Municipio municipio = (Municipio) buscarCatalogo(catalogoDaoImpl);
        if(municipio != null){
            municipio.setNombre(ReadUtil.read(ConsolaPrinter.nuevoNombre));
            System.out.println("Nuevo Estado: ");
            Estado estado = (Estado) buscarCatalogo(Estado.catalogoDaoImpl);
            if(estado != null){
                municipio.setIdEstado(estado.getId());
                catalogoDaoImpl.actualizar(municipio);
            }
        }
    }
    public void actualizaciones(Long idMunicipio, String nuevoNombre, Integer nuevoIdEstado) {
        System.out.println("-MODIFICACIÓN DE MUNICIPIO (Automática para Test)-");
        // Asegúrate de que tu findById de CatalogoDaoImpl acepte Integer o cástalo a int
        Municipio municipio = catalogoDaoImpl.findById(Math.toIntExact(idMunicipio));
        if(municipio != null){
            municipio.setNombre(nuevoNombre);
            municipio.setIdEstado(nuevoIdEstado); // Actualizar el ID del estado
            catalogoDaoImpl.actualizar(municipio);
            System.out.println("Municipio con ID " + idMunicipio + " actualizado a '" + nuevoNombre + "' automáticamente.");
        } else {
            System.out.println("Municipio con ID " + idMunicipio + " no encontrado para actualización automática.");
        }
    }
    @Override
    public void bajas() {
        realizarBaja(Municipio.class);
    }
    public void bajas(Long idMunicipio) {
        System.out.println("-BAJA DE MUNICIPIO (Automática para Test)-");
        Municipio municipioParaEliminar = catalogoDaoImpl.findById(Math.toIntExact(idMunicipio));
        if (municipioParaEliminar != null) {
            catalogoDaoImpl.eliminar(municipioParaEliminar);
            System.out.println("Municipio con ID " + idMunicipio + " eliminado automáticamente.");
        } else {
            System.out.println("Municipio con ID " + idMunicipio + " no encontrado para baja automática.");
        }
    }
    @Override
    public void lista() {
        System.out.println(realizarVista(catalogoDaoImpl));
    }
    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getId() {
        return super.getId();
    }
}