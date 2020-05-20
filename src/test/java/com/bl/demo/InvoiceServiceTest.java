package com.bl.demo;

import com.bl.demo.enums.RideType;
import com.bl.demo.model.Ride;
import com.bl.demo.services.InvoiceService;
import com.bl.demo.services.InvoiceSummary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
   InvoiceService invoiceService = null;

   @Before
   public void setUp() {
      invoiceService = new InvoiceService();
   }

   @Test
    public void givenDistanceAndTime_WhenNormalRide_ThenShouldReturnFare() {
       double distance = 2.0;
       int time = 5;
       double fare = invoiceService.calculateFare(new Ride(distance,time), RideType.Normal);
       Assert.assertEquals(25,fare,0.0);
   }

   @Test
   public void givenMinimumDistanceAndTime_WhenNormalRide_ThenShouldReturnFare() {
      double distance = 0.1;
      int time = 1;
      double fare = invoiceService.calculateFare(new Ride(distance,time), RideType.Normal);
      Assert.assertEquals(5,fare,0.0);
   }

   @Test
   public void givenMultipleRides_WhenNormalRide_ThenShouldReturnTotalFare() {
      Ride[] rides = { new Ride(2.0, 5),
                       new Ride(0.1,1)
                     };
      double fare = invoiceService.calculateFares(rides, RideType.Normal);
      Assert.assertEquals(30,fare,0.0);
   }

   @Test
   public void givenMultipleRides_WhenNormalRide_ThenShouldReturnInvoiceSummary() {
      Ride[] rides = { new Ride(2.0, 5),
              new Ride(0.1,1)
      };
      InvoiceSummary summary = invoiceService.calculateFare(rides, RideType.Normal);
      InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
      Assert.assertEquals(expectedInvoiceSummary,summary);
   }

   @Test
   public void givenUserIdAndRides_WhenNormalRide_ThenShouldReturnInvoiceSummary() {
      String userId = "a@b.com";
      Ride[] rides = { new Ride(2.0, 5),
              new Ride(0.1,1)
      };
      invoiceService.addRides(userId,rides);
      InvoiceSummary summary = invoiceService.getInvoiceSummary(userId, RideType.Normal);
      InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
      Assert.assertEquals(expectedInvoiceSummary,summary);
   }

   @Test
   public void givenDistanceAndTime_WhenForPremiumRide_ThenShouldReturnFare() {
      double distance = 2.0;
      int time = 5;
      double fare = invoiceService.calculateFare(new Ride(distance,time), RideType.Premium);
      Assert.assertEquals(40,fare,0.0);
   }

   @Test
   public void givenMinimumDistanceAndTime_WhenForPremiumRide_ThenShouldReturnFare() {
      double distance = 0.1;
      int time = 1;
      double fare = invoiceService.calculateFare(new Ride(distance,time), RideType.Premium);
      Assert.assertEquals(20,fare,0.0);
   }

   @Test
   public void givenMultipleRides_WhenForPremiumRide_ThenShouldReturnTotalFare() {
      Ride[] rides = { new Ride(2.0, 5),
              new Ride(0.1,1)
      };
      double fare = invoiceService.calculateFares(rides, RideType.Premium);
      Assert.assertEquals(60,fare,0.0);
   }

   @Test
   public void givenMultipleRides_WhenForPremiumRide_ThenShouldReturnInvoiceSummary() {
      Ride[] rides = { new Ride(2.0, 5),
              new Ride(0.1,1)
      };
      InvoiceSummary summary = invoiceService.calculateFare(rides, RideType.Premium);
      InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
      Assert.assertEquals(expectedInvoiceSummary,summary);
   }

   @Test
   public void givenUserIdAndRides_WhenForPremiumRide_ThenShouldReturnInvoiceSummary() {
      String userId = "a@b.com";
      Ride[] rides = { new Ride(2.0, 5),
              new Ride(0.1,1)
      };
      invoiceService.addRides(userId,rides);
      InvoiceSummary summary = invoiceService.getInvoiceSummary(userId, RideType.Premium);
      InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
      Assert.assertEquals(expectedInvoiceSummary,summary);
   }
}
