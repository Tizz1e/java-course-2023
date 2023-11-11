package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalsTest {
    private static List<Animal> animals = List.of(
        new Animal("Jack", Animal.Type.BIRD, Animal.Sex.M, 2, -4, 4, false),
        new Animal("Jessy", Animal.Type.DOG, Animal.Sex.M, 3, 110, 14, true),
        new Animal("Ginger", Animal.Type.FISH, Animal.Sex.F, 0, 20, 3, false),
        new Animal("Dan", Animal.Type.FISH, Animal.Sex.M, 0, 10, 1, false),
        new Animal("Katty", Animal.Type.CAT, Animal.Sex.F, 8, 40, 12, true),
        new Animal("Black Bobby", Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 1, true),
        new Animal("Rob", Animal.Type.DOG, Animal.Sex.M, -1, 30, 8, false)
    );

    @DisplayName("Task1")
    @Test
    void task1() {
        List<String> result = AnimalHandler.sortByHeightAsc(animals).stream()
            .map(Animal::name).toList();
        List<String> correctAns = List.of("Jack", "Dan", "Black Bobby", "Ginger", "Rob", "Katty", "Jessy");
        assertEquals(correctAns, result);
    }

    @DisplayName("Task2")
    @Test
    void task2() {
        List<String> result = AnimalHandler.sortByWeightDesc(animals, 3).stream()
            .map(Animal::name).toList();
        List<String> correctAns = List.of("Jessy", "Katty", "Rob");
        assertEquals(correctAns, result);
    }

    @DisplayName("Task3")
    @Test
    void task3() {
        Map<Animal.Type, Long> result = AnimalHandler.animalStat(animals);
        Map<Animal.Type, Long> answer = Map.of(
            Animal.Type.DOG, 2L,
            Animal.Type.BIRD, 1L,
            Animal.Type.FISH, 2L,
            Animal.Type.CAT, 1L,
            Animal.Type.SPIDER, 1L
        );
        assertEquals(answer, result);
    }

    @DisplayName("Task4")
    @Test
    void task4() {
         Animal result = AnimalHandler.longestName(animals);
         Animal answer = new Animal("Black Bobby",
             Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 1, true);
         assertEquals(answer, result);
    }

    @DisplayName("Task5")
    @Test
    void task5() {
        Animal.Sex result = AnimalHandler.mostFrequentSex(animals);
        Animal.Sex answer = Animal.Sex.M;
        assertEquals(answer, result);
    }

    @DisplayName("Task6")
    @Test
    void task6() {
        Map<Animal.Type, Animal> result = AnimalHandler.heaviestAnimals(animals);
        Map<Animal.Type, Animal> answer = Map.of(
            Animal.Type.DOG,
            new Animal("Jessy", Animal.Type.DOG, Animal.Sex.M, 3, 110, 14, true),
            Animal.Type.CAT,
            new Animal("Katty", Animal.Type.CAT, Animal.Sex.F, 8, 40, 12, true),
            Animal.Type.FISH,
            new Animal("Ginger", Animal.Type.FISH, Animal.Sex.F, 0, 20, 3, false),
            Animal.Type.SPIDER,
            new Animal("Black Bobby", Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 1, true),
            Animal.Type.BIRD,
            new Animal("Jack", Animal.Type.BIRD, Animal.Sex.M, 2, -4, 4, false)
        );
        assertEquals(answer, result);
    }

    @DisplayName("Task7")
    @Test
    void task7() {
        Animal result = AnimalHandler.kOldestAnimal(animals, 3);
        Animal answer = new Animal("Jack", Animal.Type.BIRD,
            Animal.Sex.M, 2, -4, 4, false);
        assertEquals(answer, result);
    }

    @DisplayName("Task8")
    @Test
    void task8() {
        Optional<Animal> result = AnimalHandler.heaviestAnimalLowerK(animals, 25);
        Optional<Animal> answer = Optional.of(
            new Animal("Jack", Animal.Type.BIRD,
                Animal.Sex.M, 2, -4, 4, false)
        );
        assertEquals(answer, result);
    }

    @DisplayName("Task9")
    @Test
    void task9() {
        Integer result = AnimalHandler.summaryPaws(animals);
        Integer answer = 22;
        assertEquals(answer, result);
    }

    @DisplayName("Task10")
    @Test
    void task10() {
        List<Animal> result = AnimalHandler.ageNotEqualsPawsAnimals(animals);
        List<Animal> answer = List.of(
            new Animal("Jessy", Animal.Type.DOG, Animal.Sex.M, 3, 110, 14, true),
            new Animal("Katty", Animal.Type.CAT, Animal.Sex.F, 8, 40, 12, true),
            new Animal("Black Bobby", Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 1, true),
            new Animal("Rob", Animal.Type.DOG, Animal.Sex.M, -1, 30, 8, false)
        );
        assertEquals(answer, result);
    }

    @DisplayName("Task11")
    @Test
    void task11() {
        List<Animal> result = AnimalHandler.bitesAndHigh(animals);
        List<Animal> answer = List.of(
            new Animal("Jessy", Animal.Type.DOG, Animal.Sex.M, 3, 110, 14, true)
        );
        assertEquals(answer, result);
    }

    @DisplayName("Task12")
    @Test
    void task12() {
        Integer result = AnimalHandler.countWeightBiggerThanHeight(animals);
        Integer answer = 1;
        assertEquals(answer, result);
    }

    @DisplayName("Task13")
    @Test
    void task13() {
        List<Animal> result = AnimalHandler.compositeName(animals);
        List<Animal> answer = List.of(
            new Animal("Black Bobby", Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 1, true)
        );
        assertEquals(answer, result);
    }

    @DisplayName("Task14")
    @Test
    void task14() {
        Boolean result = AnimalHandler.dogHigherThanK(animals, 130);
        Boolean answer = false;
        assertEquals(answer, result);
    }

    @DisplayName("Task15")
    @Test
    void task15() {
        Map<Animal.Type, Integer> result = AnimalHandler.summarySpeciesWeightInAgeRange(animals, 2, 8);
        Map<Animal.Type, Integer> answer = Map.of(
            Animal.Type.DOG, 14,
            Animal.Type.CAT, 12,
            Animal.Type.BIRD, 4
        );
        assertEquals(answer, result);
    }

    @DisplayName("Task16")
    @Test
    void task16() {
        List<String> result = AnimalHandler.complexSort(animals).stream()
            .map(Animal::name)
            .toList();
        List<String> answer = List.of("Katty", "Jessy", "Rob", "Jack", "Dan", "Ginger", "Black Bobby");
        assertEquals(answer, result);
    }

    @DisplayName("Task17")
    @Test
    void task17() {
        Boolean result = AnimalHandler.spidersBitesMoreOftenThanDog(animals);
        Boolean answer = true;
        assertEquals(answer, result);
    }

    @DisplayName("Task18")
    @Test
    void task18() {
        List<Animal> otherAnimals = List.of(new Animal("Steve", Animal.Type.FISH,
            Animal.Sex.M, 4, 40, 8, true));
        Animal result = AnimalHandler.heaviestFish(animals, otherAnimals);
        Animal answer = new Animal("Steve", Animal.Type.FISH,
            Animal.Sex.M, 4, 40, 8, true);
        assertEquals(answer, result);
    }

    @DisplayName("Task19")
    @Test
    void task19() {
        Map<String, Set<String>> result = new HashMap<>();
        AnimalHandler.findError(animals).forEach(
            (key, value) -> result.put(
                key,
                value.stream().
                    map(ValidationError::toString)
                    .collect(Collectors.toSet())
            )
        );

        Map<String, Set<String>> answer = Map.of(
            "Jack", Set.of("ValidationError{Incorrect height}"),
            "Rob", Set.of("ValidationError{Incorrect age}")
        );

        assertEquals(answer, result);
    }

    @DisplayName("Task20")
    @Test
    void test20() {
        Map<String, String> result = AnimalHandler.findErrorFields(animals);
        Map<String, String> answer = Map.of(
            "Rob", "Age",
            "Jack", "Height"
        );

        assertEquals(answer, result);
    }
}
