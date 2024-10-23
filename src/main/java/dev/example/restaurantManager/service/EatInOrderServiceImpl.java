package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.repository.EatInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EatInOrderServiceImpl implements EatInOrderService{

    @Autowired
    private EatInOrderRepository eatInOrderRepository;

    @Override
    public List<EatInOrderRestaurant> getAllEatInOrders() {
        return eatInOrderRepository.findAll();
    }

    @Override
    public EatInOrderRestaurant createEatInOrder(EatInOrderRestaurant eatInOrder) {
        return eatInOrderRepository.save(eatInOrder);
    }

    @Override
    public EatInOrderRestaurant getEatInOrderById(String id) {
        return eatInOrderRepository.findById(id).orElse(null);
    }

    @Override
    public EatInOrderRestaurant updateEatInOrder(String id, EatInOrderRestaurant eatInOrderDetails) {
        EatInOrderRestaurant eatInOrder = getEatInOrderById(id);
        if(eatInOrder== null){
            return null;
        }
        return eatInOrderRepository.save(eatInOrderDetails);
    }

    @Override
    public boolean deleteEatInOrder(String id) {
        EatInOrderRestaurant eatInOrder = getEatInOrderById(id);
        eatInOrderRepository.deleteById(id);
        return eatInOrder != null;
    }

    @Override
    public long countEatInOrders() {
        return eatInOrderRepository.count();
    }
}
