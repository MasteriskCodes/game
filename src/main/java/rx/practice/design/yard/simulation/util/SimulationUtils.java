package rx.practice.design.yard.simulation.util;

import rx.practice.design.yard.AbstractAnimal;
import rx.practice.design.yard.Animal;
import rx.practice.design.yard.AnimalProxy;
import rx.practice.design.yard.bird.BirdFood;
import rx.practice.design.yard.bird.Chicken;
import rx.practice.design.yard.bird.Parrot;
import rx.practice.design.yard.bird.Rooster;
import rx.practice.design.yard.cat.Cat;
import rx.practice.design.yard.cat.CatFood;
import rx.practice.design.yard.dog.Breed;
import rx.practice.design.yard.dog.Dog;
import rx.practice.design.yard.dog.DogFood;
import rx.practice.design.yard.simulation.util.table.Row;
import rx.practice.design.yard.simulation.util.table.RowImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public final class SimulationUtils {
    private SimulationUtils() {}
    public static List<Animal> initializeAnimals() {
        List<Animal> animals = new ArrayList<>();
        //initialize dog Rex and dog Tom
        AbstractAnimal dogRex = new Dog("Rex", DogFood.ROYAL_CANIN, Breed.SHEPHERD);
        AbstractAnimal dogTom = new Dog("Tom", DogFood.ROYAL_CANIN, Breed.HUSKY);
        AnimalProxy rexProxy = new AnimalProxy(dogRex);
        rexProxy.setBestFriendForEver(dogTom);
        animals.add(dogRex);
        //initialize dog Max and dog Jay
        AbstractAnimal dogMax = new Dog("Max", DogFood.PURINA_ONE, Breed.SHEPHERD);
        AbstractAnimal dogJay = new Dog("Jay", DogFood.PURINA_ONE, Breed.HUSKY);
        AnimalProxy maxProxy = new AnimalProxy(dogMax);
        maxProxy.setBestFriendForEver(dogJay);
        animals.add(dogMax);
        animals.add(dogTom);
        animals.add(dogJay);
        //initialze cat Zoe and Ada
        AbstractAnimal catZoe = new Cat("Zoe", CatFood.NINE_LIVES);
        AbstractAnimal catAda = new Cat("Ada", CatFood.PURINA_FRISKIES);
        AnimalProxy zoeProxy = new AnimalProxy(catZoe);
        zoeProxy.setBestFriendForEver(catAda);
        animals.add(catZoe);
        animals.add(catAda);
        //initialize chicken Meg and chicken Lis
        AbstractAnimal chickenMeg = new Chicken.Builder("Meg", BirdFood.PURINA_LAYENA)
                .setWingSpan(new BigDecimal(BigInteger.valueOf(4), 1))
                .setLayEggs()
                .build();
        AbstractAnimal chickenLis = new Chicken.Builder("Lis", BirdFood.MANNA_PRO)
                .setWingSpan(new BigDecimal(BigInteger.valueOf(35), 2))
                .setLayEggs()
                .build();
        AnimalProxy megProxy = new AnimalProxy(chickenMeg);
        megProxy.setBestFriendForEver(chickenLis);
        animals.add(chickenMeg);
        animals.add(chickenLis);
        //initialize chicken Emi and Lua
        AbstractAnimal chickenEmi = new Chicken.Builder("Emi", BirdFood.PURINA_LAYENA)
                .setWingSpan(new BigDecimal(BigInteger.valueOf(25), 2))
                .build();
        AbstractAnimal chickenLua = new Chicken.Builder("Lua", BirdFood.MANNA_PRO)
                .setWingSpan(new BigDecimal(BigInteger.valueOf(3), 1))
                .build();
        AnimalProxy emiProxy = new AnimalProxy(chickenEmi);
        emiProxy.setBestFriendForEver(chickenLua);
        animals.add(chickenEmi);
        animals.add(chickenLua);
        //initialize rooster Bob
        Animal roosterBob = new Rooster.Builder("Rooster", BirdFood.MANNA_PRO)
                .setWingSpan(new BigDecimal(BigInteger.valueOf(5), 1))
                .build();
        animals.add(roosterBob);
        //initialize parrot Mac and parrot Alf
        AbstractAnimal parrotMac = new Parrot.Builder("Mac", BirdFood.LAFEBER_ORIGINAL)
                .setWingSpan(new BigDecimal(BigInteger.valueOf(33), 2))
                .setCanSpeak()
                .build();
        AbstractAnimal parrotAlf = new Parrot.Builder("Alf", BirdFood.KAYTEE_FIESTA)
                .setWingSpan(new BigDecimal(BigInteger.valueOf(25), 2))
                .build();
        AnimalProxy macProxy = new AnimalProxy(parrotMac);
        macProxy.setBestFriendForEver(parrotAlf);
        animals.add(parrotMac);
        animals.add(parrotAlf);
        return animals;
    }
    public static final Predicate<Animal> DEFAULT_FRIENDSHIP_DECISION = decider -> {
        Set<Animal> currentFriends = decider.getOtherFriends();
        if (currentFriends.size() >= 3) {
            return Math.random() < 0.1;
        }
        return Math.random() < 0.9;
    };

    public static Function<Set<Animal>,Animal> randomAnimal = animalSet -> {
        if (animalSet == null || animalSet.size() == 0) {
            throw new IllegalArgumentException("the given set of animals cannot be null and cannot be empty");
        }
        List<Animal> mutableAnimals = new ArrayList<>(animalSet);
        Collections.shuffle(mutableAnimals, new Random());
        return mutableAnimals.get(0);
    };
}
