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

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.EAGER
//            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "ORDER_MENU_QUANTITY",
//            joinColumns = @JoinColumn(name = "ORDER_ID_FK"),
//            inverseJoinColumns = @JoinColumn(name = "MENU_ID_FK")
//    )
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")  // Sin esta anotación se crea la tabla order_restaurant_menus_qty !!!
    private List<OrderMenuQty> menusQty;

    public OrderRestaurant(String id, Date date, String waiter, int peopleQty,
                                   double totalPayment, boolean paid, ArrayList<MenuRestaurant> menus){
        this.id=id;
        this.date=date;
        this.waiter=waiter;
        this.peopleQty=peopleQty;
        this.totalPayment=totalPayment;
        this.paid=paid;
        this.menusQty = Converter.convertMenus2QtyMenus(this,menus);
    }


    public List<OrderMenuQty> addMenuQty(MenuRestaurant menu, int qty) {
        if(this.getMenusQty()==null){
            this.setMenusQty(new ArrayList<OrderMenuQty>());
        }
        boolean found = false;
        for(OrderMenuQty omq: this.getMenusQty()){
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
            this.getMenusQty().add(omq);
        }
        return this.getMenusQty();
    }

    public List<OrderMenuQty> addMenu(MenuRestaurant menu) {
        if(this.getMenusQty()==null){
            this.setMenusQty(new ArrayList<OrderMenuQty>());
        }
        boolean found = false;
        for(OrderMenuQty omq: this.getMenusQty()){
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
            this.getMenusQty().add(omq);
        }
        return this.getMenusQty();
    }

    public List<OrderMenuQty> removeMenuQty(MenuRestaurant menu, int qty) {
        if(this.getMenusQty()==null){
            return null;
        }
        for(OrderMenuQty omq: this.getMenusQty()){
            if(omq.getMenu().equals(menu)){
                int oldQty = omq.getQuantity();
                if (oldQty > qty) {
                    omq.setQuantity(omq.getQuantity() - qty);
                } else{
                    this.getMenusQty().remove(omq);
                }
                break;
            }
        }
        return this.getMenusQty();
    }

    public List<OrderMenuQty> removeMenu(MenuRestaurant menu) {
        if(this.getMenusQty()==null){
            return null;
        }
        for(OrderMenuQty omq: this.getMenusQty()){
            if(omq.getMenu().equals(menu)){
                int qty = omq.getQuantity();
                if (qty > 1) {
                    omq.setQuantity(omq.getQuantity() - 1);
                } else{
                    this.getMenusQty().remove(omq);
                }
                break;
            }
        }
        return this.getMenusQty();
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
                ", menusQtyCount=" + (menusQty != null ? menusQty.size() : 0) +
                ", menusQty=" + menusQty +
                '}';
    }

}