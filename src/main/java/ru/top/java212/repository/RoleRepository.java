package ru.top.java212.repository;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Address;
import ru.top.java212.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
