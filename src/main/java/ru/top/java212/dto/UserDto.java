package ru.top.java212.dto;

import java.math.BigDecimal;

public record UserDto(String name, String login, String password, BigDecimal startCapital) {
}
