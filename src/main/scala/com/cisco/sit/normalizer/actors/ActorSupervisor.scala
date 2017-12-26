package com.cisco.sit.normalizer.actors

import akka.actor.Actor
import org.slf4j.LoggerFactory
import com.cisco.sit.normalizer.logic._
import com.typesafe.scalalogging.Logger
import com.cisco.sit.normalizer.actors.ActorSupervisor._


object ActorSupervisor {
  case object TimerEvent
  case object InitializeEvent
  case object NormalizationRequest
}


class ActorSupervisor (service: NormalizerService) extends Actor{
  private val logger = Logger(LoggerFactory.getLogger("ActorDataReader"))
  override def receive: Receive = {
    case InitializeEvent => initialize()

  }


  private def initialize() = {
    logger.info("initialize actors subsystem")
  }
}
