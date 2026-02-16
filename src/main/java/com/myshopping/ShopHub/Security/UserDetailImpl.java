package com.myshopping.ShopHub.Security;

import com.myshopping.ShopHub.Entity.AppUsers;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailImpl implements UserDetails {

    private AppUsers appUsers;

    public UserDetailImpl(AppUsers appUsers) {
        this.appUsers = appUsers;
    }

    public AppUsers getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(AppUsers appUsers) {
        this.appUsers = appUsers;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.appUsers.getRole().name()));
    }

    @Override
    public @Nullable String getPassword() {
        return this.appUsers.getPassword();
    }

    @Override
    public String getUsername() {
        return this.appUsers.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {  //accountExpiration
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  //account Locked
        return this.appUsers.getIs_active();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //check for active
    }

    @Override
    public boolean isEnabled() {  // check for verify
        return true;
    }
}
