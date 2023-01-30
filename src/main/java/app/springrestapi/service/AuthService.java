package app.springrestapi.service;

public interface AuthService {
    boolean validateBasicAuthentication(String basicAuthHeaderValue, String role);
}