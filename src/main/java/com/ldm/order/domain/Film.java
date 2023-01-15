package com.ldm.order.domain;

public class Film {

    public Film(int id, String name) {
        this.id = id;
        this.name = name;
    }
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
