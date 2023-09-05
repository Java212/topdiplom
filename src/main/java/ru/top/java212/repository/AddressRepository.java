package ru.top.java212.repository;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
