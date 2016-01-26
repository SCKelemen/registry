/**
 * Generated by apidoc - http://www.apidoc.me
 * Service version: 0.0.1
 * apidoc:0.11.6 http://www.apidoc.me/flow/registry/0.0.1/anorm_2_x_parsers
 */
import anorm._

package io.flow.registry.v0.anorm.parsers {

  import io.flow.registry.v0.anorm.conversions.Json._

  object ApplicationType {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(s"$prefix${sep}name")

    def parser(name: String = "application_type"): RowParser[io.flow.registry.v0.models.ApplicationType] = {
      SqlParser.str(name) map {
        case value => io.flow.registry.v0.models.ApplicationType(value)
      }
    }

  }

  object Application {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      id = s"$prefix${sep}id",
      `type` = s"$prefix${sep}type",
      ports = s"$prefix${sep}ports"
    )

    def parser(
      id: String = "id",
      `type`: String = "type",
      ports: String = "ports"
    ): RowParser[io.flow.registry.v0.models.Application] = {
      SqlParser.str(id) ~
      io.flow.registry.v0.anorm.parsers.ApplicationType.parser(`type`) ~
      SqlParser.get[Seq[Long]](ports) map {
        case id ~ typeInstance ~ ports => {
          io.flow.registry.v0.models.Application(
            id = id,
            `type` = typeInstance,
            ports = ports
          )
        }
      }
    }

  }

  object ApplicationForm {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      id = s"$prefix${sep}id",
      `type` = s"$prefix${sep}type"
    )

    def parser(
      id: String = "id",
      `type`: String = "type"
    ): RowParser[io.flow.registry.v0.models.ApplicationForm] = {
      SqlParser.str(id) ~
      io.flow.registry.v0.anorm.parsers.ApplicationType.parser(`type`) map {
        case id ~ typeInstance => {
          io.flow.registry.v0.models.ApplicationForm(
            id = id,
            `type` = typeInstance
          )
        }
      }
    }

  }

  object ApplicationPutForm {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      `type` = s"$prefix${sep}type"
    )

    def parser(
      `type`: String = "type"
    ): RowParser[io.flow.registry.v0.models.ApplicationPutForm] = {
      io.flow.registry.v0.anorm.parsers.ApplicationType.parser(`type`) map {
        case typeInstance => {
          io.flow.registry.v0.models.ApplicationPutForm(
            `type` = typeInstance
          )
        }
      }
    }

  }

  object Port {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      number = s"$prefix${sep}number"
    )

    def parser(
      number: String = "number"
    ): RowParser[io.flow.registry.v0.models.Port] = {
      SqlParser.long(number) map {
        case number => {
          io.flow.registry.v0.models.Port(
            number = number
          )
        }
      }
    }

  }

  object PortForm {

    def parserWithPrefix(prefix: String, sep: String = "_") = parser(
      applicationId = s"$prefix${sep}application_id",
      number = s"$prefix${sep}number"
    )

    def parser(
      applicationId: String = "application_id",
      number: String = "number"
    ): RowParser[io.flow.registry.v0.models.PortForm] = {
      SqlParser.str(applicationId) ~
      SqlParser.long(number) map {
        case applicationId ~ number => {
          io.flow.registry.v0.models.PortForm(
            applicationId = applicationId,
            number = number
          )
        }
      }
    }

  }

}