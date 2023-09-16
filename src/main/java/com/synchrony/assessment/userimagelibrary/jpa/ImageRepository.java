package com.synchrony.assessment.userimagelibrary.jpa;

import com.synchrony.assessment.userimagelibrary.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, String> {
}
