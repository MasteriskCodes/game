package rx.practice.design.yard.simulation.util;

import rx.practice.design.yard.Animal;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Simulation on a single Animal object
 */
public final class AnimalSimulations {
    private AnimalSimulations() {}
    public static void unfriendAndPrintEvent(Animal animal, Function<Set<Animal>,Animal> randomFriend) {
        Set<Animal> otherFriends = animal.getOtherFriends();
        if (!otherFriends.isEmpty()) {
            Animal friend = randomFriend.apply(otherFriends);
            animal.unfriend(friend);
            System.out.println(animal.getName() + " and " + friend.getName() + " are not friend anymore");
        }
    }
    public static void establishFriendshipAndPrintEvent(Animal animal, Set<Animal> animals, Function<Set<Animal>,Animal> randomFriend) {
        Set<Animal> mutableAnimals = new HashSet<>(animals);
        mutableAnimals.remove(animal);
        Optional<Animal> optBestFriend = animal.getBestFriendForEver();
        if (optBestFriend.isPresent())
            mutableAnimals.remove(optBestFriend.get());
        mutableAnimals.removeAll(animal.getOtherFriends());
        if (!mutableAnimals.isEmpty()) {
            Animal unacquaintedRandomAnimal = randomFriend.apply(mutableAnimals);
            StringBuilder stringBuilder = new StringBuilder(animal.getName())
                    .append(" is asking ")
                    .append(unacquaintedRandomAnimal.getName())
                    .append(" to be friend. ");
            if (animal.establishFriendship(unacquaintedRandomAnimal))
                stringBuilder.append(animal.getName())
                        .append(" and ")
                        .append(unacquaintedRandomAnimal.getName())
                        .append(" are friends now");
            else
                stringBuilder.append(unacquaintedRandomAnimal.getName())
                        .append(" doesn't want to be friends");
            System.out.println(stringBuilder.toString());
        }
    };
}
