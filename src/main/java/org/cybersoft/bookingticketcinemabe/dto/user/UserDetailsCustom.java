package org.cybersoft.bookingticketcinemabe.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsCustom implements UserDetails {

    private UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userEntity.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority("ID_" + String.valueOf(userEntity.getId())));
            if (!userEntity.getRole().contains(",")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().toUpperCase()));
            } else {
                String[] roleList = userEntity.getRole().split(",");
                for (String roleString : roleList) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + roleString.trim().toUpperCase()));
                }
            }
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getEmail();
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
