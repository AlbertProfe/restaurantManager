package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MenuItem.MainCourse;
import dev.example.restaurantManager.service.MainCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maincourse")
public class MainCourseController {

    @Autowired
    private MainCourseService mainCourseService;

    @GetMapping
    public ResponseEntity<List<MainCourse>> getAllMainCourses() {
        List<MainCourse> mainCourses = mainCourseService.getAllMainCourses();
        return new ResponseEntity<>(mainCourses, HttpStatus.OK);
    }

    @GetMapping("/vegan")
    public ResponseEntity<List<MainCourse>> getAllVeganMainCourses() {
        List<MainCourse> mainCourses = mainCourseService.getAllVeganMainCourses();
        return new ResponseEntity<>(mainCourses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MainCourse> createMainCourse(@RequestBody MainCourse mainCourse) {
        MainCourse newMainCourse = mainCourseService.createMainCourse(mainCourse);
        return new ResponseEntity<>(newMainCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainCourse> getMainCourseById(@PathVariable String id) {
        MainCourse mainCourse = mainCourseService.getMainCourseById(id);
        if (mainCourse != null) {
            return new ResponseEntity<>(mainCourse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MainCourse> updateMainCourse(@PathVariable String id, @RequestBody MainCourse mainCourseDetails) {
        MainCourse updatedMainCourse = mainCourseService.updateMainCourse(id, mainCourseDetails);
        if (updatedMainCourse != null) {
            return new ResponseEntity<>(updatedMainCourse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMainCourse(@PathVariable String id) {
        boolean deleted = mainCourseService.deleteMainCourse(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countMainCourses() {
        long count = mainCourseService.countMainCourses();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}