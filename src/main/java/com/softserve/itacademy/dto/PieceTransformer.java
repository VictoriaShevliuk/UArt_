package com.softserve.itacademy.dto;

import com.softserve.itacademy.model.Exhibition;
import com.softserve.itacademy.model.Genre;
import com.softserve.itacademy.model.Piece;
import com.softserve.itacademy.model.Description;

public class PieceTransformer {
    public static PieceDto convertToDto(Piece piece) {
        return new PieceDto(
                piece.getId(),
                piece.getName(),
                piece.getGenre().toString(),
                piece.getExhibition().getId(),
                piece.getDescription().getId()
        );
    }

    public static Piece convertToEntity(PieceDto pieceDto, Exhibition exhibition, Description description) {
        Piece piece = new Piece();
        piece.setId(pieceDto.getId());
        piece.setName(pieceDto.getName());
        piece.setGenre(Genre.valueOf(pieceDto.getGenre()));
        piece.setExhibition(exhibition);
        piece.setDescription(description);
        return piece;
    }
}
