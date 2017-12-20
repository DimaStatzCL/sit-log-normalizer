package com.cisco.sit.normalizer.logic

import com.cisco.sit.normalizer.logic.MetricsService.Metric


object MetricsService{
  case class Metric(value: Float, name: String, group: String)
  case class TimerMetric(metric: Metric, isFinish: Boolean, id: String)
}

trait MetricsService {
  def report(metric: Metric)
  def send(metrics: Seq[Metric])
}
