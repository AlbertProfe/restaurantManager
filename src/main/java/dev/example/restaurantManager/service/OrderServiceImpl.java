package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.repository.OrderRestaurantRepository;
import dev.example.restaurantManager.utilities.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.example.restaurantManager.utilities.Converter.convertMenus2QtyMenus;

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
//        for (MenuRestaurant m : menus) {
//            order.addMenu(m);
//        }
        List<OrderMenuQty> menusQt = Converter.convertMenus2QtyMenus(order,menus);
        for(OrderMenuQty omq:menusQt){
            order.addMenuQty(omq.getMenu(),omq.getQuantity());
        }
        return orderRepository.save(order);
    }

    @Override
    public OrderRestaurant deleteMenus(String idOrder, List<MenuRestaurant> menus){
        // OrderRestaurant order = orderRepository.findById(idOrder).orElse(null);
        OrderRestaurant order = orderRepository.getReferenceById(idOrder);
        if( order==null ){
            return  null;
        }
//        for (MenuRestaurant m : menus) {
//            order.removeMenu(m);
//        }
        List<OrderMenuQty> menusQt = Converter.convertMenus2QtyMenus(order,menus);
        for(OrderMenuQty omq:menusQt){
            order.removeMenuQty(omq.getMenu(),omq.getQuantity());
        }
        return orderRepository.save(order);
    }
}
