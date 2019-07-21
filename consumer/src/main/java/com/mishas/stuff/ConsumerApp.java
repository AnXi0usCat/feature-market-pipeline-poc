package com.mishas.stuff;

import com.mishas.stuff.consumer.DataConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerApp {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerApp.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Thread thread = new Thread(
                () -> {
                    try {
                        new DataConsumer().runConsumer();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
