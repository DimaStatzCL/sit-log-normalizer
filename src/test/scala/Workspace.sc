import org.apache.commons.lang3.exception.ExceptionUtils

import scala.util._

def f() = {
  throw new Exception("some exception")
}


def f1() = {
  Try(f()) match {
    case Failure(x) => println(ExceptionUtils.getStackTrace(x))
  }
}


System.currentTimeMillis()