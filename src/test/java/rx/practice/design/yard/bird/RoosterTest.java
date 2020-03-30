package rx.practice.design.yard.bird;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoosterTest {
    private Rooster tested;
    private static final String BOB = "Bob";
    @BeforeEach
    public void initRooster() {
        tested = new Rooster.Builder(BOB, BirdFood.MANNA_PRO)
                .setWingSpan(new BigDecimal(BigInteger.valueOf(5), 1))
                .build();
    }
    @Test
    public void testToString() {
        String expectedRoosterString = "Rooster " + BOB
                + "\n" + "Favorite food: Manna Pro"
                + "\n" + "Wingspan: 0.5m"
                + "\n";
        assertEquals(expectedRoosterString, tested.toString());
    }
}
