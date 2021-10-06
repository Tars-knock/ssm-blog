package cn.tarsknock.service;

import cn.tarsknock.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    public User getUserByUsername(@Param("username") String username);
    public Integer add(User user);
}
