package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.repository.OrderMenuQtyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderMenuQtyServiceImp implements OrderMenuQtyService {
    @Autowired
    private OrderMenuQtyRepository menuOrderQtyRepository;


    @Override
    public OrderMenuQty getMenuById(String id) {
        Optional<OrderMenuQty> menu = menuOrderQtyRepository.findById(id);
        return menu.get();
    }

    @Override
    public OrderMenuQty createMenu(OrderMenuQty menu) {
        Optional<OrderMenuQty> menuOptional = menuOrderQtyRepository.findById(menu.getId());
        if (menuOptional.isPresent()) {
            return null;
        } else {
            menuOrderQtyRepository.save(menu);
            return menu;
        }
    }

    @Override
    public OrderMenuQty updateMenu(String id, OrderMenuQty menuDetails) {
        Optional<OrderMenuQty> menuOptional = menuOrderQtyRepository.findById(id);
        if (menuOptional.isPresent()) {
            OrderMenuQty menu = menuOptional.get();
            menu.setQty(menuDetails.getQty());
            menuOrderQtyRepository.save(menu);
            return menu;
        } else {
            return null;
        }
    }
    @Override
    public boolean deleteMenu(String id) {
        Optional<OrderMenuQty> menuOptional = menuOrderQtyRepository.findById(id);
        if (menuOptional.isPresent()) {
            OrderMenuQty menu = menuOptional.get();
            menuOrderQtyRepository.delete(menu);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public List<OrderMenuQty> getAllMenus() {
        List<OrderMenuQty> menus = menuOrderQtyRepository.findAll();
        return menus;
    }
    @Override
    public long countMenus() {
        return menuOrderQtyRepository.count();
    }
}
