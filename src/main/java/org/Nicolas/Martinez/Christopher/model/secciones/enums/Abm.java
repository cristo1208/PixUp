package org.Nicolas.Martinez.Christopher.model.secciones.enums;

public enum Abm {
    ALTA(1),
    BAJA(2),
    VISTA(3),
    MODIFICACION(4),
    SALIR(5),
    OPCION_ERRONEA(6);
    private Integer tipo;
    Abm(Integer tipo) {
        this.tipo = tipo;
    }
    public Integer getTipo() {
        return tipo;
    }
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    public static Abm getAbmByTipo (Integer tipo ) {
        return switch (tipo) {
            case 1 -> ALTA;
            case 2 -> BAJA;
            case 3 -> VISTA;
            case 4 -> MODIFICACION;
            case 5 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }

}
