package ru.top.java212.repository;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    UserRole findUserRoleByName(String name);
}
