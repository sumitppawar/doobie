package doobiescalatest

import cats.effect.IO
import doobie._, doobie.implicits._
import doobie.scalatest.imports._
import org.scalatest._

trait CheckerChecks[M[_]] extends FunSuite with Matchers with Checker[M] {
  lazy val transactor = Transactor.fromDriverManager[M](
    "org.h2.Driver",
    "jdbc:h2:mem:queryspec;DB_CLOSE_DELAY=-1",
    "sa", ""
  )
  test("trivial") { check(sql"select 1".query[Int]) }
}

class IOCheckerCheck extends CheckerChecks[IO] with IOChecker