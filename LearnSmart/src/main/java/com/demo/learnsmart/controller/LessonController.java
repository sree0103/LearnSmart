package com.demo.learnsmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.learnsmart.entity.Course;
import com.demo.learnsmart.entity.Lesson;
import com.demo.learnsmart.service.CourseService;
import com.demo.learnsmart.service.LessonService;

@Controller
public class LessonController {

	@Autowired
	LessonService les_serv;

	@Autowired
	CourseService cou_serv;


	@GetMapping("/addlesson")
	public String createLessons(Model model) {
		List<Course> courses = cou_serv.fetchAllCourses();
		List<String> categories = cou_serv.fetchAllCategories();
		model.addAttribute("courses", courses);
		model.addAttribute("categories", categories);
		model.addAttribute("lesson", new Lesson());
		return "addlesson";
	}

	@PostMapping("/savelesson")
	public String addLesson(@ModelAttribute Lesson lesson) {
	    String lessonName = lesson.getName();

	    // Check if the course is not null
	    if (lesson.getCourse() != null) {
	        String courseName = lesson.getCourse().getName();

	        try {
	            String result = les_serv.addLesson(lesson, lesson.getCourse().getId());
	            System.out.println("Result from service: " + result);
	        } catch (Exception e) {
	            System.out.println("Error saving lesson: " + e.getMessage());
	        }
	    } else {
	        System.out.println("Error: Lesson's course is null");
	    }

	    return "adminhome";
	}


	@GetMapping("/viewlessons")
	public String viewlessons(Model model) {
		List<Lesson> lessons = les_serv.fetchAllLessons();
//		System.out.println(lessons);
		model.addAttribute("lessons", lessons);
		return "displaylessons";
	}
	
//	@GetMapping("/updatelesson/{courseId}/{lessonId}")
//    public String showUpdateLessonForm(@PathVariable int courseId, @PathVariable int lessonId, Model model) {
//        Lesson lesson = les_serv.getLessonById(lessonId);
//        model.addAttribute("lesson", lesson);
//        model.addAttribute("courseId", courseId);
//        return "updatelesson";
//    }
//
//    @PostMapping("/updatelesson/{courseId}/{lessonId}")
//    public String updateLesson(@PathVariable int courseId, @PathVariable int lessonId, @ModelAttribute Lesson updatedLesson) {
//        updatedLesson.setId(lessonId); // Ensure the ID is set
//        Lesson existingLesson = les_serv.getLessonById(lessonId);
//        if (existingLesson != null) {
//            les_serv.updateLesson(updatedLesson);
//        }
//        return "redirect:/viewlessons/" + courseId;
//    }
}

