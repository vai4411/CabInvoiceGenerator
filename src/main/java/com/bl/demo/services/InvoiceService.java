package com.bl.demo.services;

import com.bl.demo.RideRepository;
import com.bl.demo.enums.RideType;
import com.bl.demo.model.Ride;

public class InvoiceService {
    private RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(Ride ride, RideType type) {
            return type.calculateFare(ride);
    }

    public double giveFare(Ride[] rides, RideType type) {
        double totalFare = 0;
        for (Ride ride:rides) {
            totalFare += this.calculateFare(ride, type);
        }
        return totalFare;
    }

    public InvoiceSummary calculateFare(Ride[] rides, RideType type) {
        return new InvoiceSummary(rides.length,this.giveFare(rides,type));
    }

    public double calculateFares(Ride[] rides, RideType type) {
        return this.giveFare(rides, type);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId, RideType type) {
        return this.calculateFare(rideRepository.getRides(userId), type);
    }
}
