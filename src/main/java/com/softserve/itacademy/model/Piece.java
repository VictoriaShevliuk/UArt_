package com.softserve.itacademy.model;

import javax.persistence.*;

@Entity
@Table(name = "pieces")
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;



    @Column(name = "image_url", nullable = true)
    private String imageUrl;


    @ManyToOne
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

   /* @ManyToOne
    @JoinColumn(name = "description_id")
    private Description description;*/

    public Piece() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

   /* public Type getType(){return type;}
    public void setType(Type type){this.type = type;}*/

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

   /* public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }*/

    @Override
    public String toString() {
        return "Piece {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", genre = " + genre +
                ", imageUrl = " + imageUrl +
                ", exhibition = " + exhibition +
               // ", description = " + description +
                "} ";
    }

}
