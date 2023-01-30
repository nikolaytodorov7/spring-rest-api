package app.springrestapi.interceptor;

import app.springrestapi.annotation.Role;
import app.springrestapi.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;
    private boolean isValidReq;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handler1 = (HandlerMethod) handler;
        String role = handler1.getMethod().getDeclaredAnnotation(Role.class).value();

        try {
            String basicAuthHeaderValue = request.getHeader("authorization");
            isValidReq = authService.validateBasicAuthentication(basicAuthHeaderValue, role);
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        if (isValidReq)
            return true;

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}