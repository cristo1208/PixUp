package org.Nicolas.Martinez.Christopher.model.catalogos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.Nicolas.Martinez.Christopher.BD.dao.implementacion.CatalogoDaoImpl;
import org.Nicolas.Martinez.Christopher.generalUtil.ReadUtil;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class ClaseCatalogo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    public abstract void altas();
    public abstract void actualizaciones();
    public abstract void bajas();
    public abstract void lista();

    protected <T extends ClaseCatalogo> void realizarBaja(Class<T> tipo) {
        CatalogoDaoImpl<T> catalogoDaoImpl = new CatalogoDaoImpl<>(tipo);
        T objeto = tipo.cast(buscarCatalogo(catalogoDaoImpl));
        catalogoDaoImpl.eliminar(objeto);
        System.out.println("Elemento eliminado");
    }

    protected String realizarVista(CatalogoDaoImpl<? extends ClaseCatalogo> catalogoDao){
        List<? extends ClaseCatalogo> lista = catalogoDao.findAll();
        String cadena = "";
        for (ClaseCatalogo claseCatalogo : lista) {
            cadena += claseCatalogo;
            cadena += "\n";
        }
        return cadena;
    }

    protected ClaseCatalogo buscarCatalogo(CatalogoDaoImpl<? extends ClaseCatalogo> catalogoDao){
        ClaseCatalogo claseCatalogo = null;
        String mensaje =
                "Ingresa el id del elemento: \n" +
                        realizarVista(catalogoDao);
        Integer id = ReadUtil.readInt(mensaje);

        claseCatalogo = catalogoDao.findById(id);

        if(claseCatalogo == null){
            System.out.println("Elemento no encontrado");
            return null;
        } else {
            return claseCatalogo;
        }

    }

    protected ClaseCatalogo buscarCatalogo(CatalogoDaoImpl<? extends ClaseCatalogo> catalogoDao, Integer id){
        ClaseCatalogo claseCatalogo = null;
        claseCatalogo = catalogoDao.findById(id);
        if(claseCatalogo == null){
            System.out.println("Elemento no encontrado");
            return null;
        } else {
            return claseCatalogo;
        }
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getId() {
        return id;
    }
}
