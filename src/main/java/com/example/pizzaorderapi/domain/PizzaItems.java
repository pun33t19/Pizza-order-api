package com.example.pizzaorderapi.domain;

public class PizzaItems {
    private Integer pizzaId;
    private String name;
    private Integer isVeg;
    private String description;
    private Integer defaultCrust;

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Integer isVeg) {
        this.isVeg = isVeg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDefaultCrust() {
        return defaultCrust;
    }

    public void setDefaultCrust(Integer defaultCrust) {
        this.defaultCrust = defaultCrust;
    }

    public PizzaItems(Integer pizzaId, String name, Integer isVeg, String description, Integer defaultCrust) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.isVeg = isVeg;
        this.description = description;
        this.defaultCrust = defaultCrust;
    }
}
