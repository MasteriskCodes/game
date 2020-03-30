package rx.practice.design.yard;

import rx.practice.design.yard.simulation.util.SimulationUtils;
import rx.practice.design.yard.simulation.util.ToStringBuilder;

import java.util.*;
import java.util.function.Predicate;

/**
 * Skeletal implementation of Animal
 */
public abstract class AbstractAnimal<T extends AbstractAnimal> implements Animal {
    private final String name;
    private final Food favoriteFood;
    private Animal bestFriendForEver;
    private Set<Animal> otherFriends = new HashSet<>();
    private Predicate<Animal> friendshipDecision;
    protected AbstractAnimal(String name, Food favoriteFood) {
        this.name = Objects.requireNonNull(name);
        this.favoriteFood = Objects.requireNonNull(favoriteFood);
        this.friendshipDecision = SimulationUtils.DEFAULT_FRIENDSHIP_DECISION;
    }
    @Override
    public final String getName() {
        return name;
    }
    @Override
    public final Food getFavoriteFood() {
        return favoriteFood;
    }

    @Override
    public final Optional<Animal> getBestFriendForEver() {
        return Optional.ofNullable(bestFriendForEver);
    }
    @Override
    public final Set<Animal> getOtherFriends() {
        return Collections.unmodifiableSet(otherFriends);
    }
    protected void removeFriend(Animal that) {
        otherFriends.remove(that);
    }
    @Override
    public final boolean unfriend(Animal that) {
        if (!(that instanceof AbstractAnimal)) throw new IllegalArgumentException("As this method is implemented in AbstractAnimal, that animal you intend to establish friendship with must be an AbstractAniaml as well");
        if (!otherFriends.contains(that)) {
            throw new IllegalArgumentException("That friend you intend to unfriend with is either your best friend, or not your friend at all");
        }
        ((AbstractAnimal)that).removeFriend(this);
        return otherFriends.remove(that);
    }
    private boolean isBestFriend(Animal that) {
        if (that == this) throw new IllegalArgumentException("one cannot be his own friend");
        if (!that.getBestFriendForEver().isPresent()) return false;
        return this == that.getBestFriendForEver().get();
    }
    @Override
    public boolean establishFriendship(Animal that) {
        if (!(that instanceof AbstractAnimal)) throw new IllegalArgumentException("As this method is implemented in AbstractAnimal, that animal you intend to establish friendship with must be an AbstractAniaml as well");
        if (((AbstractAnimal)that).acceptFriendship(this))
            return otherFriends.add(that);
        else return false;
    }
    protected boolean acceptFriendship(AbstractAnimal that) {
        if (friendshipDecision.test(this)) {
            otherFriends.add(that);
            return true;
        } else return false;
    }
    @Override
    public final boolean isFriend(Animal that) {
        return isBestFriend(that) || otherFriends.contains(that);
    }
    protected void setBestFriendForEver(AbstractAnimal bestFriend) {
        if (this.equals(bestFriend)) throw new IllegalArgumentException("Impossible to set self as best friend");
        this.bestFriendForEver = bestFriend;
        if ((!bestFriend.getBestFriendForEver().isPresent()) || bestFriend.getBestFriendForEver().get() != this)
            bestFriend.setBestFriendForEver(this);
    }
    protected void setFriendshipDecision(Predicate<Animal> friendshipDecision) {
        this.friendshipDecision = friendshipDecision;
    }
    protected abstract T self();
    @Override
    public String getDescriptionHeader() {
        return self().getClass().getSimpleName() + " " + name;
    }
    protected final String getFavoriteFoodDescription() {
        return "Favorite food: " + favoriteFood;
    }
    protected final String getBestFriendDescription() {
        return "Best friend for ever: " + bestFriendForEver.getName();
    }
    @Override
    public String toString() {
        return getDescriptionHeader();
    }
}
