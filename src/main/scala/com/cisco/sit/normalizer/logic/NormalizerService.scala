package com.cisco.sit.normalizer.logic

import com.cisco.sit.normalizer.logic.NormalizerService._


object NormalizerService {
  type TransformHandler = String => String
  case class LogData(logInfo: LogID, data: Seq[String])
  case class LogID(tenant: String, device: String, timeStamp: Long)
}

trait NormalizerService {
  def read(): Seq[LogData]
  def write(log: LogData)
  def transform(log: LogData): LogData
}
