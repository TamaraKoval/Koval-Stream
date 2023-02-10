import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Person> personList = getTamaraburg();

        // Поиск несовершеннолетних
        long numberOfMinors = personList.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних в Тамарабурге: " + numberOfMinors);
        // personList.stream().filter(person -> person.getAge() < 18).forEach(System.out::println); строка для проверки на малой выборке

        // Список призывников
        List<String> surnamesOfConscripts = personList.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        // Список потенциально работоспособных высококварифицированных кадров
        List<Person> workableAndEducated = personList.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> (person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65) ||
                        (person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        // workableAndEducated.stream().forEach(System.out::println); строка для проверки на малой выборке

    }

    private static List<Person> getTamaraburg() {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<Person> persons = new ArrayList<>();
        for (long i = 0; i < 1000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        return persons;
    }
}
