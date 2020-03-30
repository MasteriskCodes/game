package rx.practice.design.yard.dog;

import rx.practice.design.yard.AbstractAnimal;
import rx.practice.design.yard.Animal;
import rx.practice.design.yard.simulation.util.ToStringBuilder;

import java.util.Objects;
import java.util.function.Predicate;

public final class Dog extends AbstractAnimal<Dog> {
    private final Breed breed;
    public Dog(String name, DogFood favoriteFood, Breed breed) {
        super(name, favoriteFood);
        this.breed = Objects.requireNonNull(breed);
    }
    public final Breed getBreed() {
        return breed;
    }

    @Override
    protected Dog self() {
        return this;
    }
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(super.toString())
                .append("Breed", breed.toString())
                .append(getFavoriteFoodDescription())
                .append(getBestFriendDescription());
        return builder.toString();
    }
}
