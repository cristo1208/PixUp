package org.Nicolas.Martinez.Christopher.model.secciones.vista.consola.seccion_catalogo_pendiente.catalogos;

import org.Nicolas.Martinez.Christopher.generalUtil.ReadUtil;
import org.Nicolas.Martinez.Christopher.generalUtil.ConsolaPrinter;
import org.Nicolas.Martinez.Christopher.model.catalogos.ClaseCatalogo;
import org.Nicolas.Martinez.Christopher.model.secciones.Ejecutable;
import org.Nicolas.Martinez.Christopher.model.secciones.enums.Abm;
import org.Nicolas.Martinez.Christopher.model.secciones.enums.TipoCatalogo;

public class SeccionCatalogos implements Ejecutable {
    private static SeccionCatalogos catalogos;

    private SeccionCatalogos() {
    }

    public static SeccionCatalogos getInstance() {
        if (catalogos == null) {
            catalogos = new SeccionCatalogos();
        }
        return catalogos;
    }

    @Override
    public void run() {
        int opcion;
        TipoCatalogo tipoCatalogoEnum;
        ClaseCatalogo claseCatalogo;

        while (true) {
            System.out.println(ConsolaPrinter.menuUbicacion);
            opcion = ReadUtil.readInt(ConsolaPrinter.seleccionarOpcion, 1, 9); // actualizado para cubrir nuevas opciones

            tipoCatalogoEnum = TipoCatalogo.getCatalogoByTipo(opcion);

            if (TipoCatalogo.SALIR.equals(tipoCatalogoEnum)) {
                return;
            } else if (TipoCatalogo.OPCION_ERRONEA.equals(tipoCatalogoEnum)) {
                ConsolaPrinter.opcionInvalida();
                continue;
            }

            claseCatalogo = tipoCatalogoEnum.getCatalogo();

            if (claseCatalogo != null) {
                boolean continuarAbm = true;
                while (continuarAbm) {
                    System.out.println(ConsolaPrinter.menuABM);
                    opcion = ReadUtil.readInt(ConsolaPrinter.seleccionarOpcion, 1, 5);
                    Abm abmEnum = Abm.getAbmByTipo(opcion);

                    if (Abm.SALIR.equals(abmEnum)) {
                        continuarAbm = false;
                    } else if (Abm.OPCION_ERRONEA.equals(abmEnum)) {
                        ConsolaPrinter.opcionInvalida();
                    } else {
                        switch (abmEnum) {
                            case ALTA -> {
                                System.out.println("------- ALTA -------");
                                claseCatalogo.altas();
                                System.out.println("----------------------------");
                            }
                            case BAJA -> {
                                System.out.println("------- BAJA -------");
                                claseCatalogo.bajas();
                                System.out.println("----------------------------");
                            }
                            case VISTA -> {
                                System.out.println("------- VISTA -------");
                                claseCatalogo.lista();
                                System.out.println("----------------------------");
                            }
                            case MODIFICACION -> {
                                System.out.println("------- MODIFICACIÓN -------");
                                claseCatalogo.actualizaciones();
                                System.out.println("----------------------------");
                            }
                        }
                    }
                }
            }
        }
    }
}