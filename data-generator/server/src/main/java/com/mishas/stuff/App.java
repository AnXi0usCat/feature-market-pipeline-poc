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