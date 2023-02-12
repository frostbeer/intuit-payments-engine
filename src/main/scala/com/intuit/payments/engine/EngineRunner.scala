package com.intuit.payments.engine

import com.typesafe.scalalogging.StrictLogging

import scala.util.Try

class EngineRunner(riskAnalyzer: PaymentRiskAnalyzer, consumer: PaymentsKafkaConsumer) extends StrictLogging {
  def runEngine(): Unit = {
    Try {
      while (true) {
        val payments = consumer.getPayments
        payments.map(paymentTry => {
          paymentTry.map(payment => riskAnalyzer.analyzePaymentRisk(payment)
            .map(risk => {
              logger.info("processing payment")
              PaymentRiskAnalysis(payment, risk)
              logger.info("payment processed")
            }))
            .recover(exception => logger.error("Failed to process payment message", exception))
        })
      }
    }.recover(ex => {
      logger.error("Unexpected error occurred while consuming payments message from queue", ex)
      consumer.close
    })

    consumer.close
  }
}