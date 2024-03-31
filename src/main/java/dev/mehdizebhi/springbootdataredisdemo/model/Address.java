package dev.mehdizebhi.springbootdataredisdemo.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor(staticName = "of")
@Document
public class Address {

    @Id @Indexed private String id;

    @NonNull @Indexed private String houseNumber;

    @NonNull @Searchable(nostem = true) private String street;

    @NonNull @Indexed private String city;

    @NonNull @Indexed private String state;

    @NonNull @Indexed private String postalCode;

    @NonNull @Indexed private String country;
}

