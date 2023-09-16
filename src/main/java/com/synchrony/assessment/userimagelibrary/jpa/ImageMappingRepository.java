package com.synchrony.assessment.userimagelibrary.jpa;

import com.synchrony.assessment.userimagelibrary.entity.ImageMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageMappingRepository extends JpaRepository<ImageMappingEntity, String> {

}
