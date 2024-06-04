package com.riwi.spring_boot_drill.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.spring_boot_drill.domain.entities.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long>{
    
}
