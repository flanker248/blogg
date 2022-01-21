package com.cbyk.blogg;

import com.cbyk.blogg.model.User;
import com.cbyk.blogg.repo.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BootstrapDataPopulator implements InitializingBean {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void afterPropertiesSet() throws Exception {

//        bootstrapUserCBYK();

    }

    private void bootstrapUserCBYK() {
        User user = new User();
        user.username = "cbyk";
        user.password = passwordEncoder.encode("1703") ;
        user.roles = Arrays.asList("Admin", "User");
        userRepository.save(user);
    }
}