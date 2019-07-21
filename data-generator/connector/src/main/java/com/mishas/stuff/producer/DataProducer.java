package com.mishas.stuff.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class DataProducer {

    private final static String TOPIC = "data_generator_server.data_schema.transaction";
    private final static String BOOTSTRAP_SERVERS = "kafka:9092";

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "transaction-data-generator");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    public void runProducer(final String message) {
        final Producer<Long, String> producer = createProducer();
        long time = System.currentTimeMillis();

        try {
            var index = time;
            final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, index, message);
            producer.send(record, (metadata, exception) -> {
                long elapsedTime = System.currentTimeMillis() - time;
                if (metadata != null) {
//                    System.out.printf("sent record meta(partition=%d, offset=%d) time=%d\n",
//                            metadata.partition(), metadata.offset(), elapsedTime);
                } else {
                    exception.printStackTrace();
                }
            });
        }finally {
            producer.flush();
            producer.close();
        }
    }
}
