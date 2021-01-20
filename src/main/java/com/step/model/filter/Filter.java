package com.step.model.filter;

import com.step.model.person.Person;

import java.util.List;

public interface Filter {
    void max3Salaries(List<Person> personList);

    /**
     * 2 women with birthdate after 1990
     * @param personList
     */
    void filterWomen(List<Person> personList);

    void getMen(List<Person> personList);

    void getSalariesAsc(List<Person> personList);

    void uniqueSalaries(List<Person> personList);
}
