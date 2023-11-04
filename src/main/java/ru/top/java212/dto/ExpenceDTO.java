package ru.top.java212.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenceDTO {

    private Integer id;

    private String name;

    private Double summ;

    private Integer userId;

    private Integer categoryId;

    private LocalDate date;
}
