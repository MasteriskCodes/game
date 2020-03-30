package rx.practice.design.yard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rx.practice.design.yard.dog.Breed;
import rx.practice.design.yard.dog.Dog;
import rx.practice.design.yard.dog.DogFood;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalProxyTest {
    private Dog rex;
    private Dog tom;
    @BeforeEach
    public void init() {
        rex = new Dog("Rex", DogFood.ROYAL_CANIN, Breed.SHEPHERD);
        tom = new Dog("Tom", DogFood.ROYAL_CANIN, Breed.HUSKY);
    }
    @Test
    public void testSetGetBestFriend() {
        AnimalProxy rexProxy = new AnimalProxy(rex);
        rexProxy.setBestFriendForEver(tom);
        assertEquals(rex.getBestFriendForEver().get(), tom);
        assertEquals(tom.getBestFriendForEver().get(), rex);
    }

    @Test
    public void testSetFriendshipDecision_alwaysAccept() {
        Dog max = new Dog("Max", DogFood.PURINA_ONE, Breed.SHEPHERD);
        AnimalProxy maxProxy = new AnimalProxy(max);
        maxProxy.setFriendshipDecision(animal -> true);
        assertTrue(rex.establishFriendship(max));
        assertTrue(rex.isFriend(max));
        assertTrue(max.isFriend(rex));
    }
    @Test
    public void testSetFriendshipDecision_alwaysRefuse() {
        AnimalProxy rexProxy = new AnimalProxy(rex);
        rexProxy.setFriendshipDecision(animal -> true);
        Dog refuse = new Dog("Refuse", DogFood.PURINA_ONE, Breed.SHEPHERD);
        AnimalProxy refuseProxy = new AnimalProxy(refuse);
        refuseProxy.setFriendshipDecision(animal -> false);
        assertFalse(rex.establishFriendship(refuse));
        assertFalse(rex.isFriend(refuse));
        assertFalse(refuse.isFriend(rex));
    }
}
