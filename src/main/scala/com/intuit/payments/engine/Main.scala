package com.intuit.payments.engine

import java.util.Properties
import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {
    val config = Config()
    val props = new Properties()
    props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.setProperty("bootstrap.servers", config.bootstrapServers)
    props.setProperty("group.id", config.consumerGroup)
    val riskAnalyzer = new RandomPaymentRiskAnalyzer(new Random())
    val consumer = new PaymentsKafkaConsumer(Seq(config.paymentsTopic), props)
    val engine = new EngineRunner(riskAnalyzer, consumer)
    engine.runEngine()
  }
}
