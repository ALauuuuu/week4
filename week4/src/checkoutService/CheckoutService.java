package checkoutService;

import paymentGateway.PaymentGateway;

public class CheckoutService {
    private final PaymentGateway gateway;
    public CheckoutService(PaymentGateway gateway) { this.gateway = gateway; }

    public String checkout(String userId, double amount) {
        if (amount > 1000) {
            return "REJECTED_OVER_LIMIT";
        }
        if (gateway.pay(userId, amount)) {
            return "PAID";
        } else {
            return "TEMPORARY_FAILURE";
        }
    }
    //?
}