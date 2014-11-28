package com.notempo1320.model;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

/**
 * System Users, Only valid authenticated users have access to
 * resources
 */


@Entity
@Table(
    name = "users",
    uniqueConstraints =
        {@UniqueConstraint(columnNames={"username", "email", "token"})}
)


public class User extends BaseModel {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
