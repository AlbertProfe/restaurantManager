package dev.example.restaurantManager.repository;

import org.springframework.stereotype.Component;

@Component
public class RepositoryFacade {
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final OrderRepository orderRepository;
    private final TableRestaurantRepository tableRestaurantRepository;
    private final TakeAwayOrderRepository takeAwayOrderRepository;

    public RepositoryFacade(CustomerRepository customerRepository,
                            BookingRepository bookingRepository,
                            OrderRepository orderRepository,
                            TableRestaurantRepository tableRestaurantRepository,
                            TakeAwayOrderRepository takeAwayOrderRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.orderRepository = orderRepository;
        this.tableRestaurantRepository = tableRestaurantRepository;
        this.takeAwayOrderRepository = takeAwayOrderRepository;
    }

    public CustomerRepository customers() {
        return customerRepository;
    }

    public BookingRepository bookings() {
        return bookingRepository;
    }

    public OrderRepository orders() {
        return orderRepository;
    }

    public TableRestaurantRepository tables() {
        return tableRestaurantRepository;
    }

    public TakeAwayOrderRepository takeAwayOrders() {
        return takeAwayOrderRepository;
    }
}