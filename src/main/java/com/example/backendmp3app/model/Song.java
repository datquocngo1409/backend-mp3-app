package com.example.backendmp3app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    private String description;

    @NotEmpty
    private String singer_name;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "mp3_id")
    private Mp3File mp3File;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private SongCategory songCategory;

    private Long listenCount;

    public Song() {
    }

    public Song(@NotEmpty String name, String description, @NotEmpty String singer_name, Mp3File mp3File, Image image, SongCategory songCategory, Long listenCount) {
        this.name = name;
        this.description = description;
        this.singer_name = singer_name;
        this.mp3File = mp3File;
        this.image = image;
        this.songCategory = songCategory;
        this.listenCount = listenCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Mp3File getMp3File() {
        return mp3File;
    }

    public void setMp3File(Mp3File mp3File) {
        this.mp3File = mp3File;
    }

    public SongCategory getSongCategory() {
        return songCategory;
    }

    public void setSongCategory(SongCategory songCategory) {
        this.songCategory = songCategory;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Long getListenCount() {
        return listenCount;
    }

    public void setListenCount(Long listenCount) {
        this.listenCount = listenCount;
    }
}
