package dev.mehdizebhi.springbootdataredisdemo;

import dev.mehdizebhi.springbootdataredisdemo.model.Address;
import dev.mehdizebhi.springbootdataredisdemo.model.Person;
import dev.mehdizebhi.springbootdataredisdemo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner loadTestData(
            PersonRepository personRepository
    ) {
        return args -> {

            personRepository.deleteAll();

            List<Address> addresses = List.of(
                    Address.of("248", "Seven Mile Beach Rd", "Broken Head", "NSW", "2481", "Australia"),
                    Address.of("11", "Commerce Dr", "Riverhead", "NY", "11901", "US"),
                    Address.of("605", "48th St", "New York", "NY", "10019", "US"),
                    Address.of("20", "W 34th St", "New York", "NY", "10001", "US"),
                    Address.of("107", "S Beverly Glen Blvd", "Los Angeles", "CA", "90024", "US"),
                    Address.of("11461", "Sunset Blvd", "Los Angeles", "CA", "90049", "US")
            );


            List<Person> people = List.of(
                    Person.of("Chris",
                            "Hemsworth",
                            38,
                            "The Rabbit Is Correct, And Clearly The Smartest One Among You.",
                            new Point(153.616667, -28.716667),
                            addresses.get(0),
                            Set.of("hammer", "biceps", "hair", "heart")),
                    Person.of("Robert",
                            "Downey",
                            56,
                            "Hey, fellas. Either one of you know where the Smithsonian is? I'm here to pick up a fossil.",
                            new Point(40.9190747, -72.5371874),
                            addresses.get(1),
                            Set.of("tech", "money", "one-liners", "intelligence", "resources")),
                    Person.of("Scarlett",
                            "Johansson",
                            37,
                            "Hey, fellas. Either one of you know where the Smithsonian is? I'm here to pick up a fossil.",
                            new Point(40.7215259, -74.0129994),
                            addresses.get(2),
                            Set.of("deception", "martial_arts")),
                    Person.of("Elizabeth",
                            "Olsen",
                            32,
                            "You Guys Know I Can Move Things With My Mind, Right?",
                            new Point(40.6976701, -74.2598641),
                            addresses.get(3),
                            Set.of("magic", "loyalty")),
                    Person.of("Zoe",
                            "Saldana",
                            43,
                            "I Am Going To Die Surrounded By The Biggest Idiots In The Galaxy.",
                            new Point(-118.399968, 34.073087),
                            addresses.get(4),
                            Set.of("skills", "martial_arts")),
                    Person.of("Samuel L.",
                            "Jackson",
                            73,
                            "Sir, I'm Gonna Have To Ask You To Exit The Donut",
                            new Point(-118.4345534, 34.082615),
                            addresses.get(4),
                            Set.of("planning", "deception", "resources"))
            );

            people = personRepository.saveAll(people);
            people.forEach(p -> log.info("🦸 person: {}", p));
        };
    }
}
