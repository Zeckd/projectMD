package kg.mega.pg2_hm.controllers;

import kg.mega.pg2_hm.models.DTO;
import kg.mega.pg2_hm.models.Person;
import kg.mega.pg2_hm.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(@RequestBody PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }
    @DeleteMapping("/delete")
    public Person deletePerson(@RequestBody DTO id) {
        return personService.deletePerson(id) ;
    }
    @GetMapping("/{id}")
    public Person getByPerson(@PathVariable Long id) {
        return personService.getByPerson(id);
    }
    @GetMapping
    public List<Person> findAllPersonsContainsKeyword(@RequestParam String keyword) {
        return personService.findAllPersonsContainsKeyword(keyword);
    }
}
