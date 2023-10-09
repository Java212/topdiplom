package ru.top.java212.dto;


import ru.top.java212.model.Role;
import ru.top.java212.model.UserInfo;

public class UserDto {
    private UserInfo userInfo;
    private Role role;


    public UserDto(){}

    public UserDto(UserInfo userInfo, Role role) {
        this.userInfo = userInfo;
        this.role = role;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
