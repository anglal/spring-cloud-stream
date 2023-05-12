package com.zlimbu.kafka;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zlimbu.kafka.producer.HelloKafkaProducer;
import com.zlimbu.kafka.producer.KafkaKeyProducer;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner{

	@Autowired
	private KafkaKeyProducer kafkaKeyProducer;
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(int i = 0; i < 10_000; i++) {
			var key = "key-" + (i % 4);
			var value = "value "+ i + " with key " + key; 
			this.kafkaKeyProducer.send(key, value);
			TimeUnit.SECONDS.sleep(1);
		}
		

	}

}
