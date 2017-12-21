package com.cisco.sit.normalizer.logic

import com.cisco.sit.normalizer.logic.NormalizerService.Log


object NormalizerService {
  case object NormalizationRequest
  case class Log(name: String, data: Seq[String], transformType: Int)
}

trait NormalizerService {
  def read(): Seq[Log]
  def transform(log: Log): Log
  def write(log: Log)
}
