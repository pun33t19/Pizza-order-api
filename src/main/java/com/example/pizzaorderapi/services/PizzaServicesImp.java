package com.example.pizzaorderapi.services;

import com.example.pizzaorderapi.domain.PizzaCrust;
import com.example.pizzaorderapi.domain.PizzaItems;
import com.example.pizzaorderapi.domain.PizzaSize;
import com.example.pizzaorderapi.exceptions.EtBadRequestException;
import com.example.pizzaorderapi.exceptions.EtResourceNotFoundException;
import com.example.pizzaorderapi.repository.PizzaItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PizzaServicesImp implements PizzaServices{

    @Autowired
    PizzaItemsRepository pizzaItemsRepository;

    @Override
    public List<PizzaItems> getAllPizzaItems(Integer userId) {
        return pizzaItemsRepository.findAllPizzas(userId);
    }

    @Override
    public List<PizzaCrust> getAllPizzaCrust(Integer userId) {
        return null;
    }

    @Override
    public PizzaItems addPizza(String name, Integer isVeg, String description, Integer defaultCrust) throws EtBadRequestException {
        Integer pizzaId=pizzaItemsRepository.create(name, isVeg, description, defaultCrust);
        return pizzaItemsRepository.gePizzaItemById(pizzaId);
    }

    @Override
    public PizzaCrust addPizzaCrust(String name, Integer defaultSize) throws EtBadRequestException {
        Integer crustId =pizzaItemsRepository.createCrust(name, defaultSize);
        return pizzaItemsRepository.gePizzaCrustById(crustId);
    }

    @Override
    public PizzaItems fetchPizzaById(Integer pizzaId) throws EtResourceNotFoundException {
        return pizzaItemsRepository.gePizzaItemById(pizzaId);
    }

    @Override
    public PizzaSize addPizzaBySize(String name, Integer price, Integer pizzaId, Integer crustId) {
        Integer sizeId=pizzaItemsRepository.createSize(name, price, pizzaId, crustId);
        return  pizzaItemsRepository.getPizzaSizeById(sizeId,pizzaId,crustId);
    }
}
