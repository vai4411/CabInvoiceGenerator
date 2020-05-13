package com.bl.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
   InvoiceGenerator invoiceGenerator = null;

   @Before
   public void setUp() {
      invoiceGenerator = new InvoiceGenerator();
   }

   @Test
    public void givenDistanceAndTime_ShouldReturnFare() {
       double distance = 2.0;
       int time = 5;
       double fare = invoiceGenerator.calculateFare(distance,time);
       Assert.assertEquals(25,fare,0.0);
   }

   @Test
   public void givenMinimumDistanceAndTime_ShouldReturnFare() {
      double distance = 0.1;
      int time = 1;
      double fare = invoiceGenerator.calculateFare(distance,time);
      Assert.assertEquals(5,fare,0.0);
   }

   @Test
   public void givenMultipleRides_ShouldReturnTotalFare() {
      Ride[] rides = { new Ride(2.0, 5),
                       new Ride(0.1,1)
                     };
      double fare = invoiceGenerator.calculateFares(rides);
      Assert.assertEquals(30,fare,0.0);
   }

   @Test
   public void givenMultipleRides_ShouldReturnInvoiceSummary() {
      Ride[] rides = { new Ride(2.0, 5),
              new Ride(0.1,1)
      };
      InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
      InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30.0);
      Assert.assertEquals(invoiceSummary,summary);
   }
}
