package com.cisco.sit.normalizer.logic

import com.cisco.sit.normalizer.logic.NormalizerService.Log


object NormalizerService {
  case class Log(name: String, data: Seq[String], transformType: Int)
}

trait NormalizerService {
  def load(): Seq[Log]
  def transform(log: Log): Log
  def upload(log: Log)
}
