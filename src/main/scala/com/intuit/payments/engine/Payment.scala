package com.intuit.payments.engine

import com.intuit.payments.engine.Currency.Currency
import play.api.libs.json.{Format, Json, OFormat}

import java.time.LocalDateTime

case class Payment(amount: Double,
                   currency: Currency,
                   userId: String,
                   payeeId: String,
                   paymentMethodId: String,
                   createdAt: LocalDateTime)

object Currency extends Enumeration {
  type Currency = Value

  val USD: Currency.Value = Value("USD")
  val EUR: Currency.Value = Value("EUR")
  val CAD: Currency.Value = Value("CAD")
  val GBP: Currency.Value = Value("GBP")

  implicit val format: Format[Currency] = Json.formatEnum(this)
}

object Payment {
  implicit val fmt: OFormat[Payment] = Json.format[Payment]
}

case class PaymentRiskAnalysis(payment: Payment, risky: Boolean)