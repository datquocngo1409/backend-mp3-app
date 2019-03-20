package com.example.backendmp3app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    private String favouriteMusic;

    public Admin() {
    }

    public Admin(@NotEmpty String username, @NotEmpty String password, @NotEmpty String name, String favouriteMusic) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.favouriteMusic = favouriteMusic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavouriteMusic() {
        return favouriteMusic;
    }

    public void setFavouriteMusic(String favouriteMusic) {
        this.favouriteMusic = favouriteMusic;
    }
}
