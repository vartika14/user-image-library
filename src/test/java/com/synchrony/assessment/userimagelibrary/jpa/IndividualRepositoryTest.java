package com.synchrony.assessment.userimagelibrary.jpa;

import com.synchrony.assessment.userimagelibrary.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class IndividualRepositoryTest {

    @Autowired
    private UserRepository individualRepository;

    @Test
    public void testUserTableCreation() {
        // Create a User object
        UserEntity user = new UserEntity();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("JDoe");
        user.setPassword("blinkit");
        // Save the User object to the database
        individualRepository.save(user);

        // Retrieve the User object from the database
        UserEntity retrievedUser = individualRepository.findById(user.getId()).orElse(null);

        // Check if the retrievedUser is not null (i.e., table was created)
        assertThat(retrievedUser).isNotNull();
    }
}
