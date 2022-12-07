package org.example.query.assoc;

import io.ebean.Transaction;
import io.ebean.typequery.Generated;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;
import org.example.Cliente;
import org.example.query.QCliente;

/**
 * Association query bean for AssocCliente.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@Generated("io.ebean.querybean.generator")
@TypeQueryBean("v1")
public class QAssocCliente<R> extends TQAssocBean<Cliente,R> {

  public PString<R> NIF;
  public PString<R> NOMBRE;
  public PString<R> APELLIDOS;
  public PInteger<R> EDAD;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs @SuppressWarnings("varargs")
  public final R fetch(TQProperty<QCliente>... properties) {
    return fetchProperties(properties);
  }

  /**
   * Eagerly fetch this association using a 'query join' loading the specified properties.
   */
  @SafeVarargs @SuppressWarnings("varargs")
  public final R fetchQuery(TQProperty<QCliente>... properties) {
    return fetchQueryProperties(properties);
  }

  /**
   * Eagerly fetch this association using L2 cache.
   */
  @SafeVarargs @SuppressWarnings("varargs")
  public final R fetchCache(TQProperty<QCliente>... properties) {
    return fetchCacheProperties(properties);
  }

  /**
   * Use lazy loading for this association loading the specified properties.
   */
  @SafeVarargs @SuppressWarnings("varargs")
  public final R fetchLazy(TQProperty<QCliente>... properties) {
    return fetchLazyProperties(properties);
  }

  public QAssocCliente(String name, R root) {
    super(name, root);
  }

  public QAssocCliente(String name, R root, String prefix) {
    super(name, root, prefix);
  }
}
