package com.intuit.payments.engine

case class Config(bootstrapServers: String, paymentsTopic: String, consumerGroup: String)

object Config {
  def apply(): Config = {
    val bootstrapServers = sys.env.getOrElse("BOOTSTRAP_SERVERS",
      throw new Exception("You must supply environment variable BOOTSTRAP_SERVERS for kafka brokers"))
    val paymentsTopic = sys.env.getOrElse("PAYMENTS_TOPIC",
      throw new Exception("You must supply environment variable PAYMENTS_TOPIC for the payments topic name"))
    val consumerGroup = sys.env.getOrElse("CONSUMER_GROUP_ID",
      throw new Exception("You must supply environment variable CONSUMER_GROUP_ID for the consumer group"))

    new Config(bootstrapServers, paymentsTopic, consumerGroup)
  }
}