package com.example.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

public class ProducerCreator {
		
	public static  Producer<String,  String> create() {
		KafkaUtil kafkaUtil = new KafkaUtil();
		Properties prop = kafkaUtil.getProducerConfig();
		return new KafkaProducer<String,  String>(prop);
	}
}
