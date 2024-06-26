package com.demo.learnsmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.learnsmart.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer>{

	 Course findByName(String name);

	  Optional<Course> findById(Long id);
}
