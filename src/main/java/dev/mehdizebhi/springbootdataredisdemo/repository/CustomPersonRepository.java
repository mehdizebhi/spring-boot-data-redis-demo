package dev.mehdizebhi.springbootdataredisdemo.repository;

import com.redis.om.spring.search.stream.EntityStream;
import dev.mehdizebhi.springbootdataredisdemo.model.Person;

import dev.mehdizebhi.springbootdataredisdemo.model.Person$;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.search.aggr.SortedField;

import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class CustomPersonRepository {

    private final EntityStream entityStream;

    // Find people by age range
    public Iterable<Person> findByAgeBetween(int minAge, int maxAge) {
        return entityStream //
                .of(Person.class) //
                .filter(Person$.AGE.between(minAge, maxAge)) //
                .sorted(Person$.AGE, SortedField.SortOrder.ASC) //
                .collect(Collectors.toList());
    }

    // Find people by their first and last name
    public Iterable<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return entityStream //
                .of(Person.class) //
                .filter(Person$.FIRST_NAME.eq(firstName)) //
                .filter(Person$.LAST_NAME.eq(lastName)) //
                .collect(Collectors.toList());
    }

    public Iterable<Person> findByHomeLoc(Point point, Distance distance) {
        return entityStream //
                .of(Person.class) //
                .filter(Person$.HOME_LOC.near(point, distance)) //
                .collect(Collectors.toList());
    }

    // Performs full text search on a person's personal Statement
    public Iterable<Person> searchByPersonalStatement(String text) {
        return entityStream //
                .of(Person.class) //
                .filter(Person$.PERSONAL_STATEMENT.eq(text)) //
                .collect(Collectors.toList());
    }
}
