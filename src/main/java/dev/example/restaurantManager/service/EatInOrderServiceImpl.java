package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.EatInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EatInOrderServiceImpl implements EatInOrderService{

    @Autowired
    EatInOrderRepository eatInOrderRepository;

    @Override
    public List<EatInOrderRestaurant> getAllEatInOrderRestaurant() {
        return eatInOrderRepository.findAll();
    }

    @Override
    public EatInOrderRestaurant createEatInOrder(EatInOrderRestaurant eatInOrderRestaurant) {
        eatInOrderRestaurant.setId(UUID.randomUUID().toString());
        return eatInOrderRepository.save(eatInOrderRestaurant);
    }

    @Override
    public EatInOrderRestaurant getEatInOrderById(String id) {
        return eatInOrderRepository.findById(id).orElse(null);
    }

    @Override
    public EatInOrderRestaurant updateEatInOrder(String id, EatInOrderRestaurant eatInOrderDetails) {
        return null;
    }

    @Override
    public boolean deleteEatInOrder(String id) {
        eatInOrderRepository.deleteById(id);
        Optional<EatInOrderRestaurant> eatInOrderRestaurant = eatInOrderRepository.findById(id);
        return eatInOrderRestaurant.isEmpty()
                ?false
                :true;

    }

    @Override
    public long countEatInOrder() {
        return 0;
    }
}
