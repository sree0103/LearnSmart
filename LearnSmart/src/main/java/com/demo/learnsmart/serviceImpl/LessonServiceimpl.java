package com.demo.learnsmart.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.learnsmart.entity.Course;
import com.demo.learnsmart.entity.Lesson;
import com.demo.learnsmart.repository.CourseRepo;
import com.demo.learnsmart.repository.LessonRepo;
import com.demo.learnsmart.service.LessonService;

@Service
public class LessonServiceimpl implements LessonService {

	@Autowired
	LessonRepo les_repo;

	@Autowired
	CourseRepo cou_repo;

	@Override
	public List<Lesson> fetchAllLessons() {
		List<Lesson> lessons = les_repo.findAll();
		lessons.forEach(lesson -> {
			if (lesson.getCourse() == null) {
				lesson.setCourse(new Course());
			}
		});
		return lessons;
	}

	@Override
	public boolean lessonExists(String name) {
		return les_repo.findByName(name).isPresent();
	}

	@Override
	public String addLesson(Lesson lesson, int courseId) {
		Optional<Course> courseOptional = cou_repo.findById(courseId);
		if (courseOptional.isPresent()) {
			Course course = courseOptional.get();
			lesson.setCourse(course);
			les_repo.save(lesson);
			return "Lesson added successfully";
		} else {
			return "Course with ID " + courseId + " not found";
		}
	}

	@Override
	public boolean lessonExistsInCourse(String lessonName, String courseName) {
		Course course = cou_repo.findByName(courseName);
		if (course != null) {
			List<Lesson> lessons = les_repo.findByCourse(course);
			for (Lesson lesson : lessons) {
				if (lesson.getName().equals(lessonName)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<Lesson> fetchLessonsByCourse(Course course) {

		return les_repo.findByCourse(course);
	}

	@Override
	public void updateLesson(Lesson updatedLesson) {
		Optional<Lesson> existingLessonOpt = les_repo.findById(updatedLesson.getId());
		if (existingLessonOpt.isPresent()) {
			Lesson existingLesson = existingLessonOpt.get();
			existingLesson.setName(updatedLesson.getName());
			existingLesson.setContent(updatedLesson.getContent());
			existingLesson.setVideoUrl(updatedLesson.getVideoUrl());
			existingLesson.setCourse(updatedLesson.getCourse());
			les_repo.save(existingLesson);
		} else {
			throw new IllegalArgumentException("Lesson with ID " + updatedLesson.getId() + " not found");
		}
	}

	@Override
	public Lesson getLessonById(int id) {
		return les_repo.findById(id).orElse(null);
	}

	@Override
	public void deleteLessonById(int id) {
		les_repo.deleteById(id);
	}

}
