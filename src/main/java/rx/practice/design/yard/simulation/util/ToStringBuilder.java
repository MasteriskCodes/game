package rx.practice.design.yard.simulation.util;

public final class ToStringBuilder {
    private StringBuilder stringBuilder;
    private boolean addPad;
    public ToStringBuilder(String header) {
        this(true, header);
    }
    public ToStringBuilder(boolean addPad, String header) {
        this.addPad = addPad;
        this.stringBuilder = new StringBuilder(header);
    }
    public ToStringBuilder append(String attributeLine) {
        stringBuilder.append(System.lineSeparator())
                .append(attributeLine);
        return this;
    }
    public ToStringBuilder append(String attributeName, String value) {
        stringBuilder.append(System.lineSeparator())
                .append(attributeName)
                .append(": ")
                .append(value);
        return this;
    }
    @Override
    public String toString() {
        return addPad ? stringBuilder.toString() + System.lineSeparator() : stringBuilder.toString();
    }
}
