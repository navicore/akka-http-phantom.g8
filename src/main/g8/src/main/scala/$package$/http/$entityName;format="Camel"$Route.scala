package $package$.http

import akka.actor.ActorRef
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.{Directives, Route}
import akka.pattern.ask
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.typesafe.scalalogging.LazyLogging
import $package$.actors.$entityName;format="Camel"$Service._
import $package$.models.$entityName;format="Camel,lower"$s._
import spray.json._

import scala.concurrent.Future

object $entityName;format="Camel"$Route
    extends $entityName;format="Camel"$JsonSupport
    with LazyLogging
    with Directives
    with ErrorSupport {

  val defaultLimit = 1

  def apply(service: ActorRef): Route =
    logRequest(urlpath) {
      handleErrors {
        cors(corsSettings) {

          path(urlpath / "$entityName;format="Camel,lower"$" / JavaUUID) { id =>
            get {
              val f: Future[Any] = service ask GetById(id)
              onSuccess(f) { (r: Any) =>
                {
                  r match {
                    case Some($entityName;format="Camel,lower"$: $entityName;format="Camel"$) =>
                      complete(HttpEntity(ContentTypes.`application/json`,
                                          $entityName;format="Camel,lower"$.toJson.prettyPrint))
                    case _ =>
                      complete(StatusCodes.NotFound)
                  }
                }
              }
            } ~
              delete {
                val f: Future[Any] = service ask Delete(id)
                onSuccess(f) { (r: Any) =>
                  {
                    r match {
                      case Delete(deletedId) =>
                        complete(StatusCodes.OK,
                                 s"$entityName;format="Camel,lower"$ \$deletedId deleted")
                      case _ =>
                        complete(StatusCodes.NotFound)
                    }
                  }
                }
              }

          } ~
            path(urlpath / "$entityName;format="Camel,lower"$") {
              get {
                parameters('name.as[String], 'limit.as[Int] ? defaultLimit) {
                  (name, limit) =>
                    {
                      val f: Future[Any] = service ask GetByName(name, limit)
                      onSuccess(f) { (r: Any) =>
                        {
                          r match {
                            case $entityName;format="Camel,lower"$s: List[$entityName;format="Camel"$ @unchecked] =>
                              complete(
                                HttpEntity(ContentTypes.`application/json`,
                                           $entityName;format="Camel,lower"$s.toJson.prettyPrint))
                            case _ =>
                              complete(StatusCodes.NotFound)
                          }
                        }
                      }
                    }
                }
              } ~
                post {
                  decodeRequest {
                    entity(as[$entityName;format="Camel"$]) { $entityName;format="Camel,lower"$ =>
                      val f: Future[Any] = service ask $entityName;format="Camel,lower"$
                      onSuccess(f) { (r: Any) =>
                        {
                          r match {
                            case Some($entityName;format="Camel,lower"$: $entityName;format="Camel"$) =>
                              complete(
                                HttpEntity(ContentTypes.`application/json`,
                                           $entityName;format="Camel,lower"$.toJson.prettyPrint))
                            case _ =>
                              complete(StatusCodes.NotFound)
                          }
                        }
                      }
                    }
                  }
                }
            }
        }
      }
    }

}
