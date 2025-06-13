package org.Nicolas.Martinez.Christopher.model.secciones.enums;

import org.Nicolas.Martinez.Christopher.model.secciones.Ejecutable;
import org.Nicolas.Martinez.Christopher.model.secciones.vista.consola.Consola;
import org.Nicolas.Martinez.Christopher.model.secciones.vista.ventana.Ventana;

public enum TipoEjecutable {
    CONSOLA( 1, Consola.getInstance() ),
    VENTANA( 2, Ventana.getInstance() ),
    SALIR( 3, null ),
    OPCION_ERRONEA( 4, null );
    private Integer id;
    private Ejecutable ejecutable;
    TipoEjecutable(Integer id, Ejecutable ejecutable) {
        this.id = id;
        this.ejecutable = ejecutable;
    }
    public Integer getId() {
        return id;
    }
    public Ejecutable getEjecutable() {
        return ejecutable;
    }
    public static TipoEjecutable getTipoEjecutableById (Integer opcion ) {
        switch (opcion) {
            case 1:
                return CONSOLA;
            case 2:
                return VENTANA;
            case 3:
                return SALIR;
            default:
                return OPCION_ERRONEA;

        }
    }

}
