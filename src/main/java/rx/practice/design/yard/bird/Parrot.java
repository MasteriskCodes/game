package rx.practice.design.yard.bird;

import rx.practice.design.yard.AbstractAnimal;
import rx.practice.design.yard.simulation.util.ToStringBuilder;

import java.math.BigDecimal;

public final class Parrot extends Bird<Parrot> {
    private final boolean canSpeak;
    private Parrot(Builder builder) {
        super(builder);
        this.canSpeak = builder.canSpeak;
    }
    public boolean canSpeak() {
        return canSpeak;
    }

    @Override
    protected Parrot self() {
        return this;
    }
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(super.toString())
                .append(getWingSpanDescription())
                .append("Can speak", canSpeak() ? "yes" : "no")
                .append(getBestFriendDescription());
        return builder.toString();
    }

    public static class Builder extends BirdBuilder<Builder> {
        private boolean canSpeak;
        public Builder(String name, BirdFood favoriteFood) {
            super(name, favoriteFood);
        }
        public final Builder setCanSpeak() {
            canSpeak = true;
            return this;
        }
        public Builder self() {
            return this;
        }
        @Override
        public Parrot build() {
            return new Parrot(this);
        }
    }
}
