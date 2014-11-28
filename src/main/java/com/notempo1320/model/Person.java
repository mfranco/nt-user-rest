package com.notempo1320.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column
    @NotNull @Length(min = 8)
    private String email;

    @Column
    @NotNull @Length(min = 8)
    private String username;

    @Column
    private String token;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column
    @NotNull
    private boolean active;

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getUsername() {
        return this.username;
    }

    @JsonProperty
    public String getEmail() {
        return this.email;
    }

    @JsonProperty
    public String getToken() {
        return this.token;
    }

    @JsonProperty
    public String getFirstName() {
        return this.firstName;
    }

    @JsonProperty
    public String getLastName() {
        return this.lastName;
    }

    @JsonProperty
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
