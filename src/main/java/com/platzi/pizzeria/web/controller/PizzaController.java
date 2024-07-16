package com.platzi.pizzeria.web.controller;

import com.platzi.pizzeria.persistence.entity.PizzaEntity;
import com.platzi.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        return ResponseEntity.ok(pizzaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int id) {
        return ResponseEntity.ok(pizzaService.get(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable() {
        return ResponseEntity.ok(pizzaService.getAvailable());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(pizzaService.get(name));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizzaEntity) {
        if (pizzaEntity.getIdPizza() == null || !pizzaService.existsById(pizzaEntity.getIdPizza())) {
            return ResponseEntity.ok(pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity) {
        if (pizzaEntity.getIdPizza() != null && pizzaService.existsById(pizzaEntity.getIdPizza())) {
            return ResponseEntity.ok(pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (pizzaService.existsById(id)) {
            pizzaService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
