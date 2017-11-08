package $package$.models.$entityName;format="Camel,lower"$s.db

import java.time.{ZoneOffset, ZonedDateTime}
import java.util.{Date, UUID}

import com.outworkers.phantom.dsl._
import $package$.models.$entityName;format="Camel,lower"$s.$entityName;format="Camel"$

import scala.concurrent.Future

abstract class $entityName;format="Camel"$sByNameModel extends Table[$entityName;format="Camel"$sByNameModel, $entityName;format="Camel"$] {

  override def tableName: String = "$entityName;format="Camel,lower"$s_by_name"

  object name extends Col[String] with PartitionKey

  object value extends Col[Double]

  // String because Cassandra only supports evil TimedUUIDs
  object id extends Col[String] {
    override lazy val name = "$entityName;format="Camel,lower"$_id"
  }

  object datetime extends DateColumn with ClusteringOrder {
    override lazy val name = "$entityName;format="Camel,lower"$_datetime"
  }

  override def fromRow(r: Row): $entityName;format="Camel"$ = $entityName;format="Camel"$(name(r), value(r), Some(UUID.fromString(id(r))), Some( ZonedDateTime.ofInstant(datetime(r).toInstant, ZoneOffset.UTC) ))
}

abstract class Concrete$entityName;format="Camel"$sByNameModel extends $entityName;format="Camel"$sByNameModel with RootConnector {

  def getByName(name: String): Future[List[$entityName;format="Camel"$]] = {
    select
      .where(_.name eqs name)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .fetch()
  }

  def store($entityName;format="Camel,lower"$: $entityName;format="Camel"$): Future[ResultSet] = {
    insert
      .value(_.name, $entityName;format="Camel,lower"$.name)
      .value(_.value, $entityName;format="Camel,lower"$.value)
      .value(_.id, $entityName;format="Camel,lower"$.id.get.toString)
      .value(_.datetime, new Date($entityName;format="Camel,lower"$.datetime.get.toInstant.toEpochMilli))
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .ifNotExists
      .future()
  }

  def deleteByNameAndDatetime(name: String, datetime: ZonedDateTime): Future[ResultSet] = {
    delete
      .where(_.name eqs name)
      .and(_.datetime eqs  new Date(datetime.toInstant.toEpochMilli))
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future()
  }
}
