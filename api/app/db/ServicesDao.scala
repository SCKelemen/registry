package db

import io.flow.play.util.{IdGenerator, UrlKey}
import io.flow.registry.api.lib.DefaultPortAllocator
import io.flow.registry.v0.models.{Service, ServiceForm, Port}
import io.flow.registry.v0.models.json._
import io.flow.postgresql.{Authorization, Query, OrderBy, Pager}
import io.flow.common.v0.models.User
import anorm._
import play.api.db._
import play.api.Play.current
import play.api.libs.json._

import io.flow.registry.v0.anorm.conversions.Json._

object ServicesDao {

  private[this] val urlKey = UrlKey(minKeyLength = 3)
  private[this] val BaseQuery = Query(s"""
    select services.id,
           services.default_port
      from services
  """)

  private[this] val InsertQuery = """
    insert into services
    (id, default_port, updated_by_user_id)
    values
    ({id}, {default_port}, {updated_by_user_id})
  """

  private[this] val UpdateQuery = """
    update services
       set default_port = {default_port},
           updated_by_user_id = {updated_by_user_id}
     where id = {id}
  """

  private[this] val dbHelpers = DbHelpers("services")

  private[db] def validate(
    form: ServiceForm,
    existing: Option[Service] = None
  ): Seq[String] = {
    val idErrors = if (form.id.trim.isEmpty) {
      Seq("Id cannot be empty")
    } else {
      ServicesDao.findById(Authorization.All, form.id) match {
        case None => {
          urlKey.validate(form.id)
        }
        case Some(service) => {
          Some(service.id) == existing.map(_.id) match {
            case true => Nil
            case false => Seq("Service with this id already exists")
          }
        }
      }
    }

    idErrors ++ validatePort(form.defaultPort)
  }

  private[db] def validatePort(port: Long): Seq[String] = {
    if (port <= 1024) {
      Seq("Port must be > 1024")
    } else {
      Nil
    }
  }
  
  def create(createdBy: User, form: ServiceForm): Either[Seq[String], Service] = {
    validate(form) match {
      case Nil => {
        DB.withConnection { implicit c =>
          val id = form.id.trim

          SQL(InsertQuery).on(
            'id -> id,
            'default_port -> form.defaultPort,
            'updated_by_user_id -> createdBy.id
          ).execute()
        }

        Right(
          findById(Authorization.All, form.id.trim).getOrElse {
            sys.error("Failed to create service")
          }
        )
      }
      case errors => Left(errors)
    }
  }

  def update(createdBy: User, existing: Service, form: ServiceForm): Either[Seq[String], Service] = {
    validate(form, existing = Some(existing)) match {
      case Nil => {
        DB.withConnection { implicit c =>
          SQL(UpdateQuery).on(
            'id -> existing.id,
            'default_port -> form.defaultPort,
            'updated_by_user_id -> createdBy.id
          ).execute()
        }

        Right(
          findById(Authorization.All, existing.id).getOrElse {
            sys.error("Failed to update service")
          }
        )
      }
      case errors => Left(errors)
    }
  }

  def delete(deletedBy: User, service: Service) {
    dbHelpers.delete(deletedBy, service.id)
  }

  def findById(auth: Authorization, id: String): Option[Service] = {
    findAll(auth, ids = Some(Seq(id)), limit = 1).headOption
  }

  def findAll(
    auth: Authorization,
    ids: Option[Seq[String]] = None,
    defaultPortNumbers: Option[Seq[Long]] = None,
    limit: Long = 25,
    offset: Long = 0,
    orderBy: OrderBy = OrderBy("-created_at", Some("services"))
  ): Seq[Service] = {
    // TODO: Auth

    DB.withConnection { implicit c =>
      BaseQuery.
        optionalIn("services.id", ids).
        optionalIn("services.default_port", defaultPortNumbers).
        limit(limit).
        offset(offset).
        orderBy(orderBy.sql).
        as(
          io.flow.registry.v0.anorm.parsers.Service.parser().*
        )
    }
  }

}