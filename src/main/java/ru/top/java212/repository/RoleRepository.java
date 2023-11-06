package ru.top.java212.repository;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
