package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.repository.OrderRestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRestaurantRepository orderRepository;

    @Override
    public List<OrderRestaurant> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderRestaurant createOrder(OrderRestaurant order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderRestaurant getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public OrderRestaurant updateOrder(String id, OrderRestaurant orderDetails) {
        // OrderRestaurant order = getOrderById(id);
        OrderRestaurant order = orderRepository.getReferenceById(id);

        return orderRepository.save(orderDetails);
    }

    @Override
    public boolean deleteOrder(String id) {
        boolean find = getOrderById(id) != null;
        orderRepository.deleteById(id);
        return find;
    }

    @Override
    public long countOrders() {
        return orderRepository.count();
    }

    @Override
    public OrderRestaurant addMenus(String idOrder, List<MenuRestaurant> menus){
        // OrderRestaurant order = orderRepository.findById(idOrder).orElse(null);
        OrderRestaurant order = orderRepository.getReferenceById(idOrder);
        if( order==null ){
            return  null;
        }
        for (MenuRestaurant m : menus) {
            order.addMenu(m);
        }
        return orderRepository.save(order);
    }
}
