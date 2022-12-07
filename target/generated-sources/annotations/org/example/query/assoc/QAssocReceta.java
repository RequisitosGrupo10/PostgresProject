package org.example.query.assoc;

import io.ebean.Transaction;
import io.ebean.typequery.Generated;
import io.ebean.typequery.PDouble;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PSqlDate;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;
import org.example.Receta;
import org.example.query.QReceta;

/**
 * Association query bean for AssocReceta.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@Generated("io.ebean.querybean.generator")
@TypeQueryBean("v1")
public class QAssocReceta<R> extends TQAssocBean<Receta,R> {

  public PInteger<R> ID;
  public PString<R> NIF;
  public PSqlDate<R> CONSULTA;
  public PDouble<R> OD_ESFERA;
  public PDouble<R> OD_CILINDRO;
  public PDouble<R> OD_ADICION;
  public PDouble<R> OD_AGUDEZA;
  public PDouble<R> OI_ESFERA;
  public PDouble<R> OI_CILINDRO;
  public PDouble<R> OI_ADICION;
  public PDouble<R> OI_AGUDEZA;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs @SuppressWarnings("varargs")
  public final R fetch(TQProperty<QReceta>... properties) {
    return fetchProperties(properties);
  }

  /**
   * Eagerly fetch this association using a 'query join' loading the specified properties.
   */
  @SafeVarargs @SuppressWarnings("varargs")
  public final R fetchQuery(TQProperty<QReceta>... properties) {
    return fetchQueryProperties(properties);
  }

  /**
   * Eagerly fetch this association using L2 cache.
   */
  @SafeVarargs @SuppressWarnings("varargs")
  public final R fetchCache(TQProperty<QReceta>... properties) {
    return fetchCacheProperties(properties);
  }

  /**
   * Use lazy loading for this association loading the specified properties.
   */
  @SafeVarargs @SuppressWarnings("varargs")
  public final R fetchLazy(TQProperty<QReceta>... properties) {
    return fetchLazyProperties(properties);
  }

  public QAssocReceta(String name, R root) {
    super(name, root);
  }

  public QAssocReceta(String name, R root, String prefix) {
    super(name, root, prefix);
  }
}
