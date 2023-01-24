package app.springrestapi;

import app.springrestapi.pojo.Comment;
import app.springrestapi.pojo.Post;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes({Post.class, Comment.class})
@MapperScan("app.springrestapi.mapper")
@SpringBootApplication
public class SpringRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApiApplication.class, args);
    }
}
