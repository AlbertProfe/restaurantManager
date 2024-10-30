package dev.example.restaurantManager.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import dev.example.restaurantManager.utilities.Converter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_RESTAURANT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderRestaurant {

    @Id
    private String id;
    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ORDER_MENU_QUANTITY",
            joinColumns = @JoinColumn(name = "ORDER_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "MENU_ID_FK")
    )
    private List<OrderMenuQty> menus;

    public OrderRestaurant(String id, Date date, String waiter, int peopleQty,
                                   double totalPayment, boolean paid, ArrayList<MenuRestaurant> menus){
        this.id=id;
        this.date=date;
        this.waiter=waiter;
        this.peopleQty=peopleQty;
        this.totalPayment=totalPayment;
        this.paid=paid;
        this.menus = Converter.convertMenus2QtyMenus(this,menus);
    }


    public List<OrderMenuQty> addMenuQty(MenuRestaurant menu, int qty) {
        if(this.getMenus()==null){
            this.setMenus(new ArrayList<OrderMenuQty>());
        }
        boolean found = false;
        for(OrderMenuQty omq: this.getMenus()){
            if(omq.getMenu().equals(menu)){
                omq.setQuantity(omq.getQuantity()+qty);
                found = true;
                break;
            }
        }
        if(!found){
            OrderMenuQty omq = new OrderMenuQty();
            omq.setMenu(menu);
            omq.setQuantity(qty);
            omq.setOrder(this);
            this.getMenus().add(omq);
        }
        return this.getMenus();
    }

    public List<OrderMenuQty> addMenu(MenuRestaurant menu) {
        if(this.getMenus()==null){
            this.setMenus(new ArrayList<OrderMenuQty>());
        }
        boolean found = false;
        for(OrderMenuQty omq: this.getMenus()){
            if(omq.getMenu().equals(menu)){
                omq.setQuantity(omq.getQuantity()+1);
                found = true;
                break;
            }
        }
        if(!found){
            OrderMenuQty omq = new OrderMenuQty();
            omq.setMenu(menu);
            omq.setQuantity(1);
            omq.setOrder(this);
            this.getMenus().add(omq);
        }
        return this.getMenus();
    }

    public List<OrderMenuQty> removeMenuQty(MenuRestaurant menu, int qty) {
        if(this.getMenus()==null){
            return null;
        }
        for(OrderMenuQty omq: this.getMenus()){
            if(omq.getMenu().equals(menu)){
                int oldQty = omq.getQuantity();
                if (oldQty > qty) {
                    omq.setQuantity(omq.getQuantity() - qty);
                } else{
                    this.getMenus().remove(omq);
                }
                break;
            }
        }
        return this.getMenus();
    }

    public List<OrderMenuQty> removeMenu(MenuRestaurant menu) {
        if(this.getMenus()==null){
            return null;
        }
        for(OrderMenuQty omq: this.getMenus()){
            if(omq.getMenu().equals(menu)){
                int qty = omq.getQuantity();
                if (qty > 1) {
                    omq.setQuantity(omq.getQuantity() - 1);
                } else{
                    this.getMenus().remove(omq);
                }
                break;
            }
        }
        return this.getMenus();
    }



//    public List<MenuRestaurant> addMenu(MenuRestaurant menu) {
//            this.menus.add(menu);
//            menu.getOrders().add(this);
//        return this.menus;
//    }
//
//    public List<MenuRestaurant> removeMenu(MenuRestaurant menu) {
//            this.menus.remove(menu);
//            menu.getOrders().remove(this);
//        return this.menus;
//    }

    @Override
    public String toString() {
        return "OrderRestaurant{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", waiter='" + waiter + '\'' +
                ", peopleQty=" + peopleQty +
                ", totalPayment=" + totalPayment +
                ", paid=" + paid +
                ", menusCount=" + (menus != null ? menus.size() : 0) +
                ", menus=" + menus +
                '}';
    }

}