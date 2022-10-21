package com.ufriend.user;

import java.util.List;

public interface IUserService {
    public List<UserEntity> list();
    public UserEntity findById(Long role);
    public UserEntity findByEmail(String email);
    public void save(UserEntity role);
    public void delete(UserEntity role);
}
