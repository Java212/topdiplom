package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
