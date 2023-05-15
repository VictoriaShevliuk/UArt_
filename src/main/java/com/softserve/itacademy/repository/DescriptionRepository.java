package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {

    Description findByDescription(String description);

    @Query(value = "select * from descriptions order by id", nativeQuery = true)
    List<Description> getAll();

}
