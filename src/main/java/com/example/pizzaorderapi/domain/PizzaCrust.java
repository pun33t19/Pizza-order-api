package com.example.pizzaorderapi.domain;

public class PizzaCrust {
    private Integer crustId;
    private String crustName;
    private Integer defaultSize;

    public PizzaCrust(Integer crustId, String crustName, Integer defaultSize) {
        this.crustId = crustId;
        this.crustName = crustName;
        this.defaultSize = defaultSize;
    }

    public Integer getCrustId() {
        return crustId;
    }

    public void setCrustId(Integer crustId) {
        this.crustId = crustId;
    }

    public String getCrustName() {
        return crustName;
    }

    public void setCrustName(String crustName) {
        this.crustName = crustName;
    }

    public Integer getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(Integer defaultSize) {
        this.defaultSize = defaultSize;
    }
}
