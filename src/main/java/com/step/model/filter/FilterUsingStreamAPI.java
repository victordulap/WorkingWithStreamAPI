package com.step.model.filter;

import com.step.model.person.Gender;
import com.step.model.person.Person;
import com.step.model.person.PersonNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FilterUsingStreamAPI implements Filter {
    @Override
    public void max3Salaries(List<Person> personList) {
        personList.stream()
                .sorted((o1, o2) -> o2.getSalary().compareTo(o1.getSalary()))
                .limit(3)
                .forEach(person -> System.out.println(person));
    }

    @Override
    public void filterWomen(List<Person> personList) {
        personList.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE) && person.getBirthDate().isAfter(LocalDate.of(1990, 1, 1)))
                .limit(2)
                .forEach(person -> System.out.println(person));
    }

    @Override
    public void getMen(List<Person> personList) {
        personList.stream()
                .filter(p -> p.getGender().equals(Gender.MALE))
                .forEach(p -> System.out.println(p));
    }

    @Override
    public void getSalariesAsc(List<Person> personList) {
        personList.stream()
                // map returns a new stream with new data type
                .map(person -> person.getSalary())
                .sorted()
                .forEach(salary -> System.out.println(salary));
    }

    @Override
    public void uniqueSalaries(List<Person> personList) {
        personList.stream()
                .map(person -> person.getSalary())
                .distinct()
                .forEach(salary -> System.out.println(salary));
    }

    public void findFirstDemo(List<Person> personList) {
//        if object found, returns Person, else optional is empty (not null)
//        Optional<Person> optionalPerson = personList.stream()
//                .filter(p -> p.getGender().equals(Gender.MALE) && p.getSalary() >1000)
//                .findFirst();

//        if(optionalPerson.isPresent()) {
//            System.out.println("optionalPerson: " + optionalPerson);
//            System.out.println("optionalPerson.get(): " + optionalPerson.get());
//        }

        // orElse
//        Person optionalPerson = personList.stream()
//                .filter(p -> p.getGender().equals(Gender.MALE) && p.getSalary() >1000)
//                .findFirst()
//                .orElse(new Person("implicit male", 200.0, Gender.MALE, LocalDate.now()));

        //  orElseThrow
        try {
            Person optionalPerson = personList.stream()
                    .filter(p -> p.getName().equals("1"))
                    .findFirst()
                    .orElseThrow(() -> new Exception("person not found exception"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void anyMatchDemo(List<Person> personList) {
        boolean allAreHumans = personList.stream()
                .anyMatch(person -> person.getGender().equals(Gender.OTHER));
        boolean allAreMales = personList.stream()
                .allMatch(person -> person.getGender().equals(Gender.MALE));
        boolean noneAreFemale = personList.stream()
                .noneMatch(person -> person.getGender().equals(Gender.FEMALE));
    }
}
