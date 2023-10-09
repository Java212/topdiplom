package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.top.java212.model.User;
import ru.top.java212.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    UserInfo findByUser(User user);
}
