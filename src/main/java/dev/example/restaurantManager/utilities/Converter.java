package dev.example.restaurantManager.utilities;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.model.OrderRestaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {
    public static List<OrderMenuQty> convertMenus2QtyMenus(OrderRestaurant order, List<MenuRestaurant> menus){
        HashMap<MenuRestaurant, Integer> hashmapMenus = new HashMap<>();
        for(MenuRestaurant m:menus){
            hashmapMenus.put(m,hashmapMenus.getOrDefault(m,0) + 1);
        }
        List<OrderMenuQty> menusNew = new ArrayList<>();
        for(Map.Entry<MenuRestaurant,Integer> m:hashmapMenus.entrySet()){
            OrderMenuQty omq = new OrderMenuQty();
            omq.setMenu(m.getKey());
            omq.setQuantity(m.getValue());
            omq.setOrder(order);
            menusNew.add(omq);
        }
        return menusNew;
    }

    public static List<MenuRestaurant> convertQtyMenus2Menus(List<OrderMenuQty> menus){
        List<MenuRestaurant> menusNew = new ArrayList<>();
        for(OrderMenuQty omq:menus){
            for(int i=0;i<omq.getQuantity();i++){
                menusNew.add(omq.getMenu());
            }
        }
        return menusNew;
    }
}
