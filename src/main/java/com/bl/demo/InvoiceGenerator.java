package com.bl.demo;

public class InvoiceGenerator {
    private static final double COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;
    private static final int MINIMUM_COST = 5;

    public double calculateFare(double distance, int time) {
        double fare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
        return Math.max(fare,MINIMUM_COST);
    }

    public double giveFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride:rides) {
            totalFare += this.calculateFare(ride.distance,ride.time);
        }
        return totalFare;
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        return new InvoiceSummary(rides.length,this.giveFare(rides));
    }

    public double calculateFares(Ride[] rides) {
        return this.giveFare(rides);
    }
}
