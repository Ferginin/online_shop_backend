package ru.nikolaev.eshop.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.nikolaev.eshop.model.User;

import java.util.Collection;

@Data
public class UserDetailsIMpl implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String email;

    public UserDetailsIMpl(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static UserDetailsIMpl build(User user) {
        return new UserDetailsIMpl(
                user.getUser_id(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
