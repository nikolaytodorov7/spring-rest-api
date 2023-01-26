package app.springrestapi.mapper;

import app.springrestapi.pojo.RegisteredUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    RegisteredUser findUserByUsername(String username);
}
