package com.mishas.stuff.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;


import java.util.Collections;
import java.util.Properties;

public class DataConsumer {

    private final static String TOPIC = "data_generator_server.data_schema.transaction";
    private final static String BOOTSTRAP_SERVERS = "kafka:9092";

    private static Consumer<byte[], byte[]> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "transaction-data-consumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());

        // Create the consumer using props.
        final Consumer<byte[], byte[]> consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }

    public void runConsumer() throws InterruptedException {
        final Consumer<byte[], byte[]> consumer = createConsumer();

        while(true) {
            ConsumerRecords<byte[], byte[]> records = consumer.poll(1000);
            for (ConsumerRecord<byte[], byte[]> record : records) {
                System.out.printf("Received Message topic =%s, partition =%s, offset = %d, key = %s, value = %s\n",
                        record.topic(), record.partition(), record.offset(), new String(record.key()), new String((record.value())));
            }

            consumer.commitAsync();
        }
    }
//        consumer.close();
//        System.out.println("DONE");
}
