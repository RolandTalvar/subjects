package com.miinaroland.repository;

import com.miinaroland.model.SubjectAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface SubjectAttributeRepository extends JpaRepository<SubjectAttribute, Long> {

    SubjectAttribute findBySubjectTypeFkAndSubjectFkAndSubjectAttributeTypeFk(long subjectTypeFk, long subjectFk, long attributeTypeFk);

    List<SubjectAttribute> findBySubjectFkAndSubjectTypeFkAndSubjectAttributeTypeFk(long subjectFk, long subjectTypeFk, long attributeTypeFk);
}
