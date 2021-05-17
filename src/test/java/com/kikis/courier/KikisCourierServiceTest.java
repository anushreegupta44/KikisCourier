package com.kikis.courier;

import com.kikis.courier.model.Order;
import com.kikis.courier.service.DeliveryService;
import com.kikis.courier.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class KikisCourierServiceTest {

  @InjectMocks
  private KikisCourierService kikisCourierService;

  @Mock
  private PricingService pricingService;

  @Mock
  private DeliveryService deliveryService;

  @Test
  public void shouldCallPricingServiceWhenCalculatePriceForOrderIsCalled() {
    kikisCourierService.calculatePrice(mock(Order.class));

    verify(pricingService).calculatePriceForOrder(any(Order.class));
  }

  @Test
  public void shouldCallDeliveryServiceWhenCalculateEstimatedTimeForOrderIsCalled() {
    kikisCourierService.calculateEstimatedDeliveryTimeFor(mock(Order.class));

    verify(deliveryService).deliverOrder(any(Order.class));
  }

  @Test
  public void testGetInstance() {
    assertNotNull(KikisCourierService.getInstance());
  }
}
