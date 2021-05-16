package com.kikis.courier.service;

import com.kikis.courier.domain.Order;
import com.kikis.courier.domain.Parcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PricingServiceTest {
  @InjectMocks
  private PricingService pricingService;
  @Mock
  private DiscountService discountService;

  @Test
  public void shouldCalculatePriceForOrder() {
    Order order = buildOrder();
    Parcel parcel = order.getParcels().get(0);
    when(discountService.getApplicableDiscount(eq(parcel), eq(820.0))).thenReturn(2.4);
    pricingService.calculatePriceForOrder(order);

    assertEquals(Double.valueOf(817.6), parcel.getCostToDeliver());
    assertEquals(Double.valueOf(2.4), parcel.getTotalDiscount());
  }

  private Order buildOrder() {
    Order order = new Order();
    order.setBaseDeliveryCost(100.0);
    Parcel parcel = new Parcel("parcel-1", 22.0, 100.0, "coupon-code-1");
    List<Parcel> parcels = new ArrayList<>();
    parcels.add(parcel);
    order.setParcels(parcels);
    return order;
  }
}
