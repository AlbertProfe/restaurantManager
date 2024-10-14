package dev.example.restaurantManager.service;
import java.util.List;
public interface MenuService {

    List<MenuService> getAllMenus();
    MenuService createMenu();
    MenuService getMenuByContent();
    MenuService updateStatusMenu(boolean active);
    boolean deleteMenu(String name);



}
