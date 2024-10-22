package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MenuRestaurant;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuEntityManagerRepository {

    @Autowired
    private EntityManager entityManager;

    /*
    @Autowired
    public MenuEntityManagerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }*/

    @Transactional
    public List<MenuRestaurant> findMenusByPriceRange(double minPrice, double maxPrice) {
        String jpql = "SELECT * FROM menu WHERE u.Price BETWEEN :minPrice AND :maxPrice";
        return entityManager.createQuery(jpql, MenuRestaurant.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

}
