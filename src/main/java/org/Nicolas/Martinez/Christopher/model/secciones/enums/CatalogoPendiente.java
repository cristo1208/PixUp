package org.Nicolas.Martinez.Christopher.model.secciones.enums;

import org.Nicolas.Martinez.Christopher.model.secciones.Ejecutable;
import org.Nicolas.Martinez.Christopher.model.secciones.vista.consola.seccion_catalogo_pendiente.catalogos.SeccionCatalogos;

public enum CatalogoPendiente {
    CATALOGO(1, SeccionCatalogos.getInstance()),
    PENDIENTE(2, null),
    SALIR(3, null),
    OPCION_ERRONEA(4, null);
    private Integer id;
    private Ejecutable ejecutable;
    CatalogoPendiente(Integer id, Ejecutable ejecutable) {
        this.id = id;
        this.ejecutable = ejecutable;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Ejecutable getEjecutable() {
        return ejecutable;
    }
    public void setEjecutable(Ejecutable ejecutable) {
        this.ejecutable = ejecutable;
    }
    public static CatalogoPendiente getTipoEjecutableById(Integer opcion) {
        return switch (opcion) {
            case 1 -> CATALOGO;
            case 2 -> PENDIENTE;
            case 3 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }
}