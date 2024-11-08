package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem.Dessert;
import dev.example.restaurantManager.repository.DessertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class DessertServiceImpl implements DessertService{

    @Autowired
    DessertRepository dessertRepository;

    @Override
    public List<Dessert> getAllDesserts() {
        return dessertRepository.findAll();
    }

    @Override
    public Dessert createDessert(Dessert dessert) {
        dessert.setId(UUID.randomUUID().toString());
        return dessertRepository.save(dessert);
    }

    @Override
    public Dessert getDessertById(String id) {
        return dessertRepository.findById(id).orElse(null);
    }

    @Override
    public Dessert updateDessert(String id, Dessert dessertDetails) {
        Dessert dessert = getDessertById(id);
        if(dessert==null){
            return null;
        }
        dessert.setName(dessertDetails.getName());
        dessert.setDescription(dessertDetails.getDescription());
        dessert.setPrice(dessertDetails.getPrice());
        dessert.setSugarFree(dessertDetails.isSugarFree());
        return dessertRepository.save(dessert);
    }

    @Override
    public boolean deleteDessert(String id) {
        if(!dessertRepository.existsById(id)) {
            return false;
        }
        dessertRepository.deleteById(id);
        return true;
    }

    @Override
    public long countDesserts() {
        return dessertRepository.count();
    }
}
