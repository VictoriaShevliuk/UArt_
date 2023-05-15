package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Exhibition;
import com.softserve.itacademy.repository.ExhibitionRepository;
import com.softserve.itacademy.service.ExhibitionService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {

    private ExhibitionRepository exhibitionRepository;

    public ExhibitionServiceImpl(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    @Override
    public Exhibition create(Exhibition role) {
        if (role != null) {
            return exhibitionRepository.save(role);
        }
        throw new NullEntityReferenceException("Exhibition cannot be 'null'");
    }

    @Override
    @PostAuthorize("returnObject.owner.email == authentication.name")
    public Exhibition readById(long id) {
        return exhibitionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Exhibition with id " + id + " not found"));
    }

    @Override
    public Exhibition update(Exhibition role) {
        if (role != null) {
            readById(role.getId());
            return exhibitionRepository.save(role);
        }
        throw new NullEntityReferenceException("Exhibition cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        exhibitionRepository.delete(readById(id));
    }

    @Override
    public List<Exhibition> getAll() {
        List<Exhibition> exhibitions = exhibitionRepository.findAll();
        return exhibitions.isEmpty() ? new ArrayList<>() : exhibitions;
    }

    @Override
    //@PostFilter("hasRole('ROLE_ADMIN') or filterObject.owner.getEmail() == authentication.name or filterObject.getCollaborators().contains(authentication.principal)")
    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.getOwner().getEmail() == authentication.name")
    public List<Exhibition> getByUserId(long userId) {
        List<Exhibition> exhibitions = exhibitionRepository.getByUserId(userId);
        return exhibitions.isEmpty() ? new ArrayList<>() : exhibitions;
    }
}
