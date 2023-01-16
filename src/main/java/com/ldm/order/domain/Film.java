package com.ldm.order.domain;

import javax.persistence.*;

@Entity
@Table(name="t_film", uniqueConstraints = @UniqueConstraint(name="un_filmId", columnNames = {"id"}))
public class Film {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;

    public Film(){

    }

    public Film(int id, String name) {
        this.id = id;
        this.name = name;
    }


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
