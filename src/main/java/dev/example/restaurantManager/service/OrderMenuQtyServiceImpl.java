package dev.example.restaurantManager.service;


import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.repository.OrderMenuQtyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMenuQtyServiceImpl implements OrderMenuQtyService {

    @Autowired
    OrderMenuQtyRepository orderMenuQtyRepository;

    //Retrieves all OrderMenuQty records
    @Override
    public List<OrderMenuQty> getAllOrderMenuQty(){
        return orderMenuQtyRepository.findAll();
    }

    //Creates a new OrderMenuQty entry
    @Override
    public OrderMenuQty createOrderMenuQty(OrderMenuQty orderMenuQty){
        return orderMenuQtyRepository.save(orderMenuQty);
    }

    //retrieves an OrderMenuQty by ID

    @Override
    public OrderMenuQty getOrderMenuQtyById(String id){
        return  orderMenuQtyRepository.findById(id).orElse(null);

    }
    //Updates an existing OrderMenuQty entry

    @Override
    public OrderMenuQty updateOrderMenuQty(String id, OrderMenuQty OrderMenuQtyDetails){
        OrderMenuQty orderMenuQty = orderMenuQtyRepository.findById(id).orElse(null);
        assert orderMenuQty != null;
        orderMenuQty.setQuantity(OrderMenuQtyDetails.getQuantity());
        orderMenuQty.setOrder(OrderMenuQtyDetails.getOrder());
        orderMenuQty.setMenu(OrderMenuQtyDetails.getMenu());

        return orderMenuQtyRepository.save(orderMenuQty);

    }

    //Deletes an OrderMenuQty entry by ID
    @Override
    public boolean deleteOrderMenuQty(String id) {
        if (!orderMenuQtyRepository.existsById(id)){
            throw new EntityNotFoundException("OrderMenuQty not found with id: " + id);

        }
        orderMenuQtyRepository.deleteById(id);
        return true;
    }

    //Counts all ORderMenuQty entries
    @Override
    public long countOrderMenuQty(){
        return orderMenuQtyRepository.count();
    }


}