package com.example.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class ConsumerCreator {
	public static Consumer<String, String> create() {
		KafkaUtil kafkaUtil = new KafkaUtil();
		return new KafkaConsumer<String, String> (kafkaUtil.getConsumerConfig());
	}
}
