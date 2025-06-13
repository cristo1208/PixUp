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
@Table( name = "TBL_ARTISTA" )
public class Artista extends ClaseCatalogo {
    protected static CatalogoDaoImpl<Artista> catalogoDaoImpl = new CatalogoDaoImpl<>(Artista.class);

    private static Artista manage = null;
    private Artista(){

    }
    public static Artista getManage(){
        if(manage == null){
            manage = new Artista();
        }
        return manage;
    }


    @Override
    public void altas() {
        Artista artista = new Artista();
        artista.setNombre(ReadUtil.read(ConsolaPrinter.leerNombre));

        catalogoDaoImpl.guardar(artista);
    }
    public void altas(String nombreArtista) { // <-- Esta es la nueva firma
        System.out.println("-ALTA DE ARTISTA (Automática para Test)-");
        Artista artista = new Artista();
        artista.setNombre(nombreArtista); // <-- El nombre viene de aquí, no de ReadUtil
        catalogoDaoImpl.guardar(artista);
        System.out.println("Artista '" + nombreArtista + "' guardado automáticamente.");
    }

    @Override
    public void actualizaciones() {
        Artista artista = (Artista) buscarCatalogo(catalogoDaoImpl);
        if(artista != null){
            artista.setNombre(ReadUtil.read(ConsolaPrinter.nuevoNombre));
            catalogoDaoImpl.actualizar(artista);
        }
    }
    public void actualizaciones(Long idArtista, String nuevoNombre) {
        System.out.println("-MODIFICACIÓN DE ARTISTA (Automática para Test)-");
        Artista artista = catalogoDaoImpl.findById(Math.toIntExact(idArtista)); // Asumo que findById acepta Integer/int
        if(artista != null){
            artista.setNombre(nuevoNombre);
            catalogoDaoImpl.actualizar(artista);
            System.out.println("Artista con ID " + idArtista + " actualizado a '" + nuevoNombre + "' automáticamente.");
        } else {
            System.out.println("Artista con ID " + idArtista + " no encontrado para actualización automática.");
        }
    }

    @Override
    public void bajas() {
        realizarBaja(Artista.class);
    }
    public void bajas(Long idArtista) {
        System.out.println("-BAJA DE ARTISTA (Automática para Test)-");
        Artista artistaParaEliminar = catalogoDaoImpl.findById(Math.toIntExact(idArtista));

        if (artistaParaEliminar != null) {
            catalogoDaoImpl.eliminar(artistaParaEliminar);
            System.out.println("Artista con ID " + idArtista + " eliminado automáticamente.");
        } else {
            System.out.println("Artista con ID " + idArtista + " no encontrado para baja automática.");
        }
    }

    @Override
    public void lista() {
        System.out.println(realizarVista(catalogoDaoImpl));
    }
}