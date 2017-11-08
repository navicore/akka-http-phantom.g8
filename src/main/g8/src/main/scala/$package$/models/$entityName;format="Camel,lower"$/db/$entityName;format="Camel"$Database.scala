package $package$.models.$entityName;format="Camel,lower"$s.db

import com.outworkers.phantom.dsl._
import $package$.models.$entityName;format="Camel,lower"$s.db.Connector._

class $entityName;format="Camel"$sDatabase(override val connector: KeySpaceDef)
    extends Database[$entityName;format="Camel"$sDatabase](connector) {
  object $entityName;format="Camel,lower"$sModel
      extends Concrete$entityName;format="Camel"$sModel
      with connector.Connector
  object $entityName;format="Camel,lower"$sByNamesModel
      extends Concrete$entityName;format="Camel"$sByNameModel
      with connector.Connector
}

object Db extends $entityName;format="Camel"$sDatabase(connector)

trait DbProvider {
  def database: $entityName;format="Camel"$sDatabase
}

trait CassandraDatabase extends DbProvider {
  override val database: Db.type = Db
}
