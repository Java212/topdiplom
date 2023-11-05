package ru.top.java212.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.IncomeCategoryDTO;
import ru.top.java212.model.IncomeCategory;
import ru.top.java212.repository.IncomeCategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class IncomeCategoryService {

    private final IncomeCategoryRepository incomeCategoryRepository;

    public List<IncomeCategoryDTO> getIncomeCategoryDtoList(){
        return StreamSupport.stream(incomeCategoryRepository.findAll().spliterator(), false).map(this::incomeCategoryToDto).collect(Collectors.toList());
    }

    public List<IncomeCategoryDTO> getUsedCategoryDtoList(){
        return incomeCategoryRepository.getUsedCategories().stream().map(this::incomeCategoryToDto).collect(Collectors.toList());
    }

    public IncomeCategoryDTO getDtoById(Integer id) {
        return incomeCategoryToDto(getById(id));
    }

    public IncomeCategory getById(Integer id) {
        return incomeCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Income category with id = " + id + " is not exists"));
    }

    public void convertAndSave(IncomeCategoryDTO dto) {
        incomeCategoryRepository.save(incomeCategoryToEntity(dto));
    }

    public void deleteById(Integer id) {
        incomeCategoryRepository.deleteById(id);
    }

    public IncomeCategoryDTO incomeCategoryToDto(IncomeCategory category){
        return new IncomeCategoryDTO(category.getId(), category.getName());
    }

    public IncomeCategory incomeCategoryToEntity(IncomeCategoryDTO dto){
        return new IncomeCategory(dto.getName());
    }
}
