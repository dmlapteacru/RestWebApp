package com.springapp.mvc.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Roles() {
    }

    public Roles(Role role) {
        this.role = role;
    }
}
