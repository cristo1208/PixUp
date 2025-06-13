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
@Table(name = "TBL_DISCO")
public class Disco extends ClaseCatalogo {
    @Column(name = "precio")
    private Double precio;
    @Column(name = "existencia")
    private Integer existencia;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "fechaLanzamiento")
    private String fechaLanzamiento;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "idArtista")
    private Integer idArtista;
    @Column(name = "idDisquera")
    private Integer idDisquera;
    @Column(name = "idGeneroMusical")
    private Integer idGeneroMusical;
    protected static CatalogoDaoImpl<Disco> catalogoDaoImpl = new CatalogoDaoImpl<>(Disco.class);
    private static Disco manage = null;
    public static Disco getManage() {
        if (manage == null) {
            manage = new Disco();
        }
        return manage;
    }
    @Override
    public void altas() {
        System.out.println("-ALTA DE DISCO-");
        Disco disco = new Disco();
        disco.setNombre(ReadUtil.read(ConsolaPrinter.leerNombre));
        disco.setPrecio(Double.valueOf(ReadUtil.read("Precio:")));
        disco.setExistencia(Integer.valueOf(ReadUtil.read("Existencia:")));
        disco.setDescuento(Double.valueOf(ReadUtil.read("Descuento (%):")));
        disco.setFechaLanzamiento(ReadUtil.read("Fecha de lanzamiento (dd/mm/aaaa):"));
        disco.setImagen(ReadUtil.read("Ruta de la imagen:"));
        Artista artista = (Artista) buscarCatalogo(Artista.catalogoDaoImpl);
        if (artista != null) disco.setIdArtista(artista.getId());
        Disquera disquera = (Disquera) buscarCatalogo(Disquera.catalogoDaoImpl);
        if (disquera != null) disco.setIdDisquera(disquera.getId());
        GeneroMusical genero = (GeneroMusical) buscarCatalogo(GeneroMusical.catalogoDaoImpl);
        if (genero != null) disco.setIdGeneroMusical(genero.getId());
        catalogoDaoImpl.guardar(disco);
    }
    public boolean altas(String nombre, Double precio, Integer existencia, Double descuento,
                         String fechaLanzamiento, String imagen, Integer idArtista,
                         Integer idDisquera, Integer idGeneroMusical) {
        System.out.println("-ALTA DE DISCO (Automática para Test)-");
        Disco disco = new Disco();
        disco.setNombre(nombre);
        disco.setPrecio(precio);
        disco.setExistencia(existencia);
        disco.setDescuento(descuento);
        disco.setFechaLanzamiento(fechaLanzamiento);
        disco.setImagen(imagen);
        disco.setIdArtista(idArtista);
        disco.setIdDisquera(idDisquera);
        disco.setIdGeneroMusical(idGeneroMusical);
        boolean exito = catalogoDaoImpl.guardar(disco);
        System.out.println("Disco '" + nombre + "' guardado automáticamente. Éxito: " + exito);
        return exito;
    }

    @Override
    public void actualizaciones() {
        System.out.println("-MODIFICACIÓN DE DISCO-");
        Disco disco = (Disco) buscarCatalogo(catalogoDaoImpl);
        if (disco != null) {
            disco.setNombre(ReadUtil.read(ConsolaPrinter.nuevoNombre));
            disco.setPrecio(Double.valueOf(ReadUtil.read("Nuevo precio:")));
            disco.setExistencia(Integer.valueOf(ReadUtil.read("Nueva existencia:")));
            disco.setDescuento(Double.valueOf(ReadUtil.read("Nuevo descuento (%):")));
            disco.setFechaLanzamiento(ReadUtil.read("Nueva fecha de lanzamiento (dd/mm/aaaa):"));
            disco.setImagen(ReadUtil.read("Nueva ruta de la imagen:"));

            System.out.println("Nuevo Artista:");
            Artista artista = (Artista) buscarCatalogo(Artista.catalogoDaoImpl);
            if (artista != null) disco.setIdArtista(artista.getId());

            System.out.println("Nueva Disquera:");
            Disquera disquera = (Disquera) buscarCatalogo(Disquera.catalogoDaoImpl);
            if (disquera != null) disco.setIdDisquera(disquera.getId());

            System.out.println("Nuevo Género Musical:");
            GeneroMusical genero = (GeneroMusical) buscarCatalogo(GeneroMusical.catalogoDaoImpl);
            if (genero != null) disco.setIdGeneroMusical(genero.getId());

            catalogoDaoImpl.actualizar(disco);
        }
    }
    public void actualizaciones(Long idDisco, String nuevoNombre, Double nuevoPrecio, Integer nuevaExistencia,
                                Double nuevoDescuento, String nuevaFechaLanzamiento, String nuevaImagen,
                                Integer nuevoIdArtista, Integer nuevoIdDisquera, Integer nuevoIdGeneroMusical) {
        System.out.println("-MODIFICACIÓN DE DISCO (Automática para Test)-");
        // Asegúrate de que tu findById de CatalogoDaoImpl acepte Integer o cástalo a int
        // si solo acepta int (Math.toIntExact(idDisco) o idDisco.intValue())
        Disco disco = catalogoDaoImpl.findById(Math.toIntExact(idDisco));
        if (disco != null) {
            disco.setNombre(nuevoNombre);
            disco.setPrecio(nuevoPrecio);
            disco.setExistencia(nuevaExistencia);
            disco.setDescuento(nuevoDescuento);
            disco.setFechaLanzamiento(nuevaFechaLanzamiento);
            disco.setImagen(nuevaImagen);
            disco.setIdArtista(nuevoIdArtista);
            disco.setIdDisquera(nuevoIdDisquera);
            disco.setIdGeneroMusical(nuevoIdGeneroMusical);
            catalogoDaoImpl.actualizar(disco);
            System.out.println("Disco con ID " + idDisco + " actualizado automáticamente.");
        } else {
            System.out.println("Disco con ID " + idDisco + " no encontrado para actualización automática.");
        }
    }
    @Override
    public void bajas() {
        System.out.println("-BAJA DE DISCO-");
        realizarBaja(Disco.class);
    }
    public void bajas(Long idDisco) {
        System.out.println("-BAJA DE DISCO (Automática para Test)-");
        Disco discoParaEliminar = catalogoDaoImpl.findById(Math.toIntExact(idDisco));
        if (discoParaEliminar != null) {
            catalogoDaoImpl.eliminar(discoParaEliminar);
            System.out.println("Disco con ID " + idDisco + " eliminado automáticamente.");
        } else {
            System.out.println("Disco con ID " + idDisco + " no encontrado para baja automática.");
        }
    }
    @Override
    public void lista() {
        System.out.println("-ELEMENTOS DE DISCO-");
        System.out.println(realizarVista(catalogoDaoImpl));
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }
    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }
    public void setIdDisquera(Integer idDisquera) {
        this.idDisquera = idDisquera;
    }
    public void setIdGeneroMusical(Integer idGeneroMusical) {
        this.idGeneroMusical = idGeneroMusical;
    }
}
