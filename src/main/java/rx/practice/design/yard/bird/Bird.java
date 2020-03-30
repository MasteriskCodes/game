package rx.practice.design.yard.bird;

import rx.practice.design.yard.AbstractAnimal;
import rx.practice.design.yard.simulation.util.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Bird<T extends Bird> extends AbstractAnimal {
    private final BigDecimal wingSpan;
    protected Bird(BirdBuilder builder) {
        super(builder.name, builder.favoriteFood);
        this.wingSpan = Objects.requireNonNull(builder.wingSpan);
        if (!(this.wingSpan.compareTo(BigDecimal.ZERO) > 0))
            throw new IllegalArgumentException("wing span is required and has to be positive");
    }
    public final BigDecimal getWingSpan() {
        return wingSpan;
    }
    protected final String getWingSpanDescription() {
        return "Wingspan: " + wingSpan + "m";
    }
    @Override
    public String toString() {
        return new ToStringBuilder(false, super.toString())
                .append(getFavoriteFoodDescription())
                .toString();
    }
    public static abstract class BirdBuilder<T extends BirdBuilder> {
        protected final String name;
        protected final BirdFood favoriteFood;
        protected BigDecimal wingSpan;
        protected BirdBuilder(String name, BirdFood favoriteFood) {
            this.name = Objects.requireNonNull(name);
            this.favoriteFood = Objects.requireNonNull(favoriteFood);
        }
        public final T setWingSpan(BigDecimal wingSpan) {
            this.wingSpan = Objects.requireNonNull(wingSpan);
            if (!(this.wingSpan.compareTo(BigDecimal.ZERO) > 0))
                throw new IllegalArgumentException("wing span is required and has to be positive");
            return self();
        }
        protected abstract T self();
        protected abstract Bird build();
    }
}
