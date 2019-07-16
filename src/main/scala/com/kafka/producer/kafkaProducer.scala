package com.kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory

object kafkaProducer {

  def main(args:Array[String]): Unit = {

    val log = LoggerFactory.getLogger(getClass)
    log.info("kafka producer test")
    val topic="kafkaRepo"
    //Define Producer Properties
    val pros = new Properties()
    pros.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092")
    pros.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    pros.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    pros.put(ProducerConfig.ACKS_CONFIG,"all")
    // pros.put(ProducerConfig.LINGER_MS_CONFIG,1)

    val conf = new SparkConf().setAppName("CSV to Kafka").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val data = sc.textFile("C:\\SparkScala\\SparkScala\\fakefriends_kafka.csv")


    val kproducer = new KafkaProducer[String,String](pros)

    val record = new ProducerRecord[String,String](topic,"hi kafka 2")

    kproducer.send(record)
    kproducer.flush()
    kproducer.close()
  }

}
