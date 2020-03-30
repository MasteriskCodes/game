package rx.practice.design.yard.dog;

public enum Breed {
    SHEPHERD("Shepherd"), HUSKY("Husky");
    private final String breedName;
    private Breed(String breed) {
        this.breedName = breed;
    }
    @Override
    public String toString() {
        return breedName;
    }

}
