package ru.top.java212.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.ExpenceDTO;
import ru.top.java212.model.Expence;
import ru.top.java212.repository.ExpenceRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ExpenceService {

    private final ExpenceRepository expenceRepository;

    public List<ExpenceDTO> getDtoList(){
        return StreamSupport.stream(expenceRepository.findAll().spliterator(), false).map(this::expenceToDto).collect(Collectors.toList());
    }

    public Double getAllExpencesSumm(){
        return expenceRepository.getAllExpencesSumm();
    }

    public Double getAllExpencesSummByUserId(Integer id){
        return expenceRepository.getAllExpencesSummByUserId(id);
    }

    public void convertAndSave(ExpenceDTO dto) {
        expenceRepository.save(expenceToEntity(dto));
    }

    public void deleteById(Integer id) {
        expenceRepository.deleteById(id);
    }

    public ExpenceDTO expenceToDto(Expence expence){
        return new ExpenceDTO(expence.getId(), expence.getName(), expence.getSumm(), expence.getUserId(), expence.getCategoryId(), expence.getDate());
    }

    public Expence expenceToEntity(ExpenceDTO dto){
        return new Expence(dto.getName(),dto.getSumm(),dto.getUserId(),dto.getCategoryId(),dto.getDate());
    }
}
