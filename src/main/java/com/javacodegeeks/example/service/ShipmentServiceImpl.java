package com.javacodegeeks.example.service;

import com.javacodegeeks.example.model.Shipment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final Logger LOG = LoggerFactory.getLogger("ShipmentService");

    public void shipmentLocationUpdate(Shipment shipment)
            throws InterruptedException {
        LOG.info("Shipment data: {}", shipment.getShipmentId());

        Thread.sleep(3000);

        LOG.info("Shipment with ID: {} reached at javacodegeeks!!!",
                shipment.getShipmentId());
    }
}
