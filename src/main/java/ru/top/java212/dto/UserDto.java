package ru.top.java212.dto;


import ru.top.java212.entity.Role;
import ru.top.java212.entity.Profile;

public class UserDto {
    private Profile profile;
    private Role role;
    public UserDto(){}
    public UserDto(Profile profile, Role role) {
        this.profile = profile;
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
