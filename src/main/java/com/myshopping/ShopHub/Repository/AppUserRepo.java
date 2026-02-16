package com.myshopping.ShopHub.Repository;

import com.myshopping.ShopHub.Entity.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUsers,Long> {

    Optional<AppUsers> findByEmail(String email);
    boolean existsByEmail(String email);
}
