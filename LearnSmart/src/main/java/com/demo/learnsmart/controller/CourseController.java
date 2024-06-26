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
public class CourseController {

	 
    @Autowired
    CourseService cou_serv;
    
    @Autowired
    LessonService les_serv;
    
    @GetMapping("/createcourse")
    public String createCourses(Model model) {
        List<Lesson> lessons = les_serv.fetchAllLessons();
        model.addAttribute("lessons", lessons);
        model.addAttribute("course", new Course());
        return "addcourse";
    }
    
    @PostMapping("/savecourse")
    public String saveCourse(@ModelAttribute Course course, Model model) {
        cou_serv.saveCourse(course);
        return "adminhome";
    }
     
    @GetMapping("/viewcourses")
    public String viewCourses(Model model) {
        List<Course> courses = cou_serv.fetchAllCourses();
        model.addAttribute("courses", courses);
        return "viewcourses";
    }
    
    @GetMapping("/viewlessons/{courseId}")
    public String viewLessons(@PathVariable int courseId, Model model) {
        Course course = cou_serv.getCourseById(courseId);
        if (course == null) {
        return "errorpage";
        }
        List<Lesson> lessons = les_serv.fetchLessonsByCourse(course);
        model.addAttribute("course", course);
        model.addAttribute("lessons", lessons);
        return "viewlessons";
    }
    
    @GetMapping("/displaycourses")
    public String displayCourses(Model model) {
        boolean premium = true;
        if (premium) {
            List<Course> courses = cou_serv.fetchAllCourses();
            model.addAttribute("courses", courses);
            return "learnerviewcourses";
        } else {
            return "subscriptionform";
        }
    }
    
    @GetMapping("/displaycourses/{courseId}")
    public String displayLessons(@PathVariable int courseId, Model model) {
        Course course = cou_serv.getCourseById(courseId);
        List<Lesson> lessons = les_serv.fetchLessonsByCourse(course);
        model.addAttribute("course", course);
        model.addAttribute("lessons", lessons);
        return "learnerviewlessons";
    }
    
    @GetMapping("/updatecourse/{id}")
    public String showUpdateCourseForm(@PathVariable int id, Model model) {
        Course course = cou_serv.findById(id);
        model.addAttribute("course", course);
        return "updatecourse"; //html page
    }

    @PostMapping("/updatecourse/{id}")
    public String updateCourse(@PathVariable int id, @ModelAttribute Course updatedCourse) {
    	Course existingCourse = cou_serv.getCourseById(id);
        if (existingCourse != null) {
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setCategory(updatedCourse.getCategory());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setThumbnail(updatedCourse.getThumbnail());
            existingCourse.setStatus(updatedCourse.getStatus());
            
            cou_serv.updateCourse(existingCourse);
        }
        return "redirect:/viewcourses"; 
    }
    
    @GetMapping("/deletecourse/{id}")
    public String deleteCourse(@PathVariable int id) {
        cou_serv.deleteCourseById(id);
        return "redirect:/viewcourses";
    }
    @GetMapping("/deletelesson/{courseId}/{lessonId}")
    public String deleteLesson(@PathVariable int courseId, @PathVariable int lessonId) {
    	les_serv.deleteLessonById(lessonId);
    	return "redirect:/viewlessons/" + courseId; // Redirect to view lessons after deletion
    }
    
    
    
    
    
    
    
    
//    @GetMapping("/updatelesson/{courseId}/{lessonId}")
//    public String showUpdateLessonForm(@PathVariable int courseId, @PathVariable int lessonId, Model model) {
//        Lesson lesson = les_serv.getLessonById(lessonId);
//        model.addAttribute("lesson", lesson);
//        model.addAttribute("courseId", courseId);
//        return "updatelesson"; // Name of the Thymeleaf template for updating a lesson
//    }
//
//    @PostMapping("/updatelesson/{courseId}/{lessonId}")
//    public String updateLesson(@PathVariable int courseId, @PathVariable int lessonId, @ModelAttribute Lesson updatedLesson) {
//        Lesson existingLesson = les_serv.getLessonById(lessonId);
//        if (existingLesson != null) {
//            existingLesson.setName(updatedLesson.getName());
//            existingLesson.setContent(updatedLesson.getContent());
//            existingLesson.setVideoUrl(updatedLesson.getVideoUrl());
//            existingLesson.setCourse(updatedLesson.getCourse());
//       
//            
//            les_serv.updateLesson(existingLesson);
//        }
//        return "redirect:/viewlessons/" + courseId; 
//    }

//    @PostMapping("/updatelesson/{courseId}/{lessonId}")
//  public String updateLesson(@PathVariable int courseId, @PathVariable int lessonId, @ModelAttribute Lesson updatedLesson) {
//      updatedLesson.setId(lessonId);
//      Lesson existingLesson = les_serv.getLessonById(lessonId);
//      if (existingLesson != null) {
//          les_serv.updateLesson(updatedLesson);
//      }
//      return "redirect:/viewlessons/" + courseId;
//  }
}
