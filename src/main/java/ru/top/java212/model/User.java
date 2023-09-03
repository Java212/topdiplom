package ru.top.java212.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer id;
    private String login;
    private String password;
    @Column(name="person_name")
    private String personName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id", nullable = false)
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Tool> tools;

//    @OneToMany (mappedBy = "user")
//    private List<Tool> toolList = new LinkedList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public  void setPassword(String password){
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    User(){
    }
    public User(int id){
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId(){
        return this.id;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonId(String personName) {
        this.personName = personName;
    }

    public Set<Tool> getTools() {
        return this.tools;
    }

    public void setToolList(Set<Tool> tools) {
        this.tools = tools;
    }
}
