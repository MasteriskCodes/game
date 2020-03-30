package rx.practice.design;

import rx.practice.design.yard.Animal;
import rx.practice.design.yard.simulation.GroupAnimalsSimulation;
import rx.practice.design.yard.simulation.Scheduler;
import rx.practice.design.yard.simulation.SimulationExecutor;
import rx.practice.design.yard.simulation.util.AnimalSimulations;
import rx.practice.design.yard.simulation.util.SimulationUtils;
import rx.practice.design.yard.simulation.util.table.TableUtils;

import java.util.*;

/**
 * The village yard simulator
 *
 */
public class VillageYardSimulator {
    private final SimulationExecutor executor;
    private final List<GroupAnimalsSimulation> taskList;
    public VillageYardSimulator(SimulationExecutor executor, List<GroupAnimalsSimulation> taskList) {
        this.executor = executor;
        this.taskList = Collections.unmodifiableList(taskList);
    }
    public void simulateOneDay() {
        final Queue<GroupAnimalsSimulation> taskQueue = new LinkedList<>(taskList);
        Scheduler dailyScheduler = () -> taskQueue;
        for (Optional<GroupAnimalsSimulation> currentSimulation = dailyScheduler.schedule(); currentSimulation.isPresent(); currentSimulation = dailyScheduler.schedule()) {
            executor.run(currentSimulation.get());
            System.out.println();
        }
    }
    public void simulate(int days) {
        for (int i = 0; i < days; i++) {
            System.out.println("DAY-" + i + "*****************************************************************");
            simulateOneDay();
        }
    }
    public static void main( String[] args )
    {
        SimulationExecutor executor = new SimulationExecutor(SimulationUtils.initializeAnimals());
        System.out.println("START-UP :)");
        executor.run(animals -> animals.forEach(System.out::println));
        final List<GroupAnimalsSimulation> taskList = new ArrayList<>();
        taskList.add(animals -> animals.forEach(animal -> AnimalSimulations.unfriendAndPrintEvent(animal, friends -> friends.stream().findAny().get())));
        taskList.add(GroupAnimalsSimulation.PrintFavoriteFoodGroupedByBrand);
        taskList.add(animals -> animals.forEach(animal -> AnimalSimulations.establishFriendshipAndPrintEvent(animal, new HashSet<Animal>(animals), SimulationUtils.randomAnimal)));
        taskList.add(animals -> System.out.println(TableUtils.getFriendMappingTable(animals)));
        VillageYardSimulator villageYardSimulator = new VillageYardSimulator(executor, taskList);
        villageYardSimulator.simulate(20);
    }
}
