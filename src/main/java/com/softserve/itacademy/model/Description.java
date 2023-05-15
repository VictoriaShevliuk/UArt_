package com.softserve.itacademy.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "descriptions")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The 'name' cannot be empty")
    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @OneToMany(mappedBy = "description")
    private List<Piece> pieces;

    public Description() {
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public List<Piece> getExhibition() {
        return pieces;
    }

    public void setExhibition(List<Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return "Description {" +
                "id = " + id +
                ", description = '" + description + '\'' +
                "} ";
    }
}
