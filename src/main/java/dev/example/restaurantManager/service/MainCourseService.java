package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem.MainCourse;

import java.util.List;

public interface MainCourseService {
    List<MainCourse> getAllMainCourses();
    MainCourse createMainCourse(MainCourse mainCourse);
    MainCourse getMainCourseById(String id);
    MainCourse updateMainCourse(String id, MainCourse mainCourseDetails);
    boolean deleteMainCourse(String id);
    long countMainCourses();
}
