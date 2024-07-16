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
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity get(int idPizza) {
        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity get(String name) {
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
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
