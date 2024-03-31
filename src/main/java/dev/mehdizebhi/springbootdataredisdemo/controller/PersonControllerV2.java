package dev.mehdizebhi.springbootdataredisdemo.controller;

import dev.mehdizebhi.springbootdataredisdemo.model.Person;
import dev.mehdizebhi.springbootdataredisdemo.repository.CustomPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/people")
@RequiredArgsConstructor
public class PersonControllerV2 {

    private final CustomPersonRepository repo;


    @GetMapping("age_between")
    Iterable<Person> byAgeBetween(
            @RequestParam("min") int min,
            @RequestParam("max") int max) {
        return repo.findByAgeBetween(min, max);
    }

    @GetMapping("name")
    Iterable<Person> byFirstNameAndLastName(
            @RequestParam("first") String firstName,
            @RequestParam("last") String lastName) {
        return repo.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("homeloc")
    Iterable<Person> byHomeLoc(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("d") double distance) {
        return repo.findByHomeLoc(new Point(lon, lat), new Distance(distance, Metrics.MILES));
    }

    @GetMapping("statement/{q}")
    Iterable<Person> byPersonalStatement(@PathVariable("q") String q) {
        return repo.searchByPersonalStatement(q);
    }
}
