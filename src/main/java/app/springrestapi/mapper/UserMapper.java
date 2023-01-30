package app.springrestapi.mapper;

import app.springrestapi.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findUserByUsername(String username);

    @Insert("INSERT INTO users (id, username, password, role) VALUES (#{id}, #{username}, #{password}, #{role})")
    User insertPost(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    User deleteUser(int id);
}
