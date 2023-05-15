package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

    @Query(value = "select id, title, created_at, owner_id from exhibitions where owner_id = ?1 union " +
            "select id, title, created_at, owner_id from exhibitions inner join exhibition_collaborator on id = exhibition_id and " +
            "collaborator_id = ?1", nativeQuery = true)
    List<Exhibition> getByUserId(long userId);

}
