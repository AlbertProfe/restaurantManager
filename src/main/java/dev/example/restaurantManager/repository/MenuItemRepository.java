package dev.example.restaurantManager.repository;


import dev.example.restaurantManager.model.MainCourse;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.SideCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

    public interface MenuItemRepository extends JpaRepository<MenuItem, String> {


        @Query("SELECT m FROM MainCourse m")
        List<MainCourse> findAllMainCourses();

        @Query("SELECT s FROM SideCourse s")
        List<SideCourse> findAllSideCourses();

    }


