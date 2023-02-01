package app.springrestapi.controller.impl;

import app.springrestapi.controller.UserController;
import app.springrestapi.exception.IllegalDataException;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.mapper.UserMapper;
import app.springrestapi.pojo.Post;
import app.springrestapi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllPosts() throws NotFoundException {
        List<User> allUsers = userMapper.getAllUsers();
        if (allUsers == null || allUsers.size() == 0)
            throw new NotFoundException("Can't load all users! No users found!");

        return allUsers;
    }

    public User createUser(User user) throws IllegalDataException {
        if (user == null)
            throw new IllegalDataException("Invalid user provided!");

        return userMapper.insertUser(user);
    }

    public User deleteUser(int id) {
        return userMapper.deleteUser(id);
    }
}
