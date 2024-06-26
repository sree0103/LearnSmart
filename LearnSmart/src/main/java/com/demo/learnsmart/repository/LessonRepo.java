package com.demo.learnsmart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.learnsmart.entity.Course;
import com.demo.learnsmart.entity.Lesson;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Integer> {

	Optional<Lesson> findByName(String name);

	Optional<Lesson> findByNameAndCourseName(String lessonName, String courseName);
	
	List<Lesson> findByCourse(Course course);
	List<Lesson> findByCourseId(int id);

}
