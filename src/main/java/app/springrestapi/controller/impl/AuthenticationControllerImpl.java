package app.springrestapi.controller.impl;

import app.springrestapi.controller.AuthenticationController;
import app.springrestapi.security.JwtUtils;
import app.springrestapi.dao.UserDao;
import app.springrestapi.pojo.RegisteredUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private UserDao userDao;
    private JwtUtils jwtUtils;

    public ResponseEntity<String> authenticate(RegisteredUser requestUser) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestUser.username, requestUser.password));
        UserDetails user = userDao.findUserByUsername(requestUser.username);
        if (user != null)
            return ResponseEntity.ok(jwtUtils.generateToken(user));

        return ResponseEntity.status(400).body("Error while authenticating!");
    }
}
