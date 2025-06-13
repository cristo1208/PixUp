package org.Nicolas.Martinez.Christopher.inicio;

import org.Nicolas.Martinez.Christopher.generalUtil.ReadUtil;
import org.Nicolas.Martinez.Christopher.generalUtil.ConsolaPrinter;
import org.Nicolas.Martinez.Christopher.model.secciones.Ejecutable;
import org.Nicolas.Martinez.Christopher.model.secciones.enums.TipoEjecutable;

public class Inicio {
    public static void main(String args[]) {
        int opcion;
        Ejecutable ejecutable;
        TipoEjecutable tipoEjecutableEnum;
        while ( true ) {
            System.out.println(ConsolaPrinter.menuPrincipal);
            opcion = ReadUtil.readInt(ConsolaPrinter.seleccionarOpcion, 1, 3);

            tipoEjecutableEnum = TipoEjecutable.getTipoEjecutableById( opcion );

            if ( TipoEjecutable.SALIR.equals(tipoEjecutableEnum) ){
                return;
            } else if ( TipoEjecutable.OPCION_ERRONEA.equals(tipoEjecutableEnum) ){
                ConsolaPrinter.opcionInvalida();
            } else if ( tipoEjecutableEnum.getEjecutable() != null ){
                ejecutable = tipoEjecutableEnum.getEjecutable();
                ejecutable.run();
            }
        }
    }
}