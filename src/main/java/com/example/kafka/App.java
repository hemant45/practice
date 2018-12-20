package com.example.kafka;

import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class App {
	
	@Autowired
	static
	ProducerCreator producer;
	
	public static void main(String[] args) {
		runProducer();
		//runConsumer();
	}
	
	public static void runProducer() {
		Producer<String, String> producer = ProducerCreator.create();
		for (Integer i=0 ; i<100 ; i ++) {
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("test",  "String : "+ i);
			System.out.println("Record sent : " + i);
			producer.send(record);
		}
	}
	
	/*public static void runConsumer() {
		Consumer<String, String> consumer = ConsumerCreator.create();
		int noMessageFound = 0;
		
		consumer.subscribe("test");
		
		while (true) {
	          ConsumerRecords<String, String> consumerRecords = (ConsumerRecords<String, String>) consumer.poll(1000);
	          // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
	          if (consumerRecords.count() == 0) {
	              noMessageFound++;
	              if (noMessageFound > 100)
	                // If no message found count is reached to threshold exit loop.  
	                break;
	              else
	                  continue;
	          }
	          //print each record. 
	          consumerRecords.forEach(record -> {
	              System.out.println("Record Key " + record.key());
	              System.out.println("Record value " + record.value());
	              System.out.println("Record partition " + record.partition());
	              System.out.println("Record offset " + record.offset());
	           });
	          // commits the offset of record to broker. 
	           consumer.commitAsync();
	        }
	    consumer.close();
	    }
	}*/
}