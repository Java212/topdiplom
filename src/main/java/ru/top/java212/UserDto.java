package ru.top.java212;

import java.math.BigDecimal;

public record UserDto(String name, String login, String password, BigDecimal startCapital) {
}
