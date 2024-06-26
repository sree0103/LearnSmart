package com.demo.learnsmart.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.learnsmart.entity.Course;
import com.demo.learnsmart.entity.Lesson;
import com.demo.learnsmart.repository.CourseRepo;
import com.demo.learnsmart.repository.LessonRepo;
import com.demo.learnsmart.service.CourseService;

@Service
public class CourseServiceimpl implements CourseService {

	@Autowired
	CourseRepo cour_repo;

	@Autowired
	LessonRepo les_repo;

	@Override
	public void saveCourse(Course course) {
		cour_repo.save(course);
	}

	@Override
	public List<Course> fetchAllCourses() {
		return cour_repo.findAll();
	}

	@Override
	public List<String> fetchAllCategories() {
		return cour_repo.findAll().stream().map(Course::getCategory).distinct().collect(Collectors.toList());
	}

	@Override
	public Course getCourseById(int courseId) {
		return cour_repo.findById(courseId).orElse(null);
	}

	@Override
	public Course findById(int id) {
		return cour_repo.findById(id).orElse(null);
	}

	@Override
	public void updateCourse(int id, Course course) {
		Course existingCourse = cour_repo.findById(id).orElse(null);
		if (existingCourse != null) {
			existingCourse.setName(course.getName());
			existingCourse.setCategory(course.getCategory());
			existingCourse.setDescription(course.getDescription());
			// Can update other fields
			cour_repo.save(existingCourse);
		}
	}

	@Override
	public void deleteCourseById(int id) {
		List<Lesson> lessons = les_repo.findByCourseId(id);
		les_repo.deleteAll(lessons);
		cour_repo.deleteById(id);
	}

	@Override
	public void updateCourse(Course existingCourse) {
		cour_repo.save(existingCourse);
	}
}
