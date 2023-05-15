package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Piece;
import com.softserve.itacademy.repository.PieceRepository;
import com.softserve.itacademy.service.PieceService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PieceServiceImpl implements PieceService {
    private PieceRepository pieceRepository;

    public PieceServiceImpl(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    @Override
    public Piece create(Piece role) {
        if (role != null) {
            return pieceRepository.save(role);
        }
        throw new NullEntityReferenceException("Piece cannot be 'null'");
    }

    @Override
    public Piece readById(long id) {
        return pieceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Piece with id " + id + " not found"));
    }

    @Override
    public Piece update(Piece role) {
        if (role != null) {
            readById(role.getId());
            return pieceRepository.save(role);
        }
        throw new NullEntityReferenceException("Piece cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        pieceRepository.delete(readById(id));
    }

    @Override
    public List<Piece> getAll() {
        List<Piece> pieces = pieceRepository.findAll();
        return pieces.isEmpty() ? new ArrayList<>() : pieces;
    }

    @Override
    public List<Piece> getByExhibitionId(long exhibitionId) {
        List<Piece> pieces = pieceRepository.getByExhibitionId(exhibitionId);
        return pieces.isEmpty() ? new ArrayList<>() : pieces;
    }
}
