package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AnimalHandler {

    private AnimalHandler() {
    }

    public static List<Animal> sortByHeightAsc(List<Animal> animals) {
        return animals.stream()
            .sorted(
                (Animal first, Animal second) -> {
                    if (first == null && second == null) {
                        return 0;
                    } else if (first == null) {
                        return 1;
                    } else if (second == null) {
                        return -1;
                    } else {
                        return first.height() - second.height();
                    }
                }
            )
            .collect(Collectors.toList());
    }

    public static List<Animal> sortByWeightDesc(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(
                (Animal first, Animal second) -> {
                    if (first == null && second == null) {
                        return 0;
                    } else if (first == null) {
                        return 1;
                    } else if (second == null) {
                        return -1;
                    } else {
                        return second.weight() - first.weight();
                    }
                }
            )
            .limit(k)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Long> animalStat(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.counting()
                )
            );
    }

    public static Animal longestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt((t) -> t.name().length())).get();
    }

    public static Animal.Sex mostFrequentSex(List<Animal> animals) {
        Map<Animal.Sex, Long> stat = animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::sex,
                    Collectors.counting()
                )
            );

        if (stat.get(Animal.Sex.M) >= stat.get(Animal.Sex.F)) {
            return Animal.Sex.M;
        }
        return Animal.Sex.F;
    }

    public static Map<Animal.Type, Animal> heaviestAnimals(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.reducing(
                        new Animal(null, null, null, -1, -1, -1, false),
                        (first, second) -> first.weight() >= second.weight() ? first : second
                    )
                )
            );
    }

    public static Animal kOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst().get();
    }

    public static Optional<Animal> heaviestAnimalLowerK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer summaryPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> ageNotEqualsPawsAnimals(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> bitesAndHigh(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .toList();
    }

    public static Integer countWeightBiggerThanHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> compositeName(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length >= 2)
            .toList();
    }

    public static Boolean dogHigherThanK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> summarySpeciesWeightInAgeRange(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.summingInt(
                        Animal::weight
                    )
                )
            );
    }

    public static List<Animal> complexSort(List<Animal> animals) {
        return animals.stream()
            .sorted(
                (first, second) -> {
                    if (first == null && second == null) {
                        return 0;
                    } else if (first == null) {
                        return 1;
                    } else if (second == null) {
                        return -1;
                    }
                    return first.compareTo(second);
                }
            )
            .toList();
    }

    public static Boolean spidersBitesMoreOftenThanDog(List<Animal> animals) {
        long spiderBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();
        long spiderAmount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER)
            .count();
        long dogBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();
        long dogAmount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .count();
        if (dogAmount == 0 || spiderAmount == 0) {
            return false;
        }
        return spiderBites * dogAmount > dogBites * spiderAmount;
    }

    public static Animal heaviestFish(List<Animal>... animals) {
        return Arrays.stream(animals)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight)).get();
    }

    public static Map<String, Set<ValidationError>> findError(List<Animal> animals) {
        return animals.stream()
            .filter(Validator::check)
            .collect(
                Collectors.toMap(
                    Animal::name,
                    Validator::validate
                )
            );
    }

    public static Map<String, String> findErrorFields(List<Animal> animals) {
        return animals.stream()
            .filter(Validator::check)
            .collect(
                Collectors.toMap(
                    Animal::name,
                    Validator::incorrectFields
                )
            );
    }
}
