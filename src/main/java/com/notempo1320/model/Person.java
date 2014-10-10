package com.notempo1320.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(
    name = "persons",
    uniqueConstraints =
        {@UniqueConstraint(columnNames={"username", "email"})}
)

public class Person extends BaseModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long    id;

    @Column(name = "email")
    @NotNull @Length(min = 8)
    private String email;

    @Column(name = "username")
    @NotNull @Length(min = 8)
    private String username;

    @Column(name = "token")
    @NotNull
    private String token;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_active")
    @NotNull
    private boolean active;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getToken() {
        return this.token;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
