package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.repository.EatInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EatInServiceImpl implements EatInService {

    @Autowired
    private EatInRepository eatInRepository;

    @Override
    public List<EatInOrderRestaurant> getAllEatInOrders(){
        return eatInRepository.findAll();
    }
    @Override
    public EatInOrderRestaurant createEatInOrders(EatInOrderRestaurant EatInOrderRestaurant){
        EatInOrderRestaurant.setId(UUID.randomUUID().toString());
        return eatInRepository.save(EatInOrderRestaurant);
    }

    @Override
    public EatInOrderRestaurant getEatInOrdersById(String id){

        return eatInRepository.findById(id).orElse(null);

    }
    @Override
    public EatInOrderRestaurant updateEatInOrders(String id, EatInOrderRestaurant eatInOrderDetails) {
        Optional<EatInOrderRestaurant> optionalOrder = eatInRepository.findById(id);
        if (optionalOrder.isPresent()) {
            EatInOrderRestaurant existingOrder = optionalOrder.get();

            // update
            existingOrder.setPeopleQty(eatInOrderDetails.getPeopleQty());
            existingOrder.setTotalPayment(eatInOrderDetails.getTotalPayment());
            existingOrder.setPaid(eatInOrderDetails.isPaid());


            return eatInRepository.save(existingOrder);
        }
        return null; // si no hay orden

    }
    @Override
    public boolean deleteEatInOrders(String id){
        eatInRepository.deleteById(id);
        Optional<EatInOrderRestaurant> eatInOrderRestaurant = eatInRepository.findById(id);
        return eatInOrderRestaurant.isEmpty()
                ? false
                : true;
    }
    @Override
    public long countEatInOrders(){
        return eatInRepository.count();
    }


}