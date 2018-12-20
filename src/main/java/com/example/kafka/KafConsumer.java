package com.example.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafConsumer {

	public static void main(String[] args) {
		 Properties properties = new Properties();
	        properties.put("bootstrap.servers", "localhost:9092");
	        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        properties.put("group.id", "test-group");

	        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
	        kafkaConsumer.subscribe("test");

	}

}
