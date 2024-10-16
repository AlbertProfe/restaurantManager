package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Menu createMenu(Menu menu) {
        menu.setId(UUID.randomUUID().toString());
        return menuRepository.save(menu);
    }

    @Override
    public Menu getMenuById(String id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public Menu updateStatusMenu(String id, boolean active, Menu menuDetails) {
        Menu menu = menuRepository.findById(id).orElse(null);
        if (menu != null){
            if (menuDetails.getName() != null) {
                menu.setName(menuDetails.getName());
            }
            if (!menuDetails.isActive()) {
                menu.setActive(menuDetails.isActive());
            }
            if (menuDetails.getPrice() != null) {
                menu.setPrice(menuDetails.getPrice());
            }
            return menuRepository.save(menu);
        }
        return null;
    }

    @Override
    public boolean deleteMenu(String id) {
        menuRepository.deleteById(id);
        Optional<Menu> menu = menuRepository.findById(id);

        return menu.isPresent();
    }
    @Override
    public long countMenus() {
        return menuRepository.count();
    }

}
