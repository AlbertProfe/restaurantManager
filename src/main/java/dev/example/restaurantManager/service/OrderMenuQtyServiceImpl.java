package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.repository.OrderMenuQtyRepository;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderMenuQtyServiceImpl implements OrderMenuQtyService {

    @Autowired
    OrderMenuQtyRepository orderMenuQtyRepository;

    @Override
    public List<OrderMenuQty> getAllOrderMenuQts() {
        return orderMenuQtyRepository.findAll();
    }
    public List<OrderMenuQty> getAllByOrderId(String orderId) {
        return orderMenuQtyRepository.findAllByOrderId(orderId);
    }

    @Override
    public OrderMenuQty createOrderMenuQty(OrderMenuQty orderMenuQty) {
        return orderMenuQtyRepository.save(orderMenuQty);
    }

    @Override
    public OrderMenuQty getOrderMenuQtyById(String id) {
        return orderMenuQtyRepository.findById(id).orElse(null);
    }

    @Override
    public OrderMenuQty updateOrderMenuQty(String id, OrderMenuQty orderMenuQtyDetails) {
        OrderMenuQty orderMenuQty = orderMenuQtyRepository.findById(id).orElse(null);
        if (orderMenuQty != null) {
            if (orderMenuQtyDetails.getQty() != 0) {
                orderMenuQty.setQty(orderMenuQtyDetails.getQty());
            }
            return orderMenuQtyRepository.save(orderMenuQty);
        }
        return null;
    }

    @Override
    public boolean deleteOrderMenuQty(String id) {
        orderMenuQtyRepository.deleteById(id);
        Optional<OrderMenuQty> orderMenuQty = orderMenuQtyRepository.findById(id);
        return orderMenuQty.isEmpty() ? false : true;

    }

    @Override
    public long countOrderMenuQts() {
        return orderMenuQtyRepository.count();
    }
}
