package rx.practice.design.yard.bird;

import rx.practice.design.yard.AbstractAnimal;
import rx.practice.design.yard.simulation.util.ToStringBuilder;

public final class Chicken extends Bird<Chicken> {
    private boolean layEggs;
    private Chicken(Builder builder) {
        super(builder);
        this.layEggs = builder.layEggs;
    }
    public final boolean canLayEggs(){
        return layEggs;
    }
    @Override
    protected Chicken self() {
        return this;
    }
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(super.toString())
                .append("Lay eggs", canLayEggs() ? "yes" : "no")
                .append(getWingSpanDescription())
                .append(getBestFriendDescription());
        return builder.toString();
    }

    public static class Builder extends BirdBuilder<Builder> {
        private boolean layEggs;
        public Builder(String name, BirdFood favoriteFood) {
            super(name, favoriteFood);
        }
        public Builder setLayEggs() {
            layEggs = true;
            return this;
        }
        @Override
        public Builder self() {return this;}
        @Override
        public Chicken build() {
            return new Chicken(this);
        }
    }
}
