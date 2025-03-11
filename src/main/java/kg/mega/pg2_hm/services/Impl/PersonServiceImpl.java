package kg.mega.pg2_hm.services.Impl;

import jakarta.transaction.Transactional;
import kg.mega.pg2_hm.models.DTO;
import kg.mega.pg2_hm.models.Person;
import kg.mega.pg2_hm.repositories.PersonRepo;
import kg.mega.pg2_hm.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepo personRepo;

    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }



    @Override
    public Person createPerson(Person person) {
        if ((person.getName() == null) || (person.getAge() <= 0)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person ID must be provided");
        }
        person.setId(null);

        return personRepo.save(person) ;
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        if(!personRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        person.setId(id);



        person = personRepo.save(person);


        return person;
    }

    @Override
    public Person deletePerson(DTO id) {
        Long personId = id.getId();
        if(!personRepo.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        Person person = getByPerson(personId);



        personRepo.deleteById(personId);
        return person;

    }

    @Override
    public Person getByPerson(Long id) {





        return personRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));

    }



    @Override
    public List<Person> findAllPersonsContainsKeyword(String keyword) {
        List<Person> person = personRepo.findAll();
        for (int i = 0; i < person.size(); i++) {
            if (person.get(i).getName().contains(keyword)) {
                person.remove(i);
            }
        }
        return person;
    }
}
