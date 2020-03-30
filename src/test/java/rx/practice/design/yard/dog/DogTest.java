package rx.practice.design.yard.dog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rx.practice.design.yard.AnimalProxy;

import static org.junit.jupiter.api.Assertions.*;

public class DogTest {
    private static Dog tested;
    private static final String REX = "Rex";
    private static final String TOM = "Tom";
    @BeforeAll
    public static void initDog() {
        tested = new Dog(REX, DogFood.ROYAL_CANIN, Breed.SHEPHERD);
        AnimalProxy proxy = new AnimalProxy(tested);
        Dog bestFriend = new Dog(TOM, DogFood.ROYAL_CANIN, Breed.HUSKY);
        proxy.setBestFriendForEver(bestFriend);
    }
    @Test
    public void testGetters() {
        assertEquals(REX, tested.getName());
        assertEquals(DogFood.ROYAL_CANIN, tested.getFavoriteFood());
        assertEquals(Breed.SHEPHERD, tested.getBreed());
        assertEquals(0, tested.getOtherFriends().size());
    }
    @Test
    public void testToString() {
        StringBuilder expected = new StringBuilder();
        expected.append("Dog Rex")
                .append("\n")
                .append("Breed: Shepherd")
                .append("\n")
                .append("Favorite food: Royal Canin")
                .append("\n")
                .append("Best friend for ever: Tom");
        assertEquals(expected.toString(), tested.toString());
    }
}
