package app.springrestapi.interceptor;

import app.springrestapi.annotation.Role;
import app.springrestapi.pojo.AuthCode;
import app.springrestapi.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;
    private AuthCode authCode;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handler1 = (HandlerMethod) handler;
        Role role = handler1.getMethod().getDeclaredAnnotation(Role.class);
        String strRole = role != null ? role.toString() : null;

        try {
            String basicAuthHeaderValue = request.getHeader("authorization");
            authCode = authService.validateBasicAuthentication(basicAuthHeaderValue, strRole);
            System.out.println(authCode);
        } catch (Exception e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        switch (authCode.value) {
            case 0 -> {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }
            case 1 -> {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }
            case 2 -> {
                return true;
            }
            default -> throw new IllegalStateException("Unexpected value: " + authCode.value);
        }
    }
}