package com.bl.demo;

public class InvoiceService {
    private double costPerKiloMeter;
    private int costPerMinute;
    private int minimumCost;
    private RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time, String category) {
        if (category == "Normal") {
            costPerKiloMeter = 10.0;
            costPerMinute = 1;
            minimumCost = 5;
        }
        else {
            costPerKiloMeter = 15.0;
            costPerMinute = 2;
            minimumCost = 20;
        }
        double fare = distance * costPerKiloMeter + time * costPerMinute;
        return Math.max(fare, minimumCost);
    }

    public double giveFare(Ride[] rides, String category) {
        double totalFare = 0;
        for (Ride ride:rides) {
            totalFare += this.calculateFare(ride.distance,ride.time,category);
        }
        return totalFare;
    }

    public InvoiceSummary calculateFare(Ride[] rides,String category) {
        return new InvoiceSummary(rides.length,this.giveFare(rides,category));
    }

    public double calculateFares(Ride[] rides,String category) {
        return this.giveFare(rides,category);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId,String category) {
        return this.calculateFare(rideRepository.getRides(userId),category);
    }
}
