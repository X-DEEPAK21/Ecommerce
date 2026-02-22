package com.myshopping.ShopHub;

import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.Entity.Role;
import com.myshopping.ShopHub.Repository.AppUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(TestCacheConfig.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShopHubApplicationTests {
    @Autowired  
    AppUserRepo appUserRepo;
    AppUsers appUsers;

    @BeforeEach
    void setBefore(){
      appUsers=AppUsers.builder().email("deepak44@gmail.com").password("deepak@123")
              .full_name("Deepak kumar ojha")
              .phone_number("7205157883")
              .role(Role.ROLE_USER)
              .is_active(true).build();
    }


	@Test
	void contextLoads() {
	}

    @Test
    void testGetUser_Passing_validEmail(){
        //arrange the db
     appUserRepo.save(appUsers);
     //check the actual impl
     Optional<AppUsers>user1=  appUserRepo.findByEmail("deepak44@gmail.com");
      //assert
        assertThat(user1).isNotEmpty();
        assertThat(user1.get().getEmail()).isEqualTo(appUsers.getEmail());


    }

}
