package com.cisco.sit.normalizer

import akka.actor._
import akka.util.Timeout
import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.FiniteDuration
import com.typesafe.scalalogging.LazyLogging
import com.cisco.sit.normalizer.actors.ActorDataReader

object Boot extends LazyLogging {
  private implicit val ec = ExecutionContext.global
  private implicit val timeout = Timeout(FiniteDuration(5, TimeUnit.SECONDS))

  def main(args: Array[String]): Unit = {
    logger.info("starting normalizer")

    val system = ActorSystem("normalizer")
    val reader = system.actorOf(Props(new ActorDataReader(null)))
    logger.info(s"reader created ${reader.hashCode()}")

    sys addShutdownHook {
      logger.info("normalizer is shutting down")
      system.terminate()
    }
  }
}
