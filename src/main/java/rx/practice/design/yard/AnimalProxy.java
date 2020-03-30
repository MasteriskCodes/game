package rx.practice.design.yard;

import java.util.function.Predicate;

public class AnimalProxy extends AbstractAnimal<AnimalProxy> {
    private AbstractAnimal animal;
    public AnimalProxy(AbstractAnimal animal) {
        super(animal.getName(), animal.getFavoriteFood());
        if (!(animal instanceof AbstractAnimal))
            throw new IllegalArgumentException("Currently only support proxy of AbstractAnimal");
        this.animal = (AbstractAnimal)animal;
    }
    @Override
    public void setBestFriendForEver(AbstractAnimal bestFriend) {
        animal.setBestFriendForEver(bestFriend);
    }
    @Override
    public void setFriendshipDecision(Predicate<Animal> friendshipDecision) {
        animal.setFriendshipDecision(friendshipDecision);
    }

    @Override
    protected AnimalProxy self() {
        return this;
    }
}
