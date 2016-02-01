/**
 * Generated by apidoc - http://www.apidoc.me
 * Service version: 0.0.10
 * apidoc:0.11.7 http://www.apidoc.me/flow/registry/0.0.10/play_2_4_client
 */
package io.flow.registry.v0.models {

  case class Application(
    id: String,
    ports: Seq[io.flow.registry.v0.models.Port],
    dependencies: Seq[String]
  )

  case class ApplicationForm(
    id: String,
    service: String,
    port: _root_.scala.Option[Long] = None,
    dependency: Seq[String] = Nil
  )

  case class ApplicationPutForm(
    service: _root_.scala.Option[String] = None,
    port: _root_.scala.Option[Long] = None,
    dependency: _root_.scala.Option[Seq[String]] = None
  )

  case class ApplicationVersion(
    id: String,
    timestamp: _root_.org.joda.time.DateTime,
    `type`: io.flow.common.v0.models.ChangeType,
    application: io.flow.registry.v0.models.Application
  )

  case class Port(
    service: io.flow.registry.v0.models.ServiceReference,
    external: Long,
    internal: Long
  )

  /**
   * A service is used to identify what type of software is actually running. We use
   * this to enable setting up application types with enough configuration info by
   * default to support our use cases around docker, CI, etc. The name service comes
   * from
   * https://www.iana.org/assignments/service-names-port-numbers/service-names-port-numbers.txt
   */
  case class Service(
    id: String,
    defaultPort: Long
  )

  case class ServiceForm(
    id: String,
    defaultPort: Long
  )

  case class ServicePutForm(
    defaultPort: Long
  )

  case class ServiceReference(
    id: String
  )

  case class ServiceVersion(
    id: String,
    timestamp: _root_.org.joda.time.DateTime,
    `type`: io.flow.common.v0.models.ChangeType,
    service: io.flow.registry.v0.models.Service
  )

}

