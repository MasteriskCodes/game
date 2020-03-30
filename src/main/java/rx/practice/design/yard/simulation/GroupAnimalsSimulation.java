package rx.practice.design.yard.simulation;

import rx.practice.design.yard.Animal;
import rx.practice.design.yard.Food;
import rx.practice.design.yard.simulation.util.AnimalSimulations;
import rx.practice.design.yard.simulation.util.table.TableUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@FunctionalInterface
public interface GroupAnimalsSimulation extends Consumer<List<Animal>> {
   static GroupAnimalsSimulation PrintFavoriteFoodGroupedByBrand = animals -> {
        Map<Food, List<Animal>> foodAnimalMap = animals.stream()
                .collect(Collectors.groupingBy(Animal::getFavoriteFood));
        foodAnimalMap.forEach((food, animalSet) -> {
            String animalListString = animalSet.stream().map(Animal::getName)
                    .collect(Collectors.joining(" and "));
            System.out.println(animalListString + (animalListString.contains("and") ? " are " : " is ") + " eating " + food);
        });
    };
}
