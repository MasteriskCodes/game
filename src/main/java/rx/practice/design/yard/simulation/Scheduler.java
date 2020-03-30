package rx.practice.design.yard.simulation;

import java.util.Optional;
import java.util.Queue;
public interface Scheduler {
    Queue<GroupAnimalsSimulation> getTaskQueue();
    default Optional<GroupAnimalsSimulation> schedule() {
        return Optional.ofNullable(getTaskQueue().poll());
    }
}
