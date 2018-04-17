package com.springapp.mvc.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.springapp.mvc.model.Role.ROLE_USER;
import static javax.persistence.EnumType.STRING;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NaturalId
    @Column(name = " username", nullable = false, unique = true)
    @Size(min = 2)
    @NotEmpty
    private String username;

    @Column(name = "password")
    @Pattern(regexp = "(?=.*\\d)(?=.*[A-Z])(?=.*[!$^&*()_|={}\\[\\]:;'?.\\/]).{8,}")
    @NotEmpty
    private String password;

    @Transient
    private String confirmPassword;

    @NotEmpty
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$")
    @NotEmpty
    private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "gender")
    @Enumerated(value = STRING)
    private Gender gender;

    @ManyToMany
    private Set<Roles> roles = new HashSet<>();

    public User() {
    }

    public User(@Size(min = 2) @NotEmpty String username,
                @Pattern(regexp = "(?=.*\\d)(?=.*[A-Z])(?=.*[!$^&*()_|={}\\[\\]:;'?.\\/]).{8,}")
                @NotEmpty String password,
                @NotEmpty String firstName,
                @NotEmpty String lastName,
                @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$") @NotEmpty String email,
                Gender gender) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public User(@Size(min = 2) @NotEmpty String username,
                @Pattern(regexp = "(?=.*\\d)(?=.*[A-Z])(?=.*[!$^&*()_|={}\\[\\]:;'?.\\/]).{8,}") @NotEmpty String password,
                @NotEmpty String firstName,
                @NotEmpty String lastName,
                @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$") @NotEmpty String email,
                Date dateOfBirth,
                Gender gender) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
