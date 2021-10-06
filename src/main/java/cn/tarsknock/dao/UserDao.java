package cn.tarsknock.dao;

import cn.tarsknock.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    public User getUserByUsername(@Param("username") String username);
    public User findById(Integer id);
    public Integer add(User user);
}
