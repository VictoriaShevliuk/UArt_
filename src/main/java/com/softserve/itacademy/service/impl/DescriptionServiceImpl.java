package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Description;
import com.softserve.itacademy.repository.DescriptionRepository;
import com.softserve.itacademy.service.DescriptionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DescriptionServiceImpl implements DescriptionService {
    private DescriptionRepository descriptionRepository;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    @Override
    public Description create(Description role) {
        if (role != null) {
            return descriptionRepository.save(role);
        }
        throw new NullEntityReferenceException("Description cannot be 'null'");
    }

    @Override
    public Description readById(long id) {
        return descriptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Description with id " + id + " not found"));
    }

    @Override
    public Description update(Description role) {
        if (role != null) {
            readById(role.getId());
            return descriptionRepository.save(role);
        }
        throw new NullEntityReferenceException("Description cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        descriptionRepository.delete(readById(id));
    }

    @Override
    public Description getByName(String name) {
        Optional<Description> optional = Optional.ofNullable(descriptionRepository.findByDescription(name));
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Description with name '" + name + "' not found");
    }

    @Override
    public List<Description> getAll() {
        List<Description> descriptions = descriptionRepository.getAll();
        return descriptions.isEmpty() ? new ArrayList<>() : descriptions;
    }
}
