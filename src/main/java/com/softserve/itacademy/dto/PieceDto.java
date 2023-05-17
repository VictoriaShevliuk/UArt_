package com.softserve.itacademy.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PieceDto {
    private long id;

    @NotBlank(message = "The 'name' cannot be empty")
    private String name;

    @NotNull
    private String genre;

    @NotNull
    private long exhibitionId;

    /*@NotNull
    private long typeId;*/

    public PieceDto() {
    }

    public PieceDto(long id, String name, String genre, long exhibitionId) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.exhibitionId = exhibitionId;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(long exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

   /* public long getTypeId() {
        return typeId;
    }*/

  /*  public void setTypeId(long typeId) {
        this.typeId = typeId;
    }*/
}
