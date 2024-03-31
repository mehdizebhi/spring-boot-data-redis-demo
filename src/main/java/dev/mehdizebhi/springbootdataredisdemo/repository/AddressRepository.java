package dev.mehdizebhi.springbootdataredisdemo.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.mehdizebhi.springbootdataredisdemo.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends RedisDocumentRepository<Address, String> {
}
