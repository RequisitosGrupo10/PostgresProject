package org.example.query;

import io.ebean.Database;
import io.ebean.FetchGroup;
import io.ebean.Query;
import io.ebean.Transaction;
import io.ebean.typequery.Generated;
import io.ebean.typequery.PDouble;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PSqlDate;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;
import org.example.Receta;

/**
 * Query bean for Receta.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@Generated("io.ebean.querybean.generator")
@TypeQueryBean("v1")
public class QReceta extends TQRootBean<Receta,QReceta> {

  private static final QReceta _alias = new QReceta(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QReceta alias() {
    return _alias;
  }

  public PInteger<QReceta> ID;
  public PString<QReceta> NIF;
  public PSqlDate<QReceta> CONSULTA;
  public PDouble<QReceta> OD_ESFERA;
  public PDouble<QReceta> OD_CILINDRO;
  public PDouble<QReceta> OD_ADICION;
  public PDouble<QReceta> OD_AGUDEZA;
  public PDouble<QReceta> OI_ESFERA;
  public PDouble<QReceta> OI_CILINDRO;
  public PDouble<QReceta> OI_ADICION;
  public PDouble<QReceta> OI_AGUDEZA;


  /**
   * Return a query bean used to build a FetchGroup.
   * <p>
   * FetchGroups are immutable and threadsafe and can be used by many
   * concurrent queries. We typically stored FetchGroup as a static final field.
   * <p>
   * Example creating and using a FetchGroup.
   * <pre>{@code
   * 
   * static final FetchGroup<Customer> fetchGroup = 
   *   QCustomer.forFetchGroup()
   *     .shippingAddress.fetch()
   *     .contacts.fetch()
   *     .buildFetchGroup();
   * 
   * List<Customer> customers = new QCustomer()
   *   .select(fetchGroup)
   *   .findList();
   * 
   * }</pre>
   */
  public static QReceta forFetchGroup() {
    return new QReceta(FetchGroup.queryFor(Receta.class));
  }

  /**
   * Construct using the default Database.
   */
  public QReceta() {
    super(Receta.class);
  }

  /**
   * Construct with a given transaction.
   */
  public QReceta(Transaction transaction) {
    super(Receta.class, transaction);
  }

  /**
   * Construct with a given Database.
   */
  public QReceta(Database database) {
    super(Receta.class, database);
  }


  /**
   * Construct for Alias.
   */
  private QReceta(boolean dummy) {
    super(dummy);
  }

  /**
   * Private constructor for FetchGroup building.
   */
  private QReceta(Query<Receta> fetchGroupQuery) {
    super(fetchGroupQuery);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  public static class Alias {
    public static PInteger<QReceta> ID = _alias.ID;
    public static PString<QReceta> NIF = _alias.NIF;
    public static PSqlDate<QReceta> CONSULTA = _alias.CONSULTA;
    public static PDouble<QReceta> OD_ESFERA = _alias.OD_ESFERA;
    public static PDouble<QReceta> OD_CILINDRO = _alias.OD_CILINDRO;
    public static PDouble<QReceta> OD_ADICION = _alias.OD_ADICION;
    public static PDouble<QReceta> OD_AGUDEZA = _alias.OD_AGUDEZA;
    public static PDouble<QReceta> OI_ESFERA = _alias.OI_ESFERA;
    public static PDouble<QReceta> OI_CILINDRO = _alias.OI_CILINDRO;
    public static PDouble<QReceta> OI_ADICION = _alias.OI_ADICION;
    public static PDouble<QReceta> OI_AGUDEZA = _alias.OI_AGUDEZA;
  }
}
