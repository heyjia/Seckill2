package com.heyjia.seckill.service;

import com.heyjia.seckill.dao.UserDao;
import com.heyjia.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getUserById(int id) {
        User user = userDao.getUserById(id);
        return user;
    }

    @Transactional
    public boolean tx(){
        User user1 = new User(2,"222");
        userDao.insert(user1);
        User user2 = new User(1,"111");
        userDao.insert(user2);
        return true;
    }

}
