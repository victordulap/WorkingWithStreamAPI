package com.step.model.filter;

import com.step.model.person.Gender;
import com.step.model.person.Person;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterRegular implements Filter {
    @Override
    public void max3Salaries(List<Person> personList) {
        // 1. sort list after salary desc
        //// list.sort(lambda)
        //// Collections.sort(list, comparator)
        ///// comparator solutions:
        ////// 1. class anonymous
        ////// 2. lambda
        ////// 3. simple class (new class that implement comparator)

        personList.sort((o1, o2) -> o2.getSalary().compareTo(o1.getSalary()));

        // 2. show first 3
        for (int i = 0; i < 3; i++) {
            System.out.println(personList.get(i));
        }
    }

    @Override
    public void filterWomen(List<Person> personList) {
        for (Person p : personList) {
            if (p.getGender().equals(Gender.FEMALE) || p.getBirthDate().isAfter(LocalDate.of(1990, 1, 1))) {
                System.out.println(p);
            }
        }
    }

    @Override
    public void getMen(List<Person> personList) {
        for (Person p : personList) {
            if (p.getGender().equals(Gender.MALE)) {
                System.out.println(p);
            }
        }
    }

    @Override
    public void getSalariesAsc(List<Person> personList) {
        // ori sortam toata lista persoanelor dupa salariu crescator
        // ori extragem salariiile persoanelor sii le sortam ulterior
    }

    @Override
    public void uniqueSalaries(List<Person> personList) {
        // ArrayList<Double> salaries
        // parcurgem personList si veficam daca salariu exist in salaries
        // daca nu - adaugam

        // solutie mai buna, folosim set care contine doar valori unice
        Set<Double> uniqueSalaries = new HashSet<>();
        for (Person p : personList) {
            uniqueSalaries.add(p.getSalary());
        }
    }
}
