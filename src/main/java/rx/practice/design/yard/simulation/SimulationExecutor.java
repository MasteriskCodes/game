package rx.practice.design.yard.simulation;

import rx.practice.design.yard.Animal;

import java.util.List;

public class SimulationExecutor {
    private List<Animal> animals;
    public SimulationExecutor(List<Animal> animals) {
        this.animals = animals;
    }
    public void run(GroupAnimalsSimulation simulator) {
        simulator.accept(animals);
    }
}
