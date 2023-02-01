package app.springrestapi.service;

import app.springrestapi.pojo.AuthCode;

public interface AuthService {
    AuthCode validateBasicAuthentication(String basicAuthHeaderValue, String role);
}