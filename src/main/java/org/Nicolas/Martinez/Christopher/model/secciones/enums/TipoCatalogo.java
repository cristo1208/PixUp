package org.Nicolas.Martinez.Christopher.model.secciones.enums;

import lombok.Getter;
import org.Nicolas.Martinez.Christopher.model.catalogos.ClaseCatalogo;
import org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco.Artista;
import org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco.Cancion;
import org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco.Disco;
import org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco.Disquera;
import org.Nicolas.Martinez.Christopher.model.catalogos.agregarDisco.GeneroMusical;
import org.Nicolas.Martinez.Christopher.model.catalogos.registrarUsuario.Colonia;
import org.Nicolas.Martinez.Christopher.model.catalogos.registrarUsuario.Estado;
import org.Nicolas.Martinez.Christopher.model.catalogos.registrarUsuario.Municipio;

public enum TipoCatalogo {
    ESTADO(1, Estado.getManage()),
    MUNICIPIO(2, Municipio.getManage()),
    COLONIA(3, Colonia.getManage()),
    ARTISTA(4, Artista.getManage()),
    DISQUERA(5, Disquera.getManage()),
    GENERO_MUSICAL(6, GeneroMusical.getManage()),
    CANCION(7, Cancion.getManage()),
    DISCO(8, Disco.getManage()),
    SALIR(9, null),
    OPCION_ERRONEA(-1, null);
    @Getter
    private final int tipo;
    private final ClaseCatalogo claseCatalogo;
    TipoCatalogo(int tipo, ClaseCatalogo claseCatalogo) {
        this.tipo = tipo;
        this.claseCatalogo = claseCatalogo;
    }
    public ClaseCatalogo getCatalogo() {
        return claseCatalogo;
    }
    public static TipoCatalogo getCatalogoByTipo(int tipo) {
        for (TipoCatalogo tipoCatalogoEnum : values()) {
            if (tipoCatalogoEnum.tipo == tipo) {
                return tipoCatalogoEnum;
            }
        }
        return OPCION_ERRONEA;
    }
}