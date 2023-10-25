package ru.top.java212.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record ExpenseDto(String categoryName,
                         @PositiveOrZero(message = "введите значение больше или равно 0") Integer amount) {
}