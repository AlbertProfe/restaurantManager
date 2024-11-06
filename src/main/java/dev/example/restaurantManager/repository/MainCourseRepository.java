package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MainCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCourseRepository extends JpaRepository<MainCourse, String> {
}
