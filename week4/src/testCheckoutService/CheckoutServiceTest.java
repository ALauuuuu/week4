package testCheckoutService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import checkoutService.CheckoutService;
import paymentGateway.PaymentGateway;

public class CheckoutServiceTest {

	@Test
	void paysSuccessfully() {
	    class PaymentGatewayStub extends PaymentGateway {
	        @Override
	        public boolean pay(String userId, double amount) {
	            return true; // success
	        }
	    }
	
	    CheckoutService svc = new CheckoutService(new PaymentGatewayStub());
	    assertEquals("PAID", svc.checkout("u1", 99));
	}
	
	@Test
	void paymentFails() {
	    class PaymentGatewayStub extends PaymentGateway {
	        @Override
	        public boolean pay(String userId, double amount) {
	            return false; // failure
	        }
	    }
	
	    CheckoutService svc = new CheckoutService(new PaymentGatewayStub());
	    assertEquals("TEMPORARY_FAILURE", svc.checkout("u1", 99));
	}

    @Test
    void rejectWhenOverLimit() {
        CheckoutService svc = new CheckoutService(new PaymentGateway());
        assertEquals("REJECTED_OVER_LIMIT", svc.checkout("u1", 1000.01));
    }
}