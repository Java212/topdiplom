package ru.top.java212.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.IncomeDTO;
import ru.top.java212.model.Income;
import ru.top.java212.repository.IncomeRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public List<IncomeDTO> getDtoList(){
        return StreamSupport.stream(incomeRepository.findAll().spliterator(), false).map(this::incomeToDto).collect(Collectors.toList());
    }

    public Integer getAllIncomesSumm(){
        return incomeRepository.getAllIncomesSumm();
    }

    public void convertAndSave(IncomeDTO dto) {
        incomeRepository.save(incomeToEntity(dto));
    }

    public void deleteById(Integer id) {
        incomeRepository.deleteById(id);
    }

    public IncomeDTO incomeToDto(Income income){
        return new IncomeDTO(income.getId(), income.getName(), income.getSumm(), income.getUserId(), income.getCategoryId(), income.getDate());
    }

    public Income incomeToEntity(IncomeDTO dto){
        return new Income(dto.getName(),dto.getSumm(),dto.getUserId(),dto.getCategoryId(),dto.getDate());
    }
}
