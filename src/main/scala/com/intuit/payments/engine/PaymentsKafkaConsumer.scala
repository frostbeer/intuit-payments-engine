package com.intuit.payments.engine

import com.typesafe.scalalogging.StrictLogging
import org.apache.kafka.clients.consumer.KafkaConsumer
import play.api.libs.json.Json

import java.time.Duration
import java.util.Properties
import scala.jdk.CollectionConverters._
import scala.util.Try

/**
 * Important: This kafka consumer will be automatically committing offsets as a POC `enable.auto.commit=true`
 * After the POC we would still want to commit offsets but in our control using the commit API.
 * All the messages we processed will be committed to keep the flow moving.
 * However if a message is failed to parse (which is unrecoverable) we can push it into a dead letter queue topic.
 * If the message failed in the analyzing the risk logic we could fail the entire process or write the message into
 * a retry topic that will be consumed together with the main topic, but this is a more Product decision.
 * This approach will allow the messages to keep being processed and wont be stuck on a bad message for example.
 */
class PaymentsKafkaConsumer(topics: Seq[String], consumerProps: Properties) extends StrictLogging {
  private val consumer = new KafkaConsumer[String, String](consumerProps)
  consumer.subscribe(topics.asJava)

  def getPayments: Seq[Try[Payment]] = {
    val records = consumer.poll(Duration.ofMillis(100)).asScala.toSeq
    records.map(record => Try(Json.parse(record.value()).as[Payment]))
  }

  def close: Unit = {
    consumer.close()
  }
}
