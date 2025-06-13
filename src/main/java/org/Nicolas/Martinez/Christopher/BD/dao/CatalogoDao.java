package org.Nicolas.Martinez.Christopher.BD.dao;

import org.Nicolas.Martinez.Christopher.model.catalogos.ClaseCatalogo;

import java.util.List;

public interface CatalogoDao<T extends ClaseCatalogo>{
    List<T> findAll( );
    boolean guardar( T t );
    boolean actualizar( T t );
    boolean eliminar( T t );
    T findById( int id );
}
