package cn.tarsknock.service.impl;

import cn.tarsknock.dao.UserDao;
import cn.tarsknock.entity.User;
import cn.tarsknock.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsername(@Param("username") String username) {
        User user = userDao.getUserByUsername(username);
        System.out.println(user);
        return user;
    }

    @Override
    public Integer add(User user) {
        return userDao.add(user);
    }
}
