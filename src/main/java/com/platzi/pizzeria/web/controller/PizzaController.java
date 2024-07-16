package com.platzi.pizzeria.web.controller;

import com.platzi.pizzeria.persistence.entity.PizzaEntity;
import com.platzi.pizzeria.service.PizzaService;
import com.platzi.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8")int elements) {
        return ResponseEntity.ok(pizzaService.getAll(page, elements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int id) {
        return ResponseEntity.ok(pizzaService.get(id));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingrediente}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingrediente) {
        return ResponseEntity.ok(pizzaService.getWith(ingrediente));
    }

    @GetMapping("/without/{ingrediente}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingrediente) {
        return ResponseEntity.ok(pizzaService.getWithout(ingrediente));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price) {
        return ResponseEntity.ok(pizzaService.getCheapest(price));
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

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto) {
        if (pizzaService.existsById(dto.getPizzaId())) {
            pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
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
