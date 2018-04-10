package com.springapp.mvc.model;


import lombok.*;


import javax.persistence.*;

import static com.springapp.mvc.model.Role.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private  int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = ROLE_USER;

    public User(String username, String password, Gender gender, Role role) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
