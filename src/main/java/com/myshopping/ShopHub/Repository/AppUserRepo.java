package com.myshopping.ShopHub.Repository;

import com.myshopping.ShopHub.Entity.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUsers,Long> {

}
