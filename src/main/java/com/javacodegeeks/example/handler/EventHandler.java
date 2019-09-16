package com.javacodegeeks.example.handler;

import com.javacodegeeks.example.model.Shipment;
import com.javacodegeeks.example.service.ShipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class EventHandler implements Consumer<Event<Shipment>> {

    private final ShipmentService shipmentService;

    @Autowired
    public EventHandler(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    public void accept(Event<Shipment> shipmentEvent) {
        Shipment shipment = shipmentEvent.getData();
        try {
            shipmentService.shipmentLocationUpdate(shipment);
        } catch (InterruptedException e) {
            //do something as bad things have happened
        }
    }
}
