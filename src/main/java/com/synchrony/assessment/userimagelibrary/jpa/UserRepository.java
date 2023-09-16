package com.synchrony.assessment.userimagelibrary.jpa;

import com.synchrony.assessment.userimagelibrary.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName);
}