package io.flow.registry.v0.models {

  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._
    import io.flow.common.v0.models.json._
    import io.flow.registry.v0.models.json._

    private[v0] implicit val jsonReadsUUID = __.read[String].map(java.util.UUID.fromString)

    private[v0] implicit val jsonWritesUUID = new Writes[java.util.UUID] {
      def writes(x: java.util.UUID) = JsString(x.toString)
    }

    private[v0] implicit val jsonReadsJodaDateTime = __.read[String].map { str =>
      import org.joda.time.format.ISODateTimeFormat.dateTimeParser
      dateTimeParser.parseDateTime(str)
    }

    private[v0] implicit val jsonWritesJodaDateTime = new Writes[org.joda.time.DateTime] {
      def writes(x: org.joda.time.DateTime) = {
        import org.joda.time.format.ISODateTimeFormat.dateTime
        val str = dateTime.print(x)
        JsString(str)
      }
    }

    implicit def jsonReadsRegistryApplication: play.api.libs.json.Reads[Application] = {
      (
        (__ \ "id").read[String] and
        (__ \ "ports").read[Seq[io.flow.registry.v0.models.Port]] and
        (__ \ "dependencies").read[Seq[String]]
      )(Application.apply _)
    }

    def jsObjectApplication(obj: io.flow.registry.v0.models.Application) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "ports" -> play.api.libs.json.Json.toJson(obj.ports),
        "dependencies" -> play.api.libs.json.Json.toJson(obj.dependencies)
      )
    }

    implicit def jsonWritesRegistryApplication: play.api.libs.json.Writes[Application] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.Application] {
        def writes(obj: io.flow.registry.v0.models.Application) = {
          jsObjectApplication(obj)
        }
      }
    }

    implicit def jsonReadsRegistryApplicationForm: play.api.libs.json.Reads[ApplicationForm] = {
      (
        (__ \ "id").read[String] and
        (__ \ "service").read[String] and
        (__ \ "port").readNullable[Long] and
        (__ \ "dependency").read[Seq[String]]
      )(ApplicationForm.apply _)
    }

    def jsObjectApplicationForm(obj: io.flow.registry.v0.models.ApplicationForm) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "service" -> play.api.libs.json.JsString(obj.service),
        "dependency" -> play.api.libs.json.Json.toJson(obj.dependency)
      ) ++ (obj.port match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("port" -> play.api.libs.json.JsNumber(x))
      })
    }

    implicit def jsonWritesRegistryApplicationForm: play.api.libs.json.Writes[ApplicationForm] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.ApplicationForm] {
        def writes(obj: io.flow.registry.v0.models.ApplicationForm) = {
          jsObjectApplicationForm(obj)
        }
      }
    }

    implicit def jsonReadsRegistryApplicationPutForm: play.api.libs.json.Reads[ApplicationPutForm] = {
      (
        (__ \ "service").readNullable[String] and
        (__ \ "port").readNullable[Long] and
        (__ \ "dependency").readNullable[Seq[String]]
      )(ApplicationPutForm.apply _)
    }

    def jsObjectApplicationPutForm(obj: io.flow.registry.v0.models.ApplicationPutForm) = {
      (obj.service match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("service" -> play.api.libs.json.JsString(x))
      }) ++
      (obj.port match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("port" -> play.api.libs.json.JsNumber(x))
      }) ++
      (obj.dependency match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("dependency" -> play.api.libs.json.Json.toJson(x))
      })
    }

    implicit def jsonWritesRegistryApplicationPutForm: play.api.libs.json.Writes[ApplicationPutForm] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.ApplicationPutForm] {
        def writes(obj: io.flow.registry.v0.models.ApplicationPutForm) = {
          jsObjectApplicationPutForm(obj)
        }
      }
    }

    implicit def jsonReadsRegistryApplicationVersion: play.api.libs.json.Reads[ApplicationVersion] = {
      (
        (__ \ "id").read[String] and
        (__ \ "timestamp").read[_root_.org.joda.time.DateTime] and
        (__ \ "type").read[io.flow.common.v0.models.ChangeType] and
        (__ \ "application").read[io.flow.registry.v0.models.Application]
      )(ApplicationVersion.apply _)
    }

    def jsObjectApplicationVersion(obj: io.flow.registry.v0.models.ApplicationVersion) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "timestamp" -> play.api.libs.json.JsString(_root_.org.joda.time.format.ISODateTimeFormat.dateTime.print(obj.timestamp)),
        "type" -> play.api.libs.json.JsString(obj.`type`.toString),
        "application" -> jsObjectApplication(obj.application)
      )
    }

    implicit def jsonWritesRegistryApplicationVersion: play.api.libs.json.Writes[ApplicationVersion] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.ApplicationVersion] {
        def writes(obj: io.flow.registry.v0.models.ApplicationVersion) = {
          jsObjectApplicationVersion(obj)
        }
      }
    }

    implicit def jsonReadsRegistryPort: play.api.libs.json.Reads[Port] = {
      (
        (__ \ "service").read[io.flow.registry.v0.models.ServiceReference] and
        (__ \ "external").read[Long] and
        (__ \ "internal").read[Long]
      )(Port.apply _)
    }

    def jsObjectPort(obj: io.flow.registry.v0.models.Port) = {
      play.api.libs.json.Json.obj(
        "service" -> jsObjectServiceReference(obj.service),
        "external" -> play.api.libs.json.JsNumber(obj.external),
        "internal" -> play.api.libs.json.JsNumber(obj.internal)
      )
    }

    implicit def jsonWritesRegistryPort: play.api.libs.json.Writes[Port] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.Port] {
        def writes(obj: io.flow.registry.v0.models.Port) = {
          jsObjectPort(obj)
        }
      }
    }

    implicit def jsonReadsRegistryService: play.api.libs.json.Reads[Service] = {
      (
        (__ \ "id").read[String] and
        (__ \ "default_port").read[Long]
      )(Service.apply _)
    }

    def jsObjectService(obj: io.flow.registry.v0.models.Service) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "default_port" -> play.api.libs.json.JsNumber(obj.defaultPort)
      )
    }

    implicit def jsonWritesRegistryService: play.api.libs.json.Writes[Service] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.Service] {
        def writes(obj: io.flow.registry.v0.models.Service) = {
          jsObjectService(obj)
        }
      }
    }

    implicit def jsonReadsRegistryServiceForm: play.api.libs.json.Reads[ServiceForm] = {
      (
        (__ \ "id").read[String] and
        (__ \ "default_port").read[Long]
      )(ServiceForm.apply _)
    }

    def jsObjectServiceForm(obj: io.flow.registry.v0.models.ServiceForm) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "default_port" -> play.api.libs.json.JsNumber(obj.defaultPort)
      )
    }

    implicit def jsonWritesRegistryServiceForm: play.api.libs.json.Writes[ServiceForm] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.ServiceForm] {
        def writes(obj: io.flow.registry.v0.models.ServiceForm) = {
          jsObjectServiceForm(obj)
        }
      }
    }

    implicit def jsonReadsRegistryServicePutForm: play.api.libs.json.Reads[ServicePutForm] = {
      (__ \ "default_port").read[Long].map { x => new ServicePutForm(defaultPort = x) }
    }

    def jsObjectServicePutForm(obj: io.flow.registry.v0.models.ServicePutForm) = {
      play.api.libs.json.Json.obj(
        "default_port" -> play.api.libs.json.JsNumber(obj.defaultPort)
      )
    }

    implicit def jsonWritesRegistryServicePutForm: play.api.libs.json.Writes[ServicePutForm] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.ServicePutForm] {
        def writes(obj: io.flow.registry.v0.models.ServicePutForm) = {
          jsObjectServicePutForm(obj)
        }
      }
    }

    implicit def jsonReadsRegistryServiceReference: play.api.libs.json.Reads[ServiceReference] = {
      (__ \ "id").read[String].map { x => new ServiceReference(id = x) }
    }

    def jsObjectServiceReference(obj: io.flow.registry.v0.models.ServiceReference) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id)
      )
    }

    implicit def jsonWritesRegistryServiceReference: play.api.libs.json.Writes[ServiceReference] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.ServiceReference] {
        def writes(obj: io.flow.registry.v0.models.ServiceReference) = {
          jsObjectServiceReference(obj)
        }
      }
    }

    implicit def jsonReadsRegistryServiceVersion: play.api.libs.json.Reads[ServiceVersion] = {
      (
        (__ \ "id").read[String] and
        (__ \ "timestamp").read[_root_.org.joda.time.DateTime] and
        (__ \ "type").read[io.flow.common.v0.models.ChangeType] and
        (__ \ "service").read[io.flow.registry.v0.models.Service]
      )(ServiceVersion.apply _)
    }

    def jsObjectServiceVersion(obj: io.flow.registry.v0.models.ServiceVersion) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "timestamp" -> play.api.libs.json.JsString(_root_.org.joda.time.format.ISODateTimeFormat.dateTime.print(obj.timestamp)),
        "type" -> play.api.libs.json.JsString(obj.`type`.toString),
        "service" -> jsObjectService(obj.service)
      )
    }

    implicit def jsonWritesRegistryServiceVersion: play.api.libs.json.Writes[ServiceVersion] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.ServiceVersion] {
        def writes(obj: io.flow.registry.v0.models.ServiceVersion) = {
          jsObjectServiceVersion(obj)
        }
      }
    }
  }
}

