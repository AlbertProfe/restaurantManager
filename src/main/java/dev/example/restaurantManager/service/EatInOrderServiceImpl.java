package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.repository.EatInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EatInOrderServiceImpl implements EatInOrderService {

    @Autowired
    EatInOrderRepository eatInOrderRepository;
    @Override
    public List<EatInOrderRestaurant> getAllEatInOrders() {
        return eatInOrderRepository.findAll();
    }

    @Override
    public EatInOrderRestaurant getEatInOrderById(String id) {
        return eatInOrderRepository.findById(id).orElse(null);
    }


    @Override
    public EatInOrderRestaurant createEatInOrder(EatInOrderRestaurant eatInOrder) {
        Optional<EatInOrderRestaurant> eatInOrderOptional = eatInOrderRepository.findById(eatInOrder.getId());
        if (eatInOrderOptional.isPresent()) {
            return null;
        } else {
            return eatInOrderRepository.save(eatInOrder);
        }
    }


    @Override
    public EatInOrderRestaurant updateEatInOrder(String id, EatInOrderRestaurant eatInOrderDetails) {
        EatInOrderRestaurant eatInOrder = eatInOrderRepository.findById(id).orElse(null);
        if (eatInOrder != null) {
            eatInOrder.setId(eatInOrderDetails.getId());
            eatInOrder.setDate(eatInOrderDetails.getDate());
            eatInOrder.setWaiter(eatInOrderDetails.getWaiter());
            eatInOrder.setPeopleQty(eatInOrderDetails.getPeopleQty());
            eatInOrder.setTotalPayment(eatInOrderDetails.getTotalPayment());
            eatInOrder.setPaid(eatInOrderDetails.isPaid());
            return eatInOrderRepository.save(eatInOrder);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteEatInOrder(String id) {
        Optional<EatInOrderRestaurant> eatInOrder = eatInOrderRepository.findById(id);
        if (eatInOrder.isEmpty()) {
            return false;
        } else {
            eatInOrderRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public long countEatInOrders() {
        return eatInOrderRepository.count();
    }


}
