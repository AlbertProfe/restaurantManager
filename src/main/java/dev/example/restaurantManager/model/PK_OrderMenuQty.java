package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class PK_OrderMenuQty implements Serializable {
    private OrderRestaurant order;
    private MenuRestaurant menu;

    public PK_OrderMenuQty(OrderRestaurant order, MenuRestaurant menu){
        this.order=order;
        this.menu=menu;
    }

    public PK_OrderMenuQty(){}

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        PK_OrderMenuQty pk = (PK_OrderMenuQty) o;
        return order.getId().equals(pk.order.getId()) &&
                menu.getId().equals(pk.menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash( order, menu );
    }
}
