package org.example.query;

import io.ebean.Database;
import io.ebean.FetchGroup;
import io.ebean.Query;
import io.ebean.Transaction;
import io.ebean.typequery.Generated;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;
import org.example.Cliente;

/**
 * Query bean for Cliente.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@Generated("io.ebean.querybean.generator")
@TypeQueryBean("v1")
public class QCliente extends TQRootBean<Cliente,QCliente> {

  private static final QCliente _alias = new QCliente(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QCliente alias() {
    return _alias;
  }

  public PString<QCliente> NIF;
  public PString<QCliente> NOMBRE;
  public PString<QCliente> APELLIDOS;
  public PInteger<QCliente> EDAD;


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
  public static QCliente forFetchGroup() {
    return new QCliente(FetchGroup.queryFor(Cliente.class));
  }

  /**
   * Construct using the default Database.
   */
  public QCliente() {
    super(Cliente.class);
  }

  /**
   * Construct with a given transaction.
   */
  public QCliente(Transaction transaction) {
    super(Cliente.class, transaction);
  }

  /**
   * Construct with a given Database.
   */
  public QCliente(Database database) {
    super(Cliente.class, database);
  }


  /**
   * Construct for Alias.
   */
  private QCliente(boolean dummy) {
    super(dummy);
  }

  /**
   * Private constructor for FetchGroup building.
   */
  private QCliente(Query<Cliente> fetchGroupQuery) {
    super(fetchGroupQuery);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  public static class Alias {
    public static PString<QCliente> NIF = _alias.NIF;
    public static PString<QCliente> NOMBRE = _alias.NOMBRE;
    public static PString<QCliente> APELLIDOS = _alias.APELLIDOS;
    public static PInteger<QCliente> EDAD = _alias.EDAD;
  }
}
