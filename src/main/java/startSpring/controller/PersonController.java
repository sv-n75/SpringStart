package startSpring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import startSpring.controller.dto.PersonResponseDTO;
import startSpring.entity.Person;
import startSpring.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController<persons> {

//    private final PersonRepository personRepository;
//
//    @Autowired
//    public PersonController(PersonRepository personRepository){
//        this.personRepository = personRepository;
//    }


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


//    List<Person>personList = new ArrayList<>(personRepository.saveAll(persons));


//    public Long createPerson(String name, Integer age) {
//        Person person = new Person(name, age);
//        return personRepository.save(person).getId();
//    }
//
//
//    @GetMapping("//{id}")
//    public Person getPerson(@PathVariable Long id) {
//        return null;
//    }


    @GetMapping("/name")
    public PersonResponseDTO getPersonByNameFromRequestParam(@RequestParam("name") String name) {

        for (Person person : persons) {

            personRepository.save(person);
            if (person.getName().equals(name))
                return new PersonResponseDTO(person.getId(), person.getName(), person.getAge());
        }
        return null;
    }

    @GetMapping("/name/{name}")
    public PersonResponseDTO getPersonByNameFromPathVariable(@PathVariable String name) {
        for (Person person : persons) {
            personRepository.save(person);
            if (person.getName().equals(name)) {
                return new PersonResponseDTO(person.getId(), person.getName(), person.getAge());
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/description")
    public PersonResponseDTO getPersonByNameResponseBody(@RequestBody Person person){
        for (Person p : persons){
            personRepository.save(p);
            if (p.getName().equals(person.getName()))
                return new PersonResponseDTO(p.getId(), p.getName(), p.getAge());
        }
        return null;
    }

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return new Person(person.getName(), person.getAge());
    }

}
