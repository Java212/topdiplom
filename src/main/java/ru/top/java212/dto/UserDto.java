package ru.top.java212.dto;


public class UserDto {
    private String userName;
    private String password;
    private String passwordRepeat;

    public UserDto(){}

    public UserDto(String userName, String password, String passwordRepeat) {
        this.userName = userName;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
