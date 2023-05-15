package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Description;

import java.util.List;

public interface DescriptionService {
    Description create(Description description);
    Description readById(long id);
    Description update(Description description);
    void delete(long id);

    Description getByName(String description);
    List<Description> getAll();
}
