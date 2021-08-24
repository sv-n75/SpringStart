package startSpring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import startSpring.controller.dto.PersonRequestDTO;
import startSpring.controller.dto.PersonResponseDTO;
import startSpring.entity.Person;
import startSpring.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private List<Person> persons = new ArrayList<>();

    {
        persons.add(new Person("john", 20));
        persons.add(new Person("joanna", 24));
        persons.add(new Person("bred", 50));
        persons.add(new Person("kate", 33));
    }

    @GetMapping("/name")
    public PersonResponseDTO getPersonByNameFromRequestParam(@RequestParam("name") String name) {

        for (Person person : persons) {
            personRepository.save(person);
        }
        for (Person person : persons) {
            if (person.getName().equals(name))
                return new PersonResponseDTO(person.getId(), person.getName(), person.getAge());
        }
        return null;
    }

    @GetMapping("/name/{name}")
    public PersonResponseDTO getPersonByNameFromPathVariable(@PathVariable String name) {
        for (Person p : persons) {
            personRepository.save(p);
        }

        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return new PersonResponseDTO(person.getId(), person.getName(), person.getAge());
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/description")
    public PersonResponseDTO getPersonByNameResponseBody(@RequestBody Person person) {
        for (Person p : persons) {
            personRepository.save(p);
            if (p.getName().equals(person.getName()))
                return new PersonResponseDTO(p.getId(), p.getName(), p.getAge());
        }
        return null;
    }

    @PostMapping("/persons")
    public Long createPerson(@RequestBody PersonRequestDTO personRequestDTO) {

        for (Person p : persons) {
            personRepository.save(p);
        }

        Person person = new Person(personRequestDTO.getName(), personRequestDTO.getAge());
        persons.add(person);

        return personRepository.save(person).getId();
    }

    @DeleteMapping("/persons/{name}")
    public Long deletePerson(@PathVariable String name) {

        for (Person p : persons) {
            personRepository.save(p);
        }
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                persons.remove(person);
                //personRepository.delete(person);
                return person.getId();
            }
        }
        return null;
    }

    @PatchMapping("/persons/{name}/{newName}/{newAge}")
    public Long updatePerson(@PathVariable String name, @PathVariable String newName, @PathVariable Integer newAge) {
        for (Person p : persons) {
            personRepository.save(p);
        }

        for (Person person : persons) {
            if (person.getName().equals(name)) {
                person.setName(newName);
                person.setAge(newAge);
                personRepository.save(person);
                return person.getId();
            }
        }
        return null;
    }
}



