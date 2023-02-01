package app.springrestapi;

import app.springrestapi.mapper.PostMapper;
import app.springrestapi.mapper.UserMapper;
import app.springrestapi.pojo.Post;
import app.springrestapi.pojo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Base64;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserMapper userMapper;
    @MockBean
    PostMapper postMapper;

    @BeforeEach
    void setUp() {
        User admin = new User(1, "admin", "pass", "ADMIN");
        when(userMapper.findUserByUsername(admin.username)).thenReturn(admin);

        User user = new User(2, "user", "pass", "USER");
        when(userMapper.findUserByUsername(user.username)).thenReturn(user);

        when(userMapper.deleteUser(2)).thenReturn(user);
        when(userMapper.insertUser(new User(3, "ivan", "pass", "USER"))).thenReturn(new User());
        when(postMapper.getAllPosts()).thenReturn(List.of(new Post(), new Post()));
    }

    @Test
    @DisplayName("Login: Not authorized (no credentials)")
    void auth1() throws Exception {
        MockHttpServletRequestBuilder request = get("/posts");
        mockMvc.perform(request).andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Login: Authorized")
    void auth2() throws Exception {
        String credentials = "admin:pass";
        byte[] encodedBytes = credentials.getBytes();
        encodedBytes = Base64.getEncoder().encode(encodedBytes);
        String encodedCredentials = new String(encodedBytes);

        MockHttpServletRequestBuilder request = get("/posts")
                .header("Authorization", "Basic " + encodedCredentials);
        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Auth on method with ADMIN role as USER - Forbidden")
    void auth3() throws Exception {
        String credentials = "user:pass";
        byte[] encodedBytes = credentials.getBytes();
        encodedBytes = Base64.getEncoder().encode(encodedBytes);
        String encodedCredentials = new String(encodedBytes);

        MockHttpServletRequestBuilder request = delete("/users/2")
                .header("Authorization", "Basic " + encodedCredentials);
        mockMvc.perform(request).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Register user - no authentication")
    void auth4() throws Exception {
        String json = """
                {
                    "id": 3
                    "username": "ivan",
                    "password": "pass",
                    "role": "USER"
                }
                    """;

        MockHttpServletRequestBuilder request = post("/users/register").content(json).contentType(APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Register user - ok")
    void auth5() throws Exception {
        String json = """
                {
                    "username": "ivan",
                    "password": "pass",
                    "role": "USER"
                }
                    """;
        String credentials = "admin:pass";
        byte[] encodedBytes = credentials.getBytes();
        encodedBytes = Base64.getEncoder().encode(encodedBytes);
        String encodedCredentials = new String(encodedBytes);

        MockHttpServletRequestBuilder request = post("/users/register").content(json).contentType(APPLICATION_JSON)
                .header("Authorization", "Basic " + encodedCredentials);
        mockMvc.perform(request).andExpect(status().isOk());
    }
}
