package dev.mehdizebhi.springbootdataredisdemo.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.mehdizebhi.springbootdataredisdemo.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends RedisDocumentRepository<Person, String> {

}
