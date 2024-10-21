package dev.example.restaurantManager.service;


import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.EatInOrderRepository;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EatInOrderServiceImpl implements EatInOrderService {

    @Autowired
    private EatInOrderRepository eatInOrderRepository;

    @Override
    public List<EatInOrderRestaurant> getAllEatInOrders() { return eatInOrderRepository.findAll(); }

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
    public EatInOrderRestaurant updateEatInOrder(String id, EatInOrderRestaurant eatInOrderRestaurantDetails) {
        EatInOrderRestaurant eatInOrderRestaurant = eatInOrderRepository.findById(id).orElse(null);
        if (eatInOrderRestaurant != null) {
            if (eatInOrderRestaurantDetails.getId() != null) {
                eatInOrderRestaurant.setId(eatInOrderRestaurantDetails.getId());
            }
            /*if (customerDetails.getEmail() != null) {
                customer.setEmail(customerDetails.getEmail());
            }
            if (customerDetails.getPhoneNumber() != null) {
                customer.setPhoneNumber(customerDetails.getPhoneNumber());
            }*/
            return eatInOrderRepository.save(eatInOrderRestaurant);
        }
        return null;
    }

    @Override
    public boolean deleteEatInOrder (String id) {
        eatInOrderRepository.deleteById(id);
        Optional<EatInOrderRestaurant> eatInOrderRestaurant = eatInOrderRepository.findById(id);
        return eatInOrderRestaurant.isEmpty()
                ? false : true ;
    }

    @Override
    public long countEatInOrders() {
        return eatInOrderRepository.count();
    }


}
