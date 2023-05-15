package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Piece;

import java.util.List;

public interface PieceService {
    Piece create(Piece piece);
    Piece readById(long id);
    Piece update(Piece piece);
    void delete(long id);

    List<Piece> getAll();
    List<Piece> getByExhibitionId(long exhibitionId);
}
