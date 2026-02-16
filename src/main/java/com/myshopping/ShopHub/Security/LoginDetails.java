package com.myshopping.ShopHub.Security;

import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.Exception.UserIsBlocked;
import com.myshopping.ShopHub.Repository.AppUserRepo;
import com.myshopping.ShopHub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginDetails implements UserDetailsService {
    @Autowired
   private AppUserRepo appUserRepo;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Boolean exist=appUserRepo.existsByEmail(username);
      if(exist==false)throw new UsernameNotFoundException("UserName Not found exception");
        AppUsers user=userService.getUser(username);
        if(user.getIs_active()==false)throw  new UserIsBlocked("user is blocked");

        return new UserDetailImpl(user);

    }
}
