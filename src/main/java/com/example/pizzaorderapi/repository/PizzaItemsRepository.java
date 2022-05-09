package com.example.pizzaorderapi.repository;

import com.example.pizzaorderapi.domain.PizzaCrust;
import com.example.pizzaorderapi.domain.PizzaItems;
import com.example.pizzaorderapi.domain.PizzaSize;
import com.example.pizzaorderapi.exceptions.EtBadRequestException;
import com.example.pizzaorderapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface PizzaItemsRepository {
    List<PizzaItems> findAllPizzas(Integer userId) throws EtResourceNotFoundException;

    Integer create(String name,Integer isVeg,String description,Integer defaultCrust) throws EtBadRequestException;

    Integer createCrust(String name,Integer defaultSize) throws EtBadRequestException;

    PizzaItems gePizzaItemById(Integer pizzaId) throws EtResourceNotFoundException;

    PizzaCrust gePizzaCrustById(Integer crustId) throws EtResourceNotFoundException;

    Integer createSize(String name,Integer price,Integer pizzaId,Integer crustId);

    PizzaSize getPizzaSizeById(Integer sizeId,Integer pizzaId,Integer crustId);
}
