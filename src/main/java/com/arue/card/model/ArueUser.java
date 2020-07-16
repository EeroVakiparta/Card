package com.arue.card.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "arueuser")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //
public class ArueUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long arueuser_id;

    @Column(name = "name")
    private String user_name;

    public ArueUser() {
    }

    public ArueUser(@JsonProperty("id")Long id, @JsonProperty("name")String name) {
        this.arueuser_id = id;
        this.user_name = name;
    }

    public Long getId() {
        return arueuser_id;
    }

    public void setId(Long id) {
        this.arueuser_id = id;
    }

    public String getName() {
        return user_name;
    }

    public void setName(String name) {
        this.user_name = name;
    }
}
