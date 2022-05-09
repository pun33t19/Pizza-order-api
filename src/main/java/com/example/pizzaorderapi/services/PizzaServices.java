package com.example.pizzaorderapi.services;

import com.example.pizzaorderapi.domain.PizzaCrust;
import com.example.pizzaorderapi.domain.PizzaItems;
import com.example.pizzaorderapi.domain.PizzaSize;
import com.example.pizzaorderapi.exceptions.EtBadRequestException;
import com.example.pizzaorderapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface PizzaServices {
    List<PizzaItems> getAllPizzaItems(Integer userId);
    List<PizzaCrust> getAllPizzaCrust(Integer userId);

    PizzaItems addPizza(String name,Integer isVeg,String description,Integer defaultCrust) throws EtBadRequestException;

    PizzaCrust addPizzaCrust(String name,Integer defaultSize) throws EtBadRequestException;

    PizzaItems fetchPizzaById(Integer pizzaId) throws EtResourceNotFoundException;

    PizzaSize addPizzaBySize(String name,Integer price,Integer pizzaId,Integer crustId);



}
