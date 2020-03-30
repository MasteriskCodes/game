package rx.practice.design.yard.dog;

import rx.practice.design.yard.Food;

public enum DogFood implements Food {
    ROYAL_CANIN("Royal Canin"),PURINA_ONE("Purina ONE");
    private final String foodName;
    private DogFood(String foodName) {
        this.foodName = foodName;
    }
    @Override
    public String toString() {
        return foodName;
    }
}
