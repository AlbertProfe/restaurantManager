package dev.example.restaurantManager.service;


import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.repository.OrderRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderSerciceImpl implements OrderService {
    @Autowired
    private OrderRestaurantRepository orderRepository;

    @Override
    public List<OrderRestaurant> getAllOrders(){
        List<OrderRestaurant> orders = orderRepository.findAll();
        return orders;
    }

    @Override
    public OrderRestaurant getOrderById(String id){
        Optional<OrderRestaurant> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public OrderRestaurant createOrder(OrderRestaurant order){
        Optional<OrderRestaurant> orderRestaurant = orderRepository.findById(order.getId());
        if(orderRestaurant.isEmpty()){
            return orderRepository.save(order);
        }else{
            return null;
        }
    }

    @Override
    public OrderRestaurant updateOrder(String id, OrderRestaurant orderDetails){
        OrderRestaurant order = orderRepository.findById(id).orElse(null);
        if(order != null){
            order.setId(orderDetails.getId());
            order.setDate(orderDetails.getDate());
            order.setWaiter(orderDetails.getWaiter());
            order.setPeopleQty(orderDetails.getPeopleQty());
            order.setTotalPayment(orderDetails.getTotalPayment());
            order.setPaid(orderDetails.isPaid());
            return orderRepository.save(order);
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteOrder(String id){
        Optional<OrderRestaurant> order = orderRepository.findById(id);
        if(order.isEmpty()){
            return false;
        }else{
            orderRepository.deleteById(id);
            return true;
        }
    }
    @Override
    public long countOrders(){
        return orderRepository.count();
    }
}
