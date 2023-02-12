package com.intuit.payments.engine

import scala.util.{Random, Try}

class RandomPaymentRiskAnalyzer(rand: Random) extends PaymentRiskAnalyzer {
  override def analyzePaymentRisk(payment: Payment): Try[Boolean] = {
    Try(rand.between(0, 10) < 7)
  }
}
