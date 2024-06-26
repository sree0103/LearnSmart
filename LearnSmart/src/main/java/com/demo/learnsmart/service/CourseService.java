package com.demo.learnsmart.service;

import java.util.List;

import com.demo.learnsmart.entity.Course;

public interface CourseService {

	void saveCourse(Course course);

	List<Course> fetchAllCourses();

	List<String> fetchAllCategories();

	Course getCourseById(int courseId);;

	void updateCourse(int id, Course course);

	void deleteCourseById(int id);

	Course findById(int id);

	void updateCourse(Course existingCourse);

}
