package com.step;

/*
    1.  Creați un proiect maven

    2.  Adăugați clasa Person
        Câmpuri: name, salary, gender (enum), birthdate (localdate)

    3.  Clasa Main
        Metoda main()
        Adăugați într-o listă 3 femei și 3 bărbați
        alegeți aleator datele, dar să fie diferite
        Scrieți câte o metodă care accepta lista ca parametru și afișează la ecran:
        1. 3 persoane care au salariul cel mai mare salariu din lista
        2. 2 femei care au birthdate după 01.01.1990
        3. Informatia despre bărbații din listă
        4. Doar salariile tuturor persoanelor ordonate crescător
        5. Doar salariile unice ale persoanelor
        Apelați fiecare metodă din main

    4.  Proiectul îl încărcați pe github (denumirea: WorkingWithStreamAPI)
 */

import com.step.model.person.Gender;
import com.step.model.person.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = Person.getDummyPeople();
        System.out.println("\npeople:");
        for (Person person : people) {
            System.out.println(person);
        }

//        List<Person> topPeopleBySalary = getTopNPeopleBySalary(people, 3);
//        System.out.println("\n3 persoane care au salariul cel mai mare salariu din lista:");
//        for (Person person : topPeopleBySalary) {
//            System.out.println(person);
//        }
//
//        List<Person> femalesByDate = getFirst2FemalesYoungerThanDate(people, LocalDate.of(1990, 1, 1));
//        System.out.println("\n2 femei care au birthdate după 01.01.1990:");
//        for (Person person : femalesByDate) {
//            System.out.println(person);
//        }
//
//        List<Person> males = getPeopleByGender(people, Gender.MALE);
//        System.out.println("\nInformatia despre bărbații din listă:");
//        for (Person person : males) {
//            System.out.println(person);
//        }
//
//        List<Person> peopleByASCSalary = getPeopleBySalaryASC(people);
//        System.out.println("\nDoar salariile tuturor persoanelor ordonate crescător:");
//        for (Person person : peopleByASCSalary) {
//            System.out.println(person);
//        }

        getFirst2FemalesYoungerThanDateStream(people, LocalDate.of(1990, 1, 1));

        getSalariesASCStream(people);
    }

    private static List<Person> getTopNPeopleBySalary(List<Person> people, int n) {
        List<Person> result = new ArrayList<>();

        people.sort((o1, o2) -> o2.getSalary().compareTo(o1.getSalary()));

        for (int i = 0; i < n; i++) {
            result.add(people.get(i));
        }

        return result;
    }

    private static List<Person> getFirst2FemalesYoungerThanDate(List<Person> people, LocalDate date) {
        List<Person> result = new ArrayList<>();

        int counter = 0;
        for (Person person : people) {
            if(person.getGender() == Gender.FEMALE && person.getBirthDate().isAfter(date)) {
                result.add(person);
                counter++;
                if(counter == 2) {
                    break;
                }
            }
        }

        return result;
    }

    private static void getFirst2FemalesYoungerThanDateStream(List<Person> people, LocalDate date) {
        people.stream()
                .filter(person -> person.getGender() == Gender.FEMALE)
                .filter(female -> female.getBirthDate().isAfter(date))
                .limit(2) // it wont search for all females than limit it to 2, it runs optimized in bg
                .forEach(femaleFiltered -> System.out.println(femaleFiltered));
    }


    private static List<Person> getPeopleByGender(List<Person> people, Gender gender) {
        List<Person> result = new ArrayList<>();

        for (Person person : people) {
            if(person.getGender() == gender) {
                result.add(person);

            }
        }

        return result;
    }

    private static List<Person> getPeopleBySalaryASC(List<Person> people) {
        people.sort((o1, o2) -> o1.getSalary().compareTo(o2.getSalary()));
        return people;
    }

    private static List<Double> getSalariesASCStream(List<Person> people) {
        List<Double> result = people.stream()
                // extragem doar salariile intro lista noua
                .map(person -> person.getSalary())
                .sorted()
                .collect(Collectors.toList());
        return result;
    }
}
