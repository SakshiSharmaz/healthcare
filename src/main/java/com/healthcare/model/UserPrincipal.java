package com.healthcare.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

/*
 * Spring Security will use the information stored in the UserPrincipal object to perform
 * authentication and authorization.
 */

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails {


    private UUID uuid;

    private String firstName;

    private String lastName;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String getUsername() {
        return lastName + ", " + firstName;
    }

    public UserPrincipal(UUID uuid, String firstName, String lastName, String password) {

        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public static UserPrincipal create(User user) {

        return new UserPrincipal(user.getUuid(), user.getFirstName(), user.getLastName(),
                user.getPassword());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(uuid, that.uuid);
    }

}
