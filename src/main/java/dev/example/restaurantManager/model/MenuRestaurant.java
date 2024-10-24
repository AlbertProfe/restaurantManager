package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MenuRestaurant  {

    @Id
    private String id;
    private String name;
    private Double price;
    private String content;
    private boolean active;
    private boolean water;

    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<OrderRestaurant> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "ITEMS_ON_MENUS",
            joinColumns = @JoinColumn(name = "MENU_ID", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID", referencedColumnName="id"))
    private List<MenuItem> items;

    public void addMenuItems(List<MenuItem> newItems){
        if(newItems== null){
            return;
        }
        if(this.items == null){
            this.items = new ArrayList<>();
        }
        for(MenuItem menuItem:newItems){
            if(!this.items.contains(menuItem)){
                menuItem.setMenus(new ArrayList<>(Arrays.asList(this)));
                this.items.add(menuItem);
            }
        }
    }

    public void removeMenuItems(List<MenuItem> newItems){
        if(this.items == null || newItems== null || newItems.size() == 0){
            return;
        }
        for(MenuItem menuItem:newItems){
            if(this.items.contains(menuItem)){
                this.items.remove(menuItem);
            }
        }
    }

    public MenuRestaurant(String id, String name, Double price, String content, boolean active, boolean water,List<OrderRestaurant> orders) {
        this(id,name,price,content,active,water,orders,null);
    }
    public MenuRestaurant(String id, String name, Double price, String content, boolean active, boolean water) {
        this(id,name,price,content,active,water,null,null);
    }

    //We  might want to exclude 'orders' from toString() to avoid circular references
    @Override
    public String toString() {
        return "MenuRestaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", content='" + content + '\'' +
                ", active=" + active +
                ", water=" + water +
                ", ordersCount=" + (orders != null ? orders.size() : 0) +
                ", orders=" + orders +
                '}';
    }

}

