package com.example.pizzaorderapi.resources;

import com.example.pizzaorderapi.domain.PizzaCrust;
import com.example.pizzaorderapi.domain.PizzaItems;
import com.example.pizzaorderapi.domain.PizzaSize;
import com.example.pizzaorderapi.services.PizzaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pizzas")


public class PizzaResources {

    @Autowired
    PizzaServices pizzaServices;

    @PostMapping("")
    public ResponseEntity<PizzaItems> addPizza(HttpServletRequest request, @RequestBody Map<String,Object> pizzaMap){
        String name=(String) pizzaMap.get("name");
        String description =(String) pizzaMap.get("description");
        Integer isVeg=(Integer) pizzaMap.get("isVeg");
        Integer defaultCrust=(Integer) pizzaMap.get("defaultCrust");

        System.out.println(name+" "+description+" "+isVeg+" "+defaultCrust);

        PizzaItems pizzaItems =pizzaServices.addPizza(name,isVeg,description,defaultCrust);
        return new ResponseEntity<>(pizzaItems, HttpStatus.CREATED);
    }

    @PostMapping("/crust")
    public ResponseEntity<PizzaCrust> addPizzaCrust(HttpServletRequest request, @RequestBody Map<String,Object> pizzaCrustMap){
        String name=(String) pizzaCrustMap.get("name");
        Integer defaultSize=(Integer) pizzaCrustMap.get("defaultSize");

        System.out.println(name+" "+defaultSize);

        PizzaCrust pizzaCrust =pizzaServices.addPizzaCrust(name,defaultSize);
        return new ResponseEntity<>(pizzaCrust, HttpStatus.CREATED);
    }

    @PostMapping("/size")
    public ResponseEntity<PizzaSize> addPizzaSize(HttpServletRequest request, @RequestBody Map<String,Object> pizzaSizeMap){
        String name=(String) pizzaSizeMap.get("name");
        Integer price=(Integer) pizzaSizeMap.get("price");
        Integer pizzaId=(Integer) pizzaSizeMap.get("pizzaId");
        Integer crustId=(Integer) pizzaSizeMap.get("crustId");

        System.out.println(name+" "+price);

        PizzaSize pizzaSize=pizzaServices.addPizzaBySize(name,price,pizzaId,crustId);
        return new ResponseEntity<>(pizzaSize, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<PizzaItems>> getAllPizzas(HttpServletRequest request){
        int userId=(Integer) request.getAttribute("userId");
        List<PizzaItems> list=pizzaServices.getAllPizzaItems(userId);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/{pizzaId}")
    public ResponseEntity<PizzaItems> getPizzaById(HttpServletRequest request,@PathVariable("pizzaId") Integer pizzaId){

        PizzaItems pizzaItems=pizzaServices.fetchPizzaById(pizzaId);

        return new ResponseEntity<>(pizzaItems,HttpStatus.OK);
    }
}
