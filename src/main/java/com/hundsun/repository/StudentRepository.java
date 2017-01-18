package com.hundsun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hundsun.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("select s from Student s where s.name=:name")
	List<Student> findByUserName(@Param("name") String name);

}
