package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumRole name;

    public Role() {

    }

    public Role(EnumRole name) {
        this.name = name;
    }
}