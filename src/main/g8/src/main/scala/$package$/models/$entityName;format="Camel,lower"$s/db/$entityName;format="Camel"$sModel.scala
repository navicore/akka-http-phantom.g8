package $package$.models.$entityName;format="Camel,lower"$s.db

import java.time.{ZoneOffset, ZonedDateTime}
import java.util.{Date, UUID}

import com.outworkers.phantom.dsl._
import $package$.models.$entityName;format="Camel,lower"$s.$entityName;format="Camel"$

import scala.concurrent.Future

abstract class $entityName;format="Camel"$sModel extends Table[Concrete$entityName;format="Camel"$sModel, $entityName;format="Camel"$] {

  override def tableName: String = "$entityName;format="Camel,lower"$s"

  // String because TimedUUIDs are bad bad bad
  object id extends Col[String] with PartitionKey {
    override lazy val name = "$entityName;format="Camel,lower"$_id"
  }

  object name extends Col[String]

  object value extends Col[Double]

  object datetime extends DateColumn {
    override lazy val name = "$entityName;format="Camel,lower"$_datetime"
  }

  override def fromRow(r: Row): $entityName;format="Camel"$ = $entityName;format="Camel"$(name(r), value(r), Some(UUID.fromString(id(r))), Some( ZonedDateTime.ofInstant(datetime(r).toInstant, ZoneOffset.UTC) ))
}

abstract class Concrete$entityName;format="Camel"$sModel extends $entityName;format="Camel"$sModel with RootConnector {

  def getBy$entityName;format="Camel"$Id(id: UUID): Future[Option[$entityName;format="Camel"$]] = {
    select
      .where(_.id eqs id.toString)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .one()
  }

  def store($entityName;format="Camel,lower"$: $entityName;format="Camel"$): Future[ResultSet] = {
    insert
      .value(_.id, $entityName;format="Camel,lower"$.id.get.toString)
      .value(_.datetime, new Date($entityName;format="Camel,lower"$.datetime.get.toInstant.toEpochMilli))
      .value(_.name, $entityName;format="Camel,lower"$.name)
      .value(_.value, $entityName;format="Camel,lower"$.value)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .ifNotExists
      .future()
  }

  def deleteById(id: UUID): Future[ResultSet] = {
    delete
      .where(_.id eqs id.toString)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future()
  }
}

