package com.softserve.itacademy.dto;

import com.softserve.itacademy.model.Exhibition;
import com.softserve.itacademy.model.Genre;
import com.softserve.itacademy.model.Piece;

public class PieceTransformer {
    public static PieceDto convertToDto(Piece piece) {
        return new PieceDto(
                piece.getId(),
                piece.getName(),
                piece.getGenre().toString(),
                piece.getImageUrl(),
                piece.getExhibition().getId()
        );
    }

    public static Piece convertToEntity(PieceDto pieceDto, Exhibition exhibition) {
        Piece piece = new Piece();
        piece.setId(pieceDto.getId());
        piece.setName(pieceDto.getName());
        piece.setGenre(Genre.valueOf(pieceDto.getGenre()));
        piece.setImageUrl(pieceDto.getImageUrl());
        piece.setExhibition(exhibition);
        return piece;
    }
}
