package paymentGateway;

import java.util.Set;

public class PaymentGateway {
    private static final Set<String> BLOCKED_USERS = Set.of("blocked_user", "fraudster");

    public boolean pay(String userId, double amount) {
        // Validate input
        if (userId == null || userId.isEmpty()) {
            return false;
        }
        if (amount <= 0 || amount > 5000) {
            return false;
        }
        // Block high-risk users
        if (BLOCKED_USERS.contains(userId)) {
            return false;
        }
        // Simulate payment success for demo (e.g., only even amounts succeed)
        return ((int) amount % 2 == 0);
    }
}