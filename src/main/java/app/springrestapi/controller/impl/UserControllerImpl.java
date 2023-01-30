package app.springrestapi.controller.impl;

import app.springrestapi.annotation.Role;
import app.springrestapi.controller.UserController;
import app.springrestapi.exception.IllegalDataException;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.mapper.PostMapper;
import app.springrestapi.mapper.UserMapper;
import app.springrestapi.pojo.Post;
import app.springrestapi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserMapper userMapper;

    @Role("ADMIN")
    public User createUser(User user) throws IllegalDataException {
        if (user == null)
            throw new IllegalDataException("Invalid user provided!");

        return userMapper.insertPost(user);
    }

    @Role("ADMIN")
    public User deleteUser(int id) {
        return userMapper.deleteUser(id);
    }
}
