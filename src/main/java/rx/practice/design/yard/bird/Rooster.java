package rx.practice.design.yard.bird;


import rx.practice.design.yard.simulation.util.ToStringBuilder;

public final class Rooster extends Bird<Rooster> {
    private Rooster(Builder builder) {
        super(builder);
    }

    @Override
    protected Rooster self() {
        return this;
    }
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(super.toString())
                .append(getWingSpanDescription());
        return builder.toString();
    }

    public static class Builder extends BirdBuilder<Builder> {
        public Builder(String name, BirdFood favoriteFood) {
            super(name, favoriteFood);
        }
        public Builder self() {return this;}
        @Override
        public Rooster build() {
            return new Rooster(this);
        }
    }
}
