package com.javacodegeeks.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javacodegeeks.example.handler.EventHandler;
import com.javacodegeeks.example.model.Shipment;

import reactor.bus.Event;
import reactor.bus.EventBus;
import static reactor.bus.selector.Selectors.$;

/**
 * Hello world!
 */
@SpringBootApplication
public class App  implements CommandLineRunner {
    private final Logger LOG = LoggerFactory.getLogger("Application");

    private final EventBus eventBus;
    private final EventHandler eventHandler;

    @Autowired
    public App(EventBus eventBus, EventHandler eventHandler) {
        this.eventBus = eventBus;
        this.eventHandler = eventHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public void run(String... strings) throws Exception {
        eventBus.on($("eventHandler"), eventHandler);

        //Publish messages here
        for (int i = 0; i < 10; i++) {
            Shipment shipment = new Shipment();
            shipment.setShipmentId(String.valueOf(i));
            eventBus.notify("eventHandler", Event.wrap(shipment));
            LOG.info("Published shipment number {}.", i);
        }
    }
}
