package testMembershipService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

import clockProvider.ClockProvider;
import membershipService.MembershipService;

// F

class FixedClockStub implements ClockProvider {
	private LocalDate today;
	public FixedClockStub(LocalDate today) {
		this.today = today;
	}

	@Override public LocalDate today() { 
        return today;
    }
}

public class MembershipServiceTest {
    @Test
    void activeBeforeExpire() {
        MembershipService svc =
        		new MembershipService(new FixedClockStub(LocalDate.of(2025, 9, 24)));
        assertTrue(svc.isActive(LocalDate.of(2025, 9, 25)));
    }

    @Test
    void activeOnExpireDay() {
        MembershipService svc =
            new MembershipService(new FixedClockStub(LocalDate.of(2025, 9, 25)));
        assertTrue(svc.isActive(LocalDate.of(2025, 9, 25)));
    }

    @Test
    void inactiveAfterExpire() {
        MembershipService svc =
            new MembershipService(new FixedClockStub(LocalDate.of(2025, 9, 26)));
        assertFalse(svc.isActive(LocalDate.of(2025, 9, 25)));
    }
}