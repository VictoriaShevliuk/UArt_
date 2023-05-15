package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Exhibition;

import java.util.List;

public interface ExhibitionService {
    Exhibition create(Exhibition exhibition);
    Exhibition readById(long id);
    Exhibition update(Exhibition exhibition);
    void delete(long id);

    List<Exhibition> getAll();
    List<Exhibition> getByUserId(long userId);
}
