package com.intuit.payments.engine

import scala.util.Try

trait PaymentRiskAnalyzer {
  def analyzePaymentRisk(payment: Payment): Try[Boolean]
}
