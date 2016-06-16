package com.miinaroland.repository;

import com.miinaroland.model.SubjectAttributeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface SubjectAttributeTypeRepository extends JpaRepository<SubjectAttributeType, Long> {

    List<SubjectAttributeType> findBySubjectTypeFk(long subjectTypeFk);
}
