package app.springrestapi.dao;

import app.springrestapi.mapper.UserMapper;
import app.springrestapi.pojo.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@Repository
public class UserDao {
    @Autowired
    UserMapper userMapper;

    public UserDetails findUserByUsername(String username) {
        RegisteredUser userByUsername = userMapper.findUserByUsername(username);
        return new User(userByUsername.username, userByUsername.password, Collections.singleton(new SimpleGrantedAuthority("User")));
    }
}