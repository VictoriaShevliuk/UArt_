package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long> {

    @Query(value = "select * from pieces where exhibition_id = ?1", nativeQuery = true)
    List<Piece> getByExhibitionId(long exhibitionId);

}
