package com.bl.demo.enums;

import com.bl.demo.model.Ride;

public enum RideType {
    Premium(15.0,2,20),
    Normal(10.0,1,5);

    public final double costPerKiloMeter;
    public final int costPerMinute;
    public final int minimumCost;

    RideType(double costPerKiloMeter, int costPerMinute, int minimumCost) {
        this.costPerKiloMeter = costPerKiloMeter;
        this.costPerMinute = costPerMinute;
        this.minimumCost = minimumCost;
    }

    public double calculateFare(Ride ride) {
        double cost = ride.distance * costPerKiloMeter + ride.time * costPerMinute;
        return Math.max(cost,minimumCost);
    }
}
