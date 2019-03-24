package com.example.backendmp3app.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

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

    @Email
    private String email;

    @NotEmpty
    private String address;

    private boolean isMale;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    public User() {
    }

    public User(@NotEmpty String username, @NotEmpty String password, @NotEmpty String name, String favouriteMusic, @Email String email, @NotEmpty String address, boolean isMale, Avatar avatar) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.favouriteMusic = favouriteMusic;
        this.email = email;
        this.address = address;
        this.isMale = isMale;
        this.avatar = avatar;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
