package com.bl.demo;

public class InvoiceService {
    private double costPerKiloMeter;
    private int costPerMinute;
    private int minimumCost;
    private RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time, boolean isPremium) {
        if (isPremium) {
            costPerKiloMeter = 15.0;
            costPerMinute = 2;
            minimumCost = 20;
        }
        else {
            costPerKiloMeter = 10.0;
            costPerMinute = 1;
            minimumCost = 5;
        }
        double fare = distance * costPerKiloMeter + time * costPerMinute;
        return Math.max(fare, minimumCost);
    }

    public double giveFare(Ride[] rides, boolean isPremium) {
        double totalFare = 0;
        for (Ride ride:rides) {
            totalFare += this.calculateFare(ride.distance,ride.time,isPremium);
        }
        return totalFare;
    }

    public InvoiceSummary calculateFare(Ride[] rides,boolean isPremium) {
        return new InvoiceSummary(rides.length,this.giveFare(rides,isPremium));
    }

    public double calculateFares(Ride[] rides,boolean isPremium) {
        return this.giveFare(rides,isPremium);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId,boolean isPremium) {
        return this.calculateFare(rideRepository.getRides(userId),isPremium);
    }
}
