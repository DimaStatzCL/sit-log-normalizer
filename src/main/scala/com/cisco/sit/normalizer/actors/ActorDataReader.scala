package com.cisco.sit.normalizer.actors

import scala.util._
import akka.actor.Actor
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger
import com.cisco.sit.normalizer.logic._
import org.apache.commons.lang3.exception.ExceptionUtils
import com.cisco.sit.normalizer.logic.NormalizerService.LogData
import com.cisco.sit.normalizer.actors.ActorSupervisor.NormalizationRequest


class ActorDataReader(service: NormalizerService) extends Actor {
  private val normalizer = service
  private val logger = Logger(LoggerFactory.getLogger("ActorDataReader"))

  override def receive: Receive = {
    case NormalizationRequest => read()
  }

  private def read() = {
    logger.info("normalization request is handled")
    Try(normalizer.read()) match {
      case Success(x) => x.foreach(transform)
      case Failure(x) => logger.error(s"failed to read data $x")
    }
  }

  private def transform(log: LogData) = {
    Try(normalizer.transform(log)) match {
      case Success(x) => logger.info(s"${log.logInfo.device} sent")
      case Failure(x) => logger.error(s"failed to send ${log.logInfo.device} " +
        s"${ExceptionUtils.getStackTrace(x)}")
    }
  }
}
