package rx.practice.design.yard;

import java.util.Optional;
import java.util.Set;

public interface Animal {
    String getName();
    Food getFavoriteFood();
    Optional<Animal> getBestFriendForEver();
    boolean isFriend(Animal that);

    /**
     * unfriend that animal
     *
     * @param that that animal
     * @return unfriend operation is successful or not
     */
    boolean unfriend(Animal that);
    Set<Animal> getOtherFriends();

    /**
     * establish friendship with that friend
     *
     * @param that the friend this animal will try to establish friendship with
     * @return friendship is established or not
     */
    boolean establishFriendship(Animal that);
    String getDescriptionHeader();
}
