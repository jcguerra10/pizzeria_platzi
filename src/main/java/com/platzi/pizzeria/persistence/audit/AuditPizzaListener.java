package com.platzi.pizzeria.persistence.audit;

import com.platzi.pizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity entity) {
        System.out.println("Pizza loaded: " + entity);
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity) {
        System.out.println("Post Persist or Update");
        System.out.println("Old value: " + this.currentValue);
        System.out.println("New value: " + entity);
    }

    @PreRemove
    public void onPreDelete(PizzaEntity entity) {
        System.out.println("Pizza deleted: " + entity);
    }
}
