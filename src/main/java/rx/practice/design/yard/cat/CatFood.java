package rx.practice.design.yard.cat;

import rx.practice.design.yard.Food;

public enum CatFood implements Food {
    NINE_LIVES("9lives"),PURINA_FRISKIES("Purina Friskies");
    private final String foodName;
    private CatFood(String foodName) {
        this.foodName = foodName;
    }
    @Override
    public String toString() {
        return foodName;
    }
}
