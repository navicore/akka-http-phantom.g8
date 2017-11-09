package $package$.actors

import java.util.UUID

import akka.actor._
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging
import $package$.actors.$entityName;format="Camel"$Service._
import $package$.models.$entityName;format="Camel,lower"$s._
import $package$.models.$entityName;format="Camel,lower"$s.db._

import scala.concurrent.{ExecutionContextExecutor, Future}

object $entityName;format="Camel"$Service {
  def props(implicit timeout: Timeout) = Props(new $entityName;format="Camel"$Service)
  def name = "$entityName;format="Camel"$Service"

  final case class GetByName(name: String, limit: Int)
  final case class GetById(id: UUID)
  final case class Delete(id: UUID)
}
class $entityName;format="Camel"$Service(implicit timeout: Timeout)
    extends Actor
    with CassandraDatabase
    with LazyLogging {

  implicit val executionContext: ExecutionContextExecutor = context.dispatcher
  import com.outworkers.phantom.dsl._
  database.create()

  def delete($entityName;format="Camel,lower"$: $entityName;format="Camel"$): Future[ResultSet] = {
    for {
      _ <- database.$entityName;format="Camel,lower"$sModel.deleteById($entityName;format="Camel,lower"$.id.get)
      byName <- database.$entityName;format="Camel,lower"$sByNamesModel
        .deleteByNameAndDatetime($entityName;format="Camel,lower"$.name, $entityName;format="Camel,lower"$.datetime.get)
    } yield byName
  }

  def store($entityName;format="Camel,lower"$: $entityName;format="Camel"$): Future[ResultSet] = {
    for {
      _ <- database.$entityName;format="Camel,lower"$sModel.store($entityName;format="Camel,lower"$)
      byName <- database.$entityName;format="Camel,lower"$sByNamesModel.store($entityName;format="Camel,lower"$)
    } yield byName
  }

  override def receive: PartialFunction[Any, Unit] = {

    case Delete(id) =>
      val sdr = sender()

      database.$entityName;format="Camel,lower"$sModel
        .getBy$entityName;format="Camel"$Id(id)
        .onComplete(r => {
          if (r.get.isEmpty) sdr ! None
          else delete(r.get.head).onComplete(_ => sdr ! Delete(id))
        })

    case GetById(id) =>
      val sdr = sender()
      database.$entityName;format="Camel,lower"$sModel
        .getBy$entityName;format="Camel"$Id(id)
        .onComplete(
          r =>
            if (r.get.isEmpty)
              sdr ! None
            else
              sdr ! Some(r.get.head))

    case GetByName(name, limit) =>
      val sdr = sender()
      database.$entityName;format="Camel,lower"$sByNamesModel
        .getByName(name)
        .onComplete(r => sdr ! r.get.slice(0, limit))

    case $entityName;format="Camel"$(name, value, _, _) =>
      val sdr = sender()
      val new$entityName;format="Camel"$ = $entityName;format="Camel"$(name, value)
      store(new$entityName;format="Camel"$).onComplete(_ => sdr ! Some(new$entityName;format="Camel"$))

    case _ => sender() ! "huh?"
  }

}
