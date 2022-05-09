package com.example.pizzaorderapi.repository;

import com.example.pizzaorderapi.domain.PizzaCrust;
import com.example.pizzaorderapi.domain.PizzaItems;
import com.example.pizzaorderapi.domain.PizzaSize;
import com.example.pizzaorderapi.exceptions.EtBadRequestException;
import com.example.pizzaorderapi.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PizzaItemsRepoImpl implements PizzaItemsRepository {

    private static final String SQL_CREATE = "INSERT INTO P_ITEM (P_ID,NAME,IS_VEG,DESCRIPTION,DEFAULT_CRUST) VALUES (NEXTVAL('P_ITEM_SEQ'),?,?,?,?)";
    private static final String SQL_FIND_ALL_PIZZAS = "SELECT * FROM P_ITEM";
    private static final String SQL_CREATE_CRUST = "INSERT INTO P_CRUST (C_ID,NAME,DEFAULT_SIZE) VALUES (NEXTVAL('P_CRUST_SEQ'),?,?)";
    private static final String SQL_CREATE_SIZE = "INSERT INTO P_SIZE (S_ID,NAME,PRICE,CRUST_ID,PIZZA_ID) VALUES (NEXTVAL('P_SIZE_SEQ'),?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM P_ITEM WHERE P_ID=?";
    private static final String SQL_FIND_CRUST_BY_ID = "SELECT * FROM P_CRUST WHERE C_ID=?";
    private static final String SQL_FIND_SIZE_BY_ID = "SELECT S.S_ID, S.NAME,S.PRICE,S.CRUST_ID,S.PIZZA_ID FROM P_SIZE S,P_ITEM P,P_CRUST C WHERE S.S_ID=? AND S.CRUST_ID=? AND S.PIZZA_ID=? LIMIT 1";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<PizzaItems> findAllPizzas(Integer userId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_PIZZAS,pizzaRowMapper);
        }
        catch (Exception e){
            throw new EtResourceNotFoundException("Pizzas not found");
        }
    }

    @Override
    public Integer create(String name, Integer isVeg, String description, Integer defaultCrust) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setInt(2, isVeg);
                ps.setString(3, description);
                ps.setInt(4, defaultCrust);
                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("P_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Request "+e.getMessage());
        }
    }

    @Override
    public Integer createCrust(String name, Integer defaultSize) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_CRUST, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setInt(2, defaultSize);
                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("C_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Request "+e.getMessage());
        }
    }

    @Override
    public PizzaItems gePizzaItemById(Integer pizzaId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{pizzaId}, pizzaRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Pizza not found"+ e.getMessage());
        }
    }

    @Override
    public PizzaCrust gePizzaCrustById(Integer crustId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_CRUST_BY_ID, new Object[]{crustId}, crustRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Pizza not found");
        }
    }

    @Override
    public Integer createSize(String name, Integer price, Integer pizzaId, Integer crustId) {
        try {
            KeyHolder keyHolder=new GeneratedKeyHolder();
            jdbcTemplate.update(connection->{
                PreparedStatement ps=connection.prepareStatement(SQL_CREATE_SIZE,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,name);
                ps.setInt(2,price);
                ps.setInt(3,crustId);
                ps.setInt(4,pizzaId);
                return ps;
            },keyHolder);
           return (Integer) keyHolder.getKeys().get("S_ID");
        }
        catch (Exception e){
            throw new EtBadRequestException("Invalid request "+e.getMessage());
        }
    }

    @Override
    public PizzaSize getPizzaSizeById(Integer sizeId, Integer pizzaId, Integer crustId) {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_SIZE_BY_ID,new Object[]{sizeId,crustId,pizzaId},sizeRowMapper);
        }
        catch (Exception e){
            throw new EtResourceNotFoundException("Crust size not found "+e.getMessage());
        }
    }

    private RowMapper<PizzaItems> pizzaRowMapper = ((rs, rowNum) -> {
        return new PizzaItems(rs.getInt("P_ID"), rs.getString("NAME"), rs.getInt("IS_VEG"), rs.getString("DESCRIPTION"), rs.getInt("DEFAULT_CRUST"));
    });

    private RowMapper<PizzaCrust> crustRowMapper = ((rs, rowNum) -> {
        return new PizzaCrust(rs.getInt("C_ID"), rs.getString("NAME"), rs.getInt("DEFAULT_SIZE"));
    });

      private RowMapper<PizzaSize> sizeRowMapper = ((rs, rowNum) -> {
        return new PizzaSize(rs.getInt("S_ID"), rs.getString("NAME"), rs.getInt("PRICE"),rs.getInt("CRUST_ID"),rs.getInt("PIZZA_ID"));
    });




}
