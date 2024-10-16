package dev.example.restaurantManager.service;
import dev.example.restaurantManager.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MenuService {

    List<Menu> getAllMenus();
    Menu createMenu(Menu menu);
    Menu getMenuById(String id);
    Menu updateStatusMenu(String id, boolean active, Menu menuDetails);
    boolean deleteMenu(String id);
    long countMenus();



}
