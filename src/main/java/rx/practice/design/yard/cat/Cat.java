package rx.practice.design.yard.cat;

import rx.practice.design.yard.AbstractAnimal;
import rx.practice.design.yard.Animal;
import rx.practice.design.yard.simulation.util.ToStringBuilder;

import java.util.function.Predicate;

public final class Cat extends AbstractAnimal<Cat> {
    public Cat(String name, CatFood favoriteFood) {
        super(name, favoriteFood);
    }

    @Override
    protected Cat self() {
        return this;
    }
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(super.toString())
                .append("Favorite food", getFavoriteFood().toString())
                .append("Best friend forever", getBestFriendForEver().get().getName());
        return builder.toString();
    }
}