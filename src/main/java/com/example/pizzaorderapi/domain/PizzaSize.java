package com.example.pizzaorderapi.domain;

public class PizzaSize {
    private Integer sizeId;
    private String name;
    private Integer price;
    private Integer pizzaId;
    private Integer crustId;

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Integer getCrustId() {
        return crustId;
    }

    public void setCrustId(Integer crustId) {
        this.crustId = crustId;
    }

    public PizzaSize(Integer sizeId, String name, Integer price, Integer crustId, Integer pizzaId) {
        this.sizeId = sizeId;
        this.name = name;
        this.price = price;
        this.pizzaId = pizzaId;
        this.crustId = crustId;
    }
}
