/**
 * Generated by apidoc - http://www.apidoc.me
 * Service version: 0.0.1
 * apidoc:0.11.6 http://www.apidoc.me/flow/registry/0.0.1/play_2_4_client
 */
package io.flow.registry.v0.models {

  case class Application(
    id: String,
    ports: Seq[io.flow.registry.v0.models.Port]
  )

  case class ApplicationForm(
    id: String
  )

  case class Port(
    id: String,
    applicationId: String,
    number: Long
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
        (__ \ "ports").read[Seq[io.flow.registry.v0.models.Port]]
      )(Application.apply _)
    }

    def jsObjectApplication(obj: io.flow.registry.v0.models.Application) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "ports" -> play.api.libs.json.Json.toJson(obj.ports)
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
      (__ \ "id").read[String].map { x => new ApplicationForm(id = x) }
    }

    def jsObjectApplicationForm(obj: io.flow.registry.v0.models.ApplicationForm) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id)
      )
    }

    implicit def jsonWritesRegistryApplicationForm: play.api.libs.json.Writes[ApplicationForm] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.ApplicationForm] {
        def writes(obj: io.flow.registry.v0.models.ApplicationForm) = {
          jsObjectApplicationForm(obj)
        }
      }
    }

    implicit def jsonReadsRegistryPort: play.api.libs.json.Reads[Port] = {
      (
        (__ \ "id").read[String] and
        (__ \ "application_id").read[String] and
        (__ \ "number").read[Long]
      )(Port.apply _)
    }

    def jsObjectPort(obj: io.flow.registry.v0.models.Port) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "application_id" -> play.api.libs.json.JsString(obj.applicationId),
        "number" -> play.api.libs.json.JsNumber(obj.number)
      )
    }

    implicit def jsonWritesRegistryPort: play.api.libs.json.Writes[Port] = {
      new play.api.libs.json.Writes[io.flow.registry.v0.models.Port] {
        def writes(obj: io.flow.registry.v0.models.Port) = {
          jsObjectPort(obj)
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
    val UserAgent = "apidoc:0.11.6 http://www.apidoc.me/flow/registry/0.0.1/play_2_4_client"
    val Version = "0.0.1"
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

    object Applications extends Applications {
      override def get(
        id: _root_.scala.Option[Seq[String]] = None,
        limit: Long = 25,
        offset: Long = 0,
        sort: String = "-created_at"
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.Application]] = {
        val queryParameters = Seq(
          Some("limit" -> limit.toString),
          Some("offset" -> offset.toString),
          Some("sort" -> sort)
        ).flatten ++
          id.getOrElse(Nil).map("id" -> _)

        _executeRequest("GET", s"/applications", queryParameters = queryParameters).map {
          case r if r.status == 200 => _root_.io.flow.registry.v0.Client.parseJson("Seq[io.flow.registry.v0.models.Application]", r, _.validate[Seq[io.flow.registry.v0.models.Application]])
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
        id: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.registry.v0.models.Application] = {
        _executeRequest("PUT", s"/applications/${play.utils.UriEncoding.encodePathSegment(id, "UTF-8")}").map {
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
    }

  }

  trait Applications {
    /**
     * Search applications. Always paginated.
     */
    def get(
      id: _root_.scala.Option[Seq[String]] = None,
      limit: Long = 25,
      offset: Long = 0,
      sort: String = "-created_at"
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.registry.v0.models.Application]]

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
      id: String
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