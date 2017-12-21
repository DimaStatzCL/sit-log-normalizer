package com.cisco.sit.normalizer.actors

import scala.util._
import akka.actor.Actor
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger
import com.cisco.sit.normalizer.logic._
import com.cisco.sit.normalizer.logic.NormalizerService._


class ActorDataReader(service: NormalizerService) extends Actor {
  private val normalizer = service
  private val logger = Logger(LoggerFactory.getLogger("name"))


  override def receive: Receive = {
    case NormalizationRequest => read()
  }

  private def read() = {
    logger.info("normalization request is handled")
    Try(normalizer.read()) match {
      case Success(x) => x.foreach(normalizer.transform)
      case Failure(x) => logger.error(s"failed to read data $x")
    }
  }
}
