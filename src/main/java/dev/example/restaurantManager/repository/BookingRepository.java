package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
