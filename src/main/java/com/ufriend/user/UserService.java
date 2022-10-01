package com.ufriend.user;

import com.ufriend.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> list() {
        return (ArrayList<UserEntity>) userDao.findAll();
    }

    @Override
    public UserEntity findById(Long userId) {
        return userDao.findById(userId).orElse(null);
    }

    @Override
    public void save(UserEntity role) {
        userDao.save(role);
    }

    @Override
    public void delete(UserEntity role) {
        userDao.delete(role);
    }
}
