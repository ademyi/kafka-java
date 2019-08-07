package com.spica.kafka.core;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

@SuppressWarnings("Duplicates")
public class ProducerDemoWithCallback {

    static Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);

    public ProducerDemoWithCallback() {
    }

    public static void main(String[] args) {
        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        String topic = "first_topic";

        for (int i = 0; i < 10; i++) {

            // create a producer record

            ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>(topic, "Hello Adem " + i);

            // send data - asyncronous
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
                    if (exception == null) {
                        logger.info("Received Metadata \n" +
                                "Topic :" + recordMetadata.topic() + "\n" +
                                "Partition :" + recordMetadata.partition() + "\n" +
                                "Offset :" + recordMetadata.offset() + "\n" +
                                "Timestamp :" + recordMetadata.timestamp());

                    } else {
                        logger.error("Error while producing");
                    }

                }
            });

        }

        producer.flush();
        producer.close();
		
/*
 kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-second-application --from-beginning 
 * */

    }

}
