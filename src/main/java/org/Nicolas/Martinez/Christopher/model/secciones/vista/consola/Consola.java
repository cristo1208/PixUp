package org.Nicolas.Martinez.Christopher.model.secciones.vista.consola;

import org.Nicolas.Martinez.Christopher.generalUtil.ReadUtil;
import org.Nicolas.Martinez.Christopher.generalUtil.ConsolaPrinter;
import org.Nicolas.Martinez.Christopher.model.secciones.Ejecutable;
import org.Nicolas.Martinez.Christopher.model.secciones.enums.CatalogoPendiente;

public class Consola implements Ejecutable {
    private static Consola consola;
    private Consola() {
    }
    public static Consola getInstance( ) {
        if(consola == null) {
            consola = new Consola();
        }
        return consola;
    }

    @Override
    public void run() {
        int opcion;
        Ejecutable ejecutable;
        CatalogoPendiente tipoEjecutable;
        while (true) {
            System.out.println(ConsolaPrinter.menuSeccion2);
            opcion = ReadUtil.readInt(ConsolaPrinter.seleccionarOpcion, 1, 3);

            tipoEjecutable = CatalogoPendiente.getTipoEjecutableById( opcion );

            if ( CatalogoPendiente.SALIR.equals(tipoEjecutable) ){
                return;
            } else if ( CatalogoPendiente.OPCION_ERRONEA.equals(tipoEjecutable) ){
                ConsolaPrinter.opcionInvalida();
            } else if ( tipoEjecutable.getEjecutable() != null ){
                ejecutable = tipoEjecutable.getEjecutable();
                ejecutable.run();
            }

        }
    }

}