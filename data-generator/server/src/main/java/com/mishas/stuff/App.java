package com.mishas.stuff;

import com.mishas.stuff.generator.TransactionGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Thread thread = new Thread(
                () -> new TransactionGenerator().streamAll()
        );
        try {
            thread.start();
            logger.info("App started in: {} milliseconds", (System.currentTimeMillis() - startTime) );
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
//{
//  "name": "data-generator-connector",
//        "config": {
//        "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
//        "tasks.max": "1",
//        "database.hostname": "postgres",
//        "database.port": "5432",
//        "database.user": "data_generator",
//        "database.password": "data_generator",
//        "database.dbname" : "data_generator",
//        "database.server.name": "data_generator_server",
//        "schema.whitelist": "data_schema",
//        "database.history.kafka.bootstrap.servers": "kafka:9092",
//        "database.history.kafka.topic": "data_geneator_migrations.transactions"
//        }
//}