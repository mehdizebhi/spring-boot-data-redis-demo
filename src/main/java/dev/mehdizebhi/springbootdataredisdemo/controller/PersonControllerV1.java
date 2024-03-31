package dev.mehdizebhi.springbootdataredisdemo.controller;

import dev.mehdizebhi.springbootdataredisdemo.model.Person;
import dev.mehdizebhi.springbootdataredisdemo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
public class PersonControllerV1 {

    private final PersonRepository repo;


    @GetMapping("age_between")
    Iterable<Person> byAgeBetween(
            @RequestParam("min") int min,
            @RequestParam("max") int max) {
        return repo.findByAgeBetween(min, max);
    }

    @GetMapping("homeloc")
    Iterable<Person> byHomeLoc(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("d") double distance) {
        return repo.findByHomeLocNear(new Point(lon, lat), new Distance(distance, Metrics.MILES));
    }

    @GetMapping("name")
    Iterable<Person> byFirstNameAndLastName(
            @RequestParam("first") String firstName,
            @RequestParam("last") String lastName) {
        return repo.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("statement")
    Iterable<Person> byPersonalStatement(@RequestParam("q") String q) {
        return repo.searchByPersonalStatement(q);
    }

    @PostMapping("new")
    Person create(@RequestBody Person newPerson) {
        return repo.save(newPerson);
    }

    @GetMapping("{id}")
    Optional<Person> byId(@PathVariable String id) {
        return repo.findById(id);
    }

    @PutMapping("{id}")
    Person update(@RequestBody Person newPerson, @PathVariable String id) {
        return repo.findById(id).map(person -> {
            person.setFirstName(newPerson.getFirstName());
            person.setLastName(newPerson.getLastName());
            person.setAge(newPerson.getAge());
            person.setAddress(newPerson.getAddress());
            person.setHomeLoc(newPerson.getHomeLoc());
            person.setPersonalStatement(newPerson.getPersonalStatement());

            return repo.save(person);
        }).orElseGet(() -> repo.save(newPerson));
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable String id) {
        repo.deleteById(id);
    }

    @GetMapping("city")
    Iterable<Person> byCity(@RequestParam("city") String city) {
        return repo.findByAddress_City(city);
    }

    @GetMapping("city_state")
    Iterable<Person> byCityAndState(
            @RequestParam("city") String city,
            @RequestParam("state") String state) {
        return repo.findByAddress_CityAndAddress_State(city, state);
    }

    @GetMapping("skills")
    Iterable<Person> byAnySkills(@RequestParam("skills") Set<String> skills) {
        return repo.findBySkills(skills);
    }

    @GetMapping("skills/all")
    Iterable<Person> byAllSkills(@RequestParam("skills") Set<String> skills) {
        return repo.findBySkillsContainingAll(skills);
    }

    @GetMapping("search/{q}")
    Iterable<Person> fullTextSearch(@PathVariable("q") String q) {
        return repo.search(q);
    }
}
