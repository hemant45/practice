package com.example.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.IntegerDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.LongDeserializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.IntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.LongSerializer;


public class KafkaUtil {

	 public Properties  getProducerConfig() {
	
		 Properties props = new Properties();
		 props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	     props.put(ProducerConfig.CLIENT_ID_CONFIG, "client1");
	     props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	     props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	     
	     return props;
	 }

	 public Properties getConsumerConfig() {
		 Properties props = new Properties();
		 
		 props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	     props.put(ConsumerConfig.GROUP_ID_CONFIG, "gropup1");
	     props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
	     props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
	     //props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
	     props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
	     props.put("partition.assignment.strategy", "");
	     props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	     return props;
	 }
	 
	 

}
