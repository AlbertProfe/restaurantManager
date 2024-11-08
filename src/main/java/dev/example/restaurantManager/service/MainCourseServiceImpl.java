package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem.MainCourse;
import dev.example.restaurantManager.repository.MainCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MainCourseServiceImpl implements MainCourseService{

    @Autowired
    MainCourseRepository mainCourseRepository;

    @Override
    public List<MainCourse> getAllMainCourses() {
        return mainCourseRepository.findAll();
    }

    @Override
    public MainCourse createMainCourse(MainCourse mainCourse) {
        mainCourse.setId(UUID.randomUUID().toString());
        return mainCourseRepository.save(mainCourse);
    }

    @Override
    public MainCourse getMainCourseById(String id) {
        return mainCourseRepository.findById(id).orElse(null);
    }

    @Override
    public MainCourse updateMainCourse(String id, MainCourse mainCourseDetails) {
        MainCourse mainCourse = getMainCourseById(id);
        if(mainCourse==null){
            return null;
        }
        mainCourse.setName(mainCourseDetails.getName());
        mainCourse.setDescription(mainCourseDetails.getDescription());
        mainCourse.setPrice(mainCourseDetails.getPrice());
        mainCourse.setVegan(mainCourseDetails.isVegan());
        return mainCourseRepository.save(mainCourse);
    }

    @Override
    public boolean deleteMainCourse(String id) {
        if(!mainCourseRepository.existsById(id)) {
            return false;
        }
        mainCourseRepository.deleteById(id);
        return true;
    }

    @Override
    public long countMainCourses() {
        return mainCourseRepository.count();
    }
}
