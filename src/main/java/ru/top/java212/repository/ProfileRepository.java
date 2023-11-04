package ru.top.java212.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.top.java212.entity.User;
import ru.top.java212.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile findByUser(User user);
    void deleteById(Integer id);
}
