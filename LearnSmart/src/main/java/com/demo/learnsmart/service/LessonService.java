package com.demo.learnsmart.service;

import java.util.List;

import com.demo.learnsmart.entity.Course;
import com.demo.learnsmart.entity.Lesson;

public interface LessonService {

	List<Lesson> fetchAllLessons();

	boolean lessonExists(String name);

	boolean lessonExistsInCourse(String lessonName, String courseName);

	List<Lesson> fetchLessonsByCourse(Course course);

	void updateLesson(Lesson lesson);

	Lesson getLessonById(int id);

	void deleteLessonById(int id);

	String addLesson(Lesson lesson, int courseId);

}
