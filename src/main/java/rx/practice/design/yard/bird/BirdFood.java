package rx.practice.design.yard.bird;

import rx.practice.design.yard.Food;

public enum BirdFood implements Food {
    PURINA_LAYENA("Purina Layena"), MANNA_PRO("Manna Pro"),
    LAFEBER_ORIGINAL("Lafeber Original"), KAYTEE_FIESTA("Kaytee Fiesta");
    private final String foodName;
    private BirdFood(String foodName) {
        this.foodName = foodName;
    }
    @Override
    public String toString() {
        return foodName;
    }
}
