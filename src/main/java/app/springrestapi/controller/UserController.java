package app.springrestapi.controller;

import app.springrestapi.annotation.Role;
import app.springrestapi.exception.IllegalDataException;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.pojo.Post;
import app.springrestapi.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserController {

    @Role("ADMIN")
    @GetMapping
    List<User> getAllPosts() throws NotFoundException;

    @Role("ADMIN")
    @PostMapping("/register")
    User createUser(@RequestBody User user) throws IllegalDataException;

    @Role("ADMIN")
    @DeleteMapping("/{id}")
    User deleteUser(@PathVariable int id) throws NotFoundException;
}
