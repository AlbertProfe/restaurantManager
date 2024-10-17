package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrder;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.EatInOrderRepository;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import dev.example.restaurantManager.service.EatInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EatInOrderServiceImpl implements EatInOrderService {

    private final EatInOrderRepository eatInOrderRepository;
    private final TableRestaurantRepository tableRestaurantRepository;

    @Autowired
    public EatInOrderServiceImpl(EatInOrderRepository eatInOrderRepository, TableRestaurantRepository tableRestaurantRepository) {
        this.eatInOrderRepository = eatInOrderRepository;
        this.tableRestaurantRepository = tableRestaurantRepository;
    }

    @Override
    public List<EatInOrder> getAllOrders() {
        return eatInOrderRepository.findAll();
    }

    @Override
    public EatInOrder createOrder(EatInOrder eatInOrderRestaurant) {
        return eatInOrderRepository.save(eatInOrderRestaurant);
    }

    @Override
    public EatInOrder getOrderById(String id) {
        Optional<EatInOrder> optionalOrder = eatInOrderRepository.findById(id);
        return optionalOrder.orElse(null);  // return null or handle not found differently
    }

    @Override
    public EatInOrder updateOrder(String id, EatInOrder eatInOrderRestaurantDetails) {
        Optional<EatInOrder> optionalOrder = eatInOrderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            EatInOrder existingOrder = optionalOrder.get();

            existingOrder.setWaiter(eatInOrderRestaurantDetails.getWaiter());
            existingOrder.setDate(eatInOrderRestaurantDetails.getDate());
            existingOrder.setPeopleQty(eatInOrderRestaurantDetails.getPeopleQty());
            existingOrder.setPaid(eatInOrderRestaurantDetails.isPaid());
            existingOrder.setMenus(eatInOrderRestaurantDetails.getMenus());
            existingOrder.setTableRestaurants(eatInOrderRestaurantDetails.getTableRestaurants());

            return eatInOrderRepository.save(existingOrder);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteOrder(String id) {
        Optional<EatInOrder> optionalOrder = eatInOrderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            eatInOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long countOrders() {
        return eatInOrderRepository.count();
    }

    @Override
    public List<EatInOrder> getOrdersByTableId(String tableId) {
        return eatInOrderRepository.findByTableRestaurantId(tableId);
    }

    @Override
    public EatInOrder updateOrderTable(String orderId, String tableId) {
        Optional<EatInOrder> optionalOrder = eatInOrderRepository.findById(orderId);
        Optional<TableRestaurant> optionalTable = tableRestaurantRepository.findById(tableId);

        if (optionalOrder.isPresent() && optionalTable.isPresent()) {
            EatInOrder order = optionalOrder.get();
            TableRestaurant table = optionalTable.get();

            order.setTableRestaurant(table);
            return eatInOrderRepository.save(order);
        }

        return null;
    }
}
