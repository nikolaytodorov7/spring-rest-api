package app.springrestapi.controller;

import app.springrestapi.exception.IllegalDataException;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.pojo.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserController {

    @PostMapping
    User createUser(@RequestBody User user) throws IllegalDataException;

    @DeleteMapping(value = "/{id}")
    User deleteUser(@PathVariable int id) throws NotFoundException;
}
