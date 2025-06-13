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
@Table( name = "TBL_COLONIA" )
public class Colonia extends ClaseCatalogo {
    @Column( name = "idMunicipio" )
    private Integer idMunicipio;
    protected static CatalogoDaoImpl<Colonia> catalogoDaoImpl = new CatalogoDaoImpl<>(Colonia.class);
    private static Colonia manage = null;
    public static Colonia getManage(){
        if(manage == null){
            manage = new Colonia();
        }
        return manage;
    }
    @Override
    public void altas() {
        Colonia colonia = new Colonia();
        colonia.setNombre(ReadUtil.read(ConsolaPrinter.leerNombre));
        Municipio municipio = (Municipio) buscarCatalogo(Municipio.catalogoDaoImpl);
        if(municipio != null) colonia.setIdMunicipio(municipio.getId());
        catalogoDaoImpl.guardar(colonia);
    }
    public boolean altas(String nombreColonia, Integer idMunicipio) {
        System.out.println("-ALTA DE COLONIA (Automática para Test)-");
        Colonia colonia = new Colonia();
        colonia.setNombre(nombreColonia);
        colonia.setIdMunicipio(idMunicipio);
        boolean exito = catalogoDaoImpl.guardar(colonia);
        System.out.println("Colonia '" + nombreColonia + "' guardada automáticamente. Éxito: " + exito);
        return exito;
    }
    @Override
    public void actualizaciones() {
        Colonia colonia = (Colonia) buscarCatalogo(catalogoDaoImpl);
        if(colonia != null){
            colonia.setNombre(ReadUtil.read(ConsolaPrinter.nuevoNombre));

            System.out.println("Nuevo Municipio: ");
            Municipio municipio = (Municipio) buscarCatalogo(Municipio.catalogoDaoImpl);
            if(municipio != null){
                colonia.setIdMunicipio(municipio.getId());
                catalogoDaoImpl.actualizar(colonia);
            }
        }
    }
    public void actualizaciones(Long idColonia, String nuevoNombre, Integer nuevoIdMunicipio) {
        System.out.println("-MODIFICACIÓN DE COLONIA (Automática para Test)-");
        // Asegúrate de que tu findById de CatalogoDaoImpl acepte Integer o cástalo a int
        Colonia colonia = catalogoDaoImpl.findById(Math.toIntExact(idColonia));
        if(colonia != null){
            colonia.setNombre(nuevoNombre);
            colonia.setIdMunicipio(nuevoIdMunicipio); // Actualizar el ID del municipio
            catalogoDaoImpl.actualizar(colonia);
            System.out.println("Colonia con ID " + idColonia + " actualizada a '" + nuevoNombre + "' automáticamente.");
        } else {
            System.out.println("Colonia con ID " + idColonia + " no encontrada para actualización automática.");
        }
    }
    @Override
    public void bajas() {
        realizarBaja(Colonia.class);
    }
    public void bajas(Long idColonia) {
        System.out.println("-BAJA DE COLONIA (Automática para Test)-");
        Colonia coloniaParaEliminar = catalogoDaoImpl.findById(Math.toIntExact(idColonia));
        if (coloniaParaEliminar != null) {
            catalogoDaoImpl.eliminar(coloniaParaEliminar);
            System.out.println("Colonia con ID " + idColonia + " eliminada automáticamente.");
        } else {
            System.out.println("Colonia con ID " + idColonia + " no encontrada para baja automática.");
        }
    }
    @Override
    public void lista() {
        System.out.println(realizarVista(catalogoDaoImpl));
    }
    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
}