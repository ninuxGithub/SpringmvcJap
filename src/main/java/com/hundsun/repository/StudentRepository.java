package com.hundsun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hundsun.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	

}
