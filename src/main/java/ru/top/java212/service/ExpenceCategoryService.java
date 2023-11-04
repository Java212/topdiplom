package ru.top.java212.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.ExpenceCategoryDTO;
import ru.top.java212.model.ExpenceCategory;
import ru.top.java212.repository.ExpenceCategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ExpenceCategoryService {

    private final ExpenceCategoryRepository expenceCategoryRepository;

    public List<ExpenceCategoryDTO> getExpenceCategoryDtoList() {
        return StreamSupport.stream(expenceCategoryRepository.findAll().spliterator(), false).map(this::expenceCategoryToDto).collect(Collectors.toList());
    }

    public ExpenceCategoryDTO getDtoById(Integer id) {
        return expenceCategoryToDto(getById(id));
    }

    public ExpenceCategory getById(Integer id) {
        return expenceCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Income category with id = " + id + " is not exists"));
    }

    public void convertAndSave(ExpenceCategoryDTO dto) {
        expenceCategoryRepository.save(expenceCategoryToEntity(dto));
    }

    public void deleteById(Integer id) {
        expenceCategoryRepository.deleteById(id);
    }

    public ExpenceCategoryDTO expenceCategoryToDto(ExpenceCategory category) {
        return new ExpenceCategoryDTO(category.getId(), category.getName());
    }

    public ExpenceCategory expenceCategoryToEntity(ExpenceCategoryDTO dto) {
        return new ExpenceCategory(dto.getName());
    }
}
