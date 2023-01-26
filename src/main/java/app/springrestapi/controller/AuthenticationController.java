package app.springrestapi.controller;

import app.springrestapi.pojo.RegisteredUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface AuthenticationController {
    @PostMapping("/login")
    ResponseEntity<String> authenticate(@RequestBody RegisteredUser requestUser);
}
