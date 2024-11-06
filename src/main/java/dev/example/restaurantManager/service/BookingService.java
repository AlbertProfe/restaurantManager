package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    Booking createBooking(Booking booking);
    Booking getBookingById(String id);
    Booking updateBooking(String id, Booking bookingDetails);
    boolean deleteBooking(String id);

}