package io.flow.registry.v0 {

  object Bindables {

    import play.api.mvc.{PathBindable, QueryStringBindable}
    import org.joda.time.{DateTime, LocalDate}
    import org.joda.time.format.ISODateTimeFormat
    import io.flow.registry.v0.models._

    // Type: date-time-iso8601
    implicit val pathBindableTypeDateTimeIso8601 = new PathBindable.Parsing[org.joda.time.DateTime](
      ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
    )

    implicit val queryStringBindableTypeDateTimeIso8601 = new QueryStringBindable.Parsing[org.joda.time.DateTime](
      ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
    )

    // Type: date-iso8601
    implicit val pathBindableTypeDateIso8601 = new PathBindable.Parsing[org.joda.time.LocalDate](
      ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: Exception) => s"Error parsing date $key. Example: 2014-04-29"
    )

    implicit val queryStringBindableTypeDateIso8601 = new QueryStringBindable.Parsing[org.joda.time.LocalDate](
      ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: Exception) => s"Error parsing date $key. Example: 2014-04-29"
    )



  }

}


package io.flow.registry.v0 {

  object Constants {

    val Namespace = "io.flow.registry.v0"
    val UserAgent = "apidoc:0.11.7 http://www.apidoc.me/flow/registry/0.0.10/play_2_4_client"
    val Version = "0.0.10"
    val VersionMajor = 0

  }

  class Client(
    apiUrl: String,
    auth: scala.Option[io.flow.registry.v0.Authorization] = None,
    defaultHeaders: Seq[(String, String)] = Nil
  ) extends interfaces.Client {
    import io.flow.common.v0.models.json._
    import io.flow.registry.v0.models.json._

    private[this] val logger = play.api.Logger("io.flow.registry.v0.Client")

    logger.info(s"Initializing io.flow.registry.v0.Client for url $apiUrl")

    def applications: Applications = Applications

    def healthchecks: Healthchecks = Healthchecks

    def services: Services = Services

    object Applications extends Applications {
      override def get(
        id: _root_.scala.Option[Seq[String]] = None,
        port: _root_.scala.Option[Seq[Long]] = None,
        service: _root_.scala.Option[Seq[String]] = None,
        prefix: _root_.scala.Option[String] = None,
        q: _root_.scala.Option[String] = None,
        limit: Long = 25,
        offset: Long = 0,
        sort: String = "-created_at"
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.Application]] = {
        val queryParameters = Seq(
          prefix.map("prefix" -> _),
          q.map("q" -> _),
          Some("limit" -> limit.toString),
          Some("offset" -> offset.toString),
          Some("sort" -> sort)
        ).flatten ++
          id.getOrElse(Nil).map("id" -> _) ++
          port.getOrElse(Nil).map("port" -> _.toString) ++
          service.getOrElse(Nil).map("service" -> _)

        _executeRequest("GET", s"/applications", queryParameters = queryParameters).map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("Seq[io.flow.registry.v0.models.Application]", r, _.validate[Seq[io.flow.registry.v0.models.Application]])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 401")
        }
      }

      override def getVersions(
        id: _root_.scala.Option[Seq[String]] = None,
        application: _root_.scala.Option[Seq[String]] = None,
        limit: Long = 25,
        offset: Long = 0,
        sort: String = "journal_timestamp"
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.ApplicationVersion]] = {
        val queryParameters = Seq(
          Some("limit" -> limit.toString),
          Some("offset" -> offset.toString),
          Some("sort" -> sort)
        ).flatten ++
          id.getOrElse(Nil).map("id" -> _) ++
          application.getOrElse(Nil).map("application" -> _)

        _executeRequest("GET", s"/applications/versions", queryParameters = queryParameters).map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("Seq[io.flow.registry.v0.models.ApplicationVersion]", r, _.validate[Seq[io.flow.registry.v0.models.ApplicationVersion]])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 401")
        }
      }

      override def getById(
        id: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Application] = {
        _executeRequest("GET", s"/applications/${play.utils.UriEncoding.encodePathSegment(id, "UTF-8")}").map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("io.flow.registry.v0.models.Application", r, _.validate[io.flow.registry.v0.models.Application])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r if r.status == 404 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 401, 404")
        }
      }

      override def post(
        applicationForm: io.flow.registry.v0.models.ApplicationForm
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Application] = {
        val payload = play.api.libs.json.Json.toJson(applicationForm)

        _executeRequest("POST", s"/applications", body = Some(payload)).map {
          case r if r.status == 201 => _root_.io.flow.registry.v0.Client.parseJson("io.flow.registry.v0.models.Application", r, _.validate[io.flow.registry.v0.models.Application])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r if r.status == 422 => throw new io.flow.registry.v0.errors.ErrorsResponse(r)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 201, 401, 422")
        }
      }

      override def putById(
        id: String,
        applicationPutForm: io.flow.registry.v0.models.ApplicationPutForm
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Application] = {
        val payload = play.api.libs.json.Json.toJson(applicationPutForm)

        _executeRequest("PUT", s"/applications/${play.utils.UriEncoding.encodePathSegment(id, "UTF-8")}", body = Some(payload)).map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("io.flow.registry.v0.models.Application", r, _.validate[io.flow.registry.v0.models.Application])
          case r if r.status == 201 => _root_.io.flow.registry.v0.Client.parseJson("io.flow.registry.v0.models.Application", r, _.validate[io.flow.registry.v0.models.Application])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r if r.status == 422 => throw new io.flow.registry.v0.errors.ErrorsResponse(r)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 201, 401, 422")
        }
      }

      override def deleteById(
        id: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Unit] = {
        _executeRequest("DELETE", s"/applications/${play.utils.UriEncoding.encodePathSegment(id, "UTF-8")}").map {
          case r if r.status == 204 => ()
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r if r.status == 404 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 204, 401, 404")
        }
      }
    }

    object Healthchecks extends Healthchecks {
      override def getHealthcheck()(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.common.v0.models.Healthcheck] = {
        _executeRequest("GET", s"/_internal_/healthcheck").map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("io.flow.common.v0.models.Healthcheck", r, _.validate[io.flow.common.v0.models.Healthcheck])
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
        }
      }
    }

    object Services extends Services {
      override def get(
        id: _root_.scala.Option[Seq[String]] = None,
        limit: Long = 25,
        offset: Long = 0,
        sort: String = "-created_at"
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.Service]] = {
        val queryParameters = Seq(
          Some("limit" -> limit.toString),
          Some("offset" -> offset.toString),
          Some("sort" -> sort)
        ).flatten ++
          id.getOrElse(Nil).map("id" -> _)

        _executeRequest("GET", s"/services", queryParameters = queryParameters).map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("Seq[io.flow.registry.v0.models.Service]", r, _.validate[Seq[io.flow.registry.v0.models.Service]])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 401")
        }
      }

      override def getVersions(
        id: _root_.scala.Option[Seq[String]] = None,
        service: _root_.scala.Option[Seq[String]] = None,
        limit: Long = 25,
        offset: Long = 0,
        sort: String = "journal_timestamp"
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.ServiceVersion]] = {
        val queryParameters = Seq(
          Some("limit" -> limit.toString),
          Some("offset" -> offset.toString),
          Some("sort" -> sort)
        ).flatten ++
          id.getOrElse(Nil).map("id" -> _) ++
          service.getOrElse(Nil).map("service" -> _)

        _executeRequest("GET", s"/services/versions", queryParameters = queryParameters).map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("Seq[io.flow.registry.v0.models.ServiceVersion]", r, _.validate[Seq[io.flow.registry.v0.models.ServiceVersion]])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 401")
        }
      }

      override def getById(
        id: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Service] = {
        _executeRequest("GET", s"/services/${play.utils.UriEncoding.encodePathSegment(id, "UTF-8")}").map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("io.flow.registry.v0.models.Service", r, _.validate[io.flow.registry.v0.models.Service])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r if r.status == 404 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 401, 404")
        }
      }

      override def post(
        serviceForm: io.flow.registry.v0.models.ServiceForm
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Service] = {
        val payload = play.api.libs.json.Json.toJson(serviceForm)

        _executeRequest("POST", s"/services", body = Some(payload)).map {
          case r if r.status == 201 => _root_.io.flow.registry.v0.Client.parseJson("io.flow.registry.v0.models.Service", r, _.validate[io.flow.registry.v0.models.Service])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r if r.status == 422 => throw new io.flow.registry.v0.errors.ErrorsResponse(r)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 201, 401, 422")
        }
      }

      override def putById(
        id: String,
        servicePutForm: io.flow.registry.v0.models.ServicePutForm
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Service] = {
        val payload = play.api.libs.json.Json.toJson(servicePutForm)

        _executeRequest("PUT", s"/services/${play.utils.UriEncoding.encodePathSegment(id, "UTF-8")}", body = Some(payload)).map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("io.flow.registry.v0.models.Service", r, _.validate[io.flow.registry.v0.models.Service])
          case r if r.status == 201 => _root_.io.flow.registry.v0.Client.parseJson("io.flow.registry.v0.models.Service", r, _.validate[io.flow.registry.v0.models.Service])
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r if r.status == 422 => throw new io.flow.registry.v0.errors.ErrorsResponse(r)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 201, 401, 422")
        }
      }

      override def deleteById(
        id: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Unit] = {
        _executeRequest("DELETE", s"/services/${play.utils.UriEncoding.encodePathSegment(id, "UTF-8")}").map {
          case r if r.status == 204 => ()
          case r if r.status == 401 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r if r.status == 404 => throw new io.flow.registry.v0.errors.UnitResponse(r.status)
          case r if r.status == 422 => throw new io.flow.registry.v0.errors.ErrorsResponse(r)
          case r => throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 204, 401, 404, 422")
        }
      }
    }

    def _requestHolder(path: String): play.api.libs.ws.WSRequest = {
      import play.api.Play.current

      val holder = play.api.libs.ws.WS.url(apiUrl + path).withHeaders(
        "User-Agent" -> Constants.UserAgent,
        "X-Apidoc-Version" -> Constants.Version,
        "X-Apidoc-Version-Major" -> Constants.VersionMajor.toString
      ).withHeaders(defaultHeaders : _*)
      auth.fold(holder) {
        case Authorization.Basic(username, password) => {
          holder.withAuth(username, password.getOrElse(""), play.api.libs.ws.WSAuthScheme.BASIC)
        }
        case a => sys.error("Invalid authorization scheme[" + a.getClass + "]")
      }
    }

    def _logRequest(method: String, req: play.api.libs.ws.WSRequest)(implicit ec: scala.concurrent.ExecutionContext): play.api.libs.ws.WSRequest = {
      val queryComponents = for {
        (name, values) <- req.queryString
        value <- values
      } yield s"$name=$value"
      val url = s"${req.url}${queryComponents.mkString("?", "&", "")}"
      auth.fold(logger.info(s"curl -X $method $url")) { _ =>
        logger.info(s"curl -X $method -u '[REDACTED]:' $url")
      }
      req
    }

    def _executeRequest(
      method: String,
      path: String,
      queryParameters: Seq[(String, String)] = Seq.empty,
      body: Option[play.api.libs.json.JsValue] = None
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[play.api.libs.ws.WSResponse] = {
      method.toUpperCase match {
        case "GET" => {
          _logRequest("GET", _requestHolder(path).withQueryString(queryParameters:_*)).get()
        }
        case "POST" => {
          _logRequest("POST", _requestHolder(path).withQueryString(queryParameters:_*).withHeaders("Content-Type" -> "application/json; charset=UTF-8")).post(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "PUT" => {
          _logRequest("PUT", _requestHolder(path).withQueryString(queryParameters:_*).withHeaders("Content-Type" -> "application/json; charset=UTF-8")).put(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "PATCH" => {
          _logRequest("PATCH", _requestHolder(path).withQueryString(queryParameters:_*)).patch(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "DELETE" => {
          _logRequest("DELETE", _requestHolder(path).withQueryString(queryParameters:_*)).delete()
        }
         case "HEAD" => {
          _logRequest("HEAD", _requestHolder(path).withQueryString(queryParameters:_*)).head()
        }
         case "OPTIONS" => {
          _logRequest("OPTIONS", _requestHolder(path).withQueryString(queryParameters:_*)).options()
        }
        case _ => {
          _logRequest(method, _requestHolder(path).withQueryString(queryParameters:_*))
          sys.error("Unsupported method[%s]".format(method))
        }
      }
    }

  }

  object Client {

    def parseJson[T](
      className: String,
      r: play.api.libs.ws.WSResponse,
      f: (play.api.libs.json.JsValue => play.api.libs.json.JsResult[T])
    ): T = {
      f(play.api.libs.json.Json.parse(r.body)) match {
        case play.api.libs.json.JsSuccess(x, _) => x
        case play.api.libs.json.JsError(errors) => {
          throw new io.flow.registry.v0.errors.FailedRequest(r.status, s"Invalid json for class[" + className + "]: " + errors.mkString(" "))
        }
      }
    }

  }

  sealed trait Authorization
  object Authorization {
    case class Basic(username: String, password: Option[String] = None) extends Authorization
  }

  package interfaces {

    trait Client {
      def applications: io.flow.registry.v0.Applications
      def healthchecks: io.flow.registry.v0.Healthchecks
      def services: io.flow.registry.v0.Services
    }

  }

  trait Applications {
    /**
     * Search applications. Always paginated.
     */
    def get(
      id: _root_.scala.Option[Seq[String]] = None,
      port: _root_.scala.Option[Seq[Long]] = None,
      service: _root_.scala.Option[Seq[String]] = None,
      prefix: _root_.scala.Option[String] = None,
      q: _root_.scala.Option[String] = None,
      limit: Long = 25,
      offset: Long = 0,
      sort: String = "-created_at"
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.Application]]

    /**
     * Provides visibility into recent changes of each application, including deletion
     */
    def getVersions(
      id: _root_.scala.Option[Seq[String]] = None,
      application: _root_.scala.Option[Seq[String]] = None,
      limit: Long = 25,
      offset: Long = 0,
      sort: String = "journal_timestamp"
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.ApplicationVersion]]

    /**
     * Returns information about a specific application.
     */
    def getById(
      id: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Application]

    /**
     * Create a new application.
     */
    def post(
      applicationForm: io.flow.registry.v0.models.ApplicationForm
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Application]

    /**
     * Upsert an application with the specified id.
     */
    def putById(
      id: String,
      applicationPutForm: io.flow.registry.v0.models.ApplicationPutForm
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Application]

    /**
     * Delete the application with this id
     */
    def deleteById(
      id: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Unit]
  }

  trait Healthchecks {
    def getHealthcheck()(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.common.v0.models.Healthcheck]
  }

  trait Services {
    /**
     * Search services. Always paginated.
     */
    def get(
      id: _root_.scala.Option[Seq[String]] = None,
      limit: Long = 25,
      offset: Long = 0,
      sort: String = "-created_at"
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.Service]]

    /**
     * Provides visibility into recent changes of each service, including deletion
     */
    def getVersions(
      id: _root_.scala.Option[Seq[String]] = None,
      service: _root_.scala.Option[Seq[String]] = None,
      limit: Long = 25,
      offset: Long = 0,
      sort: String = "journal_timestamp"
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.ServiceVersion]]

    /**
     * Returns information about a specific service.
     */
    def getById(
      id: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Service]

    /**
     * Create a new service.
     */
    def post(
      serviceForm: io.flow.registry.v0.models.ServiceForm
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Service]

    /**
     * Upsert an service with the specified id.
     */
    def putById(
      id: String,
      servicePutForm: io.flow.registry.v0.models.ServicePutForm
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Service]

    /**
     * Delete the service with this id
     */
    def deleteById(
      id: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Unit]
  }

  package errors {

    import io.flow.common.v0.models.json._
    import io.flow.registry.v0.models.json._

    case class ErrorsResponse(
      response: play.api.libs.ws.WSResponse,
      message: Option[String] = None
    ) extends Exception(message.getOrElse(response.status + ": " + response.body)){
      lazy val errors = _root_.io.flow.registry.v0.Client.parseJson("Seq[io.flow.common.v0.models.Error]", response, _.validate[Seq[io.flow.common.v0.models.Error]])
    }

    case class UnitResponse(status: Int) extends Exception(s"HTTP $status")

    case class FailedRequest(responseCode: Int, message: String, requestUri: Option[_root_.java.net.URI] = None) extends Exception(s"HTTP $responseCode: $message")

  }

}