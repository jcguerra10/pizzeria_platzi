package com.platzi.pizzeria.service;

import com.platzi.pizzeria.persistence.entity.PizzaEntity;
import com.platzi.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll() {
        return pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAvailable() {
        System.out.println(pizzaRepository.countByVeganTrue());
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity get(int idPizza) {
        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity getByName(String name) {
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElse(null);
    }

    public List<PizzaEntity> getWith(String description) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getWithout(String description) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return pizzaRepository.save(pizzaEntity);
    }

    public boolean existsById(int idPizza) {
        return pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza) {
        pizzaRepository.deleteById(idPizza);
    }
}
