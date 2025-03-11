package kg.mega.pg2_hm.services;

import kg.mega.pg2_hm.models.DTO;
import kg.mega.pg2_hm.models.Person;

import java.util.List;

public interface PersonService {
    Person createPerson(Person person);

    Person updatePerson(Long id, Person person);

    Person deletePerson(DTO id);

    Person getByPerson(Long id);

    List<Person> findAllPersonsContainsKeyword(String keyword);
}
